package app.jugadfunda.inquiryform.mom;

import java.util.ArrayList;

import app.jugadfunda.inquiryform.pojo.MomPojo;

public interface MOMView {

    void clearForm();

    void shoeMomDetails(int position);

    void populateMomList(ArrayList<MomPojo> mList);

    void checkForNoData();

    void addMOMDetailsToList(MomPojo mMomPojo);

    void removeMoMFromList(int position);

}
