package app.jugaadfunda.learningcompanion.inquiryform.industryenquiry;

import android.content.Context;
import android.widget.Toast;

import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.SignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplIndustryEnquiry implements  IndustryEnquiryImpl{
    private Context mContext;
    private IndustryEnquiryView mIndustIndustryEnquiryView;

    public ImplIndustryEnquiry(Context mContext, IndustryEnquiryView mIndustIndustryEnquiryView){
        this.mContext = mContext;
        this.mIndustIndustryEnquiryView = mIndustIndustryEnquiryView;
    }

    @Override
    public void wsAddEnquiryData(String mCompanyName, String mCompanyAddress, String mOwner_sarpanch_pricipleName, String mCity, int mPincode, String mIndustryPanNo, String mCompanyEmailId, String mCompanyContact, String mEstablishyear, int mNoOfDept, int mNoOfEmp, String mAnnualTurnOver, String mBusinessDesc, String mKeywords, String mAadharNumber, String mSecEmailId, String mSecContact, String mEfficientInfra, String mSalesandPromo, String mOperaAdnComp, String mTechRNDInnov, String mContentMgmt, String mIt, long mUserId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsAddEnquiry(
                mCompanyName,
                mCompanyAddress,
                mOwner_sarpanch_pricipleName,
                mCity,
                mPincode,
                mIndustryPanNo,
                mCompanyEmailId,
                mCompanyContact,
                mEstablishyear,
                mNoOfDept,
                mNoOfEmp,
                mAnnualTurnOver,
                mBusinessDesc,
                mKeywords,
                mAadharNumber,
                mSecEmailId,
                mSecContact,
                mEfficientInfra,
                mSalesandPromo,
                mOperaAdnComp,
                mTechRNDInnov,
                mContentMgmt,
                mIt,
                mUserId
        ).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse = response.body();
                if(signupResponse.isFlag()){
                    mIndustIndustryEnquiryView.clearForm();
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
