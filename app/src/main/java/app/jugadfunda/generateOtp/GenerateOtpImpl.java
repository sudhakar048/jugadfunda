package app.jugadfunda.generateOtp;

public interface GenerateOtpImpl {

    void generateOtp(String mobilenumber, long qzid);

    void verifyOtp(String mobilenumber, String firstname, String lastname, String emailid, String institutename, long qzid);
}
