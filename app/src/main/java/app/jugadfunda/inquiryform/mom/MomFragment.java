package app.jugadfunda.inquiryform.mom;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.home.sessions.sessiondetailsandreport.SessionDetailsandReportActivity;
import app.jugadfunda.inquiryform.adapter.MomAdapter;
import app.jugadfunda.inquiryform.pojo.MomPojo;
import app.jugadfunda.validate.Validate;

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
    private String mType;
    private DatePickerDialog mDatePickerDialog;
    private LinearLayout mLinear;
    private LinearLayout mLinearMomList;
    private LinearLayout mLinearNoData;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private Button mBtnAddMom;
    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;
    private ArrayList<MomPojo> data;
    private MomAdapter momAdapterMOM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mIteView = inflater.inflate(R.layout.fragment_mom, container, false);

        setUI();

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);
        mType = sh.getString("mt","");
        mImplMoM.wsGetMomList(mUserId, mType);
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

        mLinear = mIteView.findViewById(R.id.linear_momform);
        mLinearNoData = mIteView.findViewById(R.id.linear_nodata);
        mLinearMomList = mIteView.findViewById(R.id.linear_momlist);
        mRecyclerView = mIteView.findViewById(R.id.recycler);
        mSearchView = mIteView.findViewById(R.id.search_data);
        mBtnAddMom = mIteView.findViewById(R.id.btn_addmom);

        setDateTimeField();

        setListeners();




    }

    void setListeners(){
        mIteView.findViewById(R.id.btn_submit).setOnClickListener(this);
        mIteView.findViewById(R.id.et_mdate).setOnClickListener(this);
        mIteView.findViewById(R.id.et_starttime).setOnClickListener(this);
        mIteView.findViewById(R.id.et_endtime).setOnClickListener(this);
        mIteView.findViewById(R.id.btn_addmom).setOnClickListener(this);
        mIteView.findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                String check = validateMomForm(mEtmDate.getText().toString(), mEttitle.getText().toString(), mEtLocation.getText().toString(), mEtStartTime.getText().toString(), mEtEndTime.getText().toString(), mAttendeesName.getText().toString(), mPointDiscussed.getText().toString(), mStudentName.getText().toString(),mCollegeName.getText().toString(),mDeptName.getText().toString(), mJoiningYear.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(getContext(),""+check,Toast.LENGTH_LONG).show();
                } else {
                    mImplMoM.wsMOM(mEtmDate.getText().toString(), mEttitle.getText().toString(), mEtLocation.getText().toString(), mEtStartTime.getText().toString(), mEtEndTime.getText().toString(), mAttendeesName.getText().toString(), mPointDiscussed.getText().toString(), mStudentName.getText().toString(), mCollegeName.getText().toString(), mDeptName.getText().toString(), mJoiningYear.getText().toString(), mType, mUserId);
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

            case R.id.btn_addmom:
                mLinear.setVisibility(View.VISIBLE);
                mLinearMomList.setVisibility(View.GONE);
                break;

            case R.id.btn_cancel:
                mLinearMomList.setVisibility(View.VISIBLE);
                mLinear.setVisibility(View.GONE);
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

    @Override
    public void shoeMomDetails(int pos) {
        MomPojo mMomPojo = data.get(pos);
        LayoutInflater li = LayoutInflater.from(getContext());
        View view = (LinearLayout)li.inflate(R.layout.layout_momdetails, null);

        TextView mTitle = view.findViewById(R.id.tv_title);
        TextView mStartTime = view.findViewById(R.id.tv_stime);
        TextView mEndTime = view.findViewById(R.id.tv_etime);
        TextView mAttendeesName = view.findViewById(R.id.tv_attendees);
        TextView mPointDiscussed = view.findViewById(R.id.tv_points);
        TextView mStudentName = view.findViewById(R.id.tv_studname);
        TextView mCollegeName = view.findViewById(R.id.tv_clgname);
        TextView mDeptName = view.findViewById(R.id.tv_deptname);
        TextView mYear = view.findViewById(R.id.tv_year);
        Button mBtnBack = view.findViewById(R.id.btn_back);
        Button mBtnDeleteMom = view.findViewById(R.id.btn_delete);

        mTitle.setText(mMomPojo.getTitle());
        mStartTime.setText(mMomPojo.getStime());
        mEndTime.setText(mMomPojo.getEtime());
        mAttendeesName.setText(mMomPojo.getAttendeesname());
        mPointDiscussed.setText(mMomPojo.getPointdiscussed());
        mStudentName.setText(mMomPojo.getName());
        mCollegeName.setText(mMomPojo.getCollegename());
        mDeptName.setText(mMomPojo.getDeptname());
        mYear.setText(""+mMomPojo.getYear());

        alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alertDialog = alert.show();

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        mBtnDeleteMom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImplMoM.wsDeleteMom(mMomPojo.getMomid(), pos);
            }
        });
    }

    @Override
    public void populateMomList(ArrayList<MomPojo> mList) {
        data = mList;
        if(!data.isEmpty()){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            momAdapterMOM = new MomAdapter(getContext(), data, this);
            mRecyclerView.setAdapter(momAdapterMOM);
        }else{
            checkForNoData();
        }
    }

    @Override
    public void checkForNoData() {
        mLinearNoData.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void addMOMDetailsToList(MomPojo mMomPojo) {
        data.add(mMomPojo);
        momAdapterMOM.notifyDataSetChanged();
    }

    @Override
    public void removeMoMFromList(int position) {
        data.remove(position);
        momAdapterMOM.notifyDataSetChanged();
        alertDialog.dismiss();
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
