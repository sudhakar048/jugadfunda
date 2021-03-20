package app.jugadfunda.home.sessions.sessionlist;

import java.util.ArrayList;

import app.jugadfunda.home.pojo.SessionDetailsPojo;

public interface SessionListInterfaceView {

    void populateSessions(ArrayList<SessionDetailsPojo> mList);

    void sessionDetails(int position);

    void showEmptyRecord();
}
