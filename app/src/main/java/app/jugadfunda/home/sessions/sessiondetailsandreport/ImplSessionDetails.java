package app.jugadfunda.home.sessions.sessiondetailsandreport;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.home.pojo.AddReport;
import app.jugadfunda.home.pojo.SessionDetailsPojo;
import app.jugadfunda.home.pojo.SessionResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplSessionDetails implements SessionDetailsInterfaceImpl{
    private Context mContext;
    private SessionDetailsView mSessionDetailsView;

    public ImplSessionDetails(Context mContext, SessionDetailsView mSessionDetailsView){
        this.mContext = mContext;
        this.mSessionDetailsView = mSessionDetailsView;
    }

    @Override
    public void wsSessionDetails(long mSessionId, long mMentorId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsSessionDetails(
                mSessionId,
                mMentorId
        ).enqueue(new Callback<SessionDetailsPojo>() {
            @Override
            public void onResponse(Call<SessionDetailsPojo> call, Response<SessionDetailsPojo> response) {
                SessionDetailsPojo mSessionDetailsPojo = response.body();
                Log.e("mSessionDetailsPojo",""+mSessionDetailsPojo);
                mSessionDetailsView.showSessionDetails(mSessionDetailsPojo);
            }

            @Override
            public void onFailure(Call<SessionDetailsPojo> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsAcceptReject(long mSeesionId, long mMentorId, String mStatus, String mReason) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsAcceptReject(mSeesionId,
                mMentorId,
                mStatus,
                mReason).enqueue(new Callback<SessionResult>() {
            @Override
            public void onResponse(Call<SessionResult> call, Response<SessionResult> response) {
                SessionResult mSessionResult = response.body();
                if(mSessionResult.isFlag()){
                    mSessionDetailsView.setBtns(mStatus);
                    Toast.makeText(mContext,mSessionResult.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SessionResult> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsAddReport(long mSessionId, long mMentorId, String attendees, String mSummery, String mSuggestion, String mLink) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsAddReport(mSessionId,
                mMentorId,
                attendees,
                mSummery,
                mSuggestion,
                mLink).enqueue(new Callback<AddReport>() {
            @Override
            public void onResponse(Call<AddReport> call, Response<AddReport> response) {
                AddReport mAddReport = response.body();
                if(mAddReport != null){
                    if(mAddReport.isFlag()){
                       mSessionDetailsView.showSessionDetails(mAddReport.getSdetails());
                        mSessionDetailsView.clearReportForm();
                    }
                    Toast.makeText(mContext, mAddReport.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddReport> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsUpdateSessionStatus(long mSessionId, long mMentorId, String mStatus, String mReason) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsUpdateSessionStatus(mSessionId,
                mMentorId,
                mStatus,
                mReason).enqueue(new Callback<SessionResult>() {
            @Override
            public void onResponse(Call<SessionResult> call, Response<SessionResult> response) {
                SessionResult mSessionResult = response.body();
                Toast.makeText(mContext, ""+mSessionResult,Toast.LENGTH_LONG).show();
                if(mSessionResult != null){
                    if(mSessionResult.isFlag()){
                        mSessionDetailsView.setBtns(mStatus);
                    }
                    Toast.makeText(mContext, mSessionResult.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SessionResult> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsUpdateStatus(long mSessionId, long mMentorId, String mStatus) {
        if(mStatus.toLowerCase().equals("remunaration")){
            mSessionDetailsView.checkRerequestRenumerationBtn(1);
        }
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsUpdateStatus(
                mSessionId,
                mMentorId,
                mStatus
        ).enqueue(new Callback<SessionResult>() {
            @Override
            public void onResponse(Call<SessionResult> call, Response<SessionResult> response) {
                SessionResult mSessionResult = response.body();

                if(mSessionResult != null){
                    if(mSessionResult.isFlag()){
                        mSessionDetailsView.setBtns(mStatus);
                    }else{
                        if(mStatus.toLowerCase().equals("remunaration")){
                            mSessionDetailsView.checkRerequestRenumerationBtn(2);
                        }
                    }

                    Toast.makeText(mContext, mSessionResult.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SessionResult> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
                if(mStatus.toLowerCase().equals("remunaration")){
                    mSessionDetailsView.checkRerequestRenumerationBtn(2);
                }
            }
        });
    }
}
