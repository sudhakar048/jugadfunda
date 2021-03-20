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
import app.jugadfunda.inquiryform.captureproblem.CaptureProblemInterfaceView;
import app.jugadfunda.inquiryform.pojo.SubDomains;

public class SubDomainRecyclerAdapter extends RecyclerView.Adapter<SubDomainRecyclerAdapter.BusinessSubDomainViewHolder> implements CompoundButton.OnCheckedChangeListener {

    private ArrayList<SubDomains> mList;
    private Context mContext;
    private CaptureProblemInterfaceView mCaptureProblemInterfaceView;

    public SubDomainRecyclerAdapter(ArrayList<SubDomains> mList, Context mContext, CaptureProblemInterfaceView mCaptureProblemInterfaceView) {
        this.mList = mList;
        this.mContext = mContext;
        this.mCaptureProblemInterfaceView = mCaptureProblemInterfaceView;
    }

    @NonNull
    @Override
    public BusinessSubDomainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BusinessSubDomainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_subdomain,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessSubDomainViewHolder holder, int position) {
        SubDomains mSubDomains = mList.get(position);
        holder.tv_subdomainname.setText(mSubDomains.getSubname());
        holder.checkSubDomain.setTag(position);
        holder.checkSubDomain.setOnCheckedChangeListener(this);
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
            mCaptureProblemInterfaceView.selectProductServiceSubDomain(1, pos);
        }else{
            mCaptureProblemInterfaceView.selectProductServiceSubDomain(2, pos);
        }
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
