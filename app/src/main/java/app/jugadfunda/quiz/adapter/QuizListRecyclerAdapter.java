package app.jugadfunda.quiz.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.QuizListResponse;
import app.jugadfunda.generateOtp.GenerateOtp;
import app.jugadfunda.quiz.questions.StartQuizActivity;

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
                .inflate(R.layout.row_quiz,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        QuizListResponse quizListResponse = quizlist.get(position);
        holder.tv_title.setText(quizListResponse.getQtitle());
        holder.tv_desc.setText(quizListResponse.getQdescription());
        holder.tv_title.setTag(position);
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
        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_description);
            btn_start = itemView.findViewById(R.id.btn_start);

            itemView.findViewById(R.id.btn_start).setOnClickListener(view -> {
                SharedPreferences sh = mContext.getSharedPreferences("profile",Context.MODE_PRIVATE);
                String mType = sh.getString("mt",null);
                Intent intent = null;
                if(mType != null){
                    intent=new Intent(itemView.getContext(), StartQuizActivity.class);
                }else{
                    intent=new Intent(itemView.getContext(), StartQuizActivity.class);
                }

                int pos = (int) tv_title.getTag();
                intent.putExtra("qiz", quizlist.get(pos).getQid());
                intent.putExtra("title", tv_title.getText().toString());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
