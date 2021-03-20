package app.jugadfunda.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.SessionDetailsPojo;
import app.jugadfunda.home.sessions.sessionlist.SessionListInterfaceView;

public class SessionsRecyclerAdapter extends RecyclerView.Adapter<SessionsRecyclerAdapter.NotificationsViewHolder> implements View.OnClickListener, Filterable {
    private Context mContext;
    private SessionListInterfaceView mSessionListInterfaceView;
    private ArrayList<SessionDetailsPojo> mList;
    private ArrayList<SessionDetailsPojo> mSortedList;

    public SessionsRecyclerAdapter(Context mContext, SessionListInterfaceView mSessionListInterfaceView, ArrayList<SessionDetailsPojo> mSortedList){
        this.mContext = mContext;
        this.mSessionListInterfaceView = mSessionListInterfaceView;
        this.mSortedList = mSortedList;
        this.mList = new ArrayList<>(mSortedList);
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sessions,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        SessionDetailsPojo mSessionDetailsPojo = mSortedList.get(position);
        holder.tv_sessiontitle.setText(mSessionDetailsPojo.getVenue());
        holder.tv_date.setText(mSessionDetailsPojo.getDate()+" "+mSessionDetailsPojo.getTime());
        holder.btn_view.setOnClickListener(this);
        holder.btn_view.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_view:
            int pos = (int)v.getTag();
            mSessionListInterfaceView.sessionDetails(pos);
            break;

    }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    //run on background
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SessionDetailsPojo> filterdList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filterdList.addAll(mList);
            }else{
                for(SessionDetailsPojo sessionDetailsPojo : mList){
                  if(sessionDetailsPojo.getVenue().toLowerCase().contains(constraint.toString().toLowerCase()) || sessionDetailsPojo.getDate().toLowerCase().contains(constraint.toString().toLowerCase()) || sessionDetailsPojo.getTime().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterdList.add(sessionDetailsPojo);
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
                mSortedList.addAll((Collection<? extends SessionDetailsPojo>) results.values);
                notifyDataSetChanged();
        }
    };

    static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sessiontitle;
        TextView tv_date;
        Button btn_view;

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_sessiontitle = itemView.findViewById(R.id.tv_sessiontitle);
            tv_date = itemView.findViewById(R.id.tv_date);
            btn_view = itemView.findViewById(R.id.btn_view);
        }
    }
}
