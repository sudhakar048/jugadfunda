package app.jugadfunda.home.funcorner;

import app.jugadfunda.apiresponse.FunCornerResponse;

public interface CartoonCornerInterfaceView {

    void populateData(FunCornerResponse mFunCornerResponse);

    void updateCount(int check);

    void checkforNodata();

    void setAdvertisement(byte[] image);
}
