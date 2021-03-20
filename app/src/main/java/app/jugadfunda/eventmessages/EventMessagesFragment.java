package app.jugadfunda.eventmessages;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.ContentActivity;
import app.jugadfunda.apiresponse.EventResponse;
import app.jugadfunda.eventmessages.adapter.EventMessagesRecyclerAdapter;

public class EventMessagesFragment extends Fragment implements EventInterfaceView{
    private RecyclerView recycler;
    private LinearLayout mLinearNodata;
    private TextView mTVNodata;
    private ImplEventMessagePresenter mImplEventMessagePresenter;
    private ArrayList<EventResponse> mEList;
    private ProgressDialog dialog;
    private String mModuleType = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_messages, container, false);
        loadProgressBar();
        setRecyclerView(view);
        return view;
    }

    private void setRecyclerView(View view) {
        recycler = view.findViewById(R.id.recycler);
        mLinearNodata = view.findViewById(R.id.linear_nodata);
        mTVNodata = view.findViewById(R.id.tv_nodata);
        mImplEventMessagePresenter = new ImplEventMessagePresenter(getContext(),this);

        mModuleType = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE).getString("mt",null);
    }

    @Override
    public void setEventListtoAdapter(ArrayList<EventResponse> mEventList) {
        mEList = mEventList;
        if(!mEList.isEmpty()){
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler.setAdapter(new EventMessagesRecyclerAdapter(getContext(), mEList, this));
        }else{
            checkforNodata();
        }
    }

    @Override
    public void loadProgressBar() {
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Event / Messages");
        dialog.setMessage("Loading Events....");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImplEventMessagePresenter.wsEventList(mModuleType);
                dialog.cancel();
            }
        }, 1000);
    }

    @Override
    public void checkforNodata() {
        recycler.setVisibility(View.GONE);
        mLinearNodata.setVisibility(View.VISIBLE);
        mTVNodata.setText("No Event Found");
    }

    @Override
    public void openUrl(String url) {
        Intent intent = new Intent(getContext(), ContentActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }


}
