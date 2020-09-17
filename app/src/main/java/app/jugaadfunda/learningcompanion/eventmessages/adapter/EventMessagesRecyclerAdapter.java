package app.jugaadfunda.learningcompanion.eventmessages.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.apiresponse.EventResponse;

public class EventMessagesRecyclerAdapter extends RecyclerView.Adapter<EventMessagesRecyclerAdapter.EventMessagesHolder> {

    private Context mContext;
    private ArrayList<EventResponse> mEventList;

    public EventMessagesRecyclerAdapter(Context mContext, ArrayList<EventResponse> mEventList){
        this.mContext = mContext;
        this.mEventList = mEventList;
    }

    @NonNull
    @Override
    public EventMessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventMessagesHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event_messages, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventMessagesHolder holder, int position) {
        EventResponse eventResponse = mEventList.get(position);
        holder.tv_title.setText(eventResponse.getGetTitle());
        holder.tv_date.setText(eventResponse.getOd()+" to "+eventResponse.getCd());
        holder.tv_desc.setText(eventResponse.getDesc());
        holder.tv_url.setText(eventResponse.getHta());
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    static class EventMessagesHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_date;
        private TextView tv_desc;
        private TextView tv_url;

        public EventMessagesHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_desc = itemView.findViewById(R.id.tv_description);
            tv_url = itemView.findViewById(R.id.tv_url);
        }
    }
}
