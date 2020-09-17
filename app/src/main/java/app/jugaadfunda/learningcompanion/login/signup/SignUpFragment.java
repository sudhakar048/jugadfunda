package app.jugaadfunda.learningcompanion.login.signup;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.regex.Pattern;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.validate.Validate;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpInterfaceView, View.OnClickListener {
    private View itemview = null;
    private EditText m_etname = null;
    private EditText m_etcontact = null;
    private EditText m_etemailid = null;
    private EditText m_etpwd = null ;
    private EditText m_etcpwd = null;
    private CheckBox cb_pwd;
    private CheckBox cb_cpwd;
    private ImplSignupPresenter mImplSignupPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemview = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //setting UI
        setUI();
        return itemview;
    }

    void setUI(){
        mImplSignupPresenter = new ImplSignupPresenter(getContext(),this);

        m_etname = itemview.findViewById(R.id.et_name);
        m_etcontact = itemview.findViewById(R.id.et_contact);
        m_etemailid = itemview.findViewById(R.id.et_emailid);
        m_etpwd = itemview.findViewById(R.id.et_pwd);
        m_etcpwd = itemview.findViewById(R.id.et_cpwd);
        cb_pwd = itemview.findViewById(R.id.cb_showpwd);
        cb_cpwd = itemview.findViewById(R.id.cb_showcpwd);
        //setting listeners
        setListners();
    }


    void setListners(){
        itemview.findViewById(R.id.btn_signup).setOnClickListener(this);
        itemview.findViewById(R.id.cb_showpwd).setOnClickListener(this);
        itemview.findViewById(R.id.cb_showcpwd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_signup:
            String checkSignUp = validateSignUpForm(m_etname.getText().toString().trim(),m_etcontact.getText().toString().trim(),m_etemailid.getText().toString().trim(),m_etpwd.getText().toString().trim(),m_etcpwd.getText().toString().trim());
            if(!checkSignUp.equals("ok")){
                Toast.makeText(getContext(),checkSignUp,Toast.LENGTH_LONG).show();
            }else{
                mImplSignupPresenter.wssignup(m_etname.getText().toString().trim(),m_etemailid.getText().toString().trim(),m_etcontact.getText().toString().trim(),m_etpwd.getText().toString().trim(),m_etcpwd.getText().toString().trim(),"mobile");
            }
            break;

        case R.id.cb_showpwd:
            if(cb_pwd.isChecked()){
                m_etpwd.setTransformationMethod(null);
            }else{
                m_etpwd.setTransformationMethod(new PasswordTransformationMethod());
            }
            break;

        case R.id.cb_showcpwd:
            if(cb_cpwd.isChecked()){
                m_etcpwd.setTransformationMethod(null);
            }else{
                m_etcpwd.setTransformationMethod(new PasswordTransformationMethod());
            }
            break;
    }
    }


    String validateSignUpForm(String name, String contact, String emailId,String pwd, String cpwd){
        if(!Pattern.matches(Validate.NAME_PATTERN,name)){
            return "Invalid Name";
        }else if(!Pattern.matches(Validate.CONTACT_PATTERN,contact)){
            return "Invalid Contact";
        }else if(!Pattern.matches(Validate.EMAILID_PATTERN,emailId)){
            return "Invalid EmailId";
        }else if(!Pattern.matches(Validate.PASSWORD_PATTERN,pwd)){
            return "Invalid Password, Use atleast one uppercase, one lowercase, one number, one special character and minimum 8 characters";
        }else if(!Pattern.matches(Validate.PASSWORD_PATTERN,cpwd)){
            return "Invalid Password, Use atleast one uppercase, one lowercase, one number, one special character and minimum 8 characters";
        }else if(!pwd.trim().equals(cpwd.trim())){
            return "Password confirmation must match Password";
        }
        return "ok";
    }

    @Override
    public void clearSignUpForm() {
        m_etname.setText("");
        m_etcontact.setText("");
        m_etemailid.setText("");
        m_etpwd.setText("");
        m_etcpwd.setText("");
    }
}
