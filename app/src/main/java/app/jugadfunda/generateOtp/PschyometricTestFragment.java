package app.jugadfunda.generateOtp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private ImplPsychometricTestPresenter mImplGenerateOtpPresenter = null;
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
    private boolean flag = false;
    private View itemView = null;


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

        trueCallerTrucallerUserInit();

        boolean flag = TruecallerSDK.getInstance().isUsable();
        if(flag){
            itemView.findViewById(R.id.cv_mobilenumber).setVisibility(View.GONE);
            itemView.findViewById(R.id.tv_msg).setVisibility(View.GONE);
            itemView.findViewById(R.id.tv_tellusmobile).setVisibility(View.GONE);
        }

        mImplGenerateOtpPresenter = new ImplPsychometricTestPresenter(getContext(), this);

        setListener();

        if (mStateLists == null) {
            mImplGenerateOtpPresenter.populateStates();
        }

    }

    void setListener() {
        itemView.findViewById(R.id.iv_clear).setOnClickListener(this);
        itemView.findViewById(R.id.btn_next).setOnClickListener(this);
        itemView.findViewById(R.id.iv_ssubmit).setOnClickListener(this);
        itemView.findViewById(R.id.btn_previous).setOnClickListener(this);
        itemView.findViewById(R.id.et_dob).setOnClickListener(this);
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
                        mobilenumber = "+91"+mEtPhone.getText().toString();
                        mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mobilenumber, mEtemailid.getText().toString(), mStateId, mDistrictId, mCenterId, mInstituteId, quizId);
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
        }
    }

/*    void setGetOtpVisible() {
        findViewById(R.id.linear_getotp).setVisibility(View.VISIBLE);
        findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
        findViewById(R.id.institutedetails).setVisibility(View.GONE);
    }*/

 /*   void setGetOtpGone() {
        findViewById(R.id.linear_getotp).setVisibility(View.GONE);
        findViewById(R.id.linear_pdetails).setVisibility(View.GONE);
        findViewById(R.id.institutedetails).setVisibility(View.VISIBLE);
    }
*/
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

    public String validateDetails(String firstname, String middlename, String lastname, String gender, String dob, String emailId, String mobileumber) {

       Toast.makeText(getContext(),""+flag,Toast.LENGTH_LONG).show();
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
        }else if (flag) {
            return "ok";
        }else if(!flag && mobileumber.length() != 10){
            return "Invalid Mobile Number";
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
        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString("mb", mobilenumber);
        editor.commit();

        Intent intent = new Intent(getContext(), StartQuizActivity.class);
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
    public void populateStates(ArrayList<StateList> stateLists) {
        mStateLists = stateLists;
        mStateListAdapter = new StateListAdapter(getContext(), mStateLists, this, "otp");
        mSpinnerState.setAdapter(mStateListAdapter);
    }

    @Override
    public void populateDistricts(ArrayList<DistrictList> districtLists) {
        mDistrictLists = districtLists;
        mDistrictListAdapter = new DistrictListAdapter(getContext(), mDistrictLists, this, "otp");
        mSpinnerDistrict.setAdapter(mDistrictListAdapter);
    }

    @Override
    public void populateCenters(ArrayList<CenterList> centerLists) {
        mCenterLists = centerLists;
        mCenterListAdapter = new CenterListAdapter(getContext(), mCenterLists, this, "otp");
        mSpinnerCenter.setAdapter(mCenterListAdapter);
    }

    @Override
    public void populateInstitutes(ArrayList<InstituteList> instituteLists) {
        mInstituteLists = instituteLists;
        mInstituteListAdapter = new InstituteListAdapter(getContext(), mInstituteLists, this, "otp");
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

    void trueCallerNonTrucallerUserInit() {
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
                .sdkOptions(TruecallerSdkScope.SDK_OPTION_WITH_OTP)
                .build();

        TruecallerSDK.init(trueScope);
    }

    //Callbacks for Truecaller Users
    private final ITrueCallback sdkCallback = new ITrueCallback() {

        @Override
        public void onSuccessProfileShared(@NonNull final TrueProfile trueProfile) {
            itemView.findViewById(R.id.iv_ssubmit).setEnabled(true);
            mobilenumber = trueProfile.phoneNumber;
            mImplGenerateOtpPresenter.verifyOtp(mEtfirstname.getText().toString(), mEtmiddlename.getText().toString(), mEtlastname.getText().toString(), gender, mEtdob.getText().toString(), mobilenumber, mEtemailid.getText().toString(), mStateId, mDistrictId, mCenterId, mInstituteId, quizId);
        }

        @Override
        public void onFailureProfileShared(@NonNull final TrueError trueError) {
            itemView.findViewById(R.id.iv_ssubmit).setEnabled(false);
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
        DatePickerDialog dpDialog = new DatePickerDialog(getContext(), listener, 1993, 0, 1);
        dpDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        TruecallerSDK.clear();
    }

}


