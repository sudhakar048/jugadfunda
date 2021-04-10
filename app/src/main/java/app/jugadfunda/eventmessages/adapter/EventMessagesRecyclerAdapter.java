package app.jugadfunda.eventmessages.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.apiresponse.EventResponse;
import app.jugadfunda.eventmessages.EventInterfaceView;

public class EventMessagesRecyclerAdapter extends RecyclerView.Adapter<EventMessagesRecyclerAdapter.EventMessagesHolder> implements View.OnClickListener {

    private Context mContext;
    private ArrayList<EventResponse> mEventList;
    private EventInterfaceView mEventInterfaceView;

    public EventMessagesRecyclerAdapter(Context mContext, ArrayList<EventResponse> mEventList, EventInterfaceView mEventInterfaceView){
        this.mContext = mContext;
        this.mEventList = mEventList;
        this.mEventInterfaceView = mEventInterfaceView;
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
        holder.tv_title.setText(eventResponse.getTitle());
        holder.tv_date.setText(eventResponse.getOpeningdate()+" to "+eventResponse.getClosingdate());
        holder.tv_desc.setText(eventResponse.getDescription());
        holder.tv_url.setText(eventResponse.getHowtoapply());
        holder.tv_url.setOnClickListener(this);
        holder.tv_url.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_url:
                int pos = (int) v.getTag();
                mEventInterfaceView.openUrl(mEventList.get(pos).getHowtoapply());
                break;
        }
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
