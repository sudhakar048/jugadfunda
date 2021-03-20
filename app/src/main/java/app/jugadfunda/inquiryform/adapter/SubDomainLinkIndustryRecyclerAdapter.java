package app.jugadfunda.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;
import app.jugadfunda.inquiryform.pojo.SubDomains;

public class SubDomainLinkIndustryRecyclerAdapter extends RecyclerView.Adapter<SubDomainLinkIndustryRecyclerAdapter.BusinessSubDomainViewHolder> implements CompoundButton.OnCheckedChangeListener {

    private ArrayList<SubDomains> mList;
    private Context mContext;
    private LinkIndustryView mLinkIndustryView;

    public SubDomainLinkIndustryRecyclerAdapter(ArrayList<SubDomains> mList, Context mContext, LinkIndustryView mLinkIndustryView) {
        this.mList = mList;
        this.mContext = mContext;
        this.mLinkIndustryView = mLinkIndustryView;
    }


    @NonNull
    @Override
    public SubDomainLinkIndustryRecyclerAdapter.BusinessSubDomainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubDomainLinkIndustryRecyclerAdapter.BusinessSubDomainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_subdomain,parent,false));
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = (int) buttonView.getTag();
        if(isChecked){
            mLinkIndustryView.selectProductServiceSubDomain(1, pos);
        }else{
            mLinkIndustryView.selectProductServiceSubDomain(2, pos);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SubDomainLinkIndustryRecyclerAdapter.BusinessSubDomainViewHolder holder, int position) {
        SubDomains mSubDomains = mList.get(position);
        holder.tv_subdomainname.setText(mSubDomains.getSubname());
        holder.checkSubDomain.setTag(position);
        holder.checkSubDomain.setOnCheckedChangeListener(this);
    }


    class BusinessSubDomainViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkSubDomain;
        private TextView tv_subdomainname;

        public BusinessSubDomainViewHolder(@NonNull View itemView) {
            super(itemView);
            checkSubDomain = itemView.findViewById(R.id.checkSubDomains);
            tv_subdomainname = itemView.findViewById(R.id.tv_subdomainname);
        }
    }
}
