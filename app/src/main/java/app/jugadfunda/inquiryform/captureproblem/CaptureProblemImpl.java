package app.jugadfunda.inquiryform.captureproblem;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.http.Field;

public interface CaptureProblemImpl {

    void wsCaptureProblem(long mIndustryId, String mKeyWords, String mWhatcp, String mWhycp, String mWherecp, String mWhocp, String mWhencp, String mHowcp, String mObservecp, String mIdeal, String mReality, String mConsequences, String mOutcome, String mResources, String mSkills, String mSkillreq, String mGraduation, String mPriority, int mMaxapplicants , String mCategory, String mBdomain, String mDomains, String mSubdomains, String mModule, String mBdomainother, String mPsdomainother, String mBtn);
}
