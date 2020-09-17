package app.jugaadfunda.learningcompanion.businessactivity;

public class BusinessActivityPojo {
    private int canvasimage;
    private String canvastext;
    private int title_info;
    private int info;
    private String utubelink;
    private String canvastitle;
    private long userid;

    public BusinessActivityPojo(int canvasimage, String canvastitle, String canvastext, int title_info,int info, String utubelink, long userid) {
        this.canvasimage = canvasimage;
        this.canvastext = canvastext;
        this.title_info = title_info;
        this.info = info;
        this.utubelink = utubelink;
        this.canvastitle = canvastitle;
        this.userid = userid;
    }

    public int getCanvasimage() {
        return canvasimage;
    }

    public void setCanvasimage(int canvasimage) {
        this.canvasimage = canvasimage;
    }

    public String getCanvastext() {
        return canvastext;
    }

    public void setCanvastext(String canvastext) {
        this.canvastext = canvastext;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public String getUtubelink() {
        return utubelink;
    }

    public void setUtubelink(String utubelink) {
        this.utubelink = utubelink;
    }

    public int getTitle_info() {
        return title_info;
    }

    public void setTitle_info(int title_info) {
        this.title_info = title_info;
    }

    public String getCanvastitle() {
        return canvastitle;
    }

    public void setCanvastitle(String canvastitle) {
        this.canvastitle = canvastitle;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "BusinessActivityPojo{" +
                "canvasimage=" + canvasimage +
                ", canvastext='" + canvastext + '\'' +
                ", title_info=" + title_info +
                ", info=" + info +
                ", utubelink='" + utubelink + '\'' +
                ", canvastitle='" + canvastitle + '\'' +
                ", userid=" + userid +
                '}';
    }
}
