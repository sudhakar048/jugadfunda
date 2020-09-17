package app.jugaadfunda.learningcompanion.inquiryform.pojo;

public class GuideLinesPojo {
    private String title;
    private int guidelines;

    public GuideLinesPojo(String title, int guidelines) {
        this.title = title;
        this.guidelines = guidelines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGuidelines() {
        return guidelines;
    }

    public void setGuidelines(int guidelines) {
        this.guidelines = guidelines;
    }

    @Override
    public String toString() {
        return "GuideLinesPojo{" +
                "title='" + title + '\'' +
                ", guidelines='" + guidelines + '\'' +
                '}';
    }
}
