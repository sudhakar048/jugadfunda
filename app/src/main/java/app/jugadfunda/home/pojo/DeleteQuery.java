package app.jugadfunda.home.pojo;

import java.util.ArrayList;

public class DeleteQuery {
    private String msg;
    private boolean flag;
    private ArrayList<QueryListPojo> qlist;

    public DeleteQuery(String msg, boolean flag, ArrayList<QueryListPojo> qlist) {
        this.msg = msg;
        this.flag = flag;
        this.qlist = qlist;
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

    public ArrayList<QueryListPojo> getQlist() {
        return qlist;
    }

    public void setQlist(ArrayList<QueryListPojo> qlist) {
        this.qlist = qlist;
    }

    @Override
    public String toString() {
        return "DeleteQuery{" +
                "msg='" + msg + '\'' +
                ", flag=" + flag +
                ", qlist=" + qlist +
                '}';
    }
}
