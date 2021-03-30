package app.jugadfunda.generateOtp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.questions.StartQuizActivity;
import app.jugadfunda.validate.Validate;

public class GenerateOtp extends AppCompatActivity implements View.OnClickListener, GenerateOtpView {
    private EditText mEtfirstname;
    private EditText mEtlastname;
    private EditText mEtemailid;
    private EditText mEtinstitutename;
    private EditText mEtmobilenumber;

    private ImplGenerateOtpPresenter mImplGenerateOtpPresenter = null;
    private long quizId = 0;
    private String title = "";

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
        mEtinstitutename = findViewById(R.id.et_institute);

        mImplGenerateOtpPresenter = new ImplGenerateOtpPresenter(this, this);

        quizId = getIntent().getLongExtra("qiz",0);
        title = getIntent().getStringExtra("title");

        setListener();
    }

    void setListener(){
        findViewById(R.id.iv_clear).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.iv_login:
                if(mEtotp.getText().toString().isEmpty() || mEtotp.getText().length() != 4){
                    Toast.makeText(this,"Please enter Valid OTP",Toast.LENGTH_LONG).show();
                }else{
                    sendVerificationCode();
          //          mImplGenerateOtpPresenter.verifyOtp(mEtmobilenumber.getText().toString(), mEtotp.getText().toString(), mEtfirstname.getText().toString(), mEtlastname.getText().toString(), mEtemailid.getText().toString(),mEtinstitutename.getText().toString(), quizId);
                }
                break;*/

            case R.id.iv_clear:
                mEtmobilenumber.setText("");
                break;

            /*case R.id.btn_getotp:
                    String check = validateMobileNumber(mEtmobilenumber.getText().toString());
                    if(check.equals("ok")){

                    //    mImplGenerateOtpPresenter.generateOtp(mEtmobilenumber.getText().toString(), quizId);
                    }else{
                        Toast.makeText(this,check,Toast.LENGTH_LONG).show();
                    }
                    break;*/

            case R.id.btn_next:
                String detailscheck = validateDetails(mEtfirstname.getText().toString(), mEtlastname.getText().toString(), mEtemailid.getText().toString(), mEtmobilenumber.getText().toString(), mEtinstitutename.getText().toString());
                if(!detailscheck.equals("ok")){
                    Toast.makeText(this,detailscheck,Toast.LENGTH_LONG).show();
                }else{
                    SharedPreferences sh = getSharedPreferences("profile",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sh.edit();
                    editor.putLong("uid", 0);
                    editor.putString("mb", mEtmobilenumber.getText().toString());
                    editor.commit();

                    mImplGenerateOtpPresenter.verifyOtp(mEtmobilenumber.getText().toString(),  mEtfirstname.getText().toString(), mEtlastname.getText().toString(), mEtemailid.getText().toString(),mEtinstitutename.getText().toString(), quizId);
                }

                break;

           /* case R.id.btn_previous:
                findViewById(R.id.linear_getotp).setVisibility(View.GONE);
                findViewById(R.id.linear_pdetails).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_next).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_previous).setVisibility(View.GONE);
                break;*/
        }
    }

    String validateDetails(String firstname, String lastname, String emailId, String mobilenumber, String institutename){
        if(!Pattern.matches(Validate.FIRSTNAME_PATTERN,firstname)){
            return "Invalid first name";
        }else if(!Pattern.matches(Validate.FIRSTNAME_PATTERN,lastname)){
            return "Invalid last name";
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN,emailId)){
            return "Invalid emailid";
        }else if(!Pattern.matches(Validate.CONTACT_PATTERN,mobilenumber)){
            return "Invalid Mobile Number";
        }else if(!Pattern.matches(Validate.INSTITUTE_NAME,institutename)){
            return "Invalid institute name";
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
       mEtinstitutename.setText("");
       mEtmobilenumber.setText("");
    }


    @Override
    public void showMsg(String message) {

    }

    @Override
    public void generateOtp(String otp) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GenerateOtp.this, LoginActivity.class);
        intent.putExtra("check","quiz");
        startActivity(intent);
    }
}