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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugadfunda.R;
import app.jugadfunda.inquiryform.captureproblem.CaptureProblemInterfaceView;
import app.jugadfunda.inquiryform.pojo.SubDomains;

public class    SubDomainSpinnerAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<SubDomains> mList;
    private CaptureProblemInterfaceView mCaptureProblemInterfaceView;

    public SubDomainSpinnerAdapter(Context mContext, ArrayList<SubDomains> mList, CaptureProblemInterfaceView mCaptureProblemInterfaceView){
        this.mContext = mContext;
        this.mList = mList;
        this.mCaptureProblemInterfaceView = mCaptureProblemInterfaceView;
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_subdomain,null);
        SpinnerViewHolder holder = new SpinnerViewHolder(convertView);
        holder.mTvDomainName.setText(mList.get(position).getSubname());
        holder.mCheckBoxDomain.setTag(position);
        if(mList.get(position).isFlag()){
            holder.mCheckBoxDomain.setChecked(true);
        }else{
            holder.mCheckBoxDomain.setChecked(false);
        }
        return convertView;
    }


    class SpinnerViewHolder extends RecyclerView.ViewHolder {

        private CheckBox mCheckBoxDomain;
        private TextView mTvDomainName;
        public SpinnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBoxDomain = itemView.findViewById(R.id.checkSubDomains);
            mTvDomainName = itemView.findViewById(R.id.tv_subdomainname);

            mCheckBoxDomain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int pos = (int) buttonView.getTag();
                    if(isChecked){
                        mCaptureProblemInterfaceView.showAndHideBusinessDomainEditText(1, pos);
                        mCaptureProblemInterfaceView.selectBusinessDomain(1, pos);
                    }else{
                        mCaptureProblemInterfaceView.showAndHideBusinessDomainEditText(2, pos);
                        mCaptureProblemInterfaceView.selectBusinessDomain(2, pos);
                    }
                }
            });
        }
    }
}
