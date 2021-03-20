package app.jugadfunda.home.sessions.sessionlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.home.adapter.SessionsRecyclerAdapter;
import app.jugadfunda.home.pojo.SessionDetailsPojo;
import app.jugadfunda.home.sessions.sessiondetailsandreport.SessionDetailsandReportActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionFragment extends Fragment implements SessionListInterfaceView {
    private RecyclerView recycler;
    private ArrayList<SessionDetailsPojo> mSessionList = null;
    private SessionListImpl mImplSessionList = null;
    private long mUserId;
    private LinearLayout mLinear;
    private TextView mTvNoDate;
    private SearchView mSearchView;
    private SessionsRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        setRecyclerView(view);

        mImplSessionList = new SessionListImpl(getContext(), this);
        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        mUserId = sh.getLong("uid", 0);
        mImplSessionList.wsSessionList(mUserId);

        return view;
    }

    private void setRecyclerView(View view) {
        recycler = view.findViewById(R.id.recycler);
        mLinear = view.findViewById(R.id.linear_nodata);
        mTvNoDate = view.findViewById(R.id.tv_nodata);
        mSearchView = view.findViewById(R.id.search_data);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void populateSessions(ArrayList<SessionDetailsPojo> mList) {
        mSessionList = mList;

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SessionsRecyclerAdapter(getContext(), this, mSessionList);
        recycler.setAdapter(adapter);
    }

    @Override
    public void sessionDetails(int position) {
        Intent intent = new Intent(getContext(), SessionDetailsandReportActivity.class);
        intent.putExtra("sessionid", mSessionList.get(position).getSessionId());
        intent.putExtra("uid", mUserId);
        intent.putExtra("qlist", mSessionList.get(position).getQlist());
        startActivity(intent);
    }

    @Override
    public void showEmptyRecord() {
        mLinear.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
        mTvNoDate.setText("No Session Found !!!");
    }
}
