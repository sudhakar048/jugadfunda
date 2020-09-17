package app.jugaadfunda.learningcompanion.businessactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.businessactivity.BusinessActivityPojo;
import app.jugaadfunda.learningcompanion.businessactivity.BusinessActivityView;

public class BusinessActivityAdapter extends RecyclerView.Adapter<BusinessActivityAdapter.BusinessActivityViewHolder> {

    private Context mContext;
    private ArrayList<BusinessActivityPojo> mList;
    private BusinessActivityView bav;

    public BusinessActivityAdapter(Context mContext, ArrayList<BusinessActivityPojo> mList, BusinessActivityView bav){
        this.mContext = mContext;
        this.mList = mList;
        this.bav = bav;
    }

    @NonNull
    @Override
    public BusinessActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BusinessActivityViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_business_activity,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessActivityViewHolder holder, int position) {
        BusinessActivityPojo businessActivityPojo = mList.get(position);
        holder.mImageView.setImageResource(businessActivityPojo.getCanvasimage());
        holder.mTvText.setText(businessActivityPojo.getCanvastext());
        holder.mTvText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text = holder.mTvText.getText().toString();
                if (text.length() > 500) {
                    Toast.makeText(mContext, "Max 500 characters are allowed", Toast.LENGTH_LONG).show();
                } else {
                    bav.setData(position, text);
                    Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class BusinessActivityViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextInputEditText mTvText;


        public BusinessActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_canvas);
            mTvText = itemView.findViewById(R.id.tv_text);
        }
    }
}
