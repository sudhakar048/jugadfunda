package app.jugadfunda.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import java.util.Base64;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.SigninResponse;
import app.jugadfunda.login.pojo.RadioLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplSigninPresenter implements SigninInterfaceImpl {
    private Context mContext;
    private SigninInterfaceView signinInterfaceView;

    public ImplSigninPresenter(Context mContext, SigninInterfaceView signinInterfaceView){
        this.mContext = mContext;
        this.signinInterfaceView = signinInterfaceView;
    }

    //Guest User Login
    @Override
    public void wsRadioSignin(String mEmailId, String mPwd) {
        EndPointInterface endPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsRadioSignin(mEmailId, mPwd).enqueue(new Callback<RadioLogin>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<RadioLogin> call, Response<RadioLogin> response) {
                RadioLogin signinResponse = response.body();
                if(signinResponse != null){
                    if(signinResponse.isFlag()){
                        signinInterfaceView.movetoHomeScreen();
                        String userid = signinResponse.getUid();
                        String username = signinResponse.getUsername();
                        String emailid = signinResponse.getUseremailid();
                        String designation = signinResponse.getUserdesignation();
                        String usertype = signinResponse.getUt();

                        byte[] decodeUserId = Base64.getDecoder().decode(userid);
                        long decodeuid = Long.parseLong(new String(decodeUserId));

                        byte[] decodeUserType = Base64.getDecoder().decode(usertype);
                        String decodeutype = new String(decodeUserType);

                        // save data to sharedpreferences
                        SharedPreferences sh = mContext.getSharedPreferences("profile",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("userid",userid);
                        editor.putLong("uid", decodeuid);
                        editor.putString("username",username);
                        editor.putString("emailid",emailid);
                        editor.putString("designation",designation);
                        editor.putString("mt",decodeutype);
                        editor.putString("autologin","yes");
                        editor.commit();
                    }else{
                        Toast.makeText(mContext,signinResponse.getResult(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RadioLogin> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Jugaadfunda User Login
    @Override
    public void wsSignin(String mEmailId, String mPwd, String mType, String mToken, String mModuleName) {
       EndPointInterface endPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsSignin(mEmailId, mPwd, mType, mToken).enqueue(new Callback<SigninResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                SigninResponse signinResponse = response.body();

                if(signinResponse != null){
                    if(signinResponse.isResult()){
                        signinInterfaceView.movetoHomeScreen();
                        long uid = signinResponse.getId();
                        String userid = ""+uid;
                        String usertype = signinResponse.getModuleName();

                        SharedPreferences sh = mContext.getSharedPreferences("profile",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();

                        //encode userName
                        String encodeUsername = Base64.getEncoder().encodeToString(signinResponse.getName().getBytes());

                        //encode emailId
                        String encodeEmailid = Base64.getEncoder().encodeToString(signinResponse.getEmail().getBytes());

                        //encode Designation
                        String encodeDesignation = Base64.getEncoder().encodeToString(mModuleName.getBytes());

                        String encodeUserid = Base64.getEncoder().encodeToString(userid.getBytes());

                        // save data to sharedpreferences
                        editor.putString("userid",encodeUserid);
                        editor.putLong("uid",uid);
                        editor.putString("username",encodeUsername);
                        editor.putString("emailid",encodeEmailid);
                        editor.putString("mb",signinResponse.getMb());
                        editor.putString("designation",encodeDesignation);
                        editor.putString("mt",usertype);
                        editor.putString("autologin","yes");
                        editor.commit();

                    }else{
                        Toast.makeText(mContext,"Either Invalid Credential or you yet not verified by administration.Please wait for Admin Verification.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
