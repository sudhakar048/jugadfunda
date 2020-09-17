package app.jugaadfunda.learningcompanion.eventmessages;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.apiresponse.EventResponse;
import app.jugaadfunda.learningcompanion.eventmessages.adapter.EventMessagesRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventMessagesFragment extends Fragment implements EventInterfaceView{
    private RecyclerView recycler;
    private LinearLayout mLinearNodata;
    private TextView mTVNodata;
    private ImplEventMessagePresenter mImplEventMessagePresenter;
    private ArrayList<EventResponse> mEList;
    private ProgressDialog dialog;

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
    }

    @Override
    public void setEventListtoAdapter(ArrayList<EventResponse> mEventList) {
        mEList = mEventList;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new EventMessagesRecyclerAdapter(getContext(), mEList));
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
                mImplEventMessagePresenter.wsEventList();
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


}
