package app.jugadfunda.apiresponse;

public class VerifyOtpResponse {
    boolean flag;

    public VerifyOtpResponse(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "VerifyOtpResponse{" +
                "flag=" + flag +
                '}';
    }
}
