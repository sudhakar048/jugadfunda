package app.jugadfunda.apiresponse;

public class VerifyOtpResponse {
    boolean flag;
    String res;

    public VerifyOtpResponse(boolean flag, String res) {
        this.flag = flag;
        this.res = res;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "VerifyOtpResponse{" +
                "flag=" + flag +
                ", res='" + res + '\'' +
                '}';
    }
}
