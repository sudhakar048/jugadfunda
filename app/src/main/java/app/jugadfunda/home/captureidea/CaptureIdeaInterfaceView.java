package app.jugadfunda.home.captureidea;

import app.jugadfunda.home.pojo.CaptureIdeaPojo;

public interface CaptureIdeaInterfaceView {

    void saveData(CaptureIdeaPojo captureIdeaPojo,int pos);

    void uploadFile(CaptureIdeaPojo captureIdeaPojo,int pos);

    void clearWeekIdea();
}
