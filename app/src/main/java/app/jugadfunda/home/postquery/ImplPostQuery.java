package app.jugadfunda.home.postquery;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.home.pojo.AddReport;
import app.jugadfunda.home.pojo.CourseList;
import app.jugadfunda.home.pojo.DeleteQuery;
import app.jugadfunda.home.pojo.ModuleListPojo;
import app.jugadfunda.home.pojo.QueryListPojo;
import app.jugadfunda.home.pojo.RatingPojo;
import app.jugadfunda.home.pojo.SubModuleListPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplPostQuery implements PostQueryInterfaceImpl {
    private Context mContext;
    private PostQueryInterfaceView mPostQueryInterfaceView;

    public ImplPostQuery(Context mContext, PostQueryInterfaceView mPostQueryInterfaceView){
        this.mContext = mContext;
        this.mPostQueryInterfaceView = mPostQueryInterfaceView;
    }

    @Override
    public void wsCourseList() {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsCourseList().enqueue(new Callback<ArrayList<CourseList>>() {
            @Override
            public void onResponse(Call<ArrayList<CourseList>> call, Response<ArrayList<CourseList>> response) {
                if(response.body() != null) {
                    ArrayList<CourseList> mList = response.body();
                    mPostQueryInterfaceView.populateCourseList(mList);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<CourseList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsModuleList(long mCourseId, String BtnType) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsModuleList(""+mCourseId).enqueue(new Callback<ArrayList<ModuleListPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<ModuleListPojo>> call, Response<ArrayList<ModuleListPojo>> response) {
                if (response.body() != null) {
                    ArrayList<ModuleListPojo> mModuleList = response.body();
                    mPostQueryInterfaceView.populateModuleList(mModuleList, BtnType);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModuleListPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsSubModuleList(long mModuleId, String BtnType) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsSubModuleList(""+mModuleId).enqueue(new Callback<ArrayList<SubModuleListPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<SubModuleListPojo>> call, Response<ArrayList<SubModuleListPojo>> response) {
               if(response.body() != null){
                   ArrayList<SubModuleListPojo> mList = response.body();
                   mPostQueryInterfaceView.populateSubModuleList(mList, BtnType);
               }
            }

            @Override
            public void onFailure(Call<ArrayList<SubModuleListPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsPostedQueryList(long mUmsId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsPostedQueryList(mUmsId).enqueue(new Callback<ArrayList<QueryListPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<QueryListPojo>> call, Response<ArrayList<QueryListPojo>> response) {
               if(response.body() != null){
                    ArrayList<QueryListPojo> mQueryList = response.body();
                    mPostQueryInterfaceView.populatePostedQueryList(mQueryList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<QueryListPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsPostQuery(long mCourseId, long mModuleId, long mUmsId, String mQuery, String mSubModuleIds, String mBtn, String mType) {
       EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsAddQuery(
                mCourseId,
                mModuleId,
                mUmsId,
                mQuery,
                mSubModuleIds,
                mBtn).enqueue(new Callback<AddReport>() {
            @Override
            public void onResponse(Call<AddReport> call, Response<AddReport> response) {

                if(response.body() != null){
                    AddReport mAddReport = response.body();
                    if(mAddReport.isFlag()){
                        mPostQueryInterfaceView.clearPostQueryForm(mType);
                        mPostQueryInterfaceView.populatePostedQueryList(mAddReport.getQlist());
                    }
                    Toast.makeText(mContext, mAddReport.getMsg(), Toast.LENGTH_LONG).show();
                }
                mPostQueryInterfaceView.enableSubmitBtn();
            }

            @Override
            public void onFailure(Call<AddReport> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
                mPostQueryInterfaceView.enableSubmitBtn();
            }
        });
    }

    @Override
    public void wsUpdatePostedQuery(long queryId, long mCourseId, long mModuleId, long mUmsId, String mQuery, String mSubModuleIds, String mBtn, String mType) {
        Log.d("Update", "wsUpdatePostedQuery() called with: queryId = [" + queryId + "], mCourseId = [" + mCourseId + "], mModuleId = [" + mModuleId + "], mUmsId = [" + mUmsId + "], mQuery = [" + mQuery + "], mSubModuleIds = [" + mSubModuleIds + "], mBtn = [" + mBtn + "], mType = [" + mType + "]");
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsUpdateQuery(
                queryId,
                mCourseId,
                mModuleId,
                mUmsId,
                mQuery,
                mSubModuleIds,
                mBtn).enqueue(new Callback<AddReport>() {
            @Override
            public void onResponse(Call<AddReport> call, Response<AddReport> response) {

                if(response.body() != null){
                    AddReport mAddReport = response.body();
                    if(mAddReport.isFlag()){
                        mPostQueryInterfaceView.populatePostedQueryList(mAddReport.getQlist());
                        mPostQueryInterfaceView.dismissPopup();
                    }
                    Toast.makeText(mContext, mAddReport.getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddReport> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsRateUs(long mQueryId, int mRating, long mUmsId, String mStatus, int pos) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsRateUs(
                mQueryId,
                mRating,
                mUmsId
        ).enqueue(new Callback<RatingPojo>() {
            @Override
            public void onResponse(Call<RatingPojo> call, Response<RatingPojo> response) {
                if(response.body() != null){
                    RatingPojo mRatingPojo = response.body();

                    if(mRatingPojo.isFlag()){
                        if(mStatus.toLowerCase().equals("general query")){
                            mPostQueryInterfaceView.resetGeneralListAdapter(pos, mRating);
                        }else{
                            mPostQueryInterfaceView.resetCourseListAdapter(pos, mRating);
                        }

                    }
                    Toast.makeText(mContext, mRatingPojo.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RatingPojo> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsDeleteQuery(long mQueryId, long mUmsId, int position, String mStatus) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsDeleteQuery(
                mQueryId,
                mUmsId
        ).enqueue(new Callback<DeleteQuery>() {
            @Override
            public void onResponse(Call<DeleteQuery> call, Response<DeleteQuery> response) {
                DeleteQuery mDeleteQuery = response.body();
                if(mDeleteQuery.isFlag()){
                  mPostQueryInterfaceView.deletePos(position,mStatus);
                }
               Toast.makeText(mContext, mDeleteQuery.getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DeleteQuery> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
