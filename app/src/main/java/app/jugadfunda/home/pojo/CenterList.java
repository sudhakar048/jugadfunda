package app.jugadfunda.home.pojo;

public class CenterList {
    private long centerid;
    private String centername;

    public CenterList(long centerid, String centername) {
        this.centerid = centerid;
        this.centername = centername;
    }

    public long getCenterid() {
        return centerid;
    }

    public void setCenterid(long centerid) {
        this.centerid = centerid;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    @Override
    public String toString() {
        return "CenterList{" +
                "centerid=" + centerid +
                ", centername='" + centername + '\'' +
                '}';
    }
}
