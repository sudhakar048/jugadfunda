package app.jugadfunda.psychometricTest;

public interface PsychometricTestImpl {

    void verifyOtp(String firstname,String middlename, String lastname, String gender, String dob, String mobilenumber, String emailId, int stateid, int districtid, long centerid, long instituteid);

    void populateStates();

    void populateDistricts(long stateid);

    void populateCenters(long stateid, long districtid);

    void populateInstitutes(long centerid);

    void verifyQuizCode(String code);
}
