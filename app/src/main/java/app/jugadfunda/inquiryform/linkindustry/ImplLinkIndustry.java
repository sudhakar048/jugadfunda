package app.jugadfunda.inquiryform.linkindustry;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.IndustryRegistration;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.LinkedIndustryList;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplLinkIndustry implements LinkIndustryImpl{

    private Context mContext;
    private LinkIndustryView mLinkIndustryView;

    public ImplLinkIndustry(Context mContext, LinkIndustryView mLinkIndustryView){
        this.mContext = mContext;
        this.mLinkIndustryView = mLinkIndustryView;
    }

    @Override
    public void wsGetStateList() {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsStateList().enqueue(new Callback<List<StateList>>() {
            @Override
            public void onResponse(Call<List<StateList>> call, Response<List<StateList>> response) {
                if(response.body() != null){
                    List<StateList> mList = response.body();
                    mLinkIndustryView.populateStateList(mList);
                }
            }

            @Override
            public void onFailure(Call<List<StateList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsGetDistrictList(long mSid) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsDistrictList(mSid).enqueue(new Callback<List<DistrictList>>() {
            @Override
            public void onResponse(Call<List<DistrictList>> call, Response<List<DistrictList>> response) {
                if(response.body() != null){
                    List<DistrictList> mList = response.body();
                    mLinkIndustryView.populateDistrictList(mList);
                }
            }

            @Override
            public void onFailure(Call<List<DistrictList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsGetCenterList(long mSid, long mDid) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsCenterList(mSid,  mDid).enqueue(new Callback<List<CenterList>>() {
            @Override
            public void onResponse(Call<List<CenterList>> call, Response<List<CenterList>> response) {
                if(response.body() != null){
                    List<CenterList> mList = response.body();
                    mLinkIndustryView.populateCenterList(mList);
                }
            }

            @Override
            public void onFailure(Call<List<CenterList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsInstituteList(long mCenterId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsInstituteList(
                mCenterId
        ).enqueue(new Callback<List<InstituteList>>() {
            @Override
            public void onResponse(Call<List<InstituteList>> call, Response<List<InstituteList>> response) {
                if(response.body() != null){
                    List<InstituteList> mInstituteList = response.body();
                    mLinkIndustryView.populateInstituteList(mInstituteList);
                }
            }

            @Override
            public void onFailure(Call<List<InstituteList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsRegistration(String mCompanyName, String mGstnnumber, String mOwnername, String mContactCode, String mContact, String mEmailId, String mModule, String mOwnerAadhaar, String mCoordinatorName, String cocode, String mCoContact, String mCoOrEmailId, String mCoAadhaar, String mDate, String mDepartment, String mEmp, String mAnnualTurnover, String mAddress, String mAboutCompany, String mCity, String mPinCode, String mCheck, long mCenterId, long mInstituteId, int mStateId, int mDistrictId, String mTenKeywords, String mInstituteName, String mDomain, String mSubDomain, long mUserId, long mCourseId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsCallIndustryRegistration(
                mCompanyName,
                mGstnnumber,
                mOwnername,
                mContactCode,
                mContact,
                mEmailId,
                mModule,
                mOwnerAadhaar,
                mCoordinatorName,
                cocode,
                mCoContact,
                mCoOrEmailId,
                mCoAadhaar,
                mDate,
                mDepartment,
                mEmp,
                mAnnualTurnover,
                mAddress,
                mAboutCompany,
                mCity,
                mPinCode,
                mCheck,
                mCenterId,
                mInstituteId,
                mStateId,
                mDistrictId,
                mTenKeywords,
                mInstituteName,
                mDomain,
                mSubDomain,
                mUserId,
                mCourseId
                ).enqueue(new Callback<IndustryRegistration>() {
            @Override
            public void onResponse(Call<IndustryRegistration> call, Response<IndustryRegistration> response) {
                if(response.body() != null){
                    IndustryRegistration mIndustryRegistration = response.body();
                    Log.e("mIndustryRegistration",""+mIndustryRegistration);
                    if(mIndustryRegistration.isFlag()){
                        mLinkIndustryView.clearForm();
                        mLinkIndustryView.refreshAdapter(mIndustryRegistration.getMybusinessId(), mCompanyName, mAddress, mContact, mCoordinatorName, mCoOrEmailId, mEmailId, "Industry"+mContact,"Approved");
                    }
                    Toast.makeText(mContext, mIndustryRegistration.getMsg(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<IndustryRegistration> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsLinkedIndustry(long mUserId, String mType) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsLiknedIndustryList(
                mUserId,
                mType
        ).enqueue(new Callback<List<LinkedIndustryList>>() {
            @Override
            public void onResponse(Call<List<LinkedIndustryList>> call, Response<List<LinkedIndustryList>> response) {
                if(response.body() != null){
                    List<LinkedIndustryList> mLinkedIndustryList = response.body();
                    mLinkIndustryView.populateLinkedIndustries(mLinkedIndustryList);
                }
            }

            @Override
            public void onFailure(Call<List<LinkedIndustryList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
