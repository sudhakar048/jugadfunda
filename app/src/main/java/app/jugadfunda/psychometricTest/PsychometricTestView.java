package app.jugadfunda.psychometricTest;

import java.util.ArrayList;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.QuizCodeResponse;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;

public interface PsychometricTestView {

    void movetoQuizActivity(long quizid, String title, int duration, int totalnoofquestions);

    void clearForm();

    void populateStates(ArrayList<StateList> stateLists);

    void populateDistricts(ArrayList<DistrictList> districtLists);

    void populateCenters(ArrayList<CenterList> centerLists);

    void populateInstitutes(ArrayList<InstituteList> instituteLists);

    void callStateList(int pos);

    void callDistrictList(int pos);

    void callCenterList(int pos);

    void callInstituteList(int pos);

    void checkSignUp(boolean flag);

    void selectInstituteAlert(String instituteName);

    void passQuizCodeResponse(QuizCodeResponse quizCodeResponse);

}
