package app.jugadfunda.quiz.questions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.home.HomeActivity;
import app.jugadfunda.login.LoginActivity;
import app.jugadfunda.quiz.comparequiz.CompareQuizActivity;
import app.jugadfunda.quiz.adapter.QuizQuestionsRecyclerAdapter;
import app.jugadfunda.quiz.pojo.SelectedOptions;

public class StartQuizActivity extends AppCompatActivity implements StartQuizInterfaceView, View.OnClickListener {

   private RecyclerView recyclerView;
   private ImplStartQuizPresenter implStartQuizPresenter;
   private ArrayList<QuestionListResponse> qList;
   private ArrayList<SelectedOptions> mSelectedList;
   private ArrayList<Integer> posList;
   private static String questionids = "";
   private static String optionids = "";
   private long quizid = 0;
   private String mobilenumber = null;
   private  String title = "";
   private long mUserId = 0;
   private TextView mTVnoData;
   private QuizQuestionsRecyclerAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        setRecycler();
        findViewById(R.id.iv_menu).setOnClickListener(this::openMenu);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());

        Intent intent = getIntent();
        quizid = intent.getLongExtra("qiz",0);
        title = intent.getStringExtra("title");

        SharedPreferences sh = getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid",0);
        mobilenumber = sh.getString("mb","");

        ((TextView)findViewById(R.id.tv_title)).setText(title);

        implStartQuizPresenter.wsQuestionlist(quizid);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setRecycler(){
        implStartQuizPresenter = new ImplStartQuizPresenter(this,this);
        recyclerView=findViewById(R.id.recycler);
        mTVnoData = findViewById(R.id.tv_nodata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSelectedList = new ArrayList<>();
        posList = new ArrayList<>();


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
        if(qList != null){
            adapter = new QuizQuestionsRecyclerAdapter(this, qList,this);
            recyclerView.setAdapter(adapter);
        }else{
            findViewById(R.id.linear_nodata).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.btn_submit).setVisibility(View.GONE);
            mTVnoData.setText("No Questions Found");
        }

    }

    @Override
    public void selectOptions(int pos, long optionid) {
        SelectedOptions selectedOptions = new SelectedOptions(pos, optionid);
        if(!posList.contains(pos)){
            mSelectedList.add(selectedOptions);
            posList.add(pos);
        }else{
            selectedOptions = mSelectedList.get(pos);
            selectedOptions.setOptionid(optionid);
            selectedOptions.setPos(pos);
        }
        if(mSelectedList.size() == qList.size()){
            findViewById(R.id.btn_submit).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void serAdapter() {
        adapter = new QuizQuestionsRecyclerAdapter(this, qList,this);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_submit).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                for(SelectedOptions selectedOptions : mSelectedList){
                    QuestionListResponse questionListResponse = qList.get(selectedOptions.getPos());
                    questionids += questionListResponse.getQuesid()+",";
                    optionids += selectedOptions.getOptionid()+",";
                }
                questionids = questionids.substring(0,questionids.length()-1);
                optionids = optionids.substring(0,optionids.length()-1);

                implStartQuizPresenter.wsAddAnswers(quizid,questionids,optionids,mUserId, mobilenumber);

                questionids = "";
                optionids = "";
                break;
        }
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sp = getSharedPreferences("profile",MODE_PRIVATE);
        String AUTO_LOGIN = sp.getString("autologin","");
        if(AUTO_LOGIN.equals("yes")){
          startActivity(new Intent(StartQuizActivity.this, HomeActivity.class));
        }else{
            startActivity(new Intent(StartQuizActivity.this, LoginActivity.class));
        }
    }
}
