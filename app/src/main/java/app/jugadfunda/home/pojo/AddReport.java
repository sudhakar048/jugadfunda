package app.jugadfunda.home.pojo;

import java.util.ArrayList;

public class AddReport {
    private String msg;
    private boolean flag;
    private SessionDetailsPojo sdetails;
    private ArrayList<QueryListPojo> qlist;

    public AddReport(String msg, boolean flag, SessionDetailsPojo sessionDetailsPojo) {
        this.msg = msg;
        this.flag = flag;
        this.sdetails = sessionDetailsPojo;
    }

    public AddReport(String msg, boolean flag, ArrayList<QueryListPojo> qlist) {
        this.msg = msg;
        this.flag = flag;
        this.qlist = qlist;
    }

    public AddReport(String msg, boolean flag) {
        this.msg = msg;
        this.flag = flag;
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

    public SessionDetailsPojo getSdetails() {
        return sdetails;
    }

    public void setSdetails(SessionDetailsPojo sdetails) {
        this.sdetails = sdetails;
    }

    public ArrayList<QueryListPojo> getQlist() {
        return qlist;
    }

    public void setQlist(ArrayList<QueryListPojo> qlist) {
        this.qlist = qlist;
    }

    @Override
    public String toString() {
        return "AddReport{" +
                "msg='" + msg + '\'' +
                ", flag=" + flag +
                ", sdetails=" + sdetails +
                ", qlist=" + qlist +
                '}';
    }
}
