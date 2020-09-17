package app.jugaadfunda.learningcompanion.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.inquiryform.pojo.GuideLinesPojo;

public class IndustryInquiryAdapter extends RecyclerView.Adapter<IndustryInquiryAdapter.IndustryInquiryViewHolder> {
    private Context mContext;
    private ArrayList<GuideLinesPojo> mGuidelist;
    public IndustryInquiryAdapter(Context mContext, ArrayList<GuideLinesPojo> mGuidelist){
        this.mContext = mContext;
        this.mGuidelist = mGuidelist;
    }

    @NonNull
    @Override
    public IndustryInquiryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IndustryInquiryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_industry,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IndustryInquiryViewHolder holder, int position) {
        GuideLinesPojo glp = mGuidelist.get(position);
        holder.tv_title.setText(glp.getTitle());
        holder.tv_guideline.setText(glp.getGuidelines());
    }

    @Override
    public int getItemCount() {
        return mGuidelist.size();
    }

    static class IndustryInquiryViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_guideline;
        public IndustryInquiryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tvtitle);
            tv_guideline = itemView.findViewById(R.id.tvguidelines);
        }
    }
}
