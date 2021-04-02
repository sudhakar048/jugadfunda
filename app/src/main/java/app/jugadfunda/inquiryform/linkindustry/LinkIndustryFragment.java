package app.jugadfunda.inquiryform.linkindustry;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.LinkedIndustryList;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;
import app.jugadfunda.inquiryform.adapter.CenterListAdapter;
import app.jugadfunda.inquiryform.adapter.DistrictListAdapter;
import app.jugadfunda.inquiryform.adapter.InstituteListAdapter;
import app.jugadfunda.inquiryform.adapter.LinkIndustryAdapter;
import app.jugadfunda.inquiryform.adapter.ProductDomainLinkIndustryAdapter;
import app.jugadfunda.inquiryform.adapter.StateListAdapter;
import app.jugadfunda.inquiryform.pojo.Domains;
import app.jugadfunda.inquiryform.pojo.SubDomains;
import app.jugadfunda.utility.Utility;
import app.jugadfunda.validate.Validate;

public class LinkIndustryFragment extends Fragment implements View.OnClickListener, LinkIndustryView {
    private TextInputEditText mTIETCompanyName;
    private TextInputEditText mTIETGSTNNumber;
    private EditText mETKey1;
    private EditText mETKey2;
    private EditText mETKey3;
    private EditText mETKey4;
    private EditText mETKey5;
    private EditText mETKey6;
    private EditText mETKey7;
    private EditText mETKey8;
    private EditText mETKey9;
    private EditText mETKey10;
    private Spinner mSpinnerProductService;
    private EditText mEditOtherProductServiceDomain;
    private TextInputEditText mTIETOwnerName;
    private TextInputEditText mTIETMobileNumber;
    private TextInputEditText mTIETEmailId;
    private TextInputEditText mTIETCoordinatorName;
    private TextInputEditText mTIETCoordinatorEmailId;
    private TextInputEditText mTIETCoordinatorContact;
    private Spinner mSpinnerState;
    private Spinner mSpinnerDistrict;
    private Spinner mSpinnerCenter;
    private Spinner mSpinnerInstitute;
    private TextInputEditText mTIETExtablishmentyear;
    private TextInputEditText mTIETNoofdept;
    private TextInputEditText mTIETnoofemp;
    private TextInputEditText mTIETannualturnover;
    private TextInputEditText mTIETofficeaddress;
    private TextInputEditText mTIETabtcompany;
    private TextInputEditText mTIETcity;
    private TextInputEditText mTIETpincode;
    private ArrayList<Domains> productDomainList;
    private ArrayList<SubDomains> smartLiving;
    private ArrayList<SubDomains> agriculture;
    private ArrayList<SubDomains> wastemgmt;
    private ProductDomainLinkIndustryAdapter productAdapter;
    private static String mProductDomainCode ="";
    private static String mSubDomainName = "";
    private int mProductPosition = 0;
    private ImplLinkIndustry mImplLinkIndustry = null;
    private ArrayList<StateList> mGlobalStateList = null;
    private StateListAdapter mStateListAdapter = null;
    private ArrayList<DistrictList> mGlobalDistrictList = null;
    private DistrictListAdapter mDistrictListAdapter = null;
    private ArrayList<CenterList> mGlobalCenterList = null;
    private CenterListAdapter mCenterListAdapter = null;
    private ArrayList<InstituteList> mGlobalInstituteList = null;
    private InstituteListAdapter mInstituteListAdapter = null;
    private ArrayList<LinkedIndustryList> mGlobalIndustryList = null;
    private RecyclerView mRecyclerView;
    private LinearLayout mLinearSearch;
    private LinearLayout mLinearAddUmb;
    private long mStateId = 0;
    private long mDistrictId = 0;
    private long mCenterId = 0;
    private long mInstituteId = 0;
    private String mInstituteName = "";
    private long mUserId = 0;
    private String mModuleType = "";
    private String keywords = "-";
    private String establishmentDate = "-";
    private int mYear;
    private int mMonth;
    private int mDay;
    private LinkIndustryAdapter industryAdapter = null;
    private String mGSTNNumber = "";
    private String mDept = "";
    private String mEmp = "";
    private String mAnnualTurnOver = "";
    private String mAbtCompany = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View iteView = inflater.inflate(R.layout.fragment_linkindustry, container, false);

        setUI(iteView);

        return iteView;
    }

    void setUI(View mView){
        mTIETCompanyName = mView.findViewById(R.id.et_companyname);
        mTIETGSTNNumber = mView.findViewById(R.id.et_gstn);
        mETKey1 = mView.findViewById(R.id.et_keyword1);
        mETKey2 = mView.findViewById(R.id.et_keyword2);
        mETKey3 = mView.findViewById(R.id.et_keyword3);
        mETKey4 = mView.findViewById(R.id.et_keyword4);
        mETKey5 = mView.findViewById(R.id.et_keyword5);
        mETKey6 = mView.findViewById(R.id.et_keyword6);
        mETKey7 = mView.findViewById(R.id.et_keyword7);
        mETKey8 = mView.findViewById(R.id.et_keyword8);
        mETKey9 = mView.findViewById(R.id.et_keyword9);
        mETKey10 = mView.findViewById(R.id.et_keyword10);
        mSpinnerProductService = mView.findViewById(R.id.spinnerproductservicedomaindomain);
        mEditOtherProductServiceDomain = mView.findViewById(R.id.et_otherproductdomain);
        mTIETOwnerName = mView.findViewById(R.id.et_ownername);
        mTIETMobileNumber = mView.findViewById(R.id.et_mobilenumber);
        mTIETEmailId = mView.findViewById(R.id.et_emailid);
        mTIETCoordinatorName = mView.findViewById(R.id.et_coordinatorname);
        mTIETCoordinatorEmailId = mView.findViewById(R.id.et_coordinatoremailid);
        mTIETCoordinatorContact = mView.findViewById(R.id.et_coordinatorcontact);
        mSpinnerState = mView.findViewById(R.id.spinnerstate);
        mSpinnerDistrict = mView.findViewById(R.id.spinnerdistrict);
        mSpinnerCenter = mView.findViewById(R.id.spinnercenter);
        mSpinnerInstitute = mView.findViewById(R.id.spinnerinstitute);
        mTIETExtablishmentyear = mView.findViewById(R.id.et_establishmentyear);
        mTIETNoofdept = mView.findViewById(R.id.et_noofdept);
        mTIETnoofemp = mView.findViewById(R.id.et_noofemp);
        mTIETannualturnover = mView.findViewById(R.id.et_annualturnover);
        mTIETofficeaddress = mView.findViewById(R.id.et_officeaddress);
        mTIETabtcompany = mView.findViewById(R.id.et_abtcompany);
        mTIETcity = mView.findViewById(R.id.et_city);
        mTIETpincode = mView.findViewById(R.id.et_pincode);
        mRecyclerView = mView.findViewById(R.id.recycler);
        mLinearSearch = mView.findViewById(R.id.linearsearch);
        mLinearAddUmb = mView.findViewById(R.id.linear_umbregistration);


        productDomainList = Utility.getProductServiceDomains();
        smartLiving = productDomainList.get(1).getSubdomains();
        agriculture = productDomainList.get(2).getSubdomains();
        wastemgmt = productDomainList.get(3).getSubdomains();

        productAdapter =  new ProductDomainLinkIndustryAdapter(productDomainList, getContext(), this);
        mSpinnerProductService.setAdapter(productAdapter);

        mImplLinkIndustry = new ImplLinkIndustry(getContext(), this);
        mImplLinkIndustry.wsGetStateList();


        //fetching userId and module type from localDB
        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);

        //finding list of enrolled industries
        mImplLinkIndustry.wsLinkedIndustry(mUserId, "JAM");

        //calling Listener
        setListener(mView);
    }
    String validateRegistrationForm(String companyName, String pancardnumber,  String ownerName, String mobileNumber, String emailId, String coordinatorName, String coMobileNumber, String coEmailId, String city, String pincode, String address){
        if(!Pattern.matches(Validate.INDUSTRY_NAME, companyName)){
            return "Invalid Industry Name, it can accept only Upper case, Lower case, Numbers, ., ' and Length can't exceed 200 characters";
        }else if(!pancardnumber.isEmpty() && !Pattern.matches(Validate.GSTN, pancardnumber)){
            return "Invalid GSTN Number";
        }else if(!Pattern.matches(Validate.COMPANY_OWNERNAME,  ownerName)){
            return "Invalid Industry Owner Name, it can accept only Upper case, Lower case, ., ' and Length can't exceed 150 characters";
        }else if(mobileNumber.trim().length() != 10){
            return "Invalid Mobile Number, it can be only number of 10 digit";
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN, emailId)){
            return "Invalid Email Id";
        }else if(!Pattern.matches(Validate.NAME_PATTERN, coordinatorName)){
            return "Invalid Co-ordinator Name, it can accept only Upper case, Lower case, . , ' and space";
        }else if(coMobileNumber.length() != 10){
            return "Invalid Co-ordinator Mobile Number, it can be only number of 10 digit";
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN, coEmailId)){
            return "Invalid Co-ordinator Email Id";
        }else if(mStateId == 0){
            return "Please select State";
        }else if(mDistrictId == 0){
            return "Please select District";
        }else if(mCenterId == 0){
            return "Please select Center";
        }else if(mInstituteId == 0){
            return "Please select Institute";
        }else if(address.length() > 500){
            return "Invalid Address, it can't be more than of 500 characters";
        }else if(city.length() > 100){
            return "Invalid City, it can't be more than of 100 characters";
        }else if(!Pattern.matches(Validate.COMPANY_PINCODE, pincode)){
            return "Invalid Pincode, it can't be more than of 6 digit";
        }
        return "ok";
    }
    void setListener(View mView){
        mView.findViewById(R.id.btn_submit).setOnClickListener(this);
        mView.findViewById(R.id.btn_reset).setOnClickListener(this);
        mView.findViewById(R.id.btn_back).setOnClickListener(this);
        mView.findViewById(R.id.btn_add).setOnClickListener(this);
        mView.findViewById(R.id.et_establishmentyear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                String check = validateRegistrationForm(mTIETCompanyName.getText().toString(), mTIETGSTNNumber.getText().toString(), mTIETOwnerName.getText().toString(), mTIETMobileNumber.getText().toString(), mTIETEmailId.getText().toString(), mTIETCoordinatorName.getText().toString(), mTIETCoordinatorContact.getText().toString(), mTIETCoordinatorEmailId.getText().toString(), mTIETcity.getText().toString(), mTIETpincode.getText().toString(), mTIETofficeaddress.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(getContext(),check, Toast.LENGTH_LONG).show();
                }else {
                    keywords = getKeyWords();
                    if(!mProductDomainCode.isEmpty()){
                        mProductDomainCode = mProductDomainCode.substring(0, mProductDomainCode.length() - 1);
                    }
                    if(!mSubDomainName.isEmpty()){
                        mSubDomainName = mSubDomainName.substring(0, mSubDomainName.length() - 1);
                    }
                    if(mTIETGSTNNumber.getText().toString().isEmpty()){
                        mGSTNNumber = "-";
                    }

                    if(mTIETNoofdept.getText().toString().isEmpty()){
                        mDept = "-";
                    }

                    if(mTIETnoofemp.getText().toString().isEmpty()){
                        mEmp = "-";
                    }

                    if(mTIETannualturnover.getText().toString().isEmpty()){
                       mAnnualTurnOver = "-";
                    }

                    if(mTIETabtcompany.getText().toString().isEmpty()){
                       mAbtCompany = "-";
                    }

                    mImplLinkIndustry.wsRegistration(mTIETCompanyName.getText().toString(), mGSTNNumber, mTIETOwnerName.getText().toString(),"+91",mTIETMobileNumber.getText().toString(), mTIETEmailId.getText().toString(),"JAM","Not Available",mTIETCoordinatorName.getText().toString(),"+91", mTIETCoordinatorContact.getText().toString(), mTIETCoordinatorEmailId.getText().toString(),"Not Available", establishmentDate, mDept, mEmp, mAnnualTurnOver, mTIETofficeaddress.getText().toString(), mAbtCompany, mTIETcity.getText().toString(), mTIETpincode.getText().toString(), "yes", mCenterId, mInstituteId, (int)mStateId, (int)mDistrictId, keywords, mInstituteName, mProductDomainCode, mSubDomainName, mUserId, 24);
                }
                break;

            case R.id.btn_reset:
                clearForm();
                break;

            case R.id.btn_back:
                showUMBList();
                break;

            case R.id.btn_add:
                showUMB();
                break;

            case R.id.et_establishmentyear:
                openCalendar();
                break;
        }
    }

    @Override
    public void clearForm() {
       mTIETCompanyName.setText("");
       mTIETGSTNNumber.setText("");
        mETKey1.setText("");
        mETKey2.setText("");
        mETKey3.setText("");
        mETKey4.setText("");
        mETKey5.setText("");
        mETKey6.setText("");
        mETKey7.setText("");
        mETKey8.setText("");
        mETKey9.setText("");
        mETKey10.setText("");
        mSpinnerProductService.setSelection(0);
        mTIETOwnerName.setText("");
        mTIETMobileNumber.setText("");
        mTIETEmailId.setText("");
        mTIETCoordinatorName.setText("");
        mTIETCoordinatorEmailId.setText("");
        mTIETCoordinatorContact.setText("");
        mSpinnerState.setSelection(0);
        mSpinnerDistrict.setSelection(0);
        mSpinnerCenter.setSelection(0);
        mSpinnerInstitute.setSelection(0);
        mTIETExtablishmentyear.setText("");
        mTIETNoofdept.setText("");
        mTIETnoofemp.setText("");
        mTIETannualturnover.setText("");
        mTIETofficeaddress.setText("");
        mTIETabtcompany.setText("");
        mTIETcity.setText("");
        mTIETpincode.setText("");
        keywords = "";
    }

    @Override
    public void selectProductServiceDomain(int check, int pos) {
        mProductPosition = pos;

        if(check == 1){
            if(!mProductDomainCode.contains(productDomainList.get(mProductPosition).getCode()+",")){
                mProductDomainCode += productDomainList.get(mProductPosition).getCode()+",";
            }
        }else {
            if(mProductDomainCode.contains(productDomainList.get(mProductPosition).getCode()+",")){
                mProductDomainCode = mProductDomainCode.replace(productDomainList.get(mProductPosition).getCode()+",","");
            }
        }

        Toast.makeText(getContext(), ""+mProductDomainCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void selectProductServiceSubDomain(int check, int position) {
        if(check == 1) {
            if(!mSubDomainName.contains(productDomainList.get(mProductPosition).getSubdomains().get(position).getSubid()+",")){
                mSubDomainName += productDomainList.get(mProductPosition).getSubdomains().get(position).getSubid()+",";
            }
        }else {
            if(mSubDomainName.contains(productDomainList.get(mProductPosition).getSubdomains().get(position).getSubid()+",")){
                mSubDomainName = mSubDomainName.replace(productDomainList.get(mProductPosition).getSubdomains().get(position).getSubid()+",","");
            }
         }
        Toast.makeText(getContext(),""+mSubDomainName,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAndHideProductServiceDomainEditText(int check, int position) {
        if(position == productDomainList.size() - 1 && check == 1){
            mEditOtherProductServiceDomain.setVisibility(View.VISIBLE);
        }else if (position == productDomainList.size() - 1 && check == 2){
            mEditOtherProductServiceDomain.setVisibility(View.GONE);
        }
    }

    @Override
    public void populateStateList(List<StateList> mStateList) {
        mGlobalStateList = (ArrayList<StateList>) mStateList;
        mStateListAdapter = new StateListAdapter(getContext(), mGlobalStateList, this);
        mSpinnerState.setAdapter(mStateListAdapter);
    }

    @Override
    public void populateDistrictList(List<DistrictList> mDistrictList) {
        mGlobalDistrictList = (ArrayList<DistrictList>) mDistrictList;
        mDistrictListAdapter = new DistrictListAdapter(getContext(), mGlobalDistrictList, this);
        mSpinnerDistrict.setAdapter(mDistrictListAdapter);
    }

    @Override
    public void populateCenterList(List<CenterList> mCenterList) {
        mGlobalCenterList = (ArrayList<CenterList>) mCenterList;
        mCenterListAdapter = new CenterListAdapter(getContext(), mGlobalCenterList, this);
        mSpinnerCenter.setAdapter(mCenterListAdapter);
    }

    @Override
    public void populateInstituteList(List<InstituteList> mInstituteList) {
        mGlobalInstituteList = (ArrayList<InstituteList>) mInstituteList;
        mInstituteListAdapter = new InstituteListAdapter(getContext(), mGlobalInstituteList, this);
        mSpinnerInstitute.setAdapter(mInstituteListAdapter);
    }

    @Override
    public void callStateList(int pos) {
        mStateId = mGlobalStateList.get(pos).getSid();
        mImplLinkIndustry.wsGetDistrictList(mStateId);
    }

    @Override
    public void callDistrictList(int pos) {
        mDistrictId =  mGlobalDistrictList.get(pos).getDid();
        mImplLinkIndustry.wsGetCenterList(mStateId, mDistrictId);
     }

    @Override
    public void callCenterList(int pos) {
        mCenterId = mGlobalCenterList.get(pos).getCenterid();
        mImplLinkIndustry.wsInstituteList(mCenterId);
    }

    @Override
    public void callInstituteList(int pos) {
        mInstituteId = mGlobalInstituteList.get(pos).getInstituteId();

    }

    @Override
    public void populateLinkedIndustries(List<LinkedIndustryList> mIndustryList) {
        mGlobalIndustryList = (ArrayList<LinkedIndustryList>) mIndustryList;
        if(!mGlobalIndustryList.isEmpty()){
            showUMBList();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            industryAdapter = new LinkIndustryAdapter(getContext(), mGlobalIndustryList, this);
            mRecyclerView.setAdapter(industryAdapter);
        }
    }

    @Override
    public void loadIndustryDetailsDialog(int position) {
        AlertDialog.Builder diaBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater li = this.getLayoutInflater();
        View view = li.inflate(R.layout.layout_industrydetails, null);
        diaBuilder.setView(view);

        ImageView mIvClose = view.findViewById(R.id.iv_close);
        TextView mTvCName = view.findViewById(R.id.tv_companyname);
        TextView mTvAddress = view.findViewById(R.id.tv_address);
        TextView mTvCoName = view.findViewById(R.id.tv_coname);
        TextView mTvCoMobile = view.findViewById(R.id.tv_comobilenumber);
        TextView mTvCoEmailId = view.findViewById(R.id.tv_coemailid);


        //setting data
        mTvCName.setText(mGlobalIndustryList.get(position).getIndustryname());
        mTvAddress.setText(mGlobalIndustryList.get(position).getIndustryaddress());
        mTvCoName.setText(mGlobalIndustryList.get(position).getContactperson());
        mTvCoMobile.setText(mGlobalIndustryList.get(position).getIndustrycontact());
        mTvCoEmailId.setText(mGlobalIndustryList.get(position).getCoemail());
        AlertDialog alertDialog = diaBuilder.create();
        alertDialog.show();

        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void connectIndustry(int position) {

    }

    @Override
    public void showUMB() {
        mLinearAddUmb.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mLinearSearch.setVisibility(View.GONE);
    }

    @Override
    public void showUMBList() {
        mLinearAddUmb.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mLinearSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshAdapter(long mIndustryId, String mIndustryName, String mAddress, String mIndustryContact, String mContactPerson, String mCoEmailId, String mEmailId, String mPassword, String mRegistrationStatus) {
        mGlobalIndustryList.add(new LinkedIndustryList(mIndustryId, mIndustryName, mAddress, mIndustryContact, mContactPerson, mCoEmailId, mEmailId, mPassword, mRegistrationStatus));
        industryAdapter.notifyDataSetChanged();
        mRecyclerView.postInvalidate();
        mRecyclerView.setAdapter(industryAdapter);

        showUMBList();
    }


    String getKeyWords(){
        if(!mETKey1.getText().toString().isEmpty()){
            keywords += mETKey1.getText().toString()+",";
        }
        if(!mETKey2.getText().toString().isEmpty()){
            keywords += mETKey2.getText().toString()+",";
        }
        if(!mETKey3.getText().toString().isEmpty()){
            keywords += mETKey3.getText().toString()+",";
        }
        if(!mETKey4.getText().toString().isEmpty()){
            keywords += mETKey4.getText().toString()+",";
        }
        if(!mETKey5.getText().toString().isEmpty()){
            keywords += mETKey5.getText().toString()+",";
        }
        if(!mETKey6.getText().toString().isEmpty()){
            keywords += mETKey6.getText().toString()+",";
        }
        if(!mETKey7.getText().toString().isEmpty()){
            keywords += mETKey7.getText().toString()+",";
        }
        if(!mETKey8.getText().toString().isEmpty()){
            keywords += mETKey8.getText().toString()+",";
        }
        if(!mETKey9.getText().toString().isEmpty()){
            keywords += mETKey9.getText().toString()+",";
        }
        if(!mETKey10.getText().toString().isEmpty()){
            keywords += mETKey10.getText().toString()+",";
        }
        return keywords;
    }


    private void openCalendar() {
        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            String date = getDateString(year, month, dayOfMonth);
            establishmentDate = date.split("/")[1]+"/"+date.split("/")[0]+"/"+date.split("/")[2];
            mTIETExtablishmentyear.setText(date);
        }, mYear, mMonth, mDay);
        dialog.show();

    }

    private String getDateString(int year, int mMonth, int mDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, mMonth, mDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(calendar.getTime());
    }
}
