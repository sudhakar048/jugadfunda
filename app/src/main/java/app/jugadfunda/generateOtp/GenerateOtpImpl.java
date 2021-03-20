package app.jugadfunda.generateOtp;

public interface GenerateOtpImpl {

    void generateOtp(String mobilenumber);

    void verifyOtp(String mobilenumber, String otp, String firstname, String lastname, String emailid, String institutename, long qzid);
}
