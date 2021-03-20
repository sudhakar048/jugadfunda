package app.jugadfunda.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.SubModulePojo;

public class SubModuleRecyclerAdapter extends RecyclerView.Adapter<SubModuleRecyclerAdapter.SubModuleViewHolder>{

    private Context mContext;
    private ArrayList<SubModulePojo> mList;

    public SubModuleRecyclerAdapter(Context mContext, ArrayList<SubModulePojo> mList) {
      this.mContext = mContext;
      this.mList = mList;
    }

    @NonNull
    @Override
    public SubModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_addsubmodule,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubModuleViewHolder holder, int position) {
        SubModulePojo mSubModulePojo = mList.get(position);

        Log.e("mSubModulePojo",""+mSubModulePojo);
        holder.tv_submodulename.setText(mSubModulePojo.getSubmodulename().split("###")[0]);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SubModuleViewHolder extends RecyclerView.ViewHolder{
        TextView tv_submodulename;
        public SubModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_submodulename = itemView.findViewById(R.id.tv_submodulename);
        }
    }
}
