package app.jugadfunda.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.ModuleListPojo;

public class ModuleSpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ModuleListPojo> mList;

    public ModuleSpinnerAdapter(Context mContext, ArrayList<ModuleListPojo> mList){
        this.mContext = mContext;
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
        convertView = inflater.inflate(R.layout.row_modulelist, null);

        TextView mTvModuleId = convertView.findViewById(R.id.tv_moduleid);
        TextView mTvCourseId = convertView.findViewById(R.id.tv_courseid);
        TextView mtvModuleName = convertView.findViewById(R.id.tv_modulename);

        ModuleListPojo mModuleListPojo = mList.get(position);
        mTvModuleId.setText(""+mModuleListPojo.getMainmoduleid());
        mTvCourseId.setText(""+mModuleListPojo.getCourseid());
        mtvModuleName.setText(mModuleListPojo.getMainmodulename());
        return convertView;
    }
}
