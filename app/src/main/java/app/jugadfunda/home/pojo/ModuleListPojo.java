package app.jugadfunda.home.pojo;

public class ModuleListPojo {
    private long mainmoduleid;
    private long courseid;
    private String mainmodulename;

    public ModuleListPojo(long mainmoduleid, long courseid, String mainmodulename) {
        this.mainmoduleid = mainmoduleid;
        this.courseid = courseid;
        this.mainmodulename = mainmodulename;
    }

    public long getMainmoduleid() {
        return mainmoduleid;
    }

    public void setMainmoduleid(long mainmoduleid) {
        this.mainmoduleid = mainmoduleid;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public String getMainmodulename() {
        return mainmodulename;
    }

    public void setMainmodulename(String mainmodulename) {
        this.mainmodulename = mainmodulename;
    }

    @Override
    public String toString() {
        return "ModuleListPojo{" +
                "mainmoduleid=" + mainmoduleid +
                ", courseid=" + courseid +
                ", mainmodulename='" + mainmodulename + '\'' +
                '}';
    }
}
