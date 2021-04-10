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
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;

public class DistrictListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<DistrictList>mDistrictList;
    private LinkIndustryView mLinkIndustryView;
    private GenerateOtpView mGenerateOtpView;
    private String check;

    public DistrictListAdapter(Context mContext, ArrayList<DistrictList>mDistrictList, LinkIndustryView mLinkIndustryView, String check){
        this.mContext = mContext;
        this.mDistrictList = mDistrictList;
        this.mLinkIndustryView = mLinkIndustryView;
        this.check = check;
    }

    public DistrictListAdapter(Context mContext, ArrayList<DistrictList>mDistrictList, GenerateOtpView mGenerateOtpView, String check){
        this.mContext = mContext;
        this.mDistrictList = mDistrictList;
        this.mGenerateOtpView = mGenerateOtpView;
        this.check = check;
    }

    @Override
    public int getCount() {
        return mDistrictList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDistrictList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_twocolumnlist, null);
        DistrictViewHolder holder = new DistrictViewHolder(convertView);
        holder.mStateId.setText(""+mDistrictList.get(position).getDid());
        holder.mState.setText(mDistrictList.get(position).getDistrictname());
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
                    mGenerateOtpView.callDistrictList(pos);
                }else{
                    mLinkIndustryView.callDistrictList(pos);
                }

                break;
        }
    }

    class DistrictViewHolder extends RecyclerView.ViewHolder{
        private TextView mStateId;
        private TextView mState;
        public DistrictViewHolder(@NonNull View itemView) {
            super(itemView);
            mStateId = itemView.findViewById(R.id.tv_courseid);
            mState = itemView.findViewById(R.id.tv_coursename);
        }
    }
}
