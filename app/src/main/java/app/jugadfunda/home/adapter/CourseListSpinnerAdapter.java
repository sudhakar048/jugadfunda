package app.jugadfunda.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.pojo.CourseList;
import app.jugadfunda.home.postquery.PostQueryInterfaceView;

public class CourseListSpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private PostQueryInterfaceView mPostQueryInterfaceView;
    private ArrayList<CourseList> mCourseList;

    public CourseListSpinnerAdapter(Context mContext, PostQueryInterfaceView mPostQueryInterfaceView, ArrayList<CourseList> mCourseList){
        this.mContext = mContext;
        this.mPostQueryInterfaceView = mPostQueryInterfaceView;
        this.mCourseList = mCourseList;
    }

    @Override
    public int getCount() {
        return mCourseList.size();
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
        CourseList cl = mCourseList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.row_twocolumnlist,null);
        TextView tv_courseid = (TextView) convertView.findViewById(R.id.tv_courseid);
        TextView tv_coursename = (TextView) convertView.findViewById(R.id.tv_coursename);
        tv_courseid.setText(""+cl.getCourseId());
        tv_coursename.setText(cl.getCourseName());

     return convertView;
    }

}
