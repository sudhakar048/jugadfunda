package app.jugadfunda.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.QueryListPojo;
import app.jugadfunda.home.postquery.PostQueryInterfaceView;

public class CourseQueryListAdapter extends RecyclerView.Adapter<CourseQueryListAdapter.QueryHolder> implements Filterable, RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    private Context mContext;
    private PostQueryInterfaceView mPostQueryInterfaceView;
    private ArrayList<QueryListPojo> mList;
    private ArrayList<QueryListPojo> mSortedList;
    private float mRating;

    public CourseQueryListAdapter(Context mContext, PostQueryInterfaceView mPostQueryInterfaceView, ArrayList<QueryListPojo> mSortedList){
        this.mContext = mContext;
        this.mPostQueryInterfaceView  = mPostQueryInterfaceView;
        this.mSortedList = mSortedList;
        this.mList = new ArrayList<>(mSortedList);
    }

    @NonNull
    @Override
    public QueryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseQueryListAdapter.QueryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_coursequerylist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QueryHolder holder, int position) {
        QueryListPojo mQueryListPojo = mSortedList.get(position);
        holder.tv_date.setText(""+mQueryListPojo.getDate());
        holder.ratingBar.setRating(mQueryListPojo.getRating());
        holder.tv_coursequery.setText(mQueryListPojo.getQuery());
        holder.tv_course.setText(mQueryListPojo.getCoursename());
        holder.tv_coursemodule.setText(mQueryListPojo.getModulename());
        holder.rv_submodule.setLayoutManager(new LinearLayoutManager(mContext));
        SubModuleRecyclerAdapter adapter = new SubModuleRecyclerAdapter(mContext, mQueryListPojo.getSubmodules());
        holder.rv_submodule.setAdapter(adapter);
        holder.btn_rate.setTag(position);
        holder.btn_rate.setOnClickListener(this);
        holder.btn_delete.setTag(position);
        holder.btn_delete.setOnClickListener(this);
        holder.btn_edit.setTag(position);
        holder.btn_edit.setOnClickListener(this);
        holder.ratingBar.setOnRatingBarChangeListener(this::onRatingChanged);

        if(mQueryListPojo.getSessiondate() == null){
            holder.btn_edit.setVisibility(View.VISIBLE);
            holder.btn_delete.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }


    //run on background
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<QueryListPojo> filterdList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filterdList.addAll(mList);
            }else{
                for(QueryListPojo queryListPojo : mList){
                    if(queryListPojo.getCoursename().toLowerCase().contains(constraint.toString().toLowerCase()) || queryListPojo.getQuery().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterdList.add(queryListPojo);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterdList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mSortedList.clear();
            mSortedList.addAll((Collection<? extends QueryListPojo>) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
      mRating = rating;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_rate:
                int pos1 = (int) v.getTag();
                if(mRating == 0){
                    mRating = mList.get(pos1).getRating();
                }
                mPostQueryInterfaceView.wsRateUs((int)mRating,"Course Query", pos1);
                v.findViewById(R.id.btn_rate).setEnabled(false);
                break;

            case R.id.btn_delete:
                int pos2 = (int) v.getTag();
                mPostQueryInterfaceView.wsDeleteQuery(mList.get(pos2).getQueryid(),"course query", pos2);
                break;

            case R.id.btn_edit:
                int pos3 = (int) v.getTag();
                mPostQueryInterfaceView.updatePostedQuery(pos3, "course query");
                break;
        }
    }

    class QueryHolder extends RecyclerView.ViewHolder {
        TextView tv_date;
        RatingBar ratingBar;
        TextView tv_coursequery;
        TextView tv_course;
        TextView tv_coursemodule;
        RecyclerView rv_submodule;
        Button btn_rate;
        Button btn_delete;
        Button btn_edit;
        public QueryHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            ratingBar = itemView.findViewById(R.id.rating);
            tv_coursequery = itemView.findViewById(R.id.tv_coursequery);
            tv_course = itemView.findViewById(R.id.tv_course);
            tv_coursemodule = itemView.findViewById(R.id.tv_coursemodule);
            rv_submodule = itemView.findViewById(R.id.rv_submodule);
            btn_rate = itemView.findViewById(R.id.btn_rate);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }
}
