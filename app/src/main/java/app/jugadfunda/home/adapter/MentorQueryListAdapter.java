package app.jugadfunda.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.QueryListPojo;

public class MentorQueryListAdapter extends RecyclerView.Adapter<MentorQueryListAdapter.MentorQueryListViewHolder>  {
    private Context mContext;
    private ArrayList<QueryListPojo> qlist;

    public MentorQueryListAdapter(Context mContext, ArrayList<QueryListPojo> qlist){
        this.mContext = mContext;
        this.qlist = qlist;
    }

    @NonNull
    @Override
    public MentorQueryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MentorQueryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mentorquery, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MentorQueryListViewHolder holder, int position) {
        QueryListPojo mQuery = qlist.get(position);
        holder.mTvCourse.setText(mQuery.getCoursename());
        holder.mTvModule.setText(mQuery.getModulename());
        holder.mTvQuery.setText(mQuery.getQuery());
        holder.mRatingBar.setRating(mQuery.getRating());
        holder.tv_submodule.setText(mQuery.getSubmodulename());
        if(mQuery.getSubmodulename() == null){
            holder.title_submodule.setVisibility(View.GONE);
            holder.title_module.setVisibility(View.GONE);
            holder.title_course.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return qlist.size();
    }

    class MentorQueryListViewHolder extends RecyclerView.ViewHolder{
        TextView mTvCourse;
        TextView mTvModule;
        TextView mTvQuery;
        RatingBar mRatingBar;
        TextView tv_submodule;
        TextView title_course;
        TextView title_module;
        TextView title_submodule;

        public MentorQueryListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvCourse = itemView.findViewById(R.id.tv_course);
            mTvModule = itemView.findViewById(R.id.tv_coursemodule);
            mTvQuery = itemView.findViewById(R.id.tv_coursequery);
            mRatingBar = itemView.findViewById(R.id.rating);
            tv_submodule = itemView.findViewById(R.id.tv_submodule);
            title_course = itemView.findViewById(R.id.title_course);
            title_module = itemView.findViewById(R.id.title_module);
            title_submodule = itemView.findViewById(R.id.title_submodule);
        }
    }
}
