package app.jugadfunda.quiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.OptionResponse;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.quiz.questions.StartQuizInterfaceView;

public class QuizQuestionsRecyclerAdapter extends RecyclerView.Adapter<QuizQuestionsRecyclerAdapter.QuizQuestionsViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayList<QuestionListResponse> mList;
    private StartQuizInterfaceView startQuizInterfaceView;

    public QuizQuestionsRecyclerAdapter(Context mContext, ArrayList<QuestionListResponse> mList,StartQuizInterfaceView startQuizInterfaceView){
        this.mContext = mContext;
        this.mList = mList;
        this.startQuizInterfaceView = startQuizInterfaceView;
    }

    @NonNull
    @Override
    public QuizQuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuizQuestionsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_quiz_questions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuizQuestionsViewHolder holder, int position) {
        QuestionListResponse questionListResponse = mList.get(position);
        holder.tv_question.setText(questionListResponse.getTitle());
        ArrayList<OptionResponse>optionlist = questionListResponse.getOptions();
        holder.rb_option1.setText(optionlist.get(0).getOpts());
        holder.rb_option2.setText(optionlist.get(1).getOpts());
        holder.rb_option3.setText(optionlist.get(2).getOpts());

        holder.rb_option1.setOnClickListener(this);
        holder.rb_option2.setOnClickListener(this);
        holder.rb_option3.setOnClickListener(this);
        holder.rb_option4.setOnClickListener(this);
        holder.rb_option5.setOnClickListener(this);

        if(optionlist.size() == 3){
            holder.rb_option4.setVisibility(View.GONE);
            holder.rb_option5.setVisibility(View.GONE);
        }else if (optionlist.size() == 4){
            holder.rb_option4.setText(optionlist.get(3).getOpts());
            holder.rb_option5.setVisibility(View.GONE);
        }else if (optionlist.size() == 5){
            holder.rb_option4.setText(optionlist.get(3).getOpts());
            holder.rb_option5.setText(optionlist.get(4).getOpts());
        }

        holder.rb_option1.setTag(position);
        holder.rb_option2.setTag(position);
        holder.rb_option3.setTag(position);
        holder.rb_option4.setTag(position);
        holder.rb_option5.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        QuestionListResponse questionListResponse = mList.get(pos);
        ArrayList<OptionResponse> options = questionListResponse.getOptions();
        switch (v.getId()){
            case R.id.rb_radio1:
                startQuizInterfaceView.selectOptions(pos, options.get(0).getOid());
                break;

            case R.id.rb_radio2:
                startQuizInterfaceView.selectOptions(pos, options.get(1).getOid());
                break;

            case R.id.rb_radio3:
                startQuizInterfaceView.selectOptions(pos, options.get(2).getOid());
                break;

            case R.id.rb_radio4:
                startQuizInterfaceView.selectOptions(pos, options.get(3).getOid());
                break;

            case R.id.rb_radio5:
                startQuizInterfaceView.selectOptions(pos, options.get(4).getOid());
                break;
        }
    }

    static class QuizQuestionsViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_question;
        private RadioButton rb_option1;
        private RadioButton rb_option2;
        private RadioButton rb_option3;
        private RadioButton rb_option4;
        private RadioButton rb_option5;
        private RadioGroup rg_questions;

        public QuizQuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tv_question);
            rb_option1 = itemView.findViewById(R.id.rb_radio1);
            rb_option2 = itemView.findViewById(R.id.rb_radio2);
            rb_option3 = itemView.findViewById(R.id.rb_radio3);
            rb_option4 = itemView.findViewById(R.id.rb_radio4);
            rb_option5 = itemView.findViewById(R.id.rb_radio5);
            rg_questions = itemView.findViewById(R.id.rg_question);
        }
    }
}
