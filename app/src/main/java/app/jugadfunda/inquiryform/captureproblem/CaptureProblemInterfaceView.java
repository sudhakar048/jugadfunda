package app.jugadfunda.inquiryform.captureproblem;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public interface CaptureProblemInterfaceView {

    void showAndHideBusinessDomainEditText(int check, int position);

    void showAndHideProductServiceDomainEditText(int check, int position);

    void selectBusinessDomain(int check, int pos);

    void selectProductServiceDomain(int check, int pos);

    void selectProductServiceSubDomain(int check, int position);

    void resetCaptureProblemForm();
}
