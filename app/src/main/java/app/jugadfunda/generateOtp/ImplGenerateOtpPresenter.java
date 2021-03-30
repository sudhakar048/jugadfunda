package app.jugadfunda.generateOtp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.Base64;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.GenerateOtpResponse;
import app.jugadfunda.apiresponse.SignupResponse;
import app.jugadfunda.apiresponse.VerifyOtpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplGenerateOtpPresenter implements GenerateOtpImpl {
    private Context mContext;
    private GenerateOtpView mGenerateOtpView;

    public ImplGenerateOtpPresenter(Context mContext, GenerateOtpView mGenerateOtpView){
        this.mContext = mContext;
        this.mGenerateOtpView = mGenerateOtpView;
    }

    @Override
    public void generateOtp(String mobilenumber, long qzid) {
         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
        }

        Log.d("Data", "generateOtp() called with: mobilenumber = [" + mobilenumber + "], qzid = [" + qzid + "]");

        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsGenerateOtp(
                mobilenumber,
                qzid
        ).enqueue(new Callback<GenerateOtpResponse>() {
            @Override
            public void onResponse(Call<GenerateOtpResponse> call, Response<GenerateOtpResponse> response) {
                Log.d("onResponse", "onResponse() called with: call = [" + call + "], response = [" + response.body() + "]");
                GenerateOtpResponse res = response.body();

                if(res.getMsg().length() > 4) {
                    mGenerateOtpView.showMsg(res.getMsg());
                }else{
                    mGenerateOtpView.generateOtp(res.getMsg());
                }
            }

            @Override
            public void onFailure(Call<GenerateOtpResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void verifyOtp(String mobilenumber, String firstname, String lastname, String emailid, String institutename, long qzid) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
        }

        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsVerifyOtp(
                mobilenumber,
                firstname,
                lastname,
                emailid,
                institutename,
                qzid
        ).enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                VerifyOtpResponse res = response.body();
                if(res.isFlag()){
                    mGenerateOtpView.movetoQuizActivity();
                    mGenerateOtpView.clearForm();
                }else{
                    Toast.makeText(mContext,"Issue in Submitting Data. Pls try again after some time",Toast.LENGTH_SHORT).show();
                }
             }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
