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
import app.jugadfunda.apiresponse.LinkedIndustryList;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;

public class LinkIndustryAdapter extends RecyclerView.Adapter<LinkIndustryAdapter.LinkIndustryViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayList<LinkedIndustryList> mIndustryList;
    private LinkIndustryView mLinkIndustryView;

    public LinkIndustryAdapter(Context mContext, ArrayList<LinkedIndustryList> mIndustryList, LinkIndustryView mLinkIndustryView){
        this.mContext = mContext;
        this.mIndustryList = mIndustryList;
        this.mLinkIndustryView = mLinkIndustryView;
    }

    @NonNull
    @Override
    public LinkIndustryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinkIndustryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_linkedindustry, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkIndustryViewHolder holder, int position) {
        holder.mTvIndustryName.setText(mIndustryList.get(position).getIndustryname());
        holder.mBtnView.setTag(position);
        holder.mBtnView.setOnClickListener(this);
   //     holder.mBtnConnect.setTag(position);
    //    holder.mBtnConnect.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mIndustryList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_view:
                int pos1 = (int) v.getTag();
                mLinkIndustryView.loadIndustryDetailsDialog(pos1);
                break;

          /*  case R.id.btn_connect_industry:
                int pos2 = (int) v.getTag();
                mLinkIndustryView.connectIndustry(pos2);
                break;*/
        }
    }

    class LinkIndustryViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvIndustryName;
        private Button mBtnView;
      //  private Button mBtnConnect;

        public LinkIndustryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvIndustryName = itemView.findViewById(R.id.tv_industryname);
            mBtnView = itemView.findViewById(R.id.btn_view);
     //       mBtnConnect = itemView.findViewById(R.id.btn_connect_industry);
        }
    }
}
