package app.jugadfunda.generateOtp;

public interface GenerateOtpImpl {

    void generateOtp(String mobilenumber, long qzid);

    void verifyOtp(String firstname,String middlename, String lastname, String gender, String dob, String mobilenumber, String emailId, int stateid, int districtid, long centerid, long instituteid, long quizid);

    void populateStates();

    void populateDistricts(long stateid);

    void populateCenters(long stateid, long districtid);

    void populateInstitutes(long centerid);
}
