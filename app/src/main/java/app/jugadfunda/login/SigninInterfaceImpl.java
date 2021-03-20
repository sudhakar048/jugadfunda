package app.jugadfunda.login;

public interface SigninInterfaceImpl {

    void wsRadioSignin(String mEmailId, String mPwd);

    void wsSignin(String mEmailId, String mPwd, String mType, String mToken, String mModuleName);
}
