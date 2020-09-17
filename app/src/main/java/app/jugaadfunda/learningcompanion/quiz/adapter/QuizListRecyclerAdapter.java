package app.jugaadfunda.learningcompanion.quiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.apiresponse.QuizListResponse;
import app.jugaadfunda.learningcompanion.quiz.questions.StartQuizActivity;

public class QuizListRecyclerAdapter extends RecyclerView.Adapter<QuizListRecyclerAdapter.QuizViewHolder> {

    private Context mContext;
    private ArrayList<QuizListResponse> quizlist;

    public QuizListRecyclerAdapter(Context mContext, ArrayList<QuizListResponse> quizlist){
        this.mContext = mContext;
        this.quizlist = quizlist;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_quiz,parent,false),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        QuizListResponse quizListResponse = quizlist.get(position);
        holder.tv_title.setText(quizListResponse.getQtitle());
        holder.tv_desc.setText(quizListResponse.getQdescription());
        holder.tv_attemptedby.setText(quizListResponse.getNoofattempts());
    }

    @Override
    public int getItemCount() {
        return quizlist.size();
    }


    class QuizViewHolder extends RecyclerView.ViewHolder {
    TextView tv_title;
    TextView tv_desc;
    TextView tv_attemptedby;
    Button btn_start;
        public QuizViewHolder(@NonNull View itemView, int position) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_description);
            tv_attemptedby = itemView.findViewById(R.id.tv_attempted);
            btn_start = itemView.findViewById(R.id.btn_start);

            itemView.findViewById(R.id.btn_start).setOnClickListener(view -> {
                Intent intent=new Intent(itemView.getContext(), StartQuizActivity.class);
                intent.putExtra("qiz", quizlist.get(position).getQid());
                intent.putExtra("title", tv_title.getText().toString());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
