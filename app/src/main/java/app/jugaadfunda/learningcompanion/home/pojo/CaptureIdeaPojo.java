package app.jugaadfunda.learningcompanion.home.pojo;

import java.util.Arrays;

public class CaptureIdeaPojo {

    private String day;
    private String ideatitle;
    private String description;
    private String unique;
    private String better;
    private String futureproof;
    private String triggered;
    private String futureproofotherways;
    private String problemsolving;
    private byte[] attachfile;
    private long userid;

    public CaptureIdeaPojo(String day, String ideatitle, String description, String unique, String better, String futureproof, String triggered, String futureproofotherways, String problemsolving, byte[] attachfile, long userid) {
        this.day = day;
        this.ideatitle = ideatitle;
        this.description = description;
        this.unique = unique;
        this.better = better;
        this.futureproof = futureproof;
        this.triggered = triggered;
        this.futureproofotherways = futureproofotherways;
        this.problemsolving = problemsolving;
        this.attachfile = attachfile;
        this.userid = userid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIdeatitle() {
        return ideatitle;
    }

    public void setIdeatitle(String ideatitle) {
        this.ideatitle = ideatitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getBetter() {
        return better;
    }

    public void setBetter(String better) {
        this.better = better;
    }

    public String getFutureproof() {
        return futureproof;
    }

    public void setFutureproof(String futureproof) {
        this.futureproof = futureproof;
    }

    public String getTriggered() {
        return triggered;
    }

    public void setTriggered(String triggered) {
        this.triggered = triggered;
    }

    public String getFutureproofotherways() {
        return futureproofotherways;
    }

    public void setFutureproofotherways(String futureproofotherways) {
        this.futureproofotherways = futureproofotherways;
    }

    public String getProblemsolving() {
        return problemsolving;
    }

    public void setProblemsolving(String problemsolving) {
        this.problemsolving = problemsolving;
    }

    public byte[] getAttachfile() {
        return attachfile;
    }

    public void setAttachfile(byte[] attachfile) {
        this.attachfile = attachfile;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "CaptureIdeaPojo{" +
                "day='" + day + '\'' +
                ", ideatitle='" + ideatitle + '\'' +
                ", description='" + description + '\'' +
                ", unique='" + unique + '\'' +
                ", better='" + better + '\'' +
                ", futureproof='" + futureproof + '\'' +
                ", triggered='" + triggered + '\'' +
                ", futureproofotherways='" + futureproofotherways + '\'' +
                ", problemsolving='" + problemsolving + '\'' +
                ", attachfile=" + Arrays.toString(attachfile) +
                ", userid=" + userid +
                '}';
    }
}
