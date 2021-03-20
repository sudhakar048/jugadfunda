package app.jugadfunda.login.pojo;

public class RadioLogin {
    private boolean flag;
    private String result;
    private String uid;
    private String username;
    private String useremailid;
    private String userdesignation;
    private String ut;

    public RadioLogin(boolean flag, String result, String uid, String username, String useremailid, String userdesignation, String ut) {
        this.flag = flag;
        this.result = result;
        this.uid = uid;
        this.username = username;
        this.useremailid = useremailid;
        this.userdesignation = userdesignation;
        this.ut = ut;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremailid() {
        return useremailid;
    }

    public void setUseremailid(String useremailid) {
        this.useremailid = useremailid;
    }

    public String getUserdesignation() {
        return userdesignation;
    }

    public void setUserdesignation(String userdesignation) {
        this.userdesignation = userdesignation;
    }

    public String getUt() {
        return ut;
    }

    public void setUt(String ut) {
        this.ut = ut;
    }

    @Override
    public String toString() {
        return "RadioLogin{" +
                "flag=" + flag +
                ", result='" + result + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", useremailid='" + useremailid + '\'' +
                ", userdesignation='" + userdesignation + '\'' +
                ", ut='" + ut + '\'' +
                '}';
    }
}
