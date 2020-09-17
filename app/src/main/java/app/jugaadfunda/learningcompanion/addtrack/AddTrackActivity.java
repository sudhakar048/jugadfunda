package app.jugaadfunda.learningcompanion.addtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;

import app.jugaadfunda.R;

public class AddTrackActivity extends AppCompatActivity {
    private String userid;
    private String username;
    private String emailid;
    private String designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        setUI();
    }

    void setUI(){
        WebView webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());

        //get Data from SharedPreference
        SharedPreferences sh = getSharedPreferences("profile", MODE_PRIVATE);
        userid = sh.getString("userid","");
        username = sh.getString("username","");
        emailid = sh.getString("emailid","");
        designation = sh.getString("designation","");


        webView.loadUrl("http://www.juggadpanchayat.in/jf_radio_recording.jsp?u="+userid+"&n="+username+"&e="+emailid+"&d="+designation+"&uty=mobile");

    }
}
