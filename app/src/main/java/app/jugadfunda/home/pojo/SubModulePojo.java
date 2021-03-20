package app.jugadfunda.home.pojo;

public class SubModulePojo {

    private String submodulename;

    public SubModulePojo(String submodulename) {
        this.submodulename = submodulename;
    }

    public String getSubmodulename() {
        return submodulename;
    }

    public void setSubmodulename(String submodulename) {
        this.submodulename = submodulename;
    }

    @Override
    public String toString() {
        return "SubModulePojo{" +
                "submodulename='" + submodulename + '\'' +
                '}';
    }
}
