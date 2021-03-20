package app.jugadfunda.inquiryform.industryenquiry;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.validate.Validate;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnquiryFormFragment extends Fragment implements IndustryEnquiryView, View.OnClickListener {
    private ImplIndustryEnquiry mImplIndustryEnquiry;
    private TextInputEditText mEtCompanyName;
    private TextInputEditText mEtCompanyAddress;
    private TextInputEditText mEtOSPName;
    private TextInputEditText mEtCity;
    private TextInputEditText mEtPincode;
    private TextInputEditText mEtIndustryPanno;
    private TextInputEditText mEtCompanyEmailId;
    private TextInputEditText mEtCompanyContact;
    private EditText mEtEstablishmentYear;
    private TextInputEditText mEtNoofDept;
    private TextInputEditText mEtNoofEmp;
    private TextInputEditText mEtAnnualturnover;
    private TextInputEditText mEtBriefDesc;
    private TextInputEditText mEtKeywords;
    private TextInputEditText mEtAadharNo;
    private TextInputEditText mEtSecondaryemailid;
    private TextInputEditText mEtSecondaryContact;
    private TextInputEditText mEtEfficientInfra;
    private TextInputEditText mEtSalesPromotion;
    private TextInputEditText mEtOperations;
    private TextInputEditText mEtTechnologyRND;
    private TextInputEditText mEtContentMgmt;
    private TextInputEditText mEtItWeb;
    private View mItemView;
    private long mUserId;
    private String mModuleType;
    private DatePickerDialog mDatePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mItemView = inflater.inflate(R.layout.fragment_enquiry_form, container, false);
        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);
        mModuleType = sh.getString("mt","");

        setUI();

        return mItemView;
    }

    void setUI(){
        mImplIndustryEnquiry = new ImplIndustryEnquiry(getContext(), this);

        mEtCompanyName = mItemView.findViewById(R.id.et_companyname);
        mEtCompanyAddress = mItemView.findViewById(R.id.et_companyaddress);
        mEtOSPName = mItemView.findViewById(R.id.et_ospname);
        mEtCity = mItemView.findViewById(R.id.et_city);
        mEtPincode = mItemView.findViewById(R.id.et_pincode);
        mEtIndustryPanno = mItemView.findViewById(R.id.et_industrypanno);
        mEtCompanyEmailId = mItemView.findViewById(R.id.et_companyemailid);
        mEtCompanyContact = mItemView.findViewById(R.id.et_companycontact);
        mEtEstablishmentYear = mItemView.findViewById(R.id.et_establishyear);
        mEtNoofDept = mItemView.findViewById(R.id.et_noofdept);
        mEtNoofEmp = mItemView.findViewById(R.id.et_noofemp);
        mEtAnnualturnover = mItemView.findViewById(R.id.et_turnover);
        mEtBriefDesc = mItemView.findViewById(R.id.et_briefdescription);
        mEtKeywords = mItemView.findViewById(R.id.et_keywords);
        mEtAadharNo = mItemView.findViewById(R.id.et_aadhar);
        mEtSecondaryemailid = mItemView.findViewById(R.id.et_secondaryemailid);
        mEtSecondaryContact = mItemView.findViewById(R.id.et_secondarycontact);
        mEtEfficientInfra = mItemView.findViewById(R.id.et_efficientinfra);
        mEtSalesPromotion = mItemView.findViewById(R.id.et_salespromotion);
        mEtOperations = mItemView.findViewById(R.id.et_operation);
        mEtTechnologyRND = mItemView.findViewById(R.id.et_technology);
        mEtContentMgmt = mItemView.findViewById(R.id.et_contentmgmt);
        mEtItWeb = mItemView.findViewById(R.id.et_itweb);

        setListeners();

        setDateTimeField();
    }

    void setListeners(){
        mItemView.findViewById(R.id.btn_submit).setOnClickListener(this);
        mItemView.findViewById(R.id.et_establishyear).setOnClickListener(this);
    }

    @Override
    public void clearForm() {
        mEtCompanyName.setText("");
        mEtCompanyAddress.setText("");
        mEtOSPName.setText("");
        mEtCity.setText("");
        mEtPincode.setText("");
        mEtCompanyEmailId.setText("");
        mEtCompanyContact.setText("");
        mEtEstablishmentYear.setText("");
        mEtNoofDept.setText("");
        mEtNoofEmp.setText("");
        mEtIndustryPanno.setText("");
        mEtAnnualturnover.setText("");
        mEtBriefDesc.setText("");
        mEtKeywords.setText("");
        mEtAadharNo.setText("");
        mEtSecondaryemailid.setText("");
        mEtSecondaryContact.setText("");
        mEtEfficientInfra.setText("");
        mEtSalesPromotion.setText("");
        mEtOperations.setText("");
        mEtTechnologyRND.setText("");
        mEtContentMgmt.setText("");
        mEtItWeb.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit :
                String check = validateEnquiryForm(mEtCompanyName.getText().toString(), mEtCompanyAddress.getText().toString(), mEtOSPName.getText().toString(), mEtCity.getText().toString(), mEtPincode.getText().toString(), mEtIndustryPanno.getText().toString(), mEtCompanyEmailId.getText().toString(), mEtCompanyContact.getText().toString(),mEtEstablishmentYear.getText().toString(), mEtNoofDept.getText().toString(), mEtNoofEmp.getText().toString(), mEtAnnualturnover.getText().toString(), mEtBriefDesc.getText().toString(), mEtKeywords.getText().toString(), mEtSecondaryemailid.getText().toString(), mEtSecondaryContact.getText().toString(), mEtAadharNo.getText().toString(), mEtEfficientInfra.getText().toString(), mEtSalesPromotion.getText().toString(), mEtOperations.getText().toString(), mEtTechnologyRND.getText().toString(), mEtContentMgmt.getText().toString(), mEtItWeb.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(getContext(),""+check,Toast.LENGTH_LONG).show();
                } else {
                    mImplIndustryEnquiry.wsAddEnquiryData(mEtCompanyName.getText().toString(), mEtCompanyAddress.getText().toString(), mEtOSPName.getText().toString(), mEtCity.getText().toString(), Integer.parseInt(mEtPincode.getText().toString()), mEtIndustryPanno.getText().toString(), mEtCompanyEmailId.getText().toString(), mEtCompanyContact.getText().toString(), mEtEstablishmentYear.getText().toString(), Integer.parseInt(mEtNoofDept.getText().toString()), Integer.parseInt(mEtNoofEmp.getText().toString()), mEtAnnualturnover.getText().toString(), mEtBriefDesc.getText().toString(), mEtKeywords.getText().toString(), mEtAadharNo.getText().toString(), mEtSecondaryemailid.getText().toString(), mEtSecondaryContact.getText().toString(), mEtEfficientInfra.getText().toString(), mEtSalesPromotion.getText().toString(), mEtOperations.getText().toString(), mEtTechnologyRND.getText().toString(), mEtContentMgmt.getText().toString(), mEtItWeb.getText().toString(),mModuleType, mUserId);
                }
                break;
            case R.id.et_establishyear :
                mDatePickerDialog.show();
                break;
        }
    }

    String validateEnquiryForm(String companyname, String address, String oname, String city, String pincode,
                               String industrypanno, String cemailid, String ccontact,String estabyear, String noofdept, String noofemp,
                               String annualturnover, String businessdesc, String keys, String secondaryemailid, String secondarycontact,String aadhar,
                               String efficientinfra, String sales, String oandcm, String trndi, String cmandd, String itwebapps){
        if(!Pattern.matches(Validate.COMPANY_NAME,companyname)){
            return "Invalid Company Name, only uppercase, lowercase, numbers,-,_,.,' and Max 500 charatcers are allowed.\n For ex - Xyz12 Group's";
        }else if(address.length() > 500 || address.isEmpty()){
            return "Invalid Address, Address can't be empty and Max 500 characters are allowed";
        }else if(!Pattern.matches(Validate.COMPANY_OWNERNAME,oname)){
            return "Invalid Owner Name, First letter should be uppercase and only uppercase, lowercase,.,' and Max 150 charatcers are allowed.\n For ex - Mr's. Xyz12";
        }else if(city.length() > 100 || city.isEmpty()){
            return "Invalid City, Max 100 characters are allowed";
        }else if(!Pattern.matches(Validate.COMPANY_PINCODE,pincode)){
            return "Invalid Pincode, only numbers and Max 6 digit are allowed";
        }else if(!industrypanno.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_PANNO,industrypanno)){
                return "Invalid Pan Number";
            }
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN,cemailid)) {
                return "Invalid EmailId";
            }
        else if(!Pattern.matches(Validate.CONTACT_PATTERN,ccontact)) {
            return "Invalid Contact Number";

        }else if(estabyear.isEmpty()){
                return "Invalid Establishment Year";
        } else if(!Pattern.matches(Validate.NOOFDEPARTMENT,noofdept)){
                return "Invalid entry for Departments, only numbers and Max 3 digit are allowed";
        }else if(!Pattern.matches(Validate.NOOFEMPLOYEE,noofemp)){
                return "Invalid Entry for Employees, only numbers and Max 7 digit are allowed";
        }else if(!annualturnover.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_TURNOVER,annualturnover)) {
            return "Invalid Entry, only uppercase, lowercase,numbers.,' and Max 20 charatcers are allowed.\n For ex - 10 Lakh Rupees";
        }
        }else if(businessdesc.length() > 1000 || businessdesc.isEmpty()){
                return "Invalid Discription, Max 1000 characters are allowed";
        }else if(keys.length() > 500 || keys.isEmpty()){
                return "Invalid Keywords, Max 500 characters are allowed";
        }else if(!aadhar.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_AADHAR,aadhar)) {
            return "Invalid Aadhaar Card Number";
        }
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN,secondaryemailid)){
                return "Invalid Secondary EmailId";
        }else if(!secondarycontact.isEmpty()){
            if(!Pattern.matches(Validate.CONTACT_PATTERN,secondarycontact)) {
            return "Invalid Secondary Contact Number";
        }
        }else if(!efficientinfra.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_CHALLENGE,efficientinfra)) {
                return "Invalid Entry for Effiecient Infrastrcture, only uppercase, lowercase,numbers.,' and Max 1000 charatcers are allowed. For ex- Xys123.'-_";
            }
            }else if(!sales.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_CHALLENGE,sales)) {
                return "Invalid Entry for Sales/Promotion Branding/Business Development, only uppercase, lowercase,numbers.,' and Max 1000 charatcers are allowed. For ex- Xys123.'-_ ";
                }
            }else if(!oandcm.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_CHALLENGE,oandcm)) {
                return "Invalid Entry for Operations and Compliance Management, only uppercase, lowercase,numbers.,' and Max 1000 charatcers are allowed. For ex- Xys123.'-_";
                }
            }else if(!trndi.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_CHALLENGE,trndi)) {
            return "Invalid Entry for Technology/Rnd/Innovations, only uppercase, lowercase,numbers.,' and Max 1000 charatcers are allowed. For ex- Xys123.'-_";
                }
            }else if(!cmandd.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_CHALLENGE,cmandd)) {
            return "Invalid Entry for Contact Management and Documentation, only uppercase, lowercase,numbers.,' and Max 1000 charatcers are allowed. For ex- Xys123.'-_";
                }
            }else if(!itwebapps.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_CHALLENGE,itwebapps)) {
            return "Invalid Entry for IT (Web Presence and Apps), only uppercase, lowercase,numbers.,' and Max 1000 charatcers are allowed. For ex- Xys123.'-_";
                }
            }

        return "ok";
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                mEtEstablishmentYear.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
}
