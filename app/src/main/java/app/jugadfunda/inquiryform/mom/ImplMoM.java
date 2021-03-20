package app.jugadfunda.inquiryform.mom;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.MoMResponse;
import app.jugadfunda.apiresponse.SignupResponse;
import app.jugadfunda.inquiryform.pojo.MomPojo;
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
    public void wsMOM(String mMomDate, String mTitle, String mLocation, String mStarttime, String mEndtime, String mAttendeesName, String mPoints, String mStudentName, String mCollegeName, String mDeptName, String mJoiningYear, String mType, long mUserId) {
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
                mType,
                mUserId
        ).enqueue(new Callback<MoMResponse>() {
            @Override
            public void onResponse(Call<MoMResponse> call, Response<MoMResponse> response) {
                MoMResponse moMResponse = response.body();
                if(moMResponse.isFlag()){
                    mMomView.clearForm();
                    mMomView.addMOMDetailsToList(new MomPojo(moMResponse.getMomId(), moMResponse.getMomdate(), moMResponse.getTitle(), moMResponse.getLocation(),moMResponse.getStime(), moMResponse.getEtime(), moMResponse.getAttendeesname(), moMResponse.getPointdiscuss(), moMResponse.getName(), moMResponse.getCollegename(),moMResponse.getDeptname(), moMResponse.getYear()));
                }
                Toast.makeText(mContext,""+moMResponse.getResult(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<MoMResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsGetMomList(long mUserId, String mType) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsGetMomList(
                mUserId,
                mType).enqueue(new Callback<ArrayList<MomPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<MomPojo>> call, Response<ArrayList<MomPojo>> response) {
                ArrayList<MomPojo> data = response.body();
                mMomView.populateMomList(data);

            }

            @Override
            public void onFailure(Call<ArrayList<MomPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsDeleteMom(long mMomId, int position) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsDeleteMom(mMomId).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse mSignupResponse = response.body();
                if(mSignupResponse.isFlag()){
                    mMomView.removeMoMFromList(position);
                }
                Toast.makeText(mContext,mSignupResponse.getResult(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
