package app.jugadfunda.quiz.questions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import app.jugadfunda.R;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.home.HomeActivity;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.comparequiz.CompareQuizActivity;
import app.jugadfunda.quiz.adapter.QuizQuestionsRecyclerAdapter;
import app.jugadfunda.quiz.pojo.SelectedOptions;

public class StartQuizActivity extends AppCompatActivity implements StartQuizInterfaceView, View.OnClickListener {

   private ViewPager2 viewPager2;
   private DotsIndicator indicator;
   private ImplStartQuizPresenter implStartQuizPresenter;
   private ArrayList<QuestionListResponse> qList;
   private ArrayList<SelectedOptions> mSelectedList;
   private ArrayList<Integer> posList;
   private long quizid = 0;
   private String mobilenumber = null;
   private  String title = "";
   private TextView mTVnoData;
   private QuizQuestionsRecyclerAdapter adapter;
   private CountDownTimer cTimer = null;
   private TextView mTvtimer;
   private int minutetime = 40;
   private int secondtime = 60;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        setRecycler();
    }

    private void setRecycler(){
        implStartQuizPresenter = new ImplStartQuizPresenter(this,this);
        indicator = findViewById(R.id.dots_indicator);
        viewPager2=findViewById(R.id.pager);
        mTVnoData = findViewById(R.id.tv_nodata);
        mTvtimer = findViewById(R.id.tv_timer);
        setListener();

        mSelectedList = new ArrayList<>();
        posList = new ArrayList<>();

        Intent intent = getIntent();
        quizid = intent.getLongExtra("qiz",0);
        title = intent.getStringExtra("title");

        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        mobilenumber = sh.getString("mb","");
        if(mobilenumber.contains("-")){
            mobilenumber = mobilenumber.substring(4,13);
        }

        ((TextView)findViewById(R.id.tv_title)).setText(title);

        implStartQuizPresenter.wsQuestionlist(quizid);
    }

    void setListener(){
        findViewById(R.id.iv_menu).setOnClickListener(this::openMenu);
        findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.tv_next).setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);
        findViewById(R.id.floating_add).setOnClickListener(this);
    }
    private void openMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_quiz, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            Intent intent = new Intent(StartQuizActivity.this, CompareQuizActivity.class);
            intent.putExtra("qzid",quizid);
            intent.putExtra("title",title);
            startActivity(intent);
            return false;
        });
        popupMenu.show();
    }

    @Override
    public void passDataToRecyclerView(ArrayList<QuestionListResponse> questionsList) {
        qList = questionsList;
        adapter = new QuizQuestionsRecyclerAdapter(this, qList,this);
        viewPager2.setAdapter(adapter);

        indicator.setViewPager2(viewPager2);

        startTimer();
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


        QuestionListResponse qls = qList.get(pos);
        qls.setOption(option);
        qList.set(pos, qls);

        adapter.notifyItemChanged(pos, qls);

    }

    @Override
    public void refreshAdapter() {
        for(int i =0 ; i< qList.size(); i++){
            QuestionListResponse qlp = qList.get(i);
            qlp.setOption("0");
            qList.set(i, qlp);
        }
        adapter = new QuizQuestionsRecyclerAdapter(this, qList,this);
        viewPager2.setAdapter(adapter);
        findViewById(R.id.floating_add).setVisibility(View.GONE);
    }

    @Override
    public void showEmptyData() {
        findViewById(R.id.linear_nodata).setVisibility(View.VISIBLE);
        viewPager2.setVisibility(View.GONE);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_next:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                if (qList.size()  == viewPager2.getCurrentItem()+1) {
                    findViewById(R.id.floating_add).setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tv_back:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1);
                break;

            case R.id.floating_add:
                JSONArray list = convertListtoJSONArray(mSelectedList);
                if(list.length() == 0){
                    Toast.makeText(this, "Please Attempt atleast one Question",Toast.LENGTH_LONG).show();
                }else{
                    implStartQuizPresenter.wsAddAnswers(quizid,list, mobilenumber,40,32);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        checkForAutoLogin();
    }

    void checkForAutoLogin(){
        SharedPreferences sp = getSharedPreferences("profile",MODE_PRIVATE);
        String AUTO_LOGIN = sp.getString("autologin","");
        Intent intent = null;
        if(AUTO_LOGIN.equals("yes")){
            intent = new Intent(StartQuizActivity.this, HomeActivity.class);
        }else{
            intent = new Intent(StartQuizActivity.this, LoginActivity.class);
        }
        intent.putExtra("check","quiz");
        startActivity(intent);
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
}
