package app.jugadfunda.quiz.quizlist;

import androidx.fragment.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.QuizListResponse;
import app.jugadfunda.quiz.adapter.QuizListRecyclerAdapter;

public class QuizFragment extends Fragment implements QuizListView {
    private ImplQuizListPresenter implQuizListPresenter = null;
    private RecyclerView recycler;
    private ProgressDialog dialog;
    private LinearLayout mLinearNodata;
    private TextView mTVNodata;
    private String mobilenumber = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz, container, false);
        loadProgressBar();
        setRecyclerView(view);

        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mobilenumber = sh.getString("mb","");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.cancel();
    }

    private void setRecyclerView(View view) {
        recycler = view.findViewById(R.id.recycler);
        mLinearNodata = view.findViewById(R.id.linear_nodata);
        mTVNodata = view.findViewById(R.id.tv_nodata);
        implQuizListPresenter = new ImplQuizListPresenter(getContext(),this);
    }

    @Override
    public void passDatatoRecyclerView(ArrayList<QuizListResponse> list) {
        if(!list.isEmpty()){
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(new QuizListRecyclerAdapter(getContext(),list));
        }else{
            checkforNodata();
        }
    }

    @Override
    public void loadProgressBar() {
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Quiz / Poll");
        dialog.setMessage("Loading Quiz....");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                implQuizListPresenter.wsQuizList();
                dialog.cancel();
            }
        }, 1000);
    }

    @Override
    public void checkforNodata() {
        recycler.setVisibility(View.GONE);
        mLinearNodata.setVisibility(View.VISIBLE);
        mTVNodata.setText("No Quiz Found");
    }
}
