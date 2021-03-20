package app.jugadfunda.home.sessions.sessiondetailsandreport;

import app.jugadfunda.home.pojo.SessionDetailsPojo;

public interface SessionDetailsView {

    void showSessionDetails(SessionDetailsPojo mSessionDetailsPojo);

    void setBtns(String mStaus);

    void dismissDialog();

    void clearReasonForm();

    void clearReportForm();

    void checkRerequestRenumerationBtn(int check);
}
