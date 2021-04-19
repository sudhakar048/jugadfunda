package app.jugadfunda.generateOtp;

import java.util.ArrayList;

import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;

public interface PsychometricTestView {

    void movetoQuizActivity();

    void clearForm();

    void populateStates(ArrayList<StateList> stateLists);

    void populateDistricts(ArrayList<DistrictList> districtLists);

    void populateCenters(ArrayList<CenterList> centerLists);

    void populateInstitutes(ArrayList<InstituteList> instituteLists);

    void callStateList(int pos);

    void callDistrictList(int pos);

    void callCenterList(int pos);

    void callInstituteList(int pos);
}
