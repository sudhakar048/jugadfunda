package app.jugadfunda.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugadfunda.R;
import app.jugadfunda.generateOtp.GenerateOtpView;
import app.jugadfunda.home.pojo.StateList;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;

public class StateListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<StateList> mStateList;
    private LinkIndustryView mLinkIndustryView;
    private GenerateOtpView mGenerateOtpView;
    private String check;

    public StateListAdapter(Context mContext, ArrayList<StateList> mStateList, LinkIndustryView mLinkIndustryView, String check){
        this.mContext = mContext;
        this.mStateList = mStateList;
        this.mLinkIndustryView = mLinkIndustryView;
        this.check = check;
    }

    public StateListAdapter(Context mContext, ArrayList<StateList> mStateList, GenerateOtpView mGenerateOtpView, String check){
        this.mContext = mContext;
        this.mStateList = mStateList;
        this.mGenerateOtpView = mGenerateOtpView;
        this.check = check;
    }

    @Override
    public int getCount() {
        return mStateList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_twocolumnlist, null);
        StateViewHolder holder = new StateViewHolder(convertView);
        holder.mStateId.setText(""+mStateList.get(position).getSid());
        holder.mState.setText(mStateList.get(position).getStatename());
        holder.mState.setOnClickListener(this);
        holder.mState.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_coursename:
                int pos = (int) v.getTag();
                if(check.equals("otp")){
                    mGenerateOtpView.callStateList(pos);
                }else{
                    mLinkIndustryView.callStateList(pos);
                }
                break;
        }
    }

    class StateViewHolder extends RecyclerView.ViewHolder{
        private TextView mStateId;
        private TextView mState;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);
            mStateId = itemView.findViewById(R.id.tv_courseid);
            mState = itemView.findViewById(R.id.tv_coursename);
        }
    }
}
