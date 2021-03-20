package app.jugadfunda.inquiryform.pojo;

import java.util.ArrayList;

public class CaptureProblemPojo {

    private long cpId;
    private long industryid;
    private String whatcp;
    private ArrayList<String>category;
    private String msg;
    private boolean flag;

    public CaptureProblemPojo(long cpId, long industryid, String whatcp, ArrayList<String> category, String msg, boolean flag) {
        this.cpId = cpId;
        this.industryid = industryid;
        this.whatcp = whatcp;
        this.category = category;
        this.msg = msg;
        this.flag = flag;
    }

    public long getCpId() {
        return cpId;
    }

    public void setCpId(long cpId) {
        this.cpId = cpId;
    }

    public long getIndustryid() {
        return industryid;
    }

    public void setIndustryid(long industryid) {
        this.industryid = industryid;
    }

    public String getWhatcp() {
        return whatcp;
    }

    public void setWhatcp(String whatcp) {
        this.whatcp = whatcp;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "CaptureProblemPojo{" +
                "cpId=" + cpId +
                ", industryid=" + industryid +
                ", whatcp='" + whatcp + '\'' +
                ", category=" + category +
                ", msg='" + msg + '\'' +
                ", flag=" + flag +
                '}';
    }
}
