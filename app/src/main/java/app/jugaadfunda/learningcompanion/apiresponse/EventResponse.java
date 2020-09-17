package app.jugaadfunda.learningcompanion.apiresponse;

public class EventResponse {

    private long eventId;
    private String getTitle;
    private String od;
    private String cd;
    private String desc;
    private String hta;

    public EventResponse(long eventId, String getTitle, String od, String cd, String desc, String hta) {
        this.eventId = eventId;
        this.getTitle = getTitle;
        this.od = od;
        this.cd = cd;
        this.desc = desc;
        this.hta = hta;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getGetTitle() {
        return getTitle;
    }

    public void setGetTitle(String getTitle) {
        this.getTitle = getTitle;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHta() {
        return hta;
    }

    public void setHta(String hta) {
        this.hta = hta;
    }
}
