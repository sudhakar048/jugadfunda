package app.jugadfunda.quiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.OptionResponse;
import app.jugadfunda.apiresponse.QuestionListResponse;

public class CompareQuizRecyclerAdapter extends RecyclerView.Adapter<CompareQuizRecyclerAdapter.CompareQuizViewHolder> {

    private Context mContext;
    private ArrayList<QuestionListResponse> mList;

    public CompareQuizRecyclerAdapter(Context mContext, ArrayList<QuestionListResponse> mList){
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CompareQuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompareQuizViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_compare_quiz, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompareQuizViewHolder holder, int position) {
        QuestionListResponse questionListResponse = mList.get(position);
        holder.tv_question.setText(questionListResponse.getTitle());
        ArrayList<OptionResponse>optionlist = questionListResponse.getOptions();

        holder.tv_opt1.setText(optionlist.get(0).getOptioncount()+" A) "+optionlist.get(0).getOpts());
        holder.tv_opt2.setText(optionlist.get(1).getOptioncount()+" B) "+optionlist.get(1).getOpts());
        holder.tv_opt3.setText(optionlist.get(2).getOptioncount()+" C) "+optionlist.get(2).getOpts());

        if(optionlist.size() == 3){
            holder.tv_opt4.setVisibility(View.GONE);
            holder.tv_opt5.setVisibility(View.GONE);
        }else if (optionlist.size() == 4){
            holder.tv_opt4.setText(optionlist.get(3).getOptioncount()+" D) "+optionlist.get(3).getOpts());
            holder.tv_opt5.setVisibility(View.GONE);
        }else if (optionlist.size() == 5){
            holder.tv_opt4.setText(optionlist.get(3).getOptioncount()+" D) "+optionlist.get(3).getOpts());
            holder.tv_opt5.setText(optionlist.get(4).getOptioncount()+" E) "+optionlist.get(4).getOpts());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class CompareQuizViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_question;
        private TextView tv_opt1;
        private TextView tv_opt2;
        private TextView tv_opt3;
        private TextView tv_opt4;
        private TextView tv_opt5;

        public CompareQuizViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_opt1 = itemView.findViewById(R.id.tv_option1);
            tv_opt2 = itemView.findViewById(R.id.tv_option2);
            tv_opt3 = itemView.findViewById(R.id.tv_option3);
            tv_opt4 = itemView.findViewById(R.id.tv_option4);
            tv_opt5 = itemView.findViewById(R.id.tv_option5);
        }
    }
}
