package app.jugadfunda.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.generateOtp.GenerateOtpView;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;

public class CenterListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<CenterList> mCenterList;
    private LinkIndustryView mLinkIndustryView;
    private GenerateOtpView mGenerateOtpView;

    public CenterListAdapter(Context mContext, ArrayList<CenterList> mCenterList, LinkIndustryView mLinkIndustryView){
        this.mContext = mContext;
        this.mCenterList = mCenterList;
        this.mLinkIndustryView = mLinkIndustryView;
    }

    public CenterListAdapter(Context mContext, ArrayList<CenterList> mCenterList, GenerateOtpView mGenerateOtpView){
        this.mContext = mContext;
        this.mCenterList = mCenterList;
        this.mGenerateOtpView = mGenerateOtpView;
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
                Toast.makeText(mContext,"pos - "+pos,Toast.LENGTH_LONG).show();
                mLinkIndustryView.callCenterList(pos);
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
