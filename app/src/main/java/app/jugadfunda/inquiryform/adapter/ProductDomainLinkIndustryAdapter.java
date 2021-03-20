package app.jugadfunda.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugadfunda.R;
import app.jugadfunda.inquiryform.captureproblem.CaptureProblemInterfaceView;
import app.jugadfunda.inquiryform.linkindustry.LinkIndustryView;
import app.jugadfunda.inquiryform.pojo.Domains;

public class ProductDomainLinkIndustryAdapter extends BaseAdapter {

    private ArrayList<Domains> mList;
    private Context mContext;
    private LinkIndustryView mLinkIndustryView;

    public ProductDomainLinkIndustryAdapter(ArrayList<Domains> mList, Context mContext, LinkIndustryView mLinkIndustryView) {
        this.mList = mList;
        this.mContext = mContext;
        this.mLinkIndustryView = mLinkIndustryView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_productdomains, null);
        ProductDomainLinkIndustryAdapter.BusinessDomainViewHolder holder = new ProductDomainLinkIndustryAdapter.BusinessDomainViewHolder(convertView);
        holder.tv_domainname.setText(mList.get(position).getDomainName());
        holder.tv_domaincode.setText(mList.get(position).getCode());
        holder.checkDomain.setTag(position);


        if (mList.get(position).getSubdomains() != null) {
            holder.mRecyclerSubdomain.setLayoutManager(new LinearLayoutManager(mContext));
            SubDomainLinkIndustryRecyclerAdapter adpater = new SubDomainLinkIndustryRecyclerAdapter(mList.get(position).getSubdomains(), mContext, mLinkIndustryView);
            holder.mRecyclerSubdomain.setAdapter(adpater);
        }

        return convertView;
    }


    class BusinessDomainViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkDomain;
        private TextView tv_domainname;
        private TextView tv_domaincode;
        private RecyclerView mRecyclerSubdomain;

        public BusinessDomainViewHolder(@NonNull View itemView) {
            super(itemView);
            checkDomain = itemView.findViewById(R.id.checkDomains);
            tv_domainname = itemView.findViewById(R.id.tv_domainname);
            tv_domaincode = itemView.findViewById(R.id.tv_domaincode);
            mRecyclerSubdomain = itemView.findViewById(R.id.recycler);

            checkDomain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int pos = (int) buttonView.getTag();
                    if (isChecked) {
                        mLinkIndustryView.showAndHideProductServiceDomainEditText(1, pos);
                        mLinkIndustryView.selectProductServiceDomain(1, pos);
                        mRecyclerSubdomain.setVisibility(View.VISIBLE);
                    } else {
                        mLinkIndustryView.showAndHideProductServiceDomainEditText(2, pos);
                        mLinkIndustryView.selectProductServiceDomain(2, pos);
                        mRecyclerSubdomain.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
