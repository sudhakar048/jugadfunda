package app.jugaadfunda.learningcompanion.login;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.login.signin.LoginFragment;
import app.jugaadfunda.learningcompanion.login.signup.SignUpFragment;

public class LoginActivity extends AppCompatActivity {
    private RadioButton rbLogin, rbSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        rbLogin = findViewById(R.id.rb_login);
        rbSignup = findViewById(R.id.rb_signup);
        rbLogin.setOnClickListener(view -> {
            addFragment(new LoginFragment());
        });
        rbSignup.setOnClickListener(view -> {
            addFragment(new SignUpFragment());
        });
        rbLogin.performClick();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.login_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
    }
}
