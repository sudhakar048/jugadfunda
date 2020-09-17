package app.jugaadfunda.learningcompanion.home.funcorner;

import app.jugaadfunda.learningcompanion.apiresponse.FunCornerResponse;

public interface CartoonCornerInterfaceView {

    void populateData(FunCornerResponse mFunCornerResponse);

    void updateCount(int check);

    void checkforNodata();

    void setAdvertisement(byte[] image);
}
