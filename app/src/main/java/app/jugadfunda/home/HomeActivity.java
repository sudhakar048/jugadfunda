package app.jugadfunda.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import com.hbb20.BuildConfig;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.businessactivity.BusinessActivityFragment;
import app.jugadfunda.businessactivity.BusinessActivityPojo;
import app.jugadfunda.businessactivity.CanvasTemplateActivity;
import app.jugadfunda.home.postquery.PostQueryFragment;
import app.jugadfunda.home.sessions.sessionlist.SessionFragment;
import app.jugadfunda.inquiryform.IndustryInquiryFragment;
import app.jugadfunda.eventmessages.EventMessagesFragment;
import app.jugadfunda.home.captureidea.CaptureFragment;
import app.jugadfunda.localstorage.JfStorage;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.quizlist.QuizFragment;
import app.jugadfunda.settings.NotificationSettingsActivity;
import app.jugadfunda.track.TrackFragment;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        SharedPreferences sh = getSharedPreferences("profile", MODE_PRIVATE);
        String mType = sh.getString("mt","");
        setTabs(mType);

        if (shouldAskPermissions()) {

            askPermissions();
        }
    }

    private void init() {
        findViewById(R.id.iv_setting).setOnClickListener(view -> {
            openSetting(view);
        });

        findViewById(R.id.iv_share).setOnClickListener(view -> shareApplication());

        setBusinessCanvasData();
      }

    private void setTabs(String mType) {
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_image))
                        .setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorAccent));
                addFragments(tab.getPosition(),mType);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_image))
                        .setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //get module for localDB
        setCustomView(getString(R.string.event_messages), R.drawable.ic_event);
        setCustomView(getString(R.string.radio), R.drawable.ic_internetradio);
        setCustomView(getString(R.string.quiz), R.drawable.ic_quiz);
        setCustomView(getString(R.string.business_model), R.drawable.ic_businessmodel);
        setCustomView(getString(R.string.endustry_enquiry), R.drawable.ic_enquiry);
        setCustomView(getString(R.string.capture_idea), R.drawable.ic_captureidea);

        if(mType.equals("mentor")){
            setCustomView(getString(R.string.scheduled_sessions), R.drawable.ic_session);
        }else if(mType.equals("ums")){
            setCustomView(getString(R.string.post_query), R.drawable.ic_query);
        }

        addFragments(0, mType);
    }

    private void setCustomView(String title, Integer image) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab_items, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        ((ImageView) view.findViewById(R.id.iv_image)).setImageResource(image);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }


    private void addFragments(int position,String mType) {
        Fragment fragment = null;
        if (position == 6 && mType.equals("mentor")) {
            fragment = new SessionFragment();
        }else if(position == 6 && mType.equals("ums")){
            fragment = new PostQueryFragment();
        }else if (position == 0) {
            fragment = new EventMessagesFragment();
        }else if (position == 1) {
            fragment = new TrackFragment();
        }else if (position == 2) {
            fragment = new QuizFragment();
        }else if (position == 3) {
            fragment = new BusinessActivityFragment();
        }else if (position == 4) {
            fragment = new IndustryInquiryFragment();
        }else if (position == 5){
                fragment = new CaptureFragment();
        }
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container_home, fragment).commit();
    }

    private void openSetting(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

            popupMenu.getMenuInflater().inflate(R.menu.popup_menu_home, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.notification_setting) {
                    Intent intent = new Intent(HomeActivity.this, NotificationSettingsActivity.class);
                    startActivity(intent);
                } else if (menuItem.getItemId() == R.id.business_canvas) {
                    Intent intent = new Intent(this, CanvasTemplateActivity.class);
                    startActivity(intent);
                }else if(menuItem.getItemId() == R.id.logout){
                    logout();
                }
                return false;
            });

        popupMenu.show();
    }

    void shareApplication(){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Learning Companion");
            String shareMessage= "\nJoin Learning Companion App an one stop destination to gain information, test understanding, access to experts sessions & connect with  industries.\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share App"));
        } catch(Exception e) {
            
        }

    }
    @Override
    public void onBackPressed() {
    }

    private void logout(){
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putLong("uid",0);
        editor.putString("username","");
        editor.putString("emailid","");
        editor.putString("ut","");
        editor.putString("autologin","no");
        editor.commit();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }


    protected boolean shouldAskPermissions() {

        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {

        String[] permissions = {

                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };

        int requestCode = 200;
        requestPermissions(permissions, requestCode);

    }

    void setBusinessCanvasData(){
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String mEmailId = sh.getString("emailid","");

        //initializing JFStorage DB
        JfStorage mJfStorage = new JfStorage(this);

        ArrayList<BusinessActivityPojo> mList = mJfStorage.getAllBusinessCanvas(mEmailId);
        if(mList.isEmpty() || mList == null){
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"segment","",R.string.title_customersegment,R.string.customersegment,"https://www.youtube.com/watch?v=zPJtDohab-g", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"value","",R.string.title_valueproposition,R.string.valueproposition,"https://www.youtube.com/watch?v=ReM1uqmVfP0", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"relation","",R.string.title_customerrelation,R.string.customerrelation,"https://www.youtube.com/watch?v=7BTW_P8fYlk", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"channel","",R.string.title_channel,R.string.channel,"https://www.youtube.com/watch?v=aCgHyHq-QHs", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"activity","",R.string.title_keyactivity,R.string.keyactivity,"https://www.youtube.com/watch?v=latxGnuQseU", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"resource","",R.string.title_keyresource,R.string.keyresource,"https://www.youtube.com/watch?v=58ASl6yA4_Q", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"partner","",R.string.title_keypartner,R.string.keypartner,"https://www.youtube.com/watch?v=yigXW0KYYJI", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"cost","",R.string.title_cost,R.string.cost,"https://www.youtube.com/watch?v=VWxLVD99vGI", mEmailId));
            mJfStorage.insertBusinessCanvas(new BusinessActivityPojo(0,"revenue","",R.string.title_revenuemodel,R.string.revenuemodel,"https://www.youtube.com/watch?v=vi8RURc1MWg", mEmailId));
        }
    }

}
