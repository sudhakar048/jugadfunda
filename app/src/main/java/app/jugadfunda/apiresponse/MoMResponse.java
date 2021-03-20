package app.jugadfunda.apiresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoMResponse {

    @SerializedName("flag")
    @Expose
    private boolean flag;

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("momId")
    @Expose
    private long momId;

    @SerializedName("momdate")
    @Expose
    private String momdate;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("stime")
    @Expose
    private String stime;

    @SerializedName("etime")
    @Expose
    private String etime;


    @SerializedName("attendeesname")
    @Expose
    private String attendeesname;


    @SerializedName("pointdiscuss")
    @Expose
    private String pointdiscuss;

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("collegename")
    @Expose
    private String collegename;

    @SerializedName("deptname")
    @Expose
    private String deptname;

    @SerializedName("year")
    @Expose
    private int year;

    @SerializedName("moduletype")
    @Expose
    private String moduletype;

    public MoMResponse(long momId, String momdate, String title, String location, String stime, String etime, String attendeesname, String pointdiscuss, String name, String collegename, String deptname, int year, String moduletype) {
        this.momId = momId;
        this.momdate = momdate;
        this.title = title;
        this.location = location;
        this.stime = stime;
        this.etime = etime;
        this.attendeesname = attendeesname;
        this.pointdiscuss = pointdiscuss;
        this.name = name;
        this.collegename = collegename;
        this.deptname = deptname;
        this.year = year;
        this.moduletype = moduletype;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getMomId() {
        return momId;
    }

    public void setMomId(long momId) {
        this.momId = momId;
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

    public String getPointdiscuss() {
        return pointdiscuss;
    }

    public void setPointdiscuss(String pointdiscuss) {
        this.pointdiscuss = pointdiscuss;
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

    public String getModuletype() {
        return moduletype;
    }

    public void setModuletype(String moduletype) {
        this.moduletype = moduletype;
    }

    @Override
    public String toString() {
        return "MoMResponse{" +
                "flag=" + flag +
                ", result='" + result + '\'' +
                ", momId=" + momId +
                ", momdate='" + momdate + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", stime='" + stime + '\'' +
                ", etime='" + etime + '\'' +
                ", attendeesname='" + attendeesname + '\'' +
                ", pointdiscuss='" + pointdiscuss + '\'' +
                ", name='" + name + '\'' +
                ", collegename='" + collegename + '\'' +
                ", deptname='" + deptname + '\'' +
                ", year=" + year +
                ", moduletype='" + moduletype + '\'' +
                '}';
    }
}
