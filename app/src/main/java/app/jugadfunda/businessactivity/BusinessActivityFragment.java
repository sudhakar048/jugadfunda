package app.jugadfunda.businessactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.businessactivity.adapter.BusinessActivityAdapter;
import app.jugadfunda.localstorage.JfStorage;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessActivityFragment extends Fragment implements View.OnClickListener, BusinessActivityView{
    private ArrayList<BusinessActivityPojo> mCanvasList;
    private View mItemView;
    private ViewPager2 viewPager2;
    private JfStorage mJfStorage;
    private BusinessActivityAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mItemView = inflater.inflate(R.layout.fragment_business_activity, container, false);

        getData();

        setPager(mItemView);

        return mItemView;
    }

    void getData(){
        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        String mEmailID = sh.getString("emailid","");

        mJfStorage = new JfStorage(getContext());
        mCanvasList = mJfStorage.getAllBusinessCanvas(mEmailID);
        mCanvasList.get(0).setCanvasimage(R.drawable.ic_segment);
        mCanvasList.get(1).setCanvasimage(R.drawable.ic_value);
        mCanvasList.get(2).setCanvasimage(R.drawable.ic_relation);
        mCanvasList.get(3).setCanvasimage(R.drawable.ic_channel);
        mCanvasList.get(4).setCanvasimage(R.drawable.ic_activity);
        mCanvasList.get(5).setCanvasimage(R.drawable.ic_resource);
        mCanvasList.get(6).setCanvasimage(R.drawable.ic_partner);
        mCanvasList.get(7).setCanvasimage(R.drawable.ic_costs);
        mCanvasList.get(8).setCanvasimage(R.drawable.ic_revenue);
    }

    private void setPager(View view) {
        viewPager2 = view.findViewById(R.id.pager);
        adapter = new BusinessActivityAdapter(getContext(), mCanvasList, this);
        viewPager2.setAdapter(adapter);
        DotsIndicator indicator = view.findViewById(R.id.dots_indicator);
        indicator.setViewPager2(viewPager2);

        setListeners();
    }

    void setListeners(){
        mItemView.findViewById(R.id.iv_info).setOnClickListener(this);
        mItemView.findViewById(R.id.iv_utube).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_info:
                int pos = viewPager2.getCurrentItem();
                BusinessActivityPojo bap = mCanvasList.get(pos);
                showAlertDialog(bap.getTitle_info(), bap.getInfo());
                break;

            case R.id.iv_utube:
                int pos1 = viewPager2.getCurrentItem();
                BusinessActivityPojo bap1 = mCanvasList.get(pos1);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bap1.getUtubelink())));

                break;
        }
    }


    void showAlertDialog(int title, int info) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.help_layout, null);

        final TextView editTextMsg = (TextView) view.findViewById(R.id.tvMsg);
        final TextView editTextHeader = (TextView) view.findViewById(R.id.tvHeader);

        editTextHeader.setText(title);

        editTextMsg.setText(info);

        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }

        });

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void setData(int pos, String text) {
        BusinessActivityPojo bap = mCanvasList.get(pos);
        bap.setCanvastext(text);
        mCanvasList.set(pos, bap);
        mJfStorage.updateBusinessCanvas(bap);
        adapter.notifyDataSetChanged();
    }
}
