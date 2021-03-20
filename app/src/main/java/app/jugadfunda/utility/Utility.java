package app.jugadfunda.utility;

import java.util.ArrayList;

import app.jugadfunda.inquiryform.pojo.Domains;
import app.jugadfunda.inquiryform.pojo.SubDomains;

public class Utility {

    public static ArrayList<Domains> getProductServiceDomains(){
        //populating product / service domain
        ArrayList<Domains> productDomainList = new ArrayList<>();
        productDomainList.add(new Domains(1,"MFG-007", "Manufacturing and Engineering", null));

        ArrayList<SubDomains> smartLiving = new ArrayList<SubDomains>();
        smartLiving.add(new SubDomains(1, "Smart Village / city", false));
        smartLiving.add(new SubDomains(2, "Smart Transportation", false));
        smartLiving.add(new SubDomains(3, "Water Management", false));
        productDomainList.add(new Domains(2,"SMA-009", "Smart living", smartLiving));

        ArrayList<SubDomains> agriculture = new ArrayList<SubDomains>();
        agriculture.add(new SubDomains(4, "Agri Products (Regional)", false));
        agriculture.add(new SubDomains(5, "Agri Industry / Equipments", false));
        agriculture.add(new SubDomains(6, "Food Processing and Packaging", false));
        agriculture.add(new SubDomains(7, "Animal Husbandry", false));
        productDomainList.add(new Domains(3,"AGRI-001", "Agriculture", agriculture));

        ArrayList<SubDomains> wastemgmt = new ArrayList<SubDomains>();
        wastemgmt.add(new SubDomains(8, "Biodegradable Waste", false));
        wastemgmt.add(new SubDomains(9, "Non-Biodegradable Waste", false));
        wastemgmt.add(new SubDomains(10, "Plastic Waste", false));
        wastemgmt.add(new SubDomains(11, "E-Waste", false));
        wastemgmt.add(new SubDomains(12, "Medical waste", false));
        wastemgmt.add(new SubDomains(13, "Other waste", false));
        productDomainList.add(new Domains(4,"WAS-011", "Waste Management", wastemgmt));

        productDomainList.add(new Domains(5,"TEXT-010", "Textile", null));
        productDomainList.add(new Domains(6,"IT-006", "IT (Mobile and Web)", null));
        productDomainList.add(new Domains(7,"EDU-003", "Education / Skilling", null));
        productDomainList.add(new Domains(8,"MED-008", "Medical and Healthcare", null));
        productDomainList.add(new Domains(9,"GOV-005", "Governance", null));
        productDomainList.add(new Domains(10,"FIN-004", "Fin Tech / E-Commerce", null));
        productDomainList.add(new Domains(11,"BIOT-002", "Biotechnology", null));
        productDomainList.add(new Domains(12,"OTH-100", "Other Interdisciplinary", null));

        return productDomainList;
    }

}
