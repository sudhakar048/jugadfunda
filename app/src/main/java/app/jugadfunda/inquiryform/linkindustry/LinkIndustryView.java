package app.jugadfunda.inquiryform.linkindustry;

import java.util.List;

import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.LinkedIndustryList;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;

public interface LinkIndustryView {

    void clearForm();

    void selectProductServiceDomain(int check, int pos);

    void selectProductServiceSubDomain(int check, int position);

    void showAndHideProductServiceDomainEditText(int check, int position);

    void populateStateList(List<StateList> mStateList);

    void populateDistrictList(List<DistrictList> mDistrictList);

    void populateCenterList(List<CenterList> mCenterList);

    void populateInstituteList(List<InstituteList> mInstituteList);

    void callStateList(int pos);

    void populateLinkedIndustries(List<LinkedIndustryList> mIndustryList);

    void loadIndustryDetailsDialog(int position);

    void connectIndustry(int position);

    void showUMB();

    void showUMBList();

    void refreshAdapter(long mIndustryId, String mIndustryName, String mAddress, String mIndustryContact, String mContactPerson, String mCoEmailId, String mEmailId, String mPassword, String mRegistrationStatus);
}
