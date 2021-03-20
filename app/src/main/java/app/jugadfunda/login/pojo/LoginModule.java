package app.jugadfunda.login.pojo;

public class LoginModule {
    private int moduleicon;
    private String modulename;
    private String code;

    public LoginModule(int moduleicon, String modulename, String code) {
        this.moduleicon = moduleicon;
        this.modulename = modulename;
        this.code = code;
    }

    public int getModuleicon() {
        return moduleicon;
    }

    public void setModuleicon(int moduleicon) {
        this.moduleicon = moduleicon;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginModule{" +
                "moduleicon=" + moduleicon +
                ", modulename='" + modulename + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
