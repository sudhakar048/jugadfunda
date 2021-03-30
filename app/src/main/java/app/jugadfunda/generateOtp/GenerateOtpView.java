package app.jugadfunda.generateOtp;

public interface GenerateOtpView {

    void movetoQuizActivity();

    void clearForm();

    void showMsg(String message);

    void generateOtp(String otp);
}
