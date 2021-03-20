package app.jugadfunda.home.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class QueryListPojo implements Comparable<QueryListPojo>, Serializable {
    private long queryid;
    private String date;
    private String sessiondate;
    private String query;
    private long courseid;
    private long moduleid;
    private int rating;
    private String modulename;
    private String coursename;
    private int resolvedbyuser;
    private int resolvedbyadmin;
    private ArrayList<SubModulePojo> submodules;
    private String submodulename;

    public QueryListPojo(long queryid, String date, String sessiondate, String query, long courseid, long moduleid, int rating, String modulename, String coursename, int resolvedbyuser, int resolvedbyadmin, ArrayList<SubModulePojo> submodules) {
        this.queryid = queryid;
        this.date = date;
        this.sessiondate = sessiondate;
        this.query = query;
        this.courseid = courseid;
        this.moduleid = moduleid;
        this.rating = rating;
        this.modulename = modulename;
        this.coursename = coursename;
        this.resolvedbyuser = resolvedbyuser;
        this.resolvedbyadmin = resolvedbyadmin;
        this.submodules = submodules;
    }

    public QueryListPojo(long queryid, String date, String sessiondate, String query, long courseid, long moduleid, int rating, int resolvedbyuser, int resolvedbyadmin) {
        this.queryid = queryid;
        this.date = date;
        this.sessiondate = sessiondate;
        this.query = query;
        this.courseid = courseid;
        this.moduleid = moduleid;
        this.rating = rating;
        this.resolvedbyuser = resolvedbyuser;
        this.resolvedbyadmin = resolvedbyadmin;
    }

    public QueryListPojo(long queryid, String date, String query, int rating, String modulename, String coursename, String submodulename) {
        this.queryid = queryid;
        this.date = date;
        this.query = query;
        this.rating = rating;
        this.modulename = modulename;
        this.coursename = coursename;
        this.submodulename = submodulename;
    }

    public String getSubmodulename() {
        return submodulename;
    }

    public void setSubmodulename(String submodulename) {
        this.submodulename = submodulename;
    }

    public long getQueryid() {
        return queryid;
    }

    public void setQueryid(long queryid) {
        this.queryid = queryid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSessiondate() {
        return sessiondate;
    }

    public void setSessiondate(String sessiondate) {
        this.sessiondate = sessiondate;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public long getModuleid() {
        return moduleid;
    }

    public void setModuleid(long moduleid) {
        this.moduleid = moduleid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getResolvedbyuser() {
        return resolvedbyuser;
    }

    public void setResolvedbyuser(int resolvedbyuser) {
        this.resolvedbyuser = resolvedbyuser;
    }

    public int getResolvedbyadmin() {
        return resolvedbyadmin;
    }

    public void setResolvedbyadmin(int resolvedbyadmin) {
        this.resolvedbyadmin = resolvedbyadmin;
    }

    public ArrayList<SubModulePojo> getSubmodules() {
        return submodules;
    }

    public void setSubmodules(ArrayList<SubModulePojo> submodules) {
        this.submodules = submodules;
    }

    @Override
    public String toString() {
        return "QueryListPojo{" +
                "queryid=" + queryid +
                ", date='" + date + '\'' +
                ", sessiondate='" + sessiondate + '\'' +
                ", query='" + query + '\'' +
                ", courseid=" + courseid +
                ", moduleid=" + moduleid +
                ", rating=" + rating +
                ", modulename='" + modulename + '\'' +
                ", coursename='" + coursename + '\'' +
                ", resolvedbyuser=" + resolvedbyuser +
                ", resolvedbyadmin=" + resolvedbyadmin +
                ", submodules=" + submodules +
                ", submodulename='" + submodulename + '\'' +
                '}';
    }

    @Override
    public int compareTo(QueryListPojo o) {
        long compareQueryId = o.getQueryid();
        return (int) (compareQueryId - queryid);
    }
}
