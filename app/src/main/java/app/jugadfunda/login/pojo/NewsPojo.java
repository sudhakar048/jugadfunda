package app.jugadfunda.login.pojo;

public class NewsPojo {
    private long id;
    private String title;
    private String date;
    private String encoded;
    private String description;

    public NewsPojo(long id, String title, String date, String encoded, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.encoded = encoded;
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewsPojo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", encoded='" + encoded + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
