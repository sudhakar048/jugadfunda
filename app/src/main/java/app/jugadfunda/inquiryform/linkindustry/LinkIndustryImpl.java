package app.jugadfunda.inquiryform.linkindustry;

import retrofit2.http.Field;

public interface LinkIndustryImpl {

    void wsGetStateList();

    void wsGetDistrictList(long mSid);

    void wsGetCenterList(long mSid, long mDid);

    void wsInstituteList(long mCenterId);

    void wsRegistration(String mCompanyName,String mGstnnumber, String mOwnername, String mContactCode, String mContact, String mEmailId, String mModule, String mOwnerAadhaar, String mCoordinatorName,String cocode,String mCoContact,String mCoOrEmailId, String mCoAadhaar,String mDate, String mDepartment, String mEmp,String mAnnualTurnover,String mAddress,String mAboutCompany,String mCity,String mPinCode, String mCheck,long mCenterId,long mInstituteId, int mStateId,int mDistrictId, String mTenKeywords, String mInstituteName, String mDomain, String mSubDomain, long mUserId, long mCourseId);

    void wsLinkedIndustry(long mUserId, String mType);
}
