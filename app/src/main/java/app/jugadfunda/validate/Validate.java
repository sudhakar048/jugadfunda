package app.jugadfunda.validate;

public class  Validate {
    //sign up validations
    public static String FIRSTNAME_PATTERN = "[A-Za-z.'\\s]{1,50}";
    public static String NAME_PATTERN = "[A-Za-z.'\\s]{1,150}";
    public static String EMAILID_PATTERN = "[^@\\s]+@[^@\\s]+\\.[^@\\s]+";
    public static String CONTACT_PATTERN = "[0-9]{10}";
    public static String PASSWORD_PATTERN = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*$";

    //capture idea validations
    public static String IDEA_DAY = "[A-Za-z0-9',-_.\\s]{1,15}";
    public static String TITLE_IDEA = "[A-Za-z0-9',-_.\\s]{1,200}";
    public static String IDEA_DESCRIPTION = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String IDEA_UNIQUE = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String IDEA_BETTER = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String IDEA_FUTUREPROOF = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String IDEA_TRIGGERED = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String IDEA_FUTUREPROOFOTHER = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String IDEA_PROBLEMSOLVING = "[A-Za-z0-9',-_.\\s]{1,500}";

    // add enquiry form
    public static String COMPANY_NAME = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String COMPANY_OWNERNAME = "^[A-Z][A-Za-z.'\\s]{1,150}";
    public static String COMPANY_PINCODE = "\\d{6}";
    public static String NOOFDEPARTMENT = "[0-9]{1,3}";
    public static String NOOFEMPLOYEE = "[0-9]{1,7}";
    public static String REGEX_CHALLENGE = "[A-Za-z0-9',-_.\\s]{0,1000}";
    public static String REGEX_PANNO="[A-Z]{5}[0-9]{4}[A-Z]{1}";
    public static String REGEX_TURNOVER = "[A-Za-z0-9',-_.\\s]{0,20}";
    public static String REGEX_AADHAR = "[0-9] {12}";

    // minutes of meeting
    public static String TITLE = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String LOCATION = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String ATTENDEES = "[A-Za-z0-9',-_.\\s]{1,500}";
    public static String STUDENT_NAME = "^[A-Z][A-Za-z.'\\ss]{1,150}";
    public static String REGEX_TIME = "[0-9:]{3,5}";


    //submit session report
    public static String NO_ATTENDEES = "[0-9]*";
    public static String REGEX_LINK="((http|ftp|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";

    //industry registration form
    public static String INDUSTRY_NAME = "[A-Za-z0-9'.\\s]{1,200}";
    public static String GSTN = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";

    public static String INSTITUTE_NAME = "[A-Za-z0-9',.\\s]{1,500}";

    public static String REGEX_QUIZ_CODE = "^(PQSIn21)[0-9]{7}";



}
