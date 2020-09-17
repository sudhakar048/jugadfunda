package app.jugaadfunda.learningcompanion.apiresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResponse {

    @SerializedName("flag")
    @Expose
    private boolean flag;

    @SerializedName("result")
    @Expose
    private String result;

    public SignupResponse(boolean flag, String result) {
        this.flag = flag;
        this.result = result;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SignupResponse{" +
                "flag=" + flag +
                ", result='" + result + '\'' +
                '}';
    }
}
