package app.jugadfunda.home.postquery;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import app.jugadfunda.home.pojo.CourseList;
import app.jugadfunda.home.pojo.ModuleListPojo;
import app.jugadfunda.home.pojo.QueryListPojo;
import app.jugadfunda.home.pojo.SubModuleListPojo;

public interface PostQueryInterfaceView {

    void populateCourseList(ArrayList<CourseList> mList);

    void populateModuleList(ArrayList<ModuleListPojo> mList, String btnType);

    void populateSubModuleList(ArrayList<SubModuleListPojo> mList, String btnType);

    void populatePostedQueryList(ArrayList<QueryListPojo> mQueryList);

    void clearPostQueryForm(String mType);

    void disableSubmitBtn();

    void enableSubmitBtn();

    void resetPostQueryForm(TextInputEditText mTIET);

    void addSubmodule(String addStr, int pos);

    void removeSubModule(String removeStr, int pos);

    void resetCourseListAdapter(int pos, int mRating);

    void resetGeneralListAdapter(int pos, int mRating);

    void wsRateUs(int mRating, String mStatus, int pos);

    void radio1();

    void radio2();

    void wsDeleteQuery(long mQueryId, String mStatus, int position);

    void deletePos(int pos, String mStatus);

    void updatePostedQuery(int pos, String mType);

    void dismissPopup();


}
