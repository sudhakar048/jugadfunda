package app.jugadfunda.apiresponse;

public class GenerateOtpResponse {
    private String msg;

    public GenerateOtpResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "GenerateOtpResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
