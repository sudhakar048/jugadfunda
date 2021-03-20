package app.jugadfunda.apiresponse;

public class InstituteList {
    private long instituteId;
    private String instituteName;

    public InstituteList(long instituteId, String instituteName) {
        this.instituteId = instituteId;
        this.instituteName = instituteName;
    }

    public long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(long instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    @Override
    public String toString() {
        return "InstituteList{" +
                "instituteId=" + instituteId +
                ", instituteName='" + instituteName + '\'' +
                '}';
    }
}
