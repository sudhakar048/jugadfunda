package app.jugadfunda.inquiryform.pojo;

import java.util.ArrayList;

public class Domains {
    private int domainid;
    private String code;
    private String domainname;
    private ArrayList<SubDomains>subdomains;

    public Domains(int domainid, String domainname) {
        this.domainid = domainid;
        this.domainname = domainname;
    }

    public Domains(int domainid, String code, String domainname, ArrayList<SubDomains> subdomains) {
        this.domainid = domainid;
        this.code = code;
        this.domainname = domainname;
        this.subdomains = subdomains;
    }

    public int getDomainid() {
        return domainid;
    }

    public void setDomainid(int domainid) {
        this.domainid = domainid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDomainName() {
        return domainname;
    }

    public void setDomainName(String domainname) {
        this.domainname = domainname;
    }

    public ArrayList<SubDomains> getSubdomains() {
        return subdomains;
    }

    public void setSubdomains(ArrayList<SubDomains> subdomains) {
        this.subdomains = subdomains;
    }

    @Override
    public String toString() {
        return "Domains{" +
                "domainid=" + domainid +
                ", code='" + code + '\'' +
                ", domaincode='" + domainname + '\'' +
                ", subdomains=" + subdomains +
                '}';
    }
}
