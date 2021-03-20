package app.jugadfunda.apiresponse;

import java.util.ArrayList;

public class IndustryRegistration {
    private String msg;
    private boolean flag;
    private long mybusinessId;
    private ArrayList<String> idetails;

    public IndustryRegistration(String msg, boolean flag) {
        this.msg = msg;
        this.flag = flag;
    }

    public IndustryRegistration(String msg, boolean flag, long mybusinessId, ArrayList<String> idetails) {
        this.msg = msg;
        this.flag = flag;
        this.mybusinessId = mybusinessId;
        this.idetails = idetails;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getMybusinessId() {
        return mybusinessId;
    }

    public void setMybusinessId(long mybusinessId) {
        this.mybusinessId = mybusinessId;
    }

    public ArrayList<String> getIdetails() {
        return idetails;
    }

    public void setIdetails(ArrayList<String> idetails) {
        this.idetails = idetails;
    }

    @Override
    public String toString() {
        return "IndustryRegistration{" +
                "msg='" + msg + '\'' +
                ", flag=" + flag +
                ", mybusinessId=" + mybusinessId +
                ", idetails=" + idetails +
                '}';
    }
}
