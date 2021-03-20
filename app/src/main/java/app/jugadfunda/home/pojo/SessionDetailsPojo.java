package app.jugadfunda.home.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class SessionDetailsPojo implements Serializable {
    private long sessionId;
    private String date;
    private String time;
    private String venue;
    private int canclestatus;
    private String canclereason;
    private int closestatus;
    private ArrayList<QueryListPojo>qlist;
    private String purpose;
    private String forwhom;
    private String link;
    private int response;
    private long reportId;
    private int attendees;
    private String summary;
    private String suggession;
    private String reportlink;
    private String reportdate;
    private int status;
    private String confirmmsg;
    private int reqremunration;
    private long remuneId;
    private String paydate;
    private String utrno;
    private String resreason;
    private int reopen;
    private int amount;
    private int notattendstatus;
    private String notattendreason;
    private String reopendate;

    public SessionDetailsPojo(long sessionId, String date, String time, String venue, int cancelstatus, String cancelreason, int closestatus, ArrayList<QueryListPojo> qlist) {
        this.sessionId = sessionId;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.canclestatus = cancelstatus;
        this.canclereason = cancelreason;
        this.closestatus = closestatus;
        this.qlist = qlist;
    }

    public SessionDetailsPojo(long sessionId, String date, String time, String venue, int cancelstatus, String cancelreason, int closestatus, ArrayList<QueryListPojo> qlist, String purpose, String forwhom, String link, int response, long reportId, int attendees, String summery, String suggestion, String reportlink, String reportdate, int status, String confirmmsg, int reqremunration, long remuneId, String paydate, String utrno, String rereason, int reopen, int amount, int notattendstatus, String notattendreason, String reopendate) {
        this.sessionId = sessionId;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.canclestatus = cancelstatus;
        this.canclereason = cancelreason;
        this.closestatus = closestatus;
        this.qlist = qlist;
        this.purpose = purpose;
        this.forwhom = forwhom;
        this.link = link;
        this.response = response;
        this.reportId = reportId;
        this.attendees = attendees;
        this.summary = summery;
        this.suggession = suggestion;
        this.reportlink = reportlink;
        this.reportdate = reportdate;
        this.status = status;
        this.confirmmsg = confirmmsg;
        this.reqremunration = reqremunration;
        this.remuneId = remuneId;
        this.paydate = paydate;
        this.utrno = utrno;
        this.resreason = rereason;
        this.reopen = reopen;
        this.amount = amount;
        this.notattendstatus = notattendstatus;
        this.notattendreason = notattendreason;
        this.reopendate = reopendate;
    }


    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getCancelstatus() {
        return canclestatus;
    }

    public void setCancelstatus(int cancelstatus) {
        this.canclestatus = cancelstatus;
    }

    public String getCancelreason() {
        return canclereason;
    }

    public void setCancelreason(String cancelreason) {
        this.canclereason = cancelreason;
    }

    public int getClosestatus() {
        return closestatus;
    }

    public void setClosestatus(int closestatus) {
        this.closestatus = closestatus;
    }

    public ArrayList<QueryListPojo> getQlist() {
        return qlist;
    }

    public void setQlist(ArrayList<QueryListPojo> qlist) {
        this.qlist = qlist;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getForwhom() {
        return forwhom;
    }

    public void setForwhom(String forwhom) {
        this.forwhom = forwhom;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSuggession() {
        return suggession;
    }

    public void setSuggession(String suggession) {
        this.suggession = suggession;
    }

    public String getReportlink() {
        return reportlink;
    }

    public void setReportlink(String reportlink) {
        this.reportlink = reportlink;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getConfirmmsg() {
        return confirmmsg;
    }

    public void setConfirmmsg(String confirmmsg) {
        this.confirmmsg = confirmmsg;
    }

    public int getReqremunration() {
        return reqremunration;
    }

    public void setReqremunration(int reqremunration) {
        this.reqremunration = reqremunration;
    }

    public long getRemuneId() {
        return remuneId;
    }

    public void setRemuneId(long remuneId) {
        this.remuneId = remuneId;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getUtrno() {
        return utrno;
    }

    public void setUtrno(String utrno) {
        this.utrno = utrno;
    }

    public String getResreason() {
        return resreason;
    }

    public void setResreason(String resreason) {
        this.resreason = resreason;
    }

    public int getReopen() {
        return reopen;
    }

    public void setReopen(int reopen) {
        this.reopen = reopen;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNotattendstatus() {
        return notattendstatus;
    }

    public void setNotattendstatus(int notattendstatus) {
        this.notattendstatus = notattendstatus;
    }

    public String getNotattendreason() {
        return notattendreason;
    }

    public void setNotattendreason(String notattendreason) {
        this.notattendreason = notattendreason;
    }

    public String getReopendate() {
        return reopendate;
    }

    public void setReopendate(String reopendate) {
        this.reopendate = reopendate;
    }

    @Override
    public String toString() {
        return "SessionDetailsPojo{" +
                "sessionId=" + sessionId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", venue='" + venue + '\'' +
                ", cancelstatus=" + canclestatus +
                ", cancelreason='" + canclereason + '\'' +
                ", closestatus=" + closestatus +
                ", qlist=" + qlist +
                ", purpose='" + purpose + '\'' +
                ", forwhom='" + forwhom + '\'' +
                ", link='" + link + '\'' +
                ", response=" + response +
                ", reportId=" + reportId +
                ", attendees=" + attendees +
                ", summary='" + summary + '\'' +
                ", suggession='" + suggession + '\'' +
                ", reportlink='" + reportlink + '\'' +
                ", reportdate='" + reportdate + '\'' +
                ", status=" + status +
                ", confirmmsg='" + confirmmsg + '\'' +
                ", reqremunration=" + reqremunration +
                ", remuneId=" + remuneId +
                ", paydate='" + paydate + '\'' +
                ", utrno='" + utrno + '\'' +
                ", resreason='" + resreason + '\'' +
                ", reopen=" + reopen +
                ", amount=" + amount +
                ", notattendstatus=" + notattendstatus +
                ", notattendreason='" + notattendreason + '\'' +
                ", reopendate='" + reopendate + '\'' +
                '}';
    }
}
