package app.jugadfunda.track;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import java.util.Base64;
import app.jugadfunda.R;

public class ScheduleTrackActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private static String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_track);

        setUI();
    }

    void setUI(){
        ((TextView)findViewById(R.id.tv_title)).setText(R.string.schedule_track);
        mWebView = findViewById(R.id.webview);

        //get Data from local Db
        SharedPreferences sh = getSharedPreferences("profile",MODE_PRIVATE);
        String userid = sh.getString("userid","");
        String username = sh.getString("username","");
        String emailid = sh.getString("emailid","");
        String usertype = sh.getString("mt","");
        String designation = sh.getString("designation","");

        //load url in page
        mWebView.getSettings().setJavaScriptEnabled(true);
        if(usertype.equals("begin") || usertype.equals("fmo") || usertype.equals("ums") || usertype.equals("umb") || usertype.equals("mentor") || usertype.equals("institute") || usertype.equals("sig") || usertype.equals("incubation")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                url = "http://www.juggadpanchayat.in/recording.jsp?mid="+userid+"&mname="+username+"&memail="+emailid+"&ut="+ Base64.getEncoder().encodeToString(usertype.getBytes());
            }
        }else{
            url = "http://www.juggadpanchayat.in/jf_radio_recording.jsp?u="+userid+"&n="+username+"&e="+emailid+"&d="+designation+"&ut=mobile";
        }
        mWebView.loadUrl(url);


        setListeners();
    }

    void setListeners(){
        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
