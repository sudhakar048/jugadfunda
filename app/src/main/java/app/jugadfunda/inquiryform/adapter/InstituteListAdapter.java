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
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.generateOtp.GenerateOtpView;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;

public class InstituteListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<InstituteList> mInstituteList;
    private LinkIndustryView mLinkIndustryView;
    private GenerateOtpView mGenerateOtpView;

    public InstituteListAdapter(Context mContext, ArrayList<InstituteList> mInstituteList, LinkIndustryView mLinkIndustryView){
        this.mContext = mContext;
        this.mInstituteList = mInstituteList;
        this.mLinkIndustryView = mLinkIndustryView;
    }

    public InstituteListAdapter(Context mContext, ArrayList<InstituteList> mInstituteList, GenerateOtpView mGenerateOtpView){
        this.mContext = mContext;
        this.mInstituteList = mInstituteList;
        this.mGenerateOtpView = mGenerateOtpView;
    }

    @Override
    public int getCount() {
        return mInstituteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInstituteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_twocolumnlist, null);
        InstituteViewHolder holder = new InstituteViewHolder(convertView);
        holder.mStateId.setText(""+mInstituteList.get(position).getInstituteId());
        holder.mState.setText(mInstituteList.get(position).getInstituteName());
        holder.mState.setTag(position);
        holder.mState.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_coursename:
                int pos = (int) v.getTag();
                mLinkIndustryView.callInstituteList(pos);
                break;
        }
    }


    class InstituteViewHolder extends RecyclerView.ViewHolder{
        private TextView mStateId;
        private TextView mState;
        public InstituteViewHolder(@NonNull View itemView) {
            super(itemView);
            mStateId = itemView.findViewById(R.id.tv_courseid);
            mState = itemView.findViewById(R.id.tv_coursename);
        }
    }
}
