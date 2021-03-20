package app.jugadfunda.apiresponse;

public class LinkedIndustryList {
    private long industryid;
    private String industryname;
    private String industryaddress;
    private String industrycontact;
    private String contactperson;
    private String coemail;
    private String emailid;
    private String password;
    private String registrationstatus;

    public LinkedIndustryList(long industryid, String industryname, String industryaddress, String industrycontact, String contactperson, String coemail, String emailid, String password, String registrationstatus) {
        this.industryid = industryid;
        this.industryname = industryname;
        this.industryaddress = industryaddress;
        this.industrycontact = industrycontact;
        this.contactperson = contactperson;
        this.coemail = coemail;
        this.emailid = emailid;
        this.password = password;
        this.registrationstatus = registrationstatus;
    }

    public long getIndustryid() {
        return industryid;
    }

    public void setIndustryid(long industryid) {
        this.industryid = industryid;
    }

    public String getIndustryname() {
        return industryname;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public String getIndustryaddress() {
        return industryaddress;
    }

    public void setIndustryaddress(String industryaddress) {
        this.industryaddress = industryaddress;
    }

    public String getIndustrycontact() {
        return industrycontact;
    }

    public void setIndustrycontact(String industrycontact) {
        this.industrycontact = industrycontact;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getCoemail() {
        return coemail;
    }

    public void setCoemail(String coemail) {
        this.coemail = coemail;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationstatus() {
        return registrationstatus;
    }

    public void setRegistrationstatus(String registrationstatus) {
        this.registrationstatus = registrationstatus;
    }

    @Override
    public String toString() {
        return "LinkedIndustryList{" +
                "industryid=" + industryid +
                ", industryname='" + industryname + '\'' +
                ", industryaddress='" + industryaddress + '\'' +
                ", industrycontact='" + industrycontact + '\'' +
                ", contactperson='" + contactperson + '\'' +
                ", coemail='" + coemail + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", registrationstatus='" + registrationstatus + '\'' +
                '}';
    }
}
