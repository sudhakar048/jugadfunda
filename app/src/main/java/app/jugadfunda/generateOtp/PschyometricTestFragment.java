package app.jugadfunda.generateOtp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.truecaller.android.sdk.ITrueCallback;
import com.truecaller.android.sdk.TrueError;
import com.truecaller.android.sdk.TrueException;
import com.truecaller.android.sdk.TrueProfile;
import com.truecaller.android.sdk.TruecallerSDK;
import com.truecaller.android.sdk.TruecallerSdkScope;
import com.truecaller.android.sdk.clients.VerificationCallback;
import com.truecaller.android.sdk.clients.VerificationDataBundle;
import java.util.ArrayList;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.QuizCodeResponse;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;
import app.jugadfunda.inquiryform.adapter.CenterListAdapter;
import app.jugadfunda.inquiryform.adapter.DistrictListAdapter;
import app.jugadfunda.inquiryform.adapter.InstituteListAdapter;
import app.jugadfunda.quiz.adapter.CustomSpinnerAdapter;
import app.jugadfunda.quiz.questions.StartQuizActivity;
import app.jugadfunda.validate.Validate;

public class PschyometricTestFragment extends Fragment implements View.OnClickListener, PsychometricTestView, RadioGroup.OnCheckedChangeListener {
    private EditText mEtfirstname;
    private EditText mEtmiddlename;
    private EditText mEtlastname;
    private EditText mEtemailid;
    private EditText mEtdob;
    private EditText mEtPhone;
    private Spinner mSpinnerState;
    private Spinner mSpinnerDistrict;
    private Spinner mSpinnerCenter;
    private Spinner mSpinnerInstitute;
    private RadioGroup rg_gender;
    private TextView mTvDate;
    private TextView mTvSlot;
    private ImplPsychometricTestPresenter mImplGenerateOtpPresenter = null;
    private ArrayList<StateList> mStateLists;
    private ArrayList<DistrictList> mDistrictLists;
    private ArrayList<CenterList> mCenterLists;
    private ArrayList<InstituteList> mInstituteLists;
    private String gender = "";
    private int mStateId = 0;
    private int mDistrictId = 0;
    private long mCenterId = 0;
    private long mInstituteId = 0;
    private ArrayAdapter mStateListAdapter = null;
    private DistrictListAdapter mDistrictListAdapter = null;
    private CenterListAdapter mCenterListAdapter = null;
    private InstituteListAdapter mInstituteListAdapter = null;
    private static final String TAG = "TrueCallerVerification";
    private String[] permissionArrays = null;
    private String mobilenumber;
    private boolean flag = false;
    private View itemView = null;
    private String mInstituteName;
    private EditText mEtQuizCode;
    private TextView mTvMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        itemView = inflater.inflate(R.layout.activity_generate_otp, container, false);
        setUI();
        return itemView;
    }

    void setUI() {
        mEtPhone = itemView.findViewById(R.id.et_phone_otp);
        mEtfirstname = itemView.findViewById(R.id.et_firstname);
        mEtlastname = itemView.findViewById(R.id.et_lastname);
        mEtemailid = itemView.findViewById(R.id.et_email);
        mEtmiddlename = itemView.findViewById(R.id.et_middlename);
        mEtdob = itemView.findViewById(R.id.et_dob);
        mSpinnerInstitute = itemView.findViewById(R.id.spinner_institute);
        mSpinnerCenter = itemView.findViewById(R.id.spinner_center);
        mSpinnerState = itemView.findViewById(R.id.spinner_state);
        mSpinnerDistrict = itemView.findViewById(R.id.spinner_district);
        rg_gender = itemView.findViewById(R.id.rg_gender);
        mEtQuizCode = itemView.findViewById(R.id.et_quizcode);
        mTvDate = itemView.findViewById(R.id.tv_date);
        mTvSlot = itemView.findViewById(R.id.tv_slot);
        mTvMsg = itemView.findViewById(R.id.tv_errormsg);
        trueCallerTrucallerUserInit();

        flag = TruecallerSDK.getInstance().isUsable();
        if(flag){
            itemView.findViewById(R.id.cv_mobilenumber).setVisibility(View.GONE);
            itemView.findViewById(R.id.tv_msg).setVisibility(View.GONE);
            itemView.findViewById(R.id.tv_tellusmobile).setVisibility(View.GONE);
        }

        mImplGenerateOtpPresenter = new ImplPsychometricTestPresenter(getContext(), this);

        setListener();

        SharedPreferences shcheck = getContext().getSharedPreferences("profile",Context.MODE_PRIVATE);
        String check = shcheck.getString("c","");
        if(check.equals("no")){
            if (mStateLists == null) {
                mImplGenerateOtpPresenter.populateStates();
            }
        }else{
            itemView.findViewById(R.id.cv_quizcode).setVisibility(View.VISIBLE);
            itemView.findViewById(R.id.institutedetails).setVisibility(View.GONE);
            itemView.findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
        }


        mSpinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStateId = mStateLists.get(position).getSid();
                mImplGenerateOtpPresenter.populateDistricts(mStateId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_LONG);
            }
        });

        mSpinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDistrictId = mDistrictLists.get(position).getDid();
                mImplGenerateOtpPresenter.populateCenters(mStateId, mDistrictId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_LONG);
            }
        });

        mSpinnerCenter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCenterId = mCenterLists.get(position).getCenterid();
                mImplGenerateOtpPresenter.populateInstitutes(mCenterId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_LONG);
            }
        });

        mSpinnerInstitute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInstituteId = mInstituteLists.get(position).getInstituteId();
                mInstituteName = mInstituteLists.get(position).getInstituteName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Nothing Selected", Toast.LENGTH_LONG);
            }
        });
    }

    void setListener() {
        itemView.findViewById(R.id.iv_clear).setOnClickListener(this);
        itemView.findViewById(R.id.btn_next).setOnClickListener(this);
        itemView.findViewById(R.id.iv_ssubmit).setOnClickListener(this);
        itemView.findViewById(R.id.btn_previous).setOnClickListener(this);
        itemView.findViewById(R.id.et_dob).setOnClickListener(this);
        itemView.findViewById(R.id.btn_codesubmit).setOnClickListener(this);
        rg_gender.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_dob:
                dateDialog();
                break;

            case R.id.iv_ssubmit:
                String check1 = validateNextDetails();
                if (check1.equals("ok")) {
                    if (flag) {
                        TruecallerSDK.getInstance().getUserProfile(this);
                    } else {
                        selectInstituteAlert(mInstituteName);
                   }
                } else {
                    Toast.makeText(getContext(), check1, Toast.LENGTH_LONG).show();
                }
                break;


            case R.id.btn_next:
                String check2 = validateDetails(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mEtemailid.getText().toString(),mEtPhone.getText().toString());
                if (check2.equals("ok")) {
                    itemView.findViewById(R.id.institutedetails).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), check2, Toast.LENGTH_LONG).show();
                }


                break;

            case R.id.btn_previous:
                itemView.findViewById(R.id.institutedetails).setVisibility(View.GONE);
                itemView.findViewById(R.id.linear_pdetails).setVisibility(View.VISIBLE);
                break;

            case R.id.spinner_district:
                mImplGenerateOtpPresenter.populateDistricts(mStateId);
                break;

            case R.id.spinner_center:
                mImplGenerateOtpPresenter.populateCenters(mStateId, mDistrictId);
                break;

            case R.id.spinner_institute:
                mImplGenerateOtpPresenter.populateInstitutes(mCenterId);
                break;

            case R.id.btn_codesubmit:
                String check = validateQuizCode(mEtQuizCode.getText().toString());
                if(check.equals("ok")){
                    mImplGenerateOtpPresenter.verifyQuizCode(mEtQuizCode.getText().toString());
                }else{
                    Toast.makeText(getContext(), check, Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.iv_clear:
                mEtPhone.setText("");
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        TruecallerSDK.getInstance().requestVerification("IN", mEtPhone.getText().toString(), apiCallback, (FragmentActivity) getContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TruecallerSDK.SHARE_PROFILE_REQUEST_CODE) {
            TruecallerSDK.getInstance().onActivityResultObtained((FragmentActivity) getContext(), requestCode, resultCode, data);
        }
    }

    public String validateDetails(String firstname, String middlename, String lastname, String gender, String dob, String emailId, String mb) {

        if (!Pattern.matches(Validate.FIRSTNAME_PATTERN, firstname)) {
            return "Invalid first name";
        } else if (!middlename.isEmpty() && !Pattern.matches(Validate.FIRSTNAME_PATTERN, middlename)) {
            return "Invalid middle name";
        } else if (!Pattern.matches(Validate.FIRSTNAME_PATTERN, lastname)) {
            return "Invalid last name";
        } else if (gender.isEmpty()) {
            return "Please select gender";
        } else if (dob.isEmpty()) {
            return "Please Add Date Of Birth";
        } else if (!Pattern.matches(Validate.EMAILID_PATTERN, emailId)) {
            return "Invalid emailid";
        }else if (flag) {
            return "ok";
        }else if(!flag && !Pattern.matches(Validate.CONTACT_PATTERN, mb)){
            return "Invalid Mobile Number";
        }
        return "ok";
    }

    public String validateQuizCode(String quizcode){
        if (!Pattern.matches(Validate.REGEX_QUIZ_CODE, quizcode)) {
            return "Invalid Code";
        }
        return "ok";
    }

    public String validateNextDetails() {
        if (mStateId == 0) {  
            return "Please Select State";
        } else if (mDistrictId == 0) {
            return "Please Select District";
        } else if (mCenterId == 0) {
            return "Please Select Center";
        } else if (mInstituteId == 0) {
            return "Please Select Institute";
        }
        return "ok";
    }

    @Override
    public void movetoQuizActivity(long quizid, String title, int duration, int totalnoofquestions) {
        Intent intent = new Intent(getContext(), StartQuizActivity.class);
        intent.putExtra("qiz", quizid);
        intent.putExtra("title", title);
        intent.putExtra("d", duration);
        intent.putExtra("tq", totalnoofquestions);
        startActivity(intent);
    }

    @Override
    public void clearForm() {
        mEtfirstname.setText("");
        mEtmiddlename.setText("");
        mEtlastname.setText("");
        mEtdob.setText("");
        mEtemailid.setText("");
    }

    @Override
    public void populateStates(ArrayList<StateList> stateLists) {
        mStateLists = stateLists;
        mStateListAdapter = new CustomSpinnerAdapter(getContext(), mStateLists);
        mSpinnerState.setAdapter(mStateListAdapter);
    }

    @Override
    public void populateDistricts(ArrayList<DistrictList> districtLists) {
        mDistrictLists = districtLists;
        mDistrictListAdapter = new DistrictListAdapter(getContext(), mDistrictLists);
        mSpinnerDistrict.setAdapter(mDistrictListAdapter);
    }

    @Override
    public void populateCenters(ArrayList<CenterList> centerLists) {
        mCenterLists = centerLists;
        mCenterListAdapter = new CenterListAdapter(getContext(), mCenterLists);
        mSpinnerCenter.setAdapter(mCenterListAdapter);
    }

    @Override
    public void populateInstitutes(ArrayList<InstituteList> instituteLists) {
        mInstituteLists = instituteLists;
        mInstituteListAdapter = new InstituteListAdapter(getContext(), mInstituteLists);
        mSpinnerInstitute.setAdapter(mInstituteListAdapter);
    }

    @Override
    public void callStateList(int pos) {
        mStateId = mStateLists.get(pos).getSid();
        mImplGenerateOtpPresenter.populateDistricts(mStateId);
    }

    @Override
    public void callDistrictList(int pos) {
        mDistrictId = mDistrictLists.get(pos).getDid();
        mImplGenerateOtpPresenter.populateCenters(mStateId, mCenterId);
    }

    @Override
    public void callCenterList(int pos) {
        mCenterId = mCenterLists.get(pos).getCenterid();
        mImplGenerateOtpPresenter.populateInstitutes(mCenterId);
    }

    @Override
    public void callInstituteList(int pos) {
        mInstituteId = mInstituteLists.get(pos).getInstituteId();
        mInstituteName = mInstituteLists.get(pos).getInstituteName();

    }

    @Override
    public void checkSignUp(boolean flag) {
        if(flag){
            itemView.findViewById(R.id.cv_quizcode).setVisibility(View.VISIBLE);
            itemView.findViewById(R.id.institutedetails).setVisibility(View.GONE);
            itemView.findViewById(R.id.linear_pdetails).setVisibility(View.GONE);

            SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putString("mb", mobilenumber);
            editor.putString("c", "yes");
            editor.commit();
        }
    }

    @Override
    public void selectInstituteAlert(String instituteName) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_alert_institute, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
        TextView tv_institute = view.findViewById(R.id.tv_institutename);
        Button btn_yes = view.findViewById(R.id.btn_yes);
        Button btn_no = view.findViewById(R.id.btn_no);
        tv_institute.setText(instituteName);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobilenumber = "+91-"+mEtPhone.getText().toString();
                mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mobilenumber, mEtemailid.getText().toString(), mStateId, mDistrictId, mCenterId, mInstituteId);
                dialog.cancel();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void passQuizCodeResponse(QuizCodeResponse quizCodeResponse) {
        if(!quizCodeResponse.isFlag()){
            mTvMsg.setVisibility(View.VISIBLE);
            mTvMsg.setText(quizCodeResponse.getMsg());
            itemView.findViewById(R.id.linear_slot).setVisibility(View.GONE);
        }else{
            if(quizCodeResponse.getCheck().equals("valid")){
                movetoQuizActivity(quizCodeResponse.getQid(), quizCodeResponse.getQtitle(), quizCodeResponse.getDuration(), quizCodeResponse.getTotalquestions());
            }else if(quizCodeResponse.getCheck().equals("before")){
                mTvMsg.setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.linear_slot).setVisibility(View.GONE);
                mTvMsg.setText("Your Psychometric Test Time has been Passed.Please contact to your Institute.");
            }else{
                itemView.findViewById(R.id.linear_slot).setVisibility(View.VISIBLE);
                mTvMsg.setVisibility(View.VISIBLE);
                mTvMsg.setText("Psychometric Test Details are :");
                mTvDate.setText("Date - "+quizCodeResponse.getDate());
                mTvSlot.setText("Slot - "+quizCodeResponse.getSlot());
            }
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.radio_male:
                gender = "Male";
                break;
            case R.id.radio_female:
                gender = "FeMale";
                break;
        }
    }

    void trueCallerTrucallerUserInit() {
        TruecallerSdkScope trueScope = new TruecallerSdkScope.Builder(getContext(), sdkCallback)
                .consentMode(TruecallerSdkScope.CONSENT_MODE_BOTTOMSHEET)
                .buttonColor(Color.parseColor("#e0af1f"))
                .buttonTextColor(Color.parseColor("#ffffff"))
                .loginTextPrefix(TruecallerSdkScope.LOGIN_TEXT_PREFIX_TO_GET_STARTED)
                .loginTextSuffix(TruecallerSdkScope.LOGIN_TEXT_SUFFIX_PLEASE_VERIFY_MOBILE_NO)
                .ctaTextPrefix(TruecallerSdkScope.CTA_TEXT_PREFIX_USE)
                .buttonShapeOptions(TruecallerSdkScope.BUTTON_SHAPE_ROUNDED)
                .privacyPolicyUrl("https://jugaadfunda.com/jugaadfunda-agreements/Privacy_Statement.jsp")
                .termsOfServiceUrl("https://jugaadfunda.com/jugaadfunda-agreements/Terms_Use.jsp")
                .footerType(TruecallerSdkScope.FOOTER_TYPE_NONE)
                .consentTitleOption(TruecallerSdkScope.SDK_CONSENT_TITLE_LOG_IN)
                .sdkOptions(TruecallerSdkScope.SDK_OPTION_WITHOUT_OTP)
                .build();

        TruecallerSDK.init(trueScope);
    }

    //Callbacks for Truecaller Users
    private final ITrueCallback sdkCallback = new ITrueCallback() {

        @Override
        public void onSuccessProfileShared(@NonNull final TrueProfile trueProfile) {
            itemView.findViewById(R.id.iv_ssubmit).setEnabled(true);
            mobilenumber = trueProfile.phoneNumber;
            mobilenumber = mobilenumber.substring(0,3)+"-"+mobilenumber.substring(3,13);
            mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mobilenumber, mEtemailid.getText().toString(), mStateId, mDistrictId, mCenterId, mInstituteId);
        }

        @Override
        public void onFailureProfileShared(@NonNull final TrueError trueError) {
            itemView.findViewById(R.id.iv_ssubmit).setEnabled(false);
        }

        @Override
        public void onVerificationRequired(@Nullable final TrueError trueError) {


        }

    };


    //Callbacks for non truecaller users
    static final VerificationCallback apiCallback = new VerificationCallback() {

        @Override
        public void onRequestSuccess(int requestCode, @Nullable VerificationDataBundle extras) {
            if (requestCode == VerificationCallback.TYPE_MISSED_CALL_INITIATED) {
                if (extras != null)
                    extras.getString(VerificationDataBundle.KEY_TTL);
            }

            if (requestCode == VerificationCallback.TYPE_MISSED_CALL_RECEIVED) {
                Log.d(TAG, "_MISSED_CALL_RECEIVED");
            }

            if (requestCode == VerificationCallback.TYPE_OTP_INITIATED) {
                if (extras != null)
                    extras.getString(VerificationDataBundle.KEY_TTL);
            }

            if (requestCode == VerificationCallback.TYPE_OTP_RECEIVED) {
                Log.d(TAG, "OTP Received");
            }

            if (requestCode == VerificationCallback.TYPE_VERIFICATION_COMPLETE) {
                Log.d(TAG, "VERIFICATION_COMPLETE");
            }

            if (requestCode == VerificationCallback.TYPE_PROFILE_VERIFIED_BEFORE) {
            }

        }

        @Override
        public void onRequestFailure(final int requestCode, @NonNull final TrueException e) {
       }

    };

    public void dateDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mEtdob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        };
        DatePickerDialog dpDialog = new DatePickerDialog(getContext(), listener, 2000, 0, 0);
        dpDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        TruecallerSDK.clear();
    }
}


