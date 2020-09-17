package app.jugaadfunda.learningcompanion.login.signup;

import android.content.Context;
import android.widget.Toast;
import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.SignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplSignupPresenter implements SignupInterfaceImpl{
    private Context mContext;
    private SignUpInterfaceView mSignUpInterfaceView;

public ImplSignupPresenter(Context mContext, SignUpInterfaceView mSignUpInterfaceView){
    this.mContext = mContext;
    this.mSignUpInterfaceView = mSignUpInterfaceView;
}

    @Override
    public void wssignup(String mName, String mEmailid, String mContact, String mPassword, String mCPassword, String mUserType) {
        EndPointInterface endPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsSignup(mName,mEmailid,mContact,mPassword, mCPassword, mUserType).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse = response.body();
               if(signupResponse.isFlag()){
                   mSignUpInterfaceView.clearSignUpForm();
               }
                Toast.makeText(mContext,signupResponse.getResult(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
