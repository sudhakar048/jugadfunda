package app.jugadfunda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.home.HomeActivity;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.utility.ImageFileFilter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private long mUserId = 0;
    private String mModuleType = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        creatingNotificationChannel();

        SharedPreferences sp = getSharedPreferences("profile",MODE_PRIVATE);
        String AUTO_LOGIN = sp.getString("autologin","");

        if(AUTO_LOGIN.equals("yes")){
            mUserId = sp.getLong("uid",0);
            mModuleType = sp.getString("mt","");
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }else{
            mUserId = 0;
            mModuleType = "newuser";
            setHandler();
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful()){
                    String token = task.getResult().getToken();
                    if(token != null) {
                        updateToken(token, mUserId, mModuleType);
                    }
                }
            }
        });
    }


    void setHandler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                intent.putExtra("check", "");
                startActivity(intent);
            }
        },3000);
    }

    void creatingNotificationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= 26) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(ImageFileFilter.channelId, ImageFileFilter.channelName, NotificationManager.IMPORTANCE_HIGH);

            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);

            notificationManager.createNotificationChannel(mChannel);
        }
    }

    void updateToken(String token, long userId, String moduleType){
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsUpdateToken( userId,
                moduleType,
                token
        ).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                //  boolean flag = response.body();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("t",""+t);
            }
        });
    }
}