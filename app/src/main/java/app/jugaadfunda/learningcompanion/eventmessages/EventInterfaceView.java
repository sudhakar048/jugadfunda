package app.jugaadfunda.learningcompanion.eventmessages;

import java.util.ArrayList;
import app.jugaadfunda.learningcompanion.apiresponse.EventResponse;

public interface EventInterfaceView {

    void setEventListtoAdapter(ArrayList<EventResponse> mEventList);

    void loadProgressBar();

    void checkforNodata();
}
