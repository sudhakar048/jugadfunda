package app.jugaadfunda.learningcompanion.login.signin;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.businessactivity.BusinessActivityPojo;
import app.jugaadfunda.learningcompanion.home.HomeActivity;
import app.jugaadfunda.learningcompanion.localstorage.JfStorage;
import app.jugaadfunda.learningcompanion.validate.Validate;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements SigninInterfaceView, View.OnClickListener {

    private View itemView;
    private EditText et_emailid;
    private EditText et_pwd;
    private CheckBox cb_showpwd;
    private ImplSigninPresenter implSigninPresenter;
   // private ImplSign

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        itemView = inflater.inflate(R.layout.fragment_login, container, false);
        setUI();
        return itemView;
    }

    void setUI(){
        implSigninPresenter = new ImplSigninPresenter(getContext(),this);
        et_emailid = itemView.findViewById(R.id.et_emailid);
        et_pwd = itemView.findViewById(R.id.et_pwd);
        cb_showpwd = itemView.findViewById(R.id.cb_pwd);
        setListeners();
     }

    void setListeners(){
        itemView.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        itemView.findViewById(R.id.cb_pwd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_sign_in:
            String check = validateSignForm(et_emailid.getText().toString(), et_pwd.getText().toString());
            if(!check.equals("ok")){
                Toast.makeText(getContext(),""+check,Toast.LENGTH_LONG).show();
            } else{
                implSigninPresenter.wsSignin(et_emailid.getText().toString(),et_pwd.getText().toString());
            }
            break;

        case R.id.cb_pwd:
            if(cb_showpwd.isChecked()){
                et_pwd.setTransformationMethod(null);
            }else{
                et_pwd.setTransformationMethod(new PasswordTransformationMethod());
            }
            break;
    }
    }

    @Override
    public void movetoHomeScreen() {
        Intent intents=new Intent(getContext(), HomeActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intents);
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
