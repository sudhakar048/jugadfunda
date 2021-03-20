package app.jugadfunda.eventmessages;

import java.util.ArrayList;
import app.jugadfunda.apiresponse.EventResponse;

public interface EventInterfaceView {

    void setEventListtoAdapter(ArrayList<EventResponse> mEventList);

    void loadProgressBar();

    void checkforNodata();

    void openUrl(String url);
}
