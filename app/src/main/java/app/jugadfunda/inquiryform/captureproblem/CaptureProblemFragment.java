package app.jugadfunda.inquiryform.captureproblem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;

import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.inquiryform.adapter.ProductDomainSpinnerAdpater;
import app.jugadfunda.inquiryform.adapter.SubDomainSpinnerAdapter;
import app.jugadfunda.inquiryform.pojo.Domains;
import app.jugadfunda.inquiryform.pojo.SubDomains;
import app.jugadfunda.utility.Utility;

public class CaptureProblemFragment extends Fragment implements View.OnClickListener, CaptureProblemInterfaceView, CompoundButton.OnCheckedChangeListener {
    private TextInputEditText mTIETWhatProblem;
    private TextInputEditText mTIETWhyProblem;
    private TextInputEditText mTIETWhereProblem;
    private TextInputEditText mTIETWhoImpacted;
    private TextInputEditText mTIETAware;
    private TextInputEditText mTIETObserve;
    private TextInputEditText mTIETObservePeriod;
    private TextInputEditText mTIETIdeal;
    private TextInputEditText mTIETReality;
    private TextInputEditText mTIETConseuences;
    private TextInputEditText mTIETOutcomes;
    private TextInputEditText mTIETResources;
    private TextInputEditText mTIETExpedSkills;
    private EditText mEtKeyword1;
    private EditText mEtKeyword2;
    private EditText mEtKeyword3;
    private EditText mEtKeyword4;
    private EditText mEtKeyword5;
    private EditText mEtKeyword6;
    private EditText mEtKeyword7;
    private EditText mEtKeyword8;
    private EditText mEtKeyword9;
    private EditText mEtKeyword10;
    private EditText mEtNoofApplicants;
    private Spinner mSpinnerSkillReq;
    private Spinner mSpinnerGraduationRel;
    private Spinner mSpinnerPriorityRel;
    private Spinner mSpinnerBusinessDomain;
    private Spinner mSpinnerProductServiceDomain;
    private EditText mEditOtherBusinessDomain;
    private EditText mEditOtherProductServiceDomain;
    private CheckBox mCheckConsultancy;
    private CheckBox mCheckInternship;
    private CheckBox mCheckProject;
    private ArrayList<SubDomains>businessList;
    private ArrayList<Domains> productDomainList;
    private ImplCaptureProblem mImplCaptureProblem;
    private long mUserId;
    private String mType;

    private ArrayList<Integer> mBusinessIds;
    private ArrayList<String> mProductDomainCode;
    private ArrayList<String> mProductSubDomain;
    private ArrayList<Integer> mCategory;

    private ArrayList<SubDomains> smartLiving;
    private ArrayList<SubDomains> agriculture;
    private ArrayList<SubDomains> wastemgmt;
    private SubDomainSpinnerAdapter subDomainAdapter;
    private ProductDomainSpinnerAdpater productAdapter;
    private int mProductPosition = 0;
    private int mBusinessPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_captureproblem, container, false);

        setUI(view);

        return view;
    }

    void setUI(View itemView){
        mTIETWhatProblem = itemView.findViewById(R.id.et_whatisproblem);
        mTIETWhyProblem = itemView.findViewById(R.id.et_whyisitproblem);
        mTIETWhereProblem = itemView.findViewById(R.id.et_whereobserved);
        mTIETWhoImpacted = itemView.findViewById(R.id.et_whoimpacted);
        mTIETAware = itemView.findViewById(R.id.et_aware);
        mTIETObserve = itemView.findViewById(R.id.et_observe);
        mTIETObservePeriod = itemView.findViewById(R.id.et_observedurtion);
        mTIETIdeal = itemView.findViewById(R.id.et_ideal);
        mTIETReality = itemView.findViewById(R.id.et_reality);
        mTIETConseuences = itemView.findViewById(R.id.et_conseuences);
        mTIETOutcomes = itemView.findViewById(R.id.et_outcomes);
        mTIETResources = itemView.findViewById(R.id.et_resourcesavailable);
        mTIETExpedSkills = itemView.findViewById(R.id.et_expectedskills);
        mEtKeyword1 = itemView.findViewById(R.id.et_keyword1);
        mEtKeyword2 = itemView.findViewById(R.id.et_keyword2);
        mEtKeyword3 = itemView.findViewById(R.id.et_keyword3);
        mEtKeyword4 = itemView.findViewById(R.id.et_keyword4);
        mEtKeyword5 = itemView.findViewById(R.id.et_keyword5);
        mEtKeyword6 = itemView.findViewById(R.id.et_keyword6);
        mEtKeyword7 = itemView.findViewById(R.id.et_keyword7);
        mEtKeyword8 = itemView.findViewById(R.id.et_keyword8);
        mEtKeyword9 = itemView.findViewById(R.id.et_keyword9);
        mEtKeyword10 = itemView.findViewById(R.id.et_keyword10);
        mEtNoofApplicants = itemView.findViewById(R.id.et_numberofapplicants);
        mSpinnerSkillReq = itemView.findViewById(R.id.spinnerskillrequirement);
        mSpinnerGraduationRel = itemView.findViewById(R.id.spinnergraduation_relevance);
        mSpinnerPriorityRel = itemView.findViewById(R.id.spinnerpriority_relevance);
        mSpinnerBusinessDomain = itemView.findViewById(R.id.spinnerbusinessdomain);
        mSpinnerProductServiceDomain = itemView.findViewById(R.id.spinnerproductservicedomain);
        mEditOtherBusinessDomain = itemView.findViewById(R.id.et_otherbusinessdomain);
        mEditOtherProductServiceDomain = itemView.findViewById(R.id.et_otherproductdomain);
        mCheckConsultancy = itemView.findViewById(R.id.check_consultancyoppor);
        mCheckInternship = itemView.findViewById(R.id.check_internshipoppor);
        mCheckProject = itemView.findViewById(R.id.check_projectoppor);

        productDomainList = Utility.getProductServiceDomains();
        smartLiving = productDomainList.get(1).getSubdomains();
        agriculture = productDomainList.get(2).getSubdomains();
        wastemgmt = productDomainList.get(3).getSubdomains();

        productAdapter =  new ProductDomainSpinnerAdpater(productDomainList, getContext(), this);
        mSpinnerProductServiceDomain.setAdapter(productAdapter);

        //populating business domain
        businessList = new ArrayList<>();
        businessList.add(new SubDomains(1, "Accidents, Safety, Security,& Precautions", false));
        businessList.add(new SubDomains(2, "Accounting/Tax/Tax audits & related compliance", false));
        businessList.add(new SubDomains(3, "Buisness Operations & Execution", false));
        businessList.add(new SubDomains(4, "Business Developments, /Marketing and Promotions", false));
        businessList.add(new SubDomains(5, "Business Opportunities", false));
        businessList.add(new SubDomains(6, "Customer Feedback & Customer Relation", false));
        businessList.add(new SubDomains(7, "Digital Marketing and Social Media", false));
        businessList.add(new SubDomains(8, "Employee Training, Advance Technical Training", false));
        businessList.add(new SubDomains(9, "Funds & Finances", false));
        businessList.add(new SubDomains(10, "Govt. Schmes Applications, Tendor Document", false));
        businessList.add(new SubDomains(11, "Infrastructure", false));
        businessList.add(new SubDomains(12, "IT Solutions/ Mobile App /web Solutions, websites", false));
        businessList.add(new SubDomains(13, "Job /Services Problem", false));
        businessList.add(new SubDomains(14, "Office Administration Process", false));
        businessList.add(new SubDomains(15, "Policy Document Creations", false));
        businessList.add(new SubDomains(16, "Pollution Compliance & Improvements", false));
        businessList.add(new SubDomains(17, "Total Quality Assurance", false));
        businessList.add(new SubDomains(18, "Workshop Operations", false));
        businessList.add(new SubDomains(19, "Other Interdisciplinary", false));

        subDomainAdapter = new SubDomainSpinnerAdapter(getContext(), businessList, this);
        mSpinnerBusinessDomain.setAdapter(subDomainAdapter);


        //populate Skill Requirements
        ArrayList<String> skillReList = new ArrayList<>();
        skillReList.add("Select Skill");
        skillReList.add("Unskilled");
        skillReList.add("Semi Skilled");
        skillReList.add("Skilled");
        skillReList.add("Intrapreneur");

        ArrayAdapter<String>skillAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, skillReList);
        mSpinnerSkillReq.setAdapter(skillAdapter);


        //populate Graduation Relevance
        ArrayList<String> graduationRevList = new ArrayList<>();
        graduationRevList.add("Select Graduation Relevance");
        graduationRevList.add("Below 10th Pass");
        graduationRevList.add("10th or 12th Pass");
        graduationRevList.add("Graduate");
        graduationRevList.add("Post Graduate");

        ArrayAdapter<String>graduationAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, graduationRevList);
        mSpinnerGraduationRel.setAdapter(graduationAdapter);

        //populate Priority Relevance
        ArrayList<String> priorityRevList = new ArrayList<>();
        priorityRevList.add("Select Priority Relevance");
        priorityRevList.add("Day to Day");
        priorityRevList.add("Short Term");
        priorityRevList.add("Mid Term");
        priorityRevList.add("Long Term");

        ArrayAdapter<String>priorityAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, priorityRevList);
        mSpinnerPriorityRel.setAdapter(priorityAdapter);


        mBusinessIds = new ArrayList<>();
        mProductDomainCode = new ArrayList<>();;
        mProductSubDomain = new ArrayList<>();;
        mCategory = new ArrayList<>();;

        //calling listner function
        setListener(itemView);

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);
        mType = sh.getString("mt","");

        mImplCaptureProblem = new ImplCaptureProblem(getContext(), this);
    }

    void setListener(View itemView){
        itemView.findViewById(R.id.btn_reset).setOnClickListener(this);
        itemView.findViewById(R.id.btn_submit).setOnClickListener(this);
        mCheckConsultancy.setOnCheckedChangeListener(this);
        mCheckInternship.setOnCheckedChangeListener(this);
        mCheckProject.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:

                String check  = validateCaptureProblemForm(mEtKeyword1.getText().toString(), mEtKeyword2.getText().toString(), mEtKeyword3.getText().toString(), mTIETWhatProblem.getText().toString(), mTIETWhyProblem.getText().toString(), mTIETWhereProblem.getText().toString(), mTIETWhoImpacted.getText().toString(), mTIETAware.getText().toString(), mTIETObserve.getText().toString(),mTIETObservePeriod.getText().toString(),mTIETIdeal.getText().toString(),mTIETReality.getText().toString(),mTIETConseuences.getText().toString(),mTIETOutcomes.getText().toString(),mTIETResources.getText().toString(),mTIETExpedSkills.getText().toString(), mSpinnerSkillReq.getSelectedItem().toString(), mSpinnerGraduationRel.getSelectedItem().toString(), mSpinnerPriorityRel.getSelectedItem().toString(), mEtNoofApplicants.getText().toString(),mEditOtherBusinessDomain.getText().toString(), mEditOtherProductServiceDomain.getText().toString(), mProductPosition, mBusinessPosition);
                if(!check.equals("ok")){
                    Toast.makeText(getContext(), check, Toast.LENGTH_LONG).show();
                }else{
                    String keywords = getKeywords(mEtKeyword1,mEtKeyword2,mEtKeyword3,mEtKeyword4,mEtKeyword5,mEtKeyword6,mEtKeyword7,mEtKeyword8,mEtKeyword9,mEtKeyword10);

                    JSONArray category = new JSONArray(mCategory);
                    JSONArray businessid = new JSONArray(mBusinessIds);
                    JSONArray productioncode = new JSONArray(mProductDomainCode);
                    JSONArray productsubdomain = new JSONArray(mProductSubDomain);

                    mImplCaptureProblem.wsCaptureProblem(mUserId, keywords, mTIETWhatProblem.getText().toString(), mTIETWhyProblem.getText().toString(), mTIETWhereProblem.getText().toString(), mTIETWhoImpacted.getText().toString(), mTIETAware.getText().toString(), mTIETObserve.getText().toString(), mTIETObservePeriod.getText().toString(),mTIETIdeal.getText().toString(),mTIETReality.getText().toString(),mTIETConseuences.getText().toString(),mTIETOutcomes.getText().toString(),mTIETResources.getText().toString(),mTIETExpedSkills.getText().toString(), mSpinnerSkillReq.getSelectedItem().toString(), mSpinnerGraduationRel.getSelectedItem().toString(), mSpinnerPriorityRel.getSelectedItem().toString(), Integer.parseInt(mEtNoofApplicants.getText().toString()),category.toString(), businessid.toString(), productioncode.toString(),productsubdomain.toString() , mType,mEditOtherBusinessDomain.getText().toString(), mEditOtherProductServiceDomain.getText().toString(),"Submit");
                }
                break;

            case R.id.btn_reset:
              resetCaptureProblemForm();
                break;
        }
    }

    @Override
    public void showAndHideBusinessDomainEditText(int check, int pos) {
        if(pos == businessList.size() - 1 && check == 1){
            mEditOtherBusinessDomain.setVisibility(View.VISIBLE);
        }else if (pos == businessList.size() - 1 && check == 2){
            mEditOtherBusinessDomain.setVisibility(View.GONE);
        }
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
    public void selectBusinessDomain(int check, int pos) {
        mBusinessPosition = pos;
        boolean flag = false;
        if(check == 1){
            if(!mBusinessIds.contains(businessList.get(pos).getSubid())){
                mBusinessIds.add(businessList.get(pos).getSubid());
            }
            flag = true;
        }else {
            if(mBusinessIds.contains(businessList.get(pos).getSubid())){
                mBusinessIds.remove((Object)businessList.get(pos).getSubid());
            }
            flag = false;
        }
        Toast.makeText(getContext(), ""+mBusinessIds, Toast.LENGTH_SHORT).show();
       /* SubDomains subDomains = businessList.get(pos);
        subDomains.setFlag(flag);
        businessList.set(pos,subDomains);

        subDomainAdapter.notifyDataSetChanged();
        mSpinnerBusinessDomain.setAdapter(subDomainAdapter);*/
    }

    @Override
    public void selectProductServiceDomain(int check, int pos) {
        mProductPosition = pos;
        boolean flag = false;
        if(check == 1){
            if(!mProductDomainCode.contains(productDomainList.get(mProductPosition).getCode())){
                mProductDomainCode.add(productDomainList.get(mProductPosition).getCode());
            }
            flag = true;
        }else {
            if(mProductDomainCode.contains(productDomainList.get(mProductPosition).getCode())){
               mProductDomainCode.remove(productDomainList.get(mProductPosition).getCode());
            }
            flag = false;
        }

        Toast.makeText(getContext(), ""+mProductDomainCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void selectProductServiceSubDomain(int check, int position) {
        String subDomainName = "";
        if(mProductPosition == 1){
            subDomainName = smartLiving.get(position).getSubname();
        }else if(mProductPosition == 2){
            subDomainName = agriculture.get(position).getSubname();
        }else if(mProductPosition == 3){
            subDomainName = wastemgmt.get(position).getSubname();
        }

        if(check == 1 && !mProductSubDomain.contains((subDomainName+":"+productDomainList.get(mProductPosition).getDomainid()))){
            mProductSubDomain.add(subDomainName+":"+productDomainList.get(mProductPosition).getDomainid());
        }else if(check == 2 && mProductSubDomain.contains((subDomainName+":"+productDomainList.get(mProductPosition).getDomainid()))){
            mProductSubDomain.remove(subDomainName+":"+productDomainList.get(mProductPosition).getDomainid());
        }
        Toast.makeText(getContext(),""+mProductSubDomain,Toast.LENGTH_LONG).show();
    }

    @Override
    public void resetCaptureProblemForm() {
        mTIETWhatProblem.setText("");
        mTIETWhyProblem.setText("");
        mTIETWhereProblem.setText("");
        mTIETWhoImpacted.setText("");
        mTIETAware.setText("");
        mTIETObserve.setText("");

        mTIETObservePeriod.setText("");
        mTIETIdeal.setText("");
        mTIETReality.setText("");
        mTIETConseuences.setText("");
        mTIETOutcomes.setText("");
        mTIETResources.setText("");
        mTIETExpedSkills.setText("");

        mEtNoofApplicants.setText("");

        mEtKeyword1.setText("");
        mEtKeyword2.setText("");
        mEtKeyword3.setText("");
        mEtKeyword4.setText("");
        mEtKeyword5.setText("");
        mEtKeyword6.setText("");
        mEtKeyword7.setText("");
        mEtKeyword8.setText("");
        mEtKeyword9.setText("");
        mEtKeyword10.setText("");

        mSpinnerSkillReq.setSelection(0);
        mSpinnerGraduationRel.setSelection(0);
        mSpinnerPriorityRel.setSelection(0);

        mSpinnerBusinessDomain.invalidate();
        mSpinnerBusinessDomain.setAdapter(subDomainAdapter);

        mSpinnerProductServiceDomain.invalidate();
        mSpinnerProductServiceDomain.setAdapter(productAdapter);

        mEditOtherBusinessDomain.setText("");
        mEditOtherProductServiceDomain.setText("");

        mCheckConsultancy.setChecked(false);
        mCheckInternship.setChecked(false);
        mCheckProject.setChecked(false);

        mBusinessIds.clear();
        mProductDomainCode.clear();
        mProductSubDomain.clear();
        mCategory.clear();
    }

    String validateCaptureProblemForm(String mKeyWord1, String mKeyWord2, String mKeyWord3, String mWhatcp, String mWhycp, String mWherecp, String mWhocp, String mWhencp, String mHowcp, String mObservecp, String mIdeal, String mReality, String mConsequences, String mOutcome, String mResources, String mSkills, String mSkillreq, String mGraduation, String mPriority, String mMaxapplicants, String mBdomainother, String mPsdomainother, int businessPos, int productPos){
        if(mWhatcp.isEmpty()){
            return "What is the problem? can't be empty";
        }else if(mWhatcp.length() > 1000){
            return "What is the problem? can't exceed 1000 charaters";
        }else if(mWhycp.isEmpty()){
            return "Why is it a problem? can't be empty";
        }else if(mWhycp.length() > 2000){
            return "Why is it a problem? can't exceed 2000 charaters";
        }else if(mWherecp.isEmpty()){
            return "Where is this problem observed? can't be empty";
        }else if(mWherecp.length() > 1000){
            return "Where is this problem observed? can't exceed 2000 charaters";
        }else if(mWhocp.isEmpty()){
            return "Who is impacted by this problem? can't be empty";
        }else if(mWhocp.length() > 1000){
            return "Who is impacted by this problem? can't exceed 2000 charaters";
        }else if(mWhencp.isEmpty()){
            return "When did you first become aware of this problem? can't be empty";
        }else if(mWhencp.length() > 2000){
            return "When did you first become aware of this problem? can't exceed 2000 charaters";
        }else if(mHowcp.isEmpty()){
            return "How do you observe this problem? What are the symptoms associated with the problem? can't be empty";
        }else if(mHowcp.length() > 2000){
            return "How do you observe this problem? What are the symptoms associated with the problem? can't exceed 2000 charaters";
        }else if(mKeyWord1.isEmpty()) {
            return "Keyword 1 can't be empty";
        }else if(mKeyWord2.isEmpty()) {
            return "Keyword 2 can't be empty";
        }else if(mKeyWord3.isEmpty()) {
            return "Keyword 3 can't be empty";
        }else if(mObservecp.isEmpty()){
            return "How often do you observe the problem? (Daily, Weekly, Monthly...is it becoming more frequent) can't be empty";
        }else if(mObservecp.length() > 2000){
            return "How often do you observe the problem? (Daily, Weekly, Monthly...is it becoming more frequent) can't exceed 2000 charaters";
        }else if(mBusinessIds.size() < 1){
            return "Please select at least one Business Domain";
        }else if(mProductDomainCode.size() < 1){
            return "Please select at least one Product/Service Domain";
        }else if(businessPos == businessList.size() - 1 && mBdomainother.isEmpty()){
            return "Other Business Interdisciplinary Domain can't be empty";
        }else if(businessPos == businessList.size() - 1 && mBdomainother.length() > 500){
            return "Other Business Interdisciplinary Domain can't exceed 500 charaters";
        }else if(productPos == productDomainList.size() - 1 && mPsdomainother.isEmpty()){
            return "Other Product/Service Interdisciplinary Domain can't be empty";
        }else if(productPos == productDomainList.size() - 1 && mPsdomainother.length() > 500){
            return "Other Product/Service Interdisciplinary Domain can't exceed 500 charaters";
        }else if(mSkillreq.toLowerCase().trim().equals("select skill")){
            return "Please Select at least one Skill Requirement";
        }else if(mSkillreq.isEmpty()){
            return "Please Select at least one Skill";
        }else if(mGraduation.toLowerCase().trim().equals("select graduation relevance")){
            return "Please Select at least one Graduation Relevance";
        }else if(mGraduation.isEmpty()){
            return "Please Select at least one Graduation Relevance";
        }else if(mPriority.toLowerCase().trim().equals("select priority relevance")){
            return "Please Select at least one Priority Relevance";
        }else if(mPriority.isEmpty()){
            return "Please Select at least one Priority Relevance";
        }else if(mIdeal.isEmpty()){
            return "Ideal can't be empty";
        }else if(mIdeal.length() > 1000){
            return "Ideal exceed 1000 charaters";
        }else if(mReality.isEmpty()){
            return "Reality can't be empty";
        }else if(mReality.length() > 1000){
            return "Reality can't exceed 1000 charaters";
        }else if(mConsequences.isEmpty()){
            return "Consequences can't be empty";
        }else if(mConsequences.length() > 1000){
            return "Consequences can't exceed 1000 charaters";
        }else if(mOutcome.isEmpty()){
            return "Measurable Outcomes can't be empty";
        }else if(mOutcome.length() > 1000){
            return "Measurable Outcomes can't exceed 1000 charaters";
        }else if(mResources.isEmpty()){
            return "Resources Available can't be empty";
        }else if(mResources.length() > 1000){
            return "Resources Available can't exceed 1000 charaters";
        }else if(mSkills.isEmpty()){
            return "Expected Skills can't be empty";
        }else if(mSkills.length() > 1000){
            return "Expected Skills can't exceed 1000 charaters";
        }else if(mMaxapplicants.isEmpty()){
            return "Number of Applicant can't be empty";
        }else if(Integer.parseInt(mMaxapplicants) <= 0){
            return "Invalid entry for Number of Applicants";
        }else if(Integer.parseInt(mMaxapplicants) > 50){
            return "Number of Applicants can't be more than 50";
        }else if(mCategory.size() < 1){
            return "Please select at least one Category";
        }
        return "ok";
    }

    String getKeywords(EditText mKey1, EditText mKey2, EditText mKey3, EditText mKey4, EditText mKey5, EditText mKey6, EditText mKey7, EditText mKey8, EditText mKey9, EditText mKey10){
        String keywords = "";
        if(!mKey1.getText().toString().isEmpty()){
            keywords += mKey1.getText().toString()+", ";
        }
        if(!mKey2.getText().toString().isEmpty()){
            keywords += mKey2.getText().toString()+", ";
        }
        if(!mKey3.getText().toString().isEmpty()){
            keywords += mKey3.getText().toString()+", ";
        }
        if(!mKey4.getText().toString().isEmpty()){
            keywords += mKey4.getText().toString()+", ";
        }
        if(!mKey5.getText().toString().isEmpty()){
            keywords += mKey5.getText().toString()+", ";
        }
        if(!mKey6.getText().toString().isEmpty()){
            keywords += mKey6.getText().toString()+", ";
        }
        if(!mKey7.getText().toString().isEmpty()){
            keywords += mKey7.getText().toString()+", ";
        }
        if(!mKey8.getText().toString().isEmpty()){
            keywords += mKey8.getText().toString()+", ";
        }
        if(!mKey9.getText().toString().isEmpty()){
            keywords += mKey9.getText().toString()+", ";
        }
        if(!mKey10.getText().toString().isEmpty()){
            keywords += mKey10.getText().toString()+", ";
        }

        keywords = keywords.substring(0, keywords.length()-2);
        return keywords;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.check_consultancyoppor:
                if(isChecked){
                    mCategory.add(1);
                }else{
                    mCategory.remove((Object)1);
                }
                Toast.makeText(getContext(),""+mCategory,Toast.LENGTH_LONG).show();
                break;
            case R.id.check_internshipoppor:
                if(isChecked){
                    mCategory.add(2);
                }else{
                    mCategory.remove((Object)2);
                }
                Toast.makeText(getContext(),""+mCategory,Toast.LENGTH_LONG).show();
                break;

            case R.id.check_projectoppor:
                if(isChecked){
                    mCategory.add(3);
                }else{
                    mCategory.remove((Object)3);
                }
                Toast.makeText(getContext(),""+mCategory,Toast.LENGTH_LONG).show();
                break;

        }
    }
}
