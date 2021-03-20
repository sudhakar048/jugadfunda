package app.jugadfunda.home.pojo;

public class DistrictList {
    private int did;
    private String districtname;

    public DistrictList(int did, String districtname) {
        this.did = did;
        this.districtname = districtname;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    @Override
    public String toString() {
        return "DistrictList{" +
                "did=" + did +
                ", districtname='" + districtname + '\'' +
                '}';
    }
}
