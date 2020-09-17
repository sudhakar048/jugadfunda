package app.jugaadfunda.learningcompanion.home.captureidea;

import app.jugaadfunda.learningcompanion.home.pojo.CaptureIdeaPojo;

public interface CaptureIdeaInterfaceView {

    void saveData(CaptureIdeaPojo captureIdeaPojo,int pos);

    void uploadFile(CaptureIdeaPojo captureIdeaPojo,int pos);

    void clearWeekIdea();
}
