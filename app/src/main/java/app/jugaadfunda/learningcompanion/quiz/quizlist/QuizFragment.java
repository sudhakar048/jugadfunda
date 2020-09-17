package app.jugaadfunda.learningcompanion.quiz.quizlist;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.apiresponse.QuizListResponse;
import app.jugaadfunda.learningcompanion.quiz.adapter.QuizListRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment implements QuizListView {
    private ImplQuizListPresenter implQuizListPresenter = null;
    private RecyclerView recycler;
    private ProgressDialog dialog;
    private LinearLayout mLinearNodata;
    private TextView mTVNodata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz, container, false);
        loadProgressBar();
        setRecyclerView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProgressBar();
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
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new QuizListRecyclerAdapter(getContext(),list));
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
