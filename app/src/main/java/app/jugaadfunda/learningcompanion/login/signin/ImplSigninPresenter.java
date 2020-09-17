package app.jugaadfunda.learningcompanion.login.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Base64;

import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.SigninResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplSigninPresenter implements SigninInterfaceImpl {
    private Context mContext;
    private SigninInterfaceView signinInterfaceView;

    ImplSigninPresenter(Context mContext, SigninInterfaceView signinInterfaceView){
        this.mContext = mContext;
        this.signinInterfaceView = signinInterfaceView;
    }

    @Override
    public void wsSignin(String mEmailId, String mPwd) {
        EndPointInterface endPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsSignin(mEmailId, mPwd).enqueue(new Callback<SigninResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                SigninResponse signinResponse = response.body();

                if(signinResponse != null){
                    if(signinResponse.isFlag()){
                        signinInterfaceView.movetoHomeScreen();
                        String userid = signinResponse.getUid();
                        String username = signinResponse.getUsername();
                        String emailid = signinResponse.getUseremailid();
                        String designation = signinResponse.getUserdesignation();
                        String usertype = signinResponse.getUt();

                        byte[] decodeUserId = Base64.getDecoder().decode(userid);
                        long uid = Long.parseLong(new String(decodeUserId));

                        byte[] decodeUserType = Base64.getDecoder().decode(usertype);
                        String utype = new String(decodeUserType);

                        // save data to sharedpreferences
                        SharedPreferences sh = mContext.getSharedPreferences("profile",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putLong("uid",uid);
                        editor.putString("userid",userid);
                        editor.putString("username",username);
                        editor.putString("emailid",emailid);
                        editor.putString("designation",designation);
                        editor.putString("ut",utype);
                        editor.commit();
                    }else{
                        Toast.makeText(mContext,"Yet not verified by administration.Please wait for Admin Verification.",Toast.LENGTH_LONG).show();
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
