package app.jugaadfunda.learningcompanion.quiz.comparequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.apiresponse.QuestionListResponse;
import app.jugaadfunda.learningcompanion.quiz.adapter.CompareQuizRecyclerAdapter;

public class CompareQuizActivity extends AppCompatActivity implements CompareQuizInterfaceView {

    private long qzid = 0;
    private ImplCompareQuizPresenter implCompareQuizPresenter;
    private RecyclerView recyclerView;
    ArrayList<QuestionListResponse> mList;
    private TextView mTvNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_quiz);
        ((TextView)findViewById(R.id.tv_title)).setText("Quiz Title");

        Intent intent = getIntent();
        qzid = intent.getLongExtra("qzid",0);

        setRecycler();
    }

    private void setRecycler(){
        recyclerView=findViewById(R.id.recycler);
        mTvNoData = findViewById(R.id.tv_nodata);
        findViewById(R.id.iv_back).setOnClickListener(view -> onBackPressed());

        implCompareQuizPresenter = new ImplCompareQuizPresenter(this, this);
        implCompareQuizPresenter.wsQuestionlist(qzid);
    }

    @Override
    public void passDataToRecyclerView(ArrayList<QuestionListResponse> questionsList) {
        mList = questionsList;
        if(mList != null){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new CompareQuizRecyclerAdapter(this, mList));
        }else{
            findViewById(R.id.recycler).setVisibility(View.GONE);
            findViewById(R.id.linear_nodata).setVisibility(View.VISIBLE);
            mTvNoData.setText("No Questions Found");
        }
    }
}
