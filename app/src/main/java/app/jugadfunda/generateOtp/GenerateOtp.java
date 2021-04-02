package app.jugadfunda.generateOtp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;
import app.jugadfunda.inquiryform.adapter.CenterListAdapter;
import app.jugadfunda.inquiryform.adapter.DistrictListAdapter;
import app.jugadfunda.inquiryform.adapter.InstituteListAdapter;
import app.jugadfunda.inquiryform.adapter.StateListAdapter;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.questions.StartQuizActivity;
import app.jugadfunda.validate.Validate;

public class GenerateOtp extends AppCompatActivity implements View.OnClickListener, GenerateOtpView {
    private EditText mEtfirstname;
    private EditText mEtmiddlename;
    private EditText mEtlastname;
    private EditText mEtemailid;
    private EditText mEtdob;
    private EditText mEtmobilenumber;
    private Spinner mSpinnerState;
    private Spinner mSpinnerDistrict;
    private Spinner mSpinnerCenter;
    private Spinner mSpinnerInstitute;
    private ImplGenerateOtpPresenter mImplGenerateOtpPresenter = null;
    private long quizId = 0;
    private String title = "";
    private ArrayList<StateList> mStateLists;
    private ArrayList<DistrictList> mDistrictLists;
    private ArrayList<CenterList> mCenterLists;
    private ArrayList<InstituteList> mInstituteLists;
    private String gender = "";
    private int stateid = 0;
    private int districtid = 0;
    private long centerid = 0;
    private long instituteid = 0;
    private StateListAdapter mStateListAdapter = null;
    private DistrictListAdapter mDistrictListAdapter = null;
    private CenterListAdapter mCenterListAdapter = null;
    private InstituteListAdapter mInstituteListAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);

        setUI();

    }

    void setUI(){
        mEtmobilenumber = findViewById(R.id.et_phone);
        mEtfirstname = findViewById(R.id.et_firstname);
        mEtlastname = findViewById(R.id.et_lastname);
        mEtemailid = findViewById(R.id.et_email);
        mEtmiddlename = findViewById(R.id.et_middlename);
        mEtdob = findViewById(R.id.et_dob);
        mSpinnerInstitute = findViewById(R.id.spinner_institute);
        mSpinnerCenter = findViewById(R.id.spinner_center);
        mSpinnerState = findViewById(R.id.spinner_state);
        mSpinnerDistrict = findViewById(R.id.spinner_district);

        mImplGenerateOtpPresenter = new ImplGenerateOtpPresenter(this, this);

        quizId = getIntent().getLongExtra("qiz",0);
        title = getIntent().getStringExtra("title");

        setListener();

        if(mStateLists != null){
            mImplGenerateOtpPresenter.populateStates();
        }
    }

    void setListener(){
        findViewById(R.id.iv_clear).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_previous).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_clear:
                mEtmobilenumber.setText("");
                break;

           case R.id.btn_submit:
                    String check = validateDetails(mEtfirstname.getText().toString(),mEtmiddlename.getText().toString(),mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mEtmobilenumber.getText().toString(), mEtemailid.getText().toString(), mSpinnerState.getSelectedItem().toString(), mSpinnerDistrict.getSelectedItem().toString(), mSpinnerCenter.getSelectedItem().toString(), mSpinnerInstitute.getSelectedItem().toString());
                    if(check.equals("ok")){
                        mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(),mEtmiddlename.getText().toString(),mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mEtmobilenumber.getText().toString(), mEtemailid.getText().toString(), stateid, districtid, centerid, instituteid, quizId);
                    }else{
                        Toast.makeText(this,check,Toast.LENGTH_LONG).show();
                    }
                    break;

            case R.id.btn_next:
                findViewById(R.id.institutedetails).setVisibility(View.VISIBLE);
                findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
                break;

            case R.id.btn_previous:
                findViewById(R.id.institutedetails).setVisibility(View.GONE);
                findViewById(R.id.linear_pdetails).setVisibility(View.VISIBLE);
                break;
        }
    }

    String validateDetails(String firstname, String middlename, String lastname, String gender, String dob, String mobilenumber, String emailId, String state, String district, String center, String institute){
        if(!Pattern.matches(Validate.FIRSTNAME_PATTERN,firstname)){
            return "Invalid first name";
        }else if(!Pattern.matches(Validate.FIRSTNAME_PATTERN,middlename)){
            return "Invalid middle name";
        }else if(!Pattern.matches(Validate.FIRSTNAME_PATTERN,lastname)){
            return "Invalid last name";
        }else if(!gender.equals("Male") || !gender.equals("FeMale")){
            return "Please select gender";
        }else if(dob.isEmpty()){
            return "Please Add Date Of Birth";
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN,emailId)){
            return "Invalid emailid";
        }else if(!Pattern.matches(Validate.CONTACT_PATTERN,mobilenumber)){
            return "Invalid Mobile Number";
        }else if(!Pattern.matches(Validate.CONTACT_PATTERN,mobilenumber)){
            return "Invalid Mobile Number";
        }else if(state.isEmpty()){
            return "Please Select State";
        }else if(district.isEmpty()){
            return "Please Select District";
        }else if(center.isEmpty()){
            return "Please Select Center";
        }else if(institute.isEmpty()){
            return "Please Select Institute";
        }
        return "ok";
    }
    @Override
    public void movetoQuizActivity() {
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("mb", mEtmobilenumber.getText().toString());
        editor.commit();

        Intent intent = new Intent(GenerateOtp.this, StartQuizActivity.class);
        intent.putExtra("qiz", quizId);
        intent.putExtra("title", title);
        startActivity(intent);

        mEtmobilenumber.setText("");
    }

    @Override
    public void clearForm() {
       mEtfirstname.setText("");
       mEtlastname.setText("");
       mEtemailid.setText("");
       mEtmobilenumber.setText("");
    }


    @Override
    public void showMsg(String message) {

    }

    @Override
    public void generateOtp(String otp) {

    }

    @Override
    public void populateStates(ArrayList<StateList> stateLists) {
        mStateLists = stateLists;
        mStateListAdapter = new StateListAdapter(this, mStateLists, this);
        mSpinnerState.setAdapter(mStateListAdapter);
    }

    @Override
    public void populateDistricts(ArrayList<DistrictList> districtLists) {
        mDistrictLists = districtLists;
        mDistrictListAdapter = new DistrictListAdapter(this, mDistrictLists, this);
        mSpinnerDistrict.setAdapter(mDistrictListAdapter);
    }

    @Override
    public void populateCenters(ArrayList<CenterList> centerLists) {
        mCenterLists = centerLists;
        mCenterListAdapter = new CenterListAdapter(this, mCenterLists, this);
        mSpinnerCenter.setAdapter(mCenterListAdapter);
    }

    @Override
    public void populateInstitutes(ArrayList<InstituteList> instituteLists) {
        mInstituteLists = instituteLists;
        mInstituteListAdapter = new InstituteListAdapter(this, mInstituteLists, this);
        mSpinnerInstitute.setAdapter(mInstituteListAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GenerateOtp.this, LoginActivity.class);
        intent.putExtra("check","quiz");
        startActivity(intent);
    }
}