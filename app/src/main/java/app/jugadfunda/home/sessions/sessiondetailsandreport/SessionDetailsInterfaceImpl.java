package app.jugadfunda.home.sessions.sessiondetailsandreport;

public interface SessionDetailsInterfaceImpl {

    void wsSessionDetails(long mSessionId, long mMentorId);

    void wsAcceptReject(long mSeesionId, long mMentorId, String mStatus, String mReason);

    void wsAddReport(long mSessionId, long mMentorId, String attendees, String mSummery, String mSuggestion, String mLink);

    void wsUpdateSessionStatus(long mSessionId, long mMentorId, String mStatus, String mReason);

    void wsUpdateStatus(long mSessionId, long mMentorId, String mStatus);
}
