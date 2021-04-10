package app.jugadfunda.apiresponse;

public class EventResponse {

    private long id;
    private String title;
    private String openingdate;
    private String closingdate;
    private String description;
    private String howtoapply;

    public EventResponse(long id, String title, String openingdate, String closingdate, String description, String howtoapply) {
        this.id = id;
        this.title = title;
        this.openingdate = openingdate;
        this.closingdate = closingdate;
        this.description = description;
        this.howtoapply = howtoapply;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpeningdate() {
        return openingdate;
    }

    public void setOpeningdate(String openingdate) {
        this.openingdate = openingdate;
    }

    public String getClosingdate() {
        return closingdate;
    }

    public void setClosingdate(String closingdate) {
        this.closingdate = closingdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowtoapply() {
        return howtoapply;
    }

    public void setHowtoapply(String howtoapply) {
        this.howtoapply = howtoapply;
    }

    @Override
    public String toString() {
        return "EventResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", openingdate='" + openingdate + '\'' +
                ", closingdate='" + closingdate + '\'' +
                ", description='" + description + '\'' +
                ", howtoapply='" + howtoapply + '\'' +
                '}';
    }
}
