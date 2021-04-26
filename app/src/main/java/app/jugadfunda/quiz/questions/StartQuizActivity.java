package app.jugadfunda.quiz.questions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.OptionResponse;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.home.HomeActivity;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.pojo.SelectedOptions;

public class StartQuizActivity extends AppCompatActivity implements StartQuizInterfaceView, View.OnClickListener {

   private ImplStartQuizPresenter implStartQuizPresenter;
   private ArrayList<QuestionListResponse> qList;
   private ArrayList<SelectedOptions> mSelectedList;
   private ArrayList<Integer> posList;
   private long quizid = 0;
   private String mobilenumber = null;
   private  String title = "";
   private TextView mTVnoData;
   private CountDownTimer cTimer = null;
   private TextView mTvtimer;
   private TextView mTvInstructions;
   private int minutetime = 1;
   private int secondtime = 60;
   private String mInstructions = "";

    private TextView tv_question_count;
    private TextView tv_question;
    private RadioButton rb_option1;
    private RadioButton rb_option2;
    private RadioButton rb_option3;
    private RadioButton rb_option4;
    private RadioButton rb_option5;
    private int position = 0;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        setUI();
    }

    private void setUI(){
        implStartQuizPresenter = new ImplStartQuizPresenter(this,this);
        mTVnoData = findViewById(R.id.tv_nodata);
        mTvtimer = findViewById(R.id.tv_timer);
        mTvInstructions = findViewById(R.id.tv_quizinstructions);

        tv_question_count = findViewById(R.id.tv_quescount);
        tv_question = findViewById(R.id.tv_question);
        rb_option1 = findViewById(R.id.rb_radio1);
        rb_option2 = findViewById(R.id.rb_radio2);
        rb_option3 = findViewById(R.id.rb_radio3);
        rb_option4 = findViewById(R.id.rb_radio4);
        rb_option5 = findViewById(R.id.rb_radio5);

        setListener();

        mSelectedList = new ArrayList<>();
        posList = new ArrayList<>();

        Intent intent = getIntent();
        quizid = intent.getLongExtra("qiz",0);
        title = intent.getStringExtra("title");
        minutetime = intent.getIntExtra("d", 0);
        int totalquestions = intent.getIntExtra("tq",0);
        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        mobilenumber = sh.getString("mb","");

        mInstructions = getInstructions(minutetime, totalquestions);

        ((TextView)findViewById(R.id.tv_title)).setText("Testing");

        implStartQuizPresenter.wsQuestionlist(1);

        hidepager();
    }

    void setListener(){
        findViewById(R.id.tv_next).setOnClickListener(this);
        findViewById(R.id.tv_skip).setOnClickListener(this);
        findViewById(R.id.tv_submittest).setOnClickListener(this);
        findViewById(R.id.btn_startquiz).setOnClickListener(this);
        rb_option1.setOnClickListener(this);
        rb_option2.setOnClickListener(this);
        rb_option3.setOnClickListener(this);
        rb_option4.setOnClickListener(this);
        rb_option5.setOnClickListener(this);
    }

    @Override
    public void passList(ArrayList<QuestionListResponse> questionsList) {
        qList = questionsList;
        setData(position);
    }

    @Override
    public void selectOptions(int pos, String option, long questionId) {
        SelectedOptions selectedOptions = new SelectedOptions(pos, option, questionId);
        if (!posList.contains(pos)) {
            mSelectedList.add(selectedOptions);
            posList.add(pos);
        } else {
            selectedOptions = mSelectedList.get(pos);
            selectedOptions.setOption(option);
            selectedOptions.setQuestionId(questionId);
            selectedOptions.setPos(pos);
        }
    }



    @Override
    public void showEmptyData() {
        findViewById(R.id.linear_nodata).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_submit).setVisibility(View.GONE);
        mTVnoData.setText("No Questions Found");
    }

    @Override
    public JSONArray convertListtoJSONArray(ArrayList<SelectedOptions> mSelectedList) {
        JSONArray list = new JSONArray();
        JSONObject obj = null;
        try {
        for (SelectedOptions options : mSelectedList){
            obj = new JSONObject();
            obj.put("qid", options.getQuestionId());
            obj.put("opt", options.getOption());
            list.put(obj);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void clearData() {
        rb_option1.setChecked(false);
        rb_option2.setChecked(false);
        rb_option3.setChecked(false);
        rb_option4.setChecked(false);
        rb_option5.setChecked(false);

        mTvtimer.setText("Done!");
        cTimer.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_next:
                ++position;
                setData(position);
                if(qList.size() - 1 == position){
                    findViewById(R.id.tv_submittest).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_next).setVisibility(View.GONE);
                    findViewById(R.id.tv_skip).setVisibility(View.GONE);
                }

                break;

            case R.id.tv_skip:
                ++position;
                setData(position);
                if(qList.size() - 1 == position){
                    findViewById(R.id.tv_submittest).setVisibility(View.VISIBLE);
                    findViewById(R.id.tv_skip).setVisibility(View.GONE);
                    findViewById(R.id.tv_next).setVisibility(View.GONE);
                }

                break;

            case R.id.tv_submittest:
                Toast.makeText(this,"dshyfisdf",Toast.LENGTH_LONG).show();
                JSONArray list = convertListtoJSONArray(mSelectedList);
                if(list.length() == 0){
                    Toast.makeText(this, "Please Attempt atleast one Question",Toast.LENGTH_LONG).show();
                }else{
                    implStartQuizPresenter.wsAddAnswers(quizid,list, mobilenumber,40,32);
                }
                break;

            case R.id.btn_startquiz:
                showpager();
                break;

            case R.id.rb_radio1:
                 selectOptions(position, "A", qList.get(position).getQuesid());
                break;

            case R.id.rb_radio2:
                selectOptions(position, "B", qList.get(position).getQuesid());
                break;

            case R.id.rb_radio3:
                selectOptions(position, "C", qList.get(position).getQuesid());
                break;

            case R.id.rb_radio4:
                selectOptions(position, "D", qList.get(position).getQuesid());
                break;

            case R.id.rb_radio5:
                selectOptions(position, "E", qList.get(position).getQuesid());
                break;
        }
    }

    void startTimer(){
        cTimer = new CountDownTimer(minutetime * secondtime * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTvtimer.setText("Time Remaining - "+String.format("%d minute, %d second",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                mTvtimer.setText("Done!");
            }
        };
        cTimer.start();
    }

    @Override
    public void onBackPressed() {
        if(mTvtimer.getText().equals("Done!")){
            checkForAutoLogin();
        }else{
            Toast.makeText(this, "Please Submit Psychometric Test First", Toast.LENGTH_LONG).show();
        }
    }


    String getInstructions(int duration, int totalnoquestions){
        String str = "1. The duration of the test is "+duration+" minutes*.\n" +
                "2. The timer begins automatically once you start the test.\n" +
                "3. The test will end automatically after "+duration+" minutes.\n" +
                "4. There are a total of "+totalnoquestions+" questions.\n" +
                "5. Each question has 4 options and only ONE correct answer.\n" +
                "6. You can either answer the question or skip it, you cannot go back to the previous question or change your answer after going on to the next question.\n" +
                "7. There is no negative marking.\n" +
                "8. Each question carries 1 mark.\n" +
                "9. Only 50 students will be shortlisted per institute.\n" +
                "10. If 2 students have the same score the one who submitted the test first will be given priority.";
        return str;
    }

    void showpager(){
        findViewById(R.id.tv_timer).setVisibility(View.VISIBLE);
        findViewById(R.id.cvdata).setVisibility(View.VISIBLE);
        findViewById(R.id.recycler_dots).setVisibility(View.VISIBLE);

        findViewById(R.id.linear_quizinstruction).setVisibility(View.GONE);
        findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
        startTimer();
    }


    void hidepager(){
        findViewById(R.id.cvdata).setVisibility(View.GONE);
        findViewById(R.id.linear_quizinstruction).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_timer).setVisibility(View.GONE);
        findViewById(R.id.recycler_dots).setVisibility(View.GONE);

        mTvInstructions.setText(mInstructions);
    }


    void setData(int pos){
        QuestionListResponse questionListResponse = qList.get(pos);

        tv_question.setText(questionListResponse.getTitle());
       tv_question_count.setText("Question "+(pos+1));
        ArrayList<OptionResponse>optionlist = questionListResponse.getOptions();
        if(!optionlist.isEmpty()){
            rb_option1.setText(optionlist.get(0).getOpts());
            rb_option2.setText(optionlist.get(1).getOpts());
            rb_option3.setText(optionlist.get(2).getOpts());
        }

        rb_option1.setOnClickListener(this);
        rb_option2.setOnClickListener(this);
        rb_option3.setOnClickListener(this);
        rb_option4.setOnClickListener(this);
        rb_option5.setOnClickListener(this);


        rb_option1.setChecked(questionListResponse.getOption() == "A");
        rb_option2.setChecked(questionListResponse.getOption() == "B");
        rb_option3.setChecked(questionListResponse.getOption() == "C");

        if(optionlist.size() == 3){
            rb_option4.setVisibility(View.GONE);
            rb_option5.setVisibility(View.GONE);
        }else if (optionlist.size() == 4){
            rb_option4.setText(optionlist.get(3).getOpts());
            rb_option5.setVisibility(View.GONE);
           rb_option4.setChecked(questionListResponse.getOption() == "D");
        }else if (optionlist.size() == 5){
            rb_option4.setText(optionlist.get(3).getOpts());
            rb_option5.setText(optionlist.get(4).getOpts());
            rb_option4.setChecked(questionListResponse.getOption() == "D");
            rb_option5.setChecked(questionListResponse.getOption() == "E");
        }
    }


    void checkForAutoLogin() {
        SharedPreferences sp = getSharedPreferences("profile", MODE_PRIVATE);
        String AUTO_LOGIN = sp.getString("autologin", "");
        Intent intent = null;
        if (AUTO_LOGIN.equals("yes")) {
            intent = new Intent(StartQuizActivity.this, HomeActivity.class);
        } else {
            intent = new Intent(StartQuizActivity.this, LoginActivity.class);
        }
        intent.putExtra("check","quiz");
        startActivity(intent);
    }
}
