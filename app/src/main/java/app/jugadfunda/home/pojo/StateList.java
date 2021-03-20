package app.jugadfunda.home.pojo;

public class StateList {
    private int sid;
    private String statename;

    public StateList(int sid, String statename) {
        this.sid = sid;
        this.statename = statename;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    @Override
    public String toString() {
        return "StateList{" +
                "sid=" + sid +
                ", statename='" + statename + '\'' +
                '}';
    }
}
