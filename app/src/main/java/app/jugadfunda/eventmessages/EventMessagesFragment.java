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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

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
    private ArrayList<EventResponse> mEvents;
    private ProgressDialog dialog;
    private String mModuleType = null;
    private TabLayout tabLayout;
    private EventMessagesRecyclerAdapter messagesRecyclerAdapter = null;
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
        tabLayout = view.findViewById(R.id.tab_layout);
        recycler = view.findViewById(R.id.recycler);
        mLinearNodata = view.findViewById(R.id.linear_nodata);
        mTVNodata = view.findViewById(R.id.tv_nodata);
        mImplEventMessagePresenter = new ImplEventMessagePresenter(getContext(),this);

        mModuleType = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE).getString("mt",null);
        addTabs();
    }

    @Override
    public void setEventListtoAdapter(ArrayList<EventResponse> mEventList) {
        mLinearNodata.setVisibility(View.GONE);
        mEvents = mEventList;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        messagesRecyclerAdapter = new EventMessagesRecyclerAdapter(getContext(), mEvents, this);
        recycler.setAdapter(messagesRecyclerAdapter);
        recycler.setVisibility(View.VISIBLE);

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
                    mImplEventMessagePresenter.wsEventList("CurrentEvents");
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
    private void setCustomView(String title) {
        View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.custom_tabs_title, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }

    private void addTabs() {
        setCustomView("Ongoing");
        setCustomView("Upcoming");
        setCustomView("Past");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               if(tab.getPosition() == 0){
                        mImplEventMessagePresenter.wsEventList("CurrentEvents");

                }else if(tab.getPosition() == 1){
                        mImplEventMessagePresenter.wsEventList("UpcomingEvents");

                }else if(tab.getPosition() == 2){
                        mImplEventMessagePresenter.wsEventList("PastEvents");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
