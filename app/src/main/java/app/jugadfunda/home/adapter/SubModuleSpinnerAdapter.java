package app.jugadfunda.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.SubModuleListPojo;
import app.jugadfunda.home.postquery.PostQueryInterfaceView;

public class SubModuleSpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private PostQueryInterfaceView mPostQueryInterfaceView;
    private ArrayList<SubModuleListPojo> mList;

    public SubModuleSpinnerAdapter(Context mContext, PostQueryInterfaceView mPostQueryInterfaceView, ArrayList<SubModuleListPojo> mList){
        this.mContext = mContext;
        this.mPostQueryInterfaceView = mPostQueryInterfaceView;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.row_submodule, null);
        TextView tv_submodulename = convertView.findViewById(R.id.tv_submodulename);
        TextView tv_submoduleid = convertView.findViewById(R.id.tv_submoduleid);
        CheckBox cb_submodule = convertView.findViewById(R.id.cb_submodule);
        SubModuleListPojo mSubModuleListPojo = mList.get(position);
        tv_submodulename.setText(mSubModuleListPojo.getSubmodule().split("###")[0]);
        tv_submoduleid.setText(mSubModuleListPojo.getSubmodule().split("###")[1]);
        if(mSubModuleListPojo.getCheck() == 1){
            cb_submodule.setChecked(true);
        }

        cb_submodule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mPostQueryInterfaceView.addSubmodule(tv_submoduleid.getText().toString(), position);
                }else if (!isChecked){
                    mPostQueryInterfaceView.removeSubModule(tv_submoduleid.getText().toString(), position);
                }
            }
        });


        return convertView;
    }
}
