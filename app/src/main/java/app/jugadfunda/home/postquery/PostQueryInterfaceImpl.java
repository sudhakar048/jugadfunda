package app.jugadfunda.home.postquery;

public interface PostQueryInterfaceImpl {

    void wsCourseList();

    void wsModuleList(long mCourseId, String BtnType);

    void wsSubModuleList(long mModuleId, String BtnType);

    void wsPostedQueryList(long mUmsId);

    void wsPostQuery(long mCourseId, long mModuleId, long mUmsId, String mQuery, String mSubModuleIds, String mBtn, String mType);

    void wsUpdatePostedQuery(long queryId, long mCourseId, long mModuleId, long mUmsId, String mQuery, String mSubModuleIds, String mBtn, String mType);
    void wsRateUs(long mQueryId, int mRating, long mUmsId, String mStatus, int pos);

    void wsDeleteQuery(long mQueryId, long mUmsId, int position, String mStatus);
}
