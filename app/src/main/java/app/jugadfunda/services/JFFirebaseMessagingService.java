package app.jugadfunda.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;
import app.jugadfunda.R;
import app.jugadfunda.SplashActivity;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.generateOtp.GenerateOtp;
import app.jugadfunda.home.HomeActivity;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.questions.StartQuizActivity;
import app.jugadfunda.utility.ImageFileFilter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JFFirebaseMessagingService extends FirebaseMessagingService {
    private String AUTO_LOGIN = "";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size()>0)
        {
            SharedPreferences sp = getSharedPreferences("profile",MODE_PRIVATE);
            AUTO_LOGIN = sp.getString("autologin","");

            JSONObject data = new JSONObject(remoteMessage.getData());
            Log.w("data","data"+data);
            try {
                displayNotification(data.getString("title"),data.getString("notitype"), Long.parseLong(data.getString("aid")), data.getString("check"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        SharedPreferences sp = getSharedPreferences("profile",MODE_PRIVATE);
        AUTO_LOGIN = sp.getString("autologin","");
        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
                if(AUTO_LOGIN.equals("yes")){
                    long userId = sp.getLong("uid",0);
                    String moduleType = sp.getString("mt","");
                    updateToken(s, userId, moduleType);
                }else{
                    updateToken(s, 0, "newuser");
                }
            }
        };
        timerObj.schedule(timerTaskObj, 0, 24*60*60*1000);

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
    public void displayNotification(String title, String subtext, long actionId, String check) {
        Intent intent = null;
        Uri defaultSoundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        int icon = 0;
        if(check.equals("event")){
            icon = R.drawable.ic_event;
        }else if(check.equals("session")){
            icon = R.drawable.ic_session;
        }else{
            icon = R.drawable.ic_quiz;
        }
        if (android.os.Build.VERSION.SDK_INT >= 26) {

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, ImageFileFilter.channelId)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setSubText(subtext);

            if(check.equals("event") || check.equals("session")){
                intent = new Intent(this, LoginActivity.class);
                intent.putExtra("check",check);
            }else{
                if(AUTO_LOGIN.equals("yes")){
                    intent = new Intent(this, StartQuizActivity.class);
                    intent.putExtra("qiz", actionId);
                    intent.putExtra("title",title);
                }else{
                    intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("check",check);
                }

            }

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setAutoCancel(true);
            mBuilder.setSound(defaultSoundUri);
            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);

            NotificationManager mNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

            if (mNotificationManager != null) {

                mNotificationManager.notify(1, mBuilder.build());
            }

        } else {

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,"")
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setSubText(subtext);;

            if(check.equals("event") || check.equals("session")){
                intent = new Intent(this, LoginActivity.class);
                intent.putExtra("check",check);
            }else{
                if(AUTO_LOGIN.equals("yes")){
                    intent = new Intent(this, StartQuizActivity.class);
                    intent.putExtra("qiz", actionId);
                    intent.putExtra("title",title);
                }else{
                    intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("check",check);
                }
            }

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setAutoCancel(true);
            mBuilder.setSound(defaultSoundUri);
            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);

            NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

            if (mNotificationManager != null) {

                mNotificationManager.notify(1, mBuilder.build());
            }
        }
    }
}
