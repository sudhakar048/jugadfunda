package app.jugadfunda.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import app.jugadfunda.R;
import app.jugadfunda.eventmessages.EventMessagesFragment;
import app.jugadfunda.login.fragment.LoginFragment;
import app.jugadfunda.login.news.NewFragment;
import app.jugadfunda.quiz.quizlist.QuizFragment;

public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private TabLayout.Tab tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUI();

    }

    private void setUI() {
        tabLayout = findViewById(R.id.tab_layout);
        addTabs();
    }
    private void addTabs() {
        setCustomView("Sign In");
        setCustomView("Events");
        setCustomView("News");
        setCustomView("Get Inspired");
        setCustomView("Quiz / Poll");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                addFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

             }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        String check = getIntent().getStringExtra("check");
        if(check.equals("quiz")){
            addFragment(4);
            tab = tabLayout.getTabAt(4);
        }else  if(check.equals("event")){
            addFragment(1);
            tab = tabLayout.getTabAt(1);
        }else{
            addFragment(0);
            tab = tabLayout.getTabAt(0);
        }
        tab.select();
    }

    private void setCustomView(String title) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tabs_title, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }

    private void addFragment(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment = new LoginFragment();
        } else if (position == 1) {
            setModuleToNull();
            fragment = new EventMessagesFragment();
        } else if (position == 2) {
            fragment = new NewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("check", "news");
            fragment.setArguments(bundle);
        } else if (position == 3) {
            fragment = new NewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("check", "story");
            fragment.setArguments(bundle);
        } else if (position == 4){
            setModuleToNull();
            fragment = new QuizFragment();
        }

        if (fragment != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container_login, fragment).commit();
    }

    void setModuleToNull(){
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("mt",null);
        editor.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
