package app.jugadfunda.inquiryform.pojo;

public class SubDomains {
    private int subid;
    private String subname;
    private boolean flag;

    public SubDomains() {
    }

    public SubDomains(int subid, String subname, boolean flag) {
        this.subid = subid;
        this.subname = subname;
        this.flag = flag;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SubDomains{" +
                "subid=" + subid +
                ", subname='" + subname + '\'' +
                ", flag=" + flag +
                '}';
    }
}
