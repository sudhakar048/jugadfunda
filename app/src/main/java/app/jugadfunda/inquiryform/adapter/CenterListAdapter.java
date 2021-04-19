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
import app.jugadfunda.generateOtp.PsychometricTestView;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;

public class CenterListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<CenterList> mCenterList;
    private LinkIndustryView mLinkIndustryView;
    private PsychometricTestView mGenerateOtpView;
    private String check;

    public CenterListAdapter(Context mContext, ArrayList<CenterList> mCenterList, LinkIndustryView mLinkIndustryView, String check){
        this.mContext = mContext;
        this.mCenterList = mCenterList;
        this.mLinkIndustryView = mLinkIndustryView;
        this.check = check;
    }

    public CenterListAdapter(Context mContext, ArrayList<CenterList> mCenterList, PsychometricTestView mGenerateOtpView, String check){
        this.mContext = mContext;
        this.mCenterList = mCenterList;
        this.mGenerateOtpView = mGenerateOtpView;
        this.check = check;
    }

    @Override
    public int getCount() {
        return mCenterList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCenterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_twocolumnlist, null);
        CenterListAdapter.CenterListViewHolder holder = new CenterListAdapter.CenterListViewHolder(convertView);
        holder.mStateId.setText(""+mCenterList.get(position).getCenterid());
        holder.mState.setText(mCenterList.get(position).getCentername());
        holder.mState.setTag(position);
        holder.mState.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_coursename:
                int pos = (int) v.getTag();
              if(check.equals("otp")){
                  mGenerateOtpView.callCenterList(pos);
              }else{
                  mLinkIndustryView.callCenterList(pos);
              }

                break;
        }
    }

    class CenterListViewHolder extends RecyclerView.ViewHolder{
        private TextView mStateId;
        private TextView mState;

        public CenterListViewHolder(@NonNull View itemView) {
            super(itemView);
            mStateId = itemView.findViewById(R.id.tv_courseid);
            mState = itemView.findViewById(R.id.tv_coursename);
        }
    }
}
