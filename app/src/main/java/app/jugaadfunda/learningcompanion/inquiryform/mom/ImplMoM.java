package app.jugaadfunda.learningcompanion.inquiryform.mom;

import android.content.Context;
import android.widget.Toast;

import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.SignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplMoM implements MOMImpl{
    private Context mContext;
    private MOMView mMomView;

    public ImplMoM(Context mContext, MOMView mMomView){
        this.mContext = mContext;
        this.mMomView = mMomView;
    }
    @Override
    public void wsMOM(String mMomDate, String mTitle, String mLocation, String mStarttime, String mEndtime, String mAttendeesName, String mPoints, String mStudentName, String mCollegeName, String mDeptName, String mJoiningYear, long mUserId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsMOM(
                mMomDate,
                mTitle,
                mLocation,
                mStarttime,
                mEndtime,
                mAttendeesName,
                mPoints,
                mStudentName,
                mCollegeName,
                mDeptName,
                mJoiningYear,
                mUserId
        ).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse = response.body();
                if(signupResponse.isFlag()){
                    mMomView.clearForm();
                }
                Toast.makeText(mContext,""+signupResponse.getResult(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
