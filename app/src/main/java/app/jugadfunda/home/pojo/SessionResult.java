package app.jugadfunda.home.pojo;

public class SessionResult {
    private boolean flag;
    private String msg;

    public SessionResult(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SessionResult{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                '}';
    }
}
