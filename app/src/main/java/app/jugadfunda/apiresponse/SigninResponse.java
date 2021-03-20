package app.jugadfunda.apiresponse;

public class SigninResponse {
    private boolean result;
    private long id;
    private String email;
    private String name;
    private String moduleName;
    private String mb;


    public SigninResponse(boolean result, long id, String email, String name, String moduleName, String mb) {
        this.result = result;
        this.id = id;
        this.email = email;
        this.name = name;
        this.moduleName = moduleName;
        this.mb = mb;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    @Override
    public String toString() {
        return "SigninResponse{" +
                "result=" + result +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", mb='" + mb + '\'' +
                '}';
    }
}
