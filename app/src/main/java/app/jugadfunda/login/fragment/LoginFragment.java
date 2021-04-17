package app.jugadfunda.login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.regex.Pattern;

import app.jugadfunda.ContentActivity;
import app.jugadfunda.R;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.home.HomeActivity;
import app.jugadfunda.login.ImplSigninPresenter;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.login.SigninInterfaceView;
import app.jugadfunda.login.adapter.ModuleAdapter;
import app.jugadfunda.login.pojo.LoginModule;
import app.jugadfunda.validate.Validate;

public class LoginFragment extends Fragment implements View.OnClickListener, SigninInterfaceView {
    private EditText mEtEmailId;
    private EditText mEtPassword;
    private TextView mTvUser;
    private TextView mTvInstruction;
    private CheckBox mCbpwd;
    private Spinner mSpinnerModule;
    private ImplSigninPresenter implSigninPresenter;
    private ArrayList<LoginModule> moduleList = null;
    private static String usertype = "jugaadfunda";
    private LinearLayout mLinearSpinner;
    String token = "";
    private View itemView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        itemView = inflater.inflate(R.layout.fragment_login, container, false);
        setUI(itemView);
        return itemView;
    }

    private void setUI(View view) {
        implSigninPresenter = new ImplSigninPresenter(getContext(),this);
        mEtEmailId = view.findViewById(R.id.et_emailid);
        mEtPassword = view.findViewById(R.id.et_pwd);
        mTvUser = view.findViewById(R.id.tv_user);
        mTvInstruction = view.findViewById(R.id.tv_instruction);
        mCbpwd = view.findViewById(R.id.cb_pwd);
        mSpinnerModule = view.findViewById(R.id.spinner_modules);
        mLinearSpinner = view.findViewById(R.id.linear_spinner);

        setListener(view);

        pupulateModuleSpinner();
    }


    private void setListener(View view){
        view.findViewById(R.id.cb_pwd).setOnClickListener(this);
        view.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        view.findViewById(R.id.tv_user).setOnClickListener(this);
        view.findViewById(R.id.tv_disclaimer).setOnClickListener(this);
        view.findViewById(R.id.tv_pp).setOnClickListener(this);
        view.findViewById(R.id.tv_tou).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in:
                String check = validateSignForm(mEtEmailId.getText().toString(), mEtPassword.getText().toString());

                if(!check.equals("ok")){
                    Toast.makeText(getContext(),""+check,Toast.LENGTH_LONG).show();
                } else{
                    if(usertype.equals("jugaadfunda")){
                        int pos = mSpinnerModule.getSelectedItemPosition();
                        LoginModule loginModule = moduleList.get(pos);
                        try{


                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        implSigninPresenter.wsSignin(mEtEmailId.getText().toString(),mEtPassword.getText().toString(), loginModule.getCode().toLowerCase(), token, loginModule.getModulename());

                    }else{
                        implSigninPresenter.wsRadioSignin(mEtEmailId.getText().toString(),mEtPassword.getText().toString());
                    }

                }
                break;

            case R.id.cb_pwd:
                if(mCbpwd.isChecked()){
                    mEtPassword.setTransformationMethod(null);
                }else{
                    mEtPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
                break;


            case R.id.tv_user:
                if(usertype.equals("jugaadfunda")){
                    mTvUser.setText(R.string.jugaadfunda_user);
                    usertype = "guest";
                    mTvInstruction.setText(R.string.guest_instruction);
                    mLinearSpinner.setVisibility(View.GONE);
                }else if(usertype.equals("guest")){
                    mTvUser.setText(R.string.guest_user);
                    usertype = "jugaadfunda";
                    mTvInstruction.setText(R.string.jugaadfunda_instruction);
                    mLinearSpinner.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tv_disclaimer:
                Intent intent1 = new Intent(getContext(), ContentActivity.class);
                intent1.putExtra("url", ApiClient.BASE_URL+"jugaadfunda-agreements/Disclaimer.jsp");
                startActivity(intent1);
                break;

            case R.id.tv_pp:
                Intent intent2 = new Intent(getContext(), ContentActivity.class);
                intent2.putExtra("url",ApiClient.BASE_URL+"jugaadfunda-agreements/Privacy_Statement.jsp");
                startActivity(intent2);
                break;

            case R.id.tv_tou:
                Intent intent3 = new Intent(getContext(), ContentActivity.class);
                intent3.putExtra("url",ApiClient.BASE_URL+"jugaadfunda-agreements/Terms_Use.jsp");
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void movetoHomeScreen() {
        Intent intents=new Intent(getContext(), HomeActivity.class);
        intents.putExtra("check","event");
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intents);
    }

    @Override
    public void pupulateModuleSpinner() {
        moduleList = new ArrayList<>();
        moduleList.add(new LoginModule(R.drawable.ic_bms,"BeginMyStartup (Student / Entrepreneur)","begin"));
        moduleList.add(new LoginModule(R.drawable.ic_fmo,"FindMyOpportunity (Student / Graduate)","fmo"));
        moduleList.add(new LoginModule(R.drawable.ic_ums,"UpgradeMySkill (Student / Learner)","ums"));
        moduleList.add(new LoginModule(R.drawable.ic_umb,"UpgradeMyBusiness (Industry / corporate)","umb"));
        moduleList.add(new LoginModule(R.drawable.ic_jam,"JoinAsMentor (Consultant / Professional)","mentor"));
        moduleList.add(new LoginModule(R.drawable.ic_mmins,"ManageMyInstitute (Institute / University)","institute"));
        moduleList.add(new LoginModule(R.drawable.ic_sig,"ManageMySig (Professional / Expert)","sig"));
        moduleList.add(new LoginModule(R.drawable.ic_mminc,"ManageMyIncubation (Institute / Industry)","incubation"));


        ModuleAdapter adapter = new ModuleAdapter(getContext(), moduleList);
        mSpinnerModule.setAdapter(adapter);
    }

    String validateSignForm(String emailId, String password){
        if(!Pattern.matches(Validate.EMAILID_PATTERN,emailId)){
            return "Invalid EmailId";
        }else if(!Pattern.matches(Validate.PASSWORD_PATTERN,password)){
            return "Invalid Password";
        }
        return "ok";
    }



}
