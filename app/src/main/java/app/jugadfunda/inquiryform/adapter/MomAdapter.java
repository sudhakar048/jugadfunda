package app.jugadfunda.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import app.jugadfunda.R;
import app.jugadfunda.inquiryform.mom.MOMView;
import app.jugadfunda.inquiryform.pojo.MomPojo;

public class MomAdapter extends RecyclerView.Adapter<MomAdapter.MomViewHolder> implements View.OnClickListener {

    private Context mContext;
    private ArrayList<MomPojo> mList;
    private MOMView mMomView;

    public MomAdapter(Context mContext, ArrayList<MomPojo> mList, MOMView mMomView){
        this.mContext = mContext;
        this.mList = mList;
        this.mMomView = mMomView;
    }

    @NonNull
    @Override
    public MomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MomAdapter.MomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_mom,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MomViewHolder holder, int position) {
        MomPojo mMomPojo = mList.get(position);
        holder.tv_title.setText(mMomPojo.getTitle());
        holder.tv_starttime.setText(mMomPojo.getStime());
        holder.tv_entime.setText(mMomPojo.getEtime());
        holder.btn_view.setTag(position);
        holder.btn_view.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    class MomViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_title;
        private TextView tv_starttime;
        private TextView tv_entime;
        private Button btn_view;

        public MomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_starttime = itemView.findViewById(R.id.tv_stime);
            tv_entime = itemView.findViewById(R.id.tv_etime);
            btn_view = itemView.findViewById(R.id.btn_view);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_view:
                int pos = (int) v.getTag();
                mMomView.shoeMomDetails(pos);
                break;

        }
    }
}
