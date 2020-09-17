package app.jugaadfunda.learningcompanion.inquiryform.mom;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.validate.Validate;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomFragment extends Fragment implements MOMView, View.OnClickListener {
    private View mIteView;
    private EditText mEtmDate;
    private TextInputEditText mEttitle;
    private TextInputEditText mEtLocation;
    private EditText mEtStartTime;
    private EditText mEtEndTime;
    private TextInputEditText mAttendeesName;
    private TextInputEditText mPointDiscussed;
    private TextInputEditText mStudentName;
    private TextInputEditText mCollegeName;
    private TextInputEditText mDeptName;
    private EditText mJoiningYear;
    private ImplMoM mImplMoM;
    private long mUserId;
    private DatePickerDialog mDatePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mIteView = inflater.inflate(R.layout.fragment_mom, container, false);

        setUI();

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);
        return mIteView;
    }

    void setUI(){
        mImplMoM = new ImplMoM(getContext(),this);

        mEtmDate = mIteView.findViewById(R.id.et_mdate);
        mEttitle = mIteView.findViewById(R.id.et_title);
        mEtLocation = mIteView.findViewById(R.id.et_loc);
        mEtStartTime = mIteView.findViewById(R.id.et_starttime);
        mEtEndTime = mIteView.findViewById(R.id.et_endtime);
        mAttendeesName = mIteView.findViewById(R.id.et_attendeesname);
        mPointDiscussed = mIteView.findViewById(R.id.et_pointdiscussed);
        mStudentName = mIteView.findViewById(R.id.et_studname);
        mCollegeName = mIteView.findViewById(R.id.et_collegename);
        mDeptName = mIteView.findViewById(R.id.et_deptname);
        mJoiningYear = mIteView.findViewById(R.id.et_year);

        setDateTimeField();

        setListeners();
    }

    void setListeners(){
        mIteView.findViewById(R.id.btn_submit).setOnClickListener(this);
        mIteView.findViewById(R.id.et_mdate).setOnClickListener(this);
        mIteView.findViewById(R.id.et_starttime).setOnClickListener(this);
        mIteView.findViewById(R.id.et_endtime).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                String check = validateMomForm(mEtmDate.getText().toString(), mEttitle.getText().toString(), mEtLocation.getText().toString(), mEtStartTime.getText().toString(), mEtEndTime.getText().toString(), mAttendeesName.getText().toString(), mPointDiscussed.getText().toString(), mStudentName.getText().toString(),mCollegeName.getText().toString(),mDeptName.getText().toString(), mJoiningYear.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(getContext(),""+check,Toast.LENGTH_LONG).show();
                } else {
                    mImplMoM.wsMOM(mEtmDate.getText().toString(), mEttitle.getText().toString(), mEtLocation.getText().toString(), mEtStartTime.getText().toString(), mEtEndTime.getText().toString(), mAttendeesName.getText().toString(), mPointDiscussed.getText().toString(), mStudentName.getText().toString(), mCollegeName.getText().toString(), mDeptName.getText().toString(), mJoiningYear.getText().toString(),mUserId);
                }
                break;


            case R.id.et_mdate:
                mDatePickerDialog.show();
                break;

            case R.id.et_starttime:
                openTimePicker(mEtStartTime);
                break;

            case R.id.et_endtime:
                openTimePicker(mEtEndTime);
                break;
        }
    }

    @Override
    public void clearForm() {
        mEtmDate.setText("");
        mEttitle.setText("");
        mEtLocation.setText("");
        mEtStartTime.setText("");
        mEtEndTime.setText("");
        mAttendeesName.setText("");
        mPointDiscussed.setText("");
        mStudentName.setText("");
        mCollegeName.setText("");
        mDeptName.setText("");
        mJoiningYear.setText("");
    }

    String validateMomForm(String mMomDate, String mTitle, String mLocation, String mStarttime, String mEndtime, String mAttendeesName, String mPoints, String mStudentName, String mCollegeName, String mDeptName, String mJoiningYear){
        if(mMomDate == null || mMomDate.isEmpty()){
            return "Invalid Format for Date";
        }else if(!Pattern.matches(Validate.TITLE,mTitle)){
            return "Invalid Title for Meeting, only uppercase, lowercase, numbers,-,.,_,' and Max 200 characters are allowed \n For ex- A.T-itle_123's";
        }else if(!Pattern.matches(Validate.LOCATION,mLocation)){
            return "Invalid Location for Meeting, only uppercase, lowercase, numbers,-,.,_,' and Max 200 characters are allowed \n For ex- NewYork_123's";
        }else if(!Pattern.matches(Validate.REGEX_TIME,mStarttime) || mStarttime == null || mStarttime.isEmpty()){
            return "Invalid Start Time. Format is HH:MM";
        }else if(!Pattern.matches(Validate.REGEX_TIME,mEndtime) || mEndtime == null || mEndtime.isEmpty()){
            return "Invalid End Time. Format is HH:MM";
        }else if(!Pattern.matches(Validate.ATTENDEES,mAttendeesName)){
            return "Invalid Entry for Attendees, only uppercase, lowercase, numbers,-,.,_,' and Max 500 characters are allowed \n For ex- 1. Mr. Xyz 2. Mrs. d'souza";
        }else if(mPoints.length() > 1000){
            return "Invalid Meeting Points, Max 1000 charaters are allowed";
        }else if(!Pattern.matches(Validate.STUDENT_NAME,mStudentName)){
            return "Invalid Student Name, First letter should be uppercase and only uppercase, lowercase,.,' and Max 150 characters are allowed \n For ex- A.T-itle_'s";
        }else if(!Pattern.matches(Validate.TITLE,mCollegeName)){
            return "Invalid College Name, only uppercase, lowercase, number,-,.,_,' and Max 500 characters are allowed \n For ex- A.T-itle_123's ";
        }else if(!Pattern.matches(Validate.TITLE,mDeptName)){
            return "Invalid Department Name Name, only uppercase, lowercase,numbers,-,.,_,' and Max 500 characters are allowed \n For ex- A.T-itle_123's";
        }else if(mJoiningYear == null || mJoiningYear.isEmpty()){
            return "Invalid Format for Year,only numers are allowed. For ex- 2020";
        }
        return "ok";
    }


    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

                mEtmDate.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private void openTimePicker(EditText et){

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                et.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}
