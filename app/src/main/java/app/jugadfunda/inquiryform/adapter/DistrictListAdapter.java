package app.jugadfunda.inquiryform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;


public class DistrictListAdapter extends ArrayAdapter<DistrictList> {
    private List<DistrictList> objects;
    private Context context;
    private TextView label;

    public DistrictListAdapter(@NonNull Context context, List<DistrictList> objects) {
        super(context, 0,objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_spinner, parent, false);
        }
        label = convertView.findViewById(R.id.spinnerdata);
        DistrictList mDistrictList = objects.get(position);
        label.setText(mDistrictList.getDistrictname());
        return convertView;
    }
}
