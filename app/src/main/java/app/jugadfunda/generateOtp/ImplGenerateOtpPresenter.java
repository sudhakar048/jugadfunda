package app.jugadfunda.generateOtp;

import android.content.Context;
import android.widget.Toast;
import java.util.Base64;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
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
    public void generateOtp(String mobilenumber) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
        }

        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsGenerateOtp(
                mobilenumber
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String otp = response.body();
                Toast.makeText(mContext, otp, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void verifyOtp(String mobilenumber, String otp, String firstname, String lastname, String emailid, String institutename, long qzid) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
            otp = Base64.getEncoder().encodeToString(otp.getBytes());
        }

        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsVerifyOtp(
                mobilenumber,
                otp,
                firstname,
                lastname,
                emailid,
                institutename,
                qzid
        ).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body()){
                    mGenerateOtpView.movetoQuizActivity();
                    mGenerateOtpView.clearForm();
                }
             }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
