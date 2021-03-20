package app.jugadfunda.home.sessions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.adapter.MentorQueryListAdapter;
import app.jugadfunda.home.pojo.QueryListPojo;

public class QueryListActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRvQueryList;
    private TextView mTvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_list);

        setUI();
    }

    void setUI(){
        mRvQueryList = findViewById(R.id.recycler);
        mTvMsg = findViewById(R.id.tv_title_rv);

        mTvMsg.setText("User queries to resolve during schedule session");
        ArrayList<QueryListPojo> qlist = (ArrayList<QueryListPojo>) getIntent().getSerializableExtra("list");

        Log.e("qlist",""+qlist);
        if(!qlist.isEmpty()){
            mRvQueryList.setLayoutManager(new LinearLayoutManager(this));
            mRvQueryList.setAdapter(new MentorQueryListAdapter(this, qlist));
        }

        setListener();
     }

     void setListener(){
        findViewById(R.id.iv_back).setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}