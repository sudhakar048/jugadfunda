package app.jugadfunda.inquiryform.mom;

public interface MOMImpl {

    void wsMOM(String mMomDate, String mTitle, String mLocation, String mStarttime, String mEndtime, String mAttendeesName, String mPoints, String mStudentName, String mCollegeName, String mDeptName, String mJoiningYear, String mType, long mUserId);

    void wsGetMomList(long mUserId, String mType);

    void wsDeleteMom(long mMomId, int position);
}
