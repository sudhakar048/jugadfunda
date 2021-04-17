package app.jugadfunda.generateOtp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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

public class GenerateOtp extends AppCompatActivity implements View.OnClickListener, GenerateOtpView, RadioGroup.OnCheckedChangeListener {
    private EditText mEtfirstname;
    private EditText mEtmiddlename;
    private EditText mEtlastname;
    private EditText mEtemailid;
    private EditText mEtdob;
    private EditText mEtPhone;
    private EditText mEtOtp;
    private Spinner mSpinnerState;
    private Spinner mSpinnerDistrict;
    private Spinner mSpinnerCenter;
    private Spinner mSpinnerInstitute;
    private RadioGroup rg_gender;
    private TextView mTvTiming;
    private ImplGenerateOtpPresenter mImplGenerateOtpPresenter = null;
    private long quizId = 0;
    private String title = "";
    private ArrayList<StateList> mStateLists;
    private ArrayList<DistrictList> mDistrictLists;
    private ArrayList<CenterList> mCenterLists;
    private ArrayList<InstituteList> mInstituteLists;
    private String gender = "";
    private int mStateId = 0;
    private int mDistrictId = 0;
    private long mCenterId = 0;
    private long mInstituteId = 0;
    private StateListAdapter mStateListAdapter = null;
    private DistrictListAdapter mDistrictListAdapter = null;
    private CenterListAdapter mCenterListAdapter = null;
    private InstituteListAdapter mInstituteListAdapter = null;
    private static final String TAG = "TrueCallerVerification";
    private String[] permissionArrays = null;
    private String mobilenumber;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);

        setUI();

    }

    void setUI() {
        mEtPhone = findViewById(R.id.et_phone_otp);
        mEtfirstname = findViewById(R.id.et_firstname);
        mEtlastname = findViewById(R.id.et_lastname);
        mEtemailid = findViewById(R.id.et_email);
        mEtmiddlename = findViewById(R.id.et_middlename);
        mEtdob = findViewById(R.id.et_dob);
        mEtOtp = findViewById(R.id.et_otp);
        mSpinnerInstitute = findViewById(R.id.spinner_institute);
        mSpinnerCenter = findViewById(R.id.spinner_center);
        mSpinnerState = findViewById(R.id.spinner_state);
        mSpinnerDistrict = findViewById(R.id.spinner_district);
        rg_gender = findViewById(R.id.rg_gender);
        mTvTiming = findViewById(R.id.tv_timer);

        mImplGenerateOtpPresenter = new ImplGenerateOtpPresenter(this, this);

        quizId = getIntent().getLongExtra("qiz", 0);
        title = getIntent().getStringExtra("title");

        setListener();

        if (mStateLists == null) {
            mImplGenerateOtpPresenter.populateStates();
        }

    }

    void setListener() {
        findViewById(R.id.iv_clear).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.iv_ssubmit).setOnClickListener(this);
        findViewById(R.id.btn_previous).setOnClickListener(this);
        findViewById(R.id.btn_getotp).setOnClickListener(this);
        findViewById(R.id.iv_login).setOnClickListener(this);
        findViewById(R.id.iv_previous).setOnClickListener(this);
        findViewById(R.id.et_dob).setOnClickListener(this);
        rg_gender.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_dob:
                dateDialog();
                break;

            case R.id.iv_login:
              /*  TrueProfile mTrueProfile = new TrueProfile();
                mTrueProfile.firstName = mEtfirstname.getText().toString();
                mTrueProfile.lastName = mEtlastname.getText().toString();

                TruecallerSDK.getInstance().verifyMissedCall(mTrueProfile, apiCallback);*/
                if(mEtPhone.getText().toString().isEmpty() || mEtPhone.getText().toString().length() != 10){
                    Toast.makeText(this,"Invalid Mobile Number",Toast.LENGTH_LONG).show();
                }else{
                    mobilenumber = "+91"+mEtPhone.getText().toString();
                    mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mobilenumber, mEtemailid.getText().toString(), mStateId, mDistrictId, mCenterId, mInstituteId, quizId);
                }

                break;

            case R.id.btn_getotp:
         //       startTimer();
           //     trueCallerNonTrucallerUserInit();
             //   TruecallerSDK.getInstance().requestVerification("IN", mEtPhone.getText().toString(), apiCallback, GenerateOtp.this);
             //   findViewById(R.id.btn_getotp).setEnabled(false);
             //   findViewById(R.id.lineartimer).setVisibility(View.VISIBLE);
                break;


            case R.id.iv_ssubmit:
                String check1 = validateNextDetails();
                trueCallerTrucallerUserInit();
                if (check1.equals("ok")) {
                    boolean flag = TruecallerSDK.getInstance().isUsable();
                    if (flag) {
                        TruecallerSDK.getInstance().getUserProfile(this);
                    } else {
                        setGetOtpVisible();
                    }
                } else {
                    Toast.makeText(this, check1, Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.iv_previous:
                setGetOtpGone();
                break;

            case R.id.btn_next:
                String check2 = validateDetails(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mEtemailid.getText().toString());
                if (check2.equals("ok")) {
                    findViewById(R.id.institutedetails).setVisibility(View.VISIBLE);
                    findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
                } else {
                    Toast.makeText(this, check2, Toast.LENGTH_LONG).show();
                }


                break;

            case R.id.btn_previous:
                findViewById(R.id.institutedetails).setVisibility(View.GONE);
                findViewById(R.id.linear_pdetails).setVisibility(View.VISIBLE);
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
        }
    }

    void setGetOtpVisible() {
        findViewById(R.id.linear_getotp).setVisibility(View.VISIBLE);
        findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
        findViewById(R.id.institutedetails).setVisibility(View.GONE);
    }

    void setGetOtpGone() {
        findViewById(R.id.linear_getotp).setVisibility(View.GONE);
        findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
        findViewById(R.id.institutedetails).setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        TruecallerSDK.getInstance().requestVerification("IN", mEtPhone.getText().toString(), apiCallback, GenerateOtp.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TruecallerSDK.SHARE_PROFILE_REQUEST_CODE) {
            TruecallerSDK.getInstance().onActivityResultObtained(this, requestCode, resultCode, data);
        }
    }

    public String validateDetails(String firstname, String middlename, String lastname, String gender, String dob, String emailId) {
        if (!Pattern.matches(Validate.FIRSTNAME_PATTERN, firstname)) {
            return "Invalid first name";
        } else if (!Pattern.matches(Validate.FIRSTNAME_PATTERN, middlename)) {
            return "Invalid middle name";
        } else if (!Pattern.matches(Validate.FIRSTNAME_PATTERN, lastname)) {
            return "Invalid last name";
        } else if (gender.isEmpty()) {
            return "Please select gender";
        } else if (dob.isEmpty()) {
            return "Please Add Date Of Birth";
        } else if (!Pattern.matches(Validate.EMAILID_PATTERN, emailId)) {
            return "Invalid emailid";
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
    public void movetoQuizActivity() {
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("mb", mobilenumber);
        editor.commit();

        Intent intent = new Intent(GenerateOtp.this, StartQuizActivity.class);
        intent.putExtra("qiz", quizId);
        intent.putExtra("title", title);
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
    public void showMsg(String message) {

    }

    @Override
    public void generateOtp(String otp) {

    }

    @Override
    public void populateStates(ArrayList<StateList> stateLists) {
        mStateLists = stateLists;
        mStateListAdapter = new StateListAdapter(this, mStateLists, this, "otp");
        mSpinnerState.setAdapter(mStateListAdapter);
    }

    @Override
    public void populateDistricts(ArrayList<DistrictList> districtLists) {
        mDistrictLists = districtLists;
        mDistrictListAdapter = new DistrictListAdapter(this, mDistrictLists, this, "otp");
        mSpinnerDistrict.setAdapter(mDistrictListAdapter);
    }

    @Override
    public void populateCenters(ArrayList<CenterList> centerLists) {
        mCenterLists = centerLists;
        mCenterListAdapter = new CenterListAdapter(this, mCenterLists, this, "otp");
        mSpinnerCenter.setAdapter(mCenterListAdapter);
    }

    @Override
    public void populateInstitutes(ArrayList<InstituteList> instituteLists) {
        mInstituteLists = instituteLists;
        mInstituteListAdapter = new InstituteListAdapter(this, mInstituteLists, this, "otp");
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

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GenerateOtp.this, LoginActivity.class);
        intent.putExtra("check", "quiz");
        startActivity(intent);
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
        TruecallerSdkScope trueScope = new TruecallerSdkScope.Builder(this, sdkCallback)
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

    void trueCallerNonTrucallerUserInit() {
        TruecallerSdkScope trueScope = new TruecallerSdkScope.Builder(this, sdkCallback)
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
                .sdkOptions(TruecallerSdkScope.SDK_OPTION_WITH_OTP)
                .build();

        TruecallerSDK.init(trueScope);
    }

    //Callbacks for Truecaller Users
    private final ITrueCallback sdkCallback = new ITrueCallback() {

        @Override
        public void onSuccessProfileShared(@NonNull final TrueProfile trueProfile) {
            findViewById(R.id.iv_ssubmit).setEnabled(true);
            mobilenumber = trueProfile.phoneNumber;
            mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mobilenumber, mEtemailid.getText().toString(), mStateId, mDistrictId, mCenterId, mInstituteId, quizId);
        }

        @Override
        public void onFailureProfileShared(@NonNull final TrueError trueError) {
            findViewById(R.id.iv_ssubmit).setEnabled(false);
            Log.d(TAG, "onFailureProfileShared() called with: trueError = [" + trueError + "]");
        }

        @Override
        public void onVerificationRequired(@Nullable final TrueError trueError) {

            Log.d(TAG, "onVerificationRequired() called with: trueError = [" + trueError + "]");
        }

    };


    //Callbacks for non truecaller users
    static final VerificationCallback apiCallback = new VerificationCallback() {

        @Override
        public void onRequestSuccess(int requestCode, @Nullable VerificationDataBundle extras) {
            Log.d(TAG, "onRequestSuccess() called with: requestCode = [" + requestCode + "], extras = [" + extras + "]");
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

            Log.d(TAG, "onRequestFailure() called with: requestCode = [" + requestCode + "], e = [" + e.getExceptionMessage() + "]");
        }

    };

    private void checkRunTimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            permissionArrays = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG, Manifest.permission.ANSWER_PHONE_CALLS};
        } else {
            permissionArrays = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG, Manifest.permission.CALL_PHONE};
        }
        requestPermissions(permissionArrays, 101);
    }

    public void dateDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mEtdob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        };
        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, 1993, 0, 1);
        dpDialog.show();
    }


//Callback for Timer
        int i = 60;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                i--;
                mTvTiming.setText("" + i);

                startTimer();
                if(i == 0){
                    cancelTimer();
                    findViewById(R.id.btn_getotp).setEnabled(true);
                    findViewById(R.id.lineartimer).setVisibility(View.GONE);
                }
            }
        };

    public void startTimer() {
        handler.postDelayed(runnable, 1000);
    }

    public void cancelTimer() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TruecallerSDK.clear();
    }
}


