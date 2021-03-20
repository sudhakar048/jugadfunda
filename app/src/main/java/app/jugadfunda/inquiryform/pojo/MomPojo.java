package app.jugadfunda.inquiryform.pojo;

public class MomPojo {

    private long momid;
    private String momdate;
    private String title;
    private String location;
    private String stime;
    private String etime;
    private String attendeesname;
    private String pointdiscussed;
    private String name;
    private String collegename;
    private String deptname;
    private int year;
    private long userid;

    public MomPojo(String title, String stime, String etime) {
        this.title = title;
        this.stime = stime;
        this.etime = etime;
    }

    public MomPojo(long momid, String momdate, String title, String location, String stime, String etime, String attendeesname, String pointdiscussed, String name, String collegename, String deptname, int year) {
        this.momid = momid;
        this.momdate = momdate;
        this.title = title;
        this.location = location;
        this.stime = stime;
        this.etime = etime;
        this.attendeesname = attendeesname;
        this.pointdiscussed = pointdiscussed;
        this.name = name;
        this.collegename = collegename;
        this.deptname = deptname;
        this.year = year;
    }

    public MomPojo(long momid, String momdate, String title, String location, String stime, String etime, String attendeesname, String pointdiscussed, String name, String collegename, String deptname, int year, long userid) {
        this.momid = momid;
        this.momdate = momdate;
        this.title = title;
        this.location = location;
        this.stime = stime;
        this.etime = etime;
        this.attendeesname = attendeesname;
        this.pointdiscussed = pointdiscussed;
        this.name = name;
        this.collegename = collegename;
        this.deptname = deptname;
        this.year = year;
        this.userid = userid;
    }

    public long getMomid() {
        return momid;
    }

    public void setMomid(long momid) {
        this.momid = momid;
    }

    public String getMomdate() {
        return momdate;
    }

    public void setMomdate(String momdate) {
        this.momdate = momdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getAttendeesname() {
        return attendeesname;
    }

    public void setAttendeesname(String attendeesname) {
        this.attendeesname = attendeesname;
    }

    public String getPointdiscussed() {
        return pointdiscussed;
    }

    public void setPointdiscussed(String pointdiscussed) {
        this.pointdiscussed = pointdiscussed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "MomPojo{" +
                "momid=" + momid +
                ", momdate='" + momdate + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", stime='" + stime + '\'' +
                ", etime='" + etime + '\'' +
                ", attendeesname='" + attendeesname + '\'' +
                ", pointdiscussed='" + pointdiscussed + '\'' +
                ", name='" + name + '\'' +
                ", collegename='" + collegename + '\'' +
                ", deptname='" + deptname + '\'' +
                ", year=" + year +
                ", userid=" + userid +
                '}';
    }
}
