package app.jugadfunda.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.login.pojo.LoginModule;

public class ModuleAdapter extends ArrayAdapter<LoginModule> {

    private Context mContext;
    private ArrayList<LoginModule> mList;

    public ModuleAdapter(Context mContext, ArrayList<LoginModule> mList) {
        super(mContext, 0, mList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_loginmodule, parent, false);
        }
        ImageView mIvIcon = convertView.findViewById(R.id.iv_module);
        TextView mTvModuleName = convertView.findViewById(R.id.tv_modulename);
        TextView mTvCode = convertView.findViewById(R.id.tv_code);

        LoginModule currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            mIvIcon.setImageResource(currentItem.getModuleicon());
            mTvModuleName.setText(currentItem.getModulename());
            mTvCode.setText(currentItem.getCode());
        }
        return convertView;
    }

}
