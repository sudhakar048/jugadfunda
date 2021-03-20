package app.jugadfunda.inquiryform.captureproblem;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.inquiryform.pojo.CaptureProblemPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplCaptureProblem implements CaptureProblemImpl{
    private Context mContext;
    private CaptureProblemInterfaceView mCaptureProblemInterfaceView;

    public ImplCaptureProblem(Context mContext, CaptureProblemInterfaceView mCaptureProblemInterfaceView){
        this.mContext = mContext;
        this.mCaptureProblemInterfaceView = mCaptureProblemInterfaceView;
    }

    @Override
    public void wsCaptureProblem(long mIndustryId, String mKeyWords, String mWhatcp, String mWhycp, String mWherecp, String mWhocp, String mWhencp, String mHowcp, String mObservecp, String mIdeal, String mReality, String mConsequences, String mOutcome, String mResources, String mSkills, String mSkillreq, String mGraduation, String mPriority, int mMaxapplicants, String mCategory, String mBdomain, String mDomains, String mSubdomains, String mModule, String mBdomainother, String mPsdomainother, String mBtn) {
        Log.d("details", "wsCaptureProblem() called with: mIndustryId = [" + mIndustryId + "], mKeyWords = [" + mKeyWords + "], mWhatcp = [" + mWhatcp + "], mWhycp = [" + mWhycp + "], mWherecp = [" + mWherecp + "], mWhocp = [" + mWhocp + "], mWhencp = [" + mWhencp + "], mHowcp = [" + mHowcp + "], mObservecp = [" + mObservecp + "], mIdeal = [" + mIdeal + "], mReality = [" + mReality + "], mConsequences = [" + mConsequences + "], mOutcome = [" + mOutcome + "], mResources = [" + mResources + "], mSkills = [" + mSkills + "], mSkillreq = [" + mSkillreq + "], mGraduation = [" + mGraduation + "], mPriority = [" + mPriority + "], mMaxapplicants = [" + mMaxapplicants + "], mCategory = [" + mCategory + "], mBdomain = [" + mBdomain + "], mDomains = [" + mDomains + "], mSubdomains = [" + mSubdomains + "], mModule = [" + mModule + "], mBdomainother = [" + mBdomainother + "], mPsdomainother = [" + mPsdomainother + "], mBtn = [" + mBtn + "]");
        EndPointInterface mEdnEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEdnEndPointInterface.wsCaptureProblem(
         mIndustryId,
         mKeyWords,
         mWhatcp,
         mWhycp,
         mWherecp,
         mWhocp,
         mWhencp,
         mHowcp,
         mObservecp,
         mIdeal,
         mReality,
         mConsequences,
         mOutcome,
         mResources,
         mSkills,
         mSkillreq,
         mGraduation,
         mPriority,
         mMaxapplicants,
         mCategory,
         mBdomain,
                mDomains,
                mSubdomains,
                mModule,
                mBdomainother,
                mPsdomainother,
                mBtn
        ).enqueue(new Callback<CaptureProblemPojo>() {
            @Override
            public void onResponse(Call<CaptureProblemPojo> call, Response<CaptureProblemPojo> response) {
                CaptureProblemPojo mCaptureProblemPojo = response.body();
                if(mCaptureProblemPojo.isFlag()){
                    mCaptureProblemInterfaceView.resetCaptureProblemForm();
                }
                Toast.makeText(mContext,mCaptureProblemPojo.getMsg(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CaptureProblemPojo> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
