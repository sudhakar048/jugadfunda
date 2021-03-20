package app.jugadfunda.home.sessions.sessiondetailsandreport;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;
import app.jugadfunda.R;
import app.jugadfunda.ContentActivity;
import app.jugadfunda.home.pojo.QueryListPojo;
import app.jugadfunda.home.pojo.SessionDetailsPojo;
import app.jugadfunda.home.sessions.QueryListActivity;
import app.jugadfunda.validate.Validate;

public class SessionDetailsandReportActivity extends AppCompatActivity implements View.OnClickListener, SessionDetailsView {
    private TextView tv_sessiondate;
    private TextView tv_sessiontime;
    private TextView tv_sessionvenue;
    private TextView tv_agenda;
    private TextView tv_attend;
    private TextView tv_linkurl;
    private TextView tv_noofattendees;
    private TextView tv_summery;
    private TextView tv_feedback;
    private TextView tv_drivelink;
    private TextView tv_paymentdate;
    private TextView tv_utr;
    private TextView tv_amount;
    private TextView tv_confirmation;
    private Button btn_accept;
    private Button btn_reject;
    private ImplSessionDetails mImplSessionDetails;
    private SessionDetailsPojo data = null;
    private long mUserId=0;
    private AlertDialog.Builder alert1;
    private AlertDialog.Builder alert2;
    private AlertDialog.Builder alert3;
    private AlertDialog alertDialog;
    private TextInputEditText et_noofattendees;
    private TextInputEditText et_summery;
    private TextInputEditText et_feedback;
    private TextInputEditText et_drivelink;
    private TextView tv_rejectionmsg;
    private TextView tv_rejectiontitle;
    private LinearLayout mLinearReason;
    private LinearLayout mLinearYesNo;
    private EditText mEtRejectionReason;
    private Date currentDate;
    private Date sessionDate2;
    private ArrayList<QueryListPojo> qlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_detailsand_report);

        setUI();

    }

    void setUI(){
        //session details
        tv_sessiondate = findViewById(R.id.tv_sessiondate);
        tv_sessiontime = findViewById(R.id.tv_sessiontime);
        tv_sessionvenue = findViewById(R.id.tv_sessionvenue);
        tv_agenda = findViewById(R.id.tv_agenda);
        tv_attend = findViewById(R.id.tv_attend);
        tv_linkurl = findViewById(R.id.tv_linkurl);

        //report details
        tv_noofattendees = findViewById(R.id.tv_attendees);
        tv_summery = findViewById(R.id.tv_summery);
        tv_feedback = findViewById(R.id.tv_feedback);
        tv_drivelink = findViewById(R.id.tv_drivelink);

        //payment details
        tv_paymentdate = findViewById(R.id.tv_paymentdate);
        tv_utr = findViewById(R.id.tv_utr);
        tv_amount = findViewById(R.id.tv_amt);
        tv_confirmation = findViewById(R.id.tv_confirmation);

        btn_accept = findViewById(R.id.btn_accept);
        btn_reject = findViewById(R.id.btn_reject);
        tv_rejectionmsg = findViewById(R.id.tv_rejectionmsg);
        tv_rejectiontitle = findViewById(R.id.tv_rejectiontitle);

        setListener();

        long mSessionId = getIntent().getLongExtra("sessionid", 0);
        mUserId = getIntent().getLongExtra("uid", 0);
        qlist = (ArrayList<QueryListPojo>) getIntent().getSerializableExtra("qlist");

        mImplSessionDetails = new ImplSessionDetails(this, this);
        mImplSessionDetails.wsSessionDetails(mSessionId, mUserId);
    }

    void setListener(){
        findViewById(R.id.btn_accept).setOnClickListener(this);
        findViewById(R.id.btn_reject).setOnClickListener(this);
        findViewById(R.id.btn_nextstep).setOnClickListener(this);
        findViewById(R.id.btn_reopen).setOnClickListener(this);
        findViewById(R.id.btn_renumeration).setOnClickListener(this);
        findViewById(R.id.btn_rerequest_renumeration).setOnClickListener(this);
        findViewById(R.id.tv_linkurl).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_drivelink).setOnClickListener(this);
        findViewById(R.id.tv_viewquery).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_accept:
                if(data.getResponse() == 0){
                    mImplSessionDetails.wsAcceptReject(data.getSessionId(), mUserId, "Accept", "");
                }
                break;

            case R.id.btn_reject:
                  loadRejectionReason();
                break;

            case R.id.tv_linkurl:
                Intent intent1 = new Intent(SessionDetailsandReportActivity.this, ContentActivity.class);
                intent1.putExtra("url",data.getLink());
                startActivity(intent1);
                break;

            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.btn_nextstep:
                loadNextStepPopup();
                break;

            case R.id.btn_renumeration:
                loadSubmitReportLayout();
                break;

            case R.id.btn_reopen:
                mImplSessionDetails.wsUpdateStatus(data.getSessionId(), mUserId, "Reopen");
                break;

            case R.id.tv_drivelink:
                Intent intent2 = new Intent(SessionDetailsandReportActivity.this, ContentActivity.class);
                intent2.putExtra("url",data.getReportlink());
                startActivity(intent2);
                break;

            case R.id.btn_rerequest_renumeration:
                mImplSessionDetails.wsUpdateStatus(data.getSessionId(), mUserId, "Remunaration");
                break;

            case R.id.tv_viewquery:
                Intent intent = new Intent(this, QueryListActivity.class);
                intent.putExtra("list", qlist);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void showSessionDetails(SessionDetailsPojo mSessionDetailsPojo) {
        data = mSessionDetailsPojo;

        tv_sessiondate.setText(data.getDate());
        tv_sessiontime.setText(data.getTime());
        tv_sessionvenue.setText(data.getVenue());
        tv_agenda.setText(data.getPurpose());
        tv_attend.setText(data.getForwhom());
        tv_linkurl.setText(data.getLink());

        if(!qlist.isEmpty()){
            findViewById(R.id.tv_viewquery).setVisibility(View.VISIBLE);
        }
        if(data.getLink().isEmpty()){
            tv_linkurl.setVisibility(View.GONE);
            findViewById(R.id.tv_urltitle).setVisibility(View.GONE);
        }

        long millis=System.currentTimeMillis();
        currentDate=new Date(millis);

        //converting session date to sql date
        String[] str = data.getDate().split("/");
        String sDate = str[2]+"-"+str[0]+"-"+str[1];
        sessionDate2 = Date.valueOf(sDate);//converting string into sql date


        if(data.getCancelstatus() == 1){
            case10();
        }else if(data.getResponse() == 2){  // if rejected    // case 1
           case1(data.getResreason());
        }else if(data.getResponse() == 0 && data.getNotattendstatus() == 0  && data.getReportId() == 0 && data.getStatus() == 0 && data.getReopen() == 0){  //acceptedbutnotNextStep  // case 2
            case9();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 0  && data.getReportId() == 0 && data.getStatus() == 0 && data.getReopen() == 0){  //acceptedbutnotNextStep  // case 2
            case2();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 2  && data.getReportId() == 0 && data.getStatus() == 0 && data.getReopen() == 0){  //acceptedbutnotNextStep  // case 3
            case3();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 1 && data.getReportId() == 0 && data.getStatus() == 0 && data.getReopen() == 0){   //acceptednextstepbutnotReportSubmitted   // case 4
            case4();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 1 && data.getReportId() == 0 && data.getStatus() == 0 && data.getReopen() == 1){   //acceptednextstepbutnotReportSubmitted   // case 5
            case5();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 1 && data.getReportId() == 0 && data.getStatus() == 0 && data.getReopen() == 2){   //acceptednextstepbutnotReportSubmitted   // case 5
            case8();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 1 && data.getReportId() != 0 && data.getStatus() == 0){  //acceptedNextStepAndReportSubmitted    // case 6
            case6();
        }else if(data.getResponse() == 1 && data.getNotattendstatus() == 1 && data.getReportId() != 0 && data.getStatus() == 1){  //acceptedNextStepAndReportSubmitted     // case 7
            case7();
        }

        if(data.getReportId() != 0){
          findViewById(R.id.cv_sessionreport).setVisibility(View.VISIBLE);
          tv_noofattendees.setText(""+data.getAttendees());
          tv_summery.setText(data.getSummary());
          tv_feedback.setText(data.getSuggession());
          if(!data.getReportlink().isEmpty()){
              tv_drivelink.setText(data.getReportlink());
          }else{
              tv_drivelink.setVisibility(View.GONE);
              findViewById(R.id.tv_drivelinktitle).setVisibility(View.GONE);
          }

          findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        }

        if(data.getRemuneId() != 0 && data.getStatus() == 1){
            findViewById(R.id.cv_paymentdetails).setVisibility(View.VISIBLE);
            tv_paymentdate.setText(data.getPaydate());
            tv_utr.setText(data.getUtrno());
            tv_amount.setText(""+data.getAmount());
            tv_confirmation.setText(data.getConfirmmsg());
        }else{
            findViewById(R.id.cv_paymentdetails).setVisibility(View.GONE);
        }

        if(data.getReportId() != 0 && data.getRemuneId() == 0 && data.getStatus() == 1){
            findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setBtns(String mStatus) {
        if(mStatus.toLowerCase().equals("accept")){
            case2();
        }else if(mStatus.toLowerCase().equals("reject")){
            dismissDialog();
           case1(mEtRejectionReason.getText().toString());
        }else if(mStatus.toLowerCase().toLowerCase().equals("next step")){
            alertDialog.dismiss();
            case4();
        }else if(mStatus.toLowerCase().toLowerCase().equals("attend")){
            alertDialog.dismiss();
            case4();
        }else if(mStatus.toLowerCase().equals("reopen")){
            case5();
        }else if(mStatus.toLowerCase().equals("notattend")){
            clearReasonForm();
            case3();
        }else if(mStatus.toLowerCase().equals("submit report claim remuneration")){
            case6();
        }
    }

    @Override
    public void dismissDialog() {
        alertDialog.dismiss();
    }

    @Override
    public void clearReasonForm() {
        mEtRejectionReason.setText("");
        alertDialog.dismiss();
    }

    @Override
    public void clearReportForm() {
        et_noofattendees.setText("");
        et_summery.setText("");
        et_feedback.setText("");
        et_drivelink.setText("");

       alertDialog.dismiss();
    }

    @Override
    public void checkRerequestRenumerationBtn(int check) {
        if(check == 1){
            findViewById(R.id.btn_rerequest_renumeration).setEnabled(false);
        }else{
            findViewById(R.id.btn_rerequest_renumeration).setEnabled(true);
        }
    }


    //Submit Report
    void loadSubmitReportLayout(){
        LayoutInflater li = LayoutInflater.from(SessionDetailsandReportActivity.this);
        View view = (ScrollView)li.inflate(R.layout.layout_submitreport, null);
        et_noofattendees = view.findViewById(R.id.et_noofattendees);
        et_summery = view.findViewById(R.id.et_summery);
        et_feedback = view.findViewById(R.id.et_feedback);
        et_drivelink = view.findViewById(R.id.et_drivelink);
        alert1 = new AlertDialog.Builder(this);
        alert1.setView(view);
        alertDialog = alert1.show();
        view.findViewById(R.id.btn_reportsubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = validateReportForm(et_noofattendees.getText().toString(), et_summery.getText().toString(), et_feedback.getText().toString(),et_drivelink.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(SessionDetailsandReportActivity.this,""+check,Toast.LENGTH_LONG).show();
                }else{
                    mImplSessionDetails.wsAddReport(data.getSessionId(), mUserId, et_noofattendees.getText().toString(),et_summery.getText().toString(),et_feedback.getText().toString(), et_drivelink.getText().toString());

                }
            }
        });

        view.findViewById(R.id.btn_reportclose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    //User attended session in yes / no
    void loadNextStepPopup(){
        LayoutInflater li = LayoutInflater.from(SessionDetailsandReportActivity.this);
        View view = (LinearLayout)li.inflate(R.layout.layout_nextstep, null);
        mEtRejectionReason = view.findViewById(R.id.et_rejectionreason);
        mLinearReason =  view.findViewById(R.id.linear_rejectionreason);
        mLinearYesNo = view.findViewById(R.id.linear_yes_no);

        alert2 = new AlertDialog.Builder(this);
        alert2.setView(view);
        alertDialog = alert2.show();

        view.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImplSessionDetails.wsUpdateSessionStatus(data.getSessionId(), mUserId, "Attend","");
            }
        });

        view.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearYesNo.setVisibility(View.GONE);
                mLinearReason.setVisibility(View.VISIBLE);

            }
        });


        view.findViewById(R.id.btn_rejectionreason).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = validateReason(mEtRejectionReason.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(SessionDetailsandReportActivity.this,""+check,Toast.LENGTH_LONG).show();
                }else{
                    mImplSessionDetails.wsUpdateSessionStatus(data.getSessionId(), mUserId, "NotAttend",mEtRejectionReason.getText().toString());
                }
            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btn_rejectionreasonclose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        
    }

    String validateReportForm(String noofattendees,String summery, String feedback, String url){
        if(!Pattern.matches(Validate.NO_ATTENDEES,noofattendees) || noofattendees.isEmpty()){
            return "Enter Valid No. of Attendees";
        }else if(summery.isEmpty()){
            return "Summery of Session can't be empty";
        }else if(feedback.isEmpty()){
            return "Feedback / Suggestions can't be empty";
        }else if(!url.isEmpty()){
            if(!Pattern.matches(Validate.REGEX_LINK,url)){
                return "Please Enter valid url";
            }
        }
        return "ok";
    }

    String validateReason(String reason){
        if(reason.isEmpty()){
            return "Reason can't be empty";
        }
        return "ok";
    }

    //load Session Rejection Reason
    void loadRejectionReason(){
        LayoutInflater li = LayoutInflater.from(SessionDetailsandReportActivity.this);
        View view = (LinearLayout)li.inflate(R.layout.layout_rejectionreason, null);
        mLinearReason =  view.findViewById(R.id.linear_rejectionreason);
        mEtRejectionReason = view.findViewById(R.id.et_rejectionreason);
        Button mBtRejectionReason = view.findViewById(R.id.btn_rejectionreason);
        Button mBtnRejectionClose = view.findViewById(R.id.btn_rejectionreasonclose);

        mLinearReason.setVisibility(View.VISIBLE);
        alert3 = new AlertDialog.Builder(this);
        alert3.setView(view);
        alertDialog = alert3.show();

        mBtRejectionReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = validateReason(mEtRejectionReason.getText().toString());
                if(!check.equals("ok")){
                    Toast.makeText(SessionDetailsandReportActivity.this,check,Toast.LENGTH_LONG).show();
                }else{
                    mImplSessionDetails.wsAcceptReject(data.getSessionId(), mUserId, "Reject", mEtRejectionReason.getText().toString());
                }
            }
        });

        mBtnRejectionClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionDetailsandReportActivity.this,"Clicked",Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
    }


    void case1(String rejectionreason){
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectionmsg.setText(rejectionreason);
        tv_rejectiontitle.setText("Rejection Reason");
    }

    void case2(){
        long nextstepsessiontime = sessionDate2.getTime() + 24*60*60*1000;
        Date nextstepsessionDate = new Date(nextstepsessiontime);
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);

        if(currentDate.getTime() < sessionDate2.getTime() - 24*60*60*1000){
            findViewById(R.id.btn_reject).setVisibility(View.VISIBLE);
        }
        if(nextstepsessionDate.getTime() <= currentDate.getTime()){
            findViewById(R.id.btn_nextstep).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        }
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        tv_rejectionmsg.setText("Session Accepted");
        tv_rejectiontitle.setVisibility(View.GONE);
    }

    void case3(){
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectionmsg.setText(data.getNotattendreason());
        tv_rejectiontitle.setVisibility(View.VISIBLE);
        tv_rejectiontitle.setText("Session Accepted but not Attended and Reason for Session Not Attend");
    }

    void case4(){
        long reopensessiontime = sessionDate2.getTime() + 7*24*60*60*1000;
        Date reopensessionDate = new Date(reopensessiontime);
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        if(reopensessionDate.getTime() <= currentDate.getTime()){
            findViewById(R.id.btn_reopen).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
            tv_rejectionmsg.setText("Session Accepted but you forget to Submit Report and Claim Renumeration. Click on Reopen for Claiming Renumeration");
        }else{
            findViewById(R.id.btn_reopen).setVisibility(View.GONE);
            findViewById(R.id.btn_renumeration).setVisibility(View.VISIBLE);
        }
        tv_rejectiontitle.setVisibility(View.GONE);

    }

    void case5(){
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectionmsg.setText("Session Accepted and you also requested to Reopen "+"Submit Report and Claim Renumeration"+" Option.");
        tv_rejectiontitle.setVisibility(View.GONE);

    }

    void case6(){
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectionmsg.setText("Session Accepted and Report Submitted but yet not Verified and Approved by Administrator");
        tv_rejectiontitle.setVisibility(View.GONE);

    }

    void case7(){
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectionmsg.setText("Session Accepted and Report Submitted and also Verified and Approved by Administrator");
        tv_rejectiontitle.setVisibility(View.GONE);
    }

    void case8(){
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectionmsg.setText("Session Accepted and your request to Reopen "+"Submit Report and Claim Renumeration"+" has been Accepted by Administrator");
        tv_rejectiontitle.setVisibility(View.GONE);
    }

    void case9(){
        findViewById(R.id.btn_accept).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);

        if(currentDate.getTime() < sessionDate2.getTime() - 24*60*60*1000){
            findViewById(R.id.btn_reject).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
    }

    void case10() {
        findViewById(R.id.btn_accept).setVisibility(View.GONE);
        findViewById(R.id.btn_renumeration).setVisibility(View.GONE);
        findViewById(R.id.btn_reject).setVisibility(View.GONE);
        findViewById(R.id.btn_nextstep).setVisibility(View.GONE);
        findViewById(R.id.btn_reopen).setVisibility(View.GONE);
        findViewById(R.id.btn_rerequest_renumeration).setVisibility(View.GONE);
        tv_rejectiontitle.setVisibility(View.VISIBLE);
        tv_rejectiontitle.setText("Reason of Cancelled Session");
        tv_rejectionmsg.setText(data.getCancelreason());
    }
}