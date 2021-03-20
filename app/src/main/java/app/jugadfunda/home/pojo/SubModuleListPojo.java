package app.jugadfunda.home.pojo;

public class SubModuleListPojo {

    private String submodule;
    private int check = 0;

    public SubModuleListPojo(String submodule, int check) {
        this.submodule = submodule;
        this.check = check;
    }

    public String getSubmodule() {
        return submodule;
    }

    public void setSubmodule(String submodule) {
        this.submodule = submodule;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "SubModuleListPojo{" +
                "submodule='" + submodule + '\'' +
                ", check=" + check +
                '}';
    }
}
