package app.jugadfunda.login.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import app.jugadfunda.R;
import app.jugadfunda.login.adapter.NewsAdapter;
import app.jugadfunda.login.pojo.NewsPojo;

public class NewFragment extends Fragment implements NewsInterfaceView {
    private RecyclerView recycler;
    private LinearLayout mLinearNodata;
    private TextView mTVNodata;
    private TabLayout tabLayout;
    private ImplNewsPresenter mImplNewsPresenter = null;
    private NewsAdapter mNewsAdapter = null;
    private ArrayList<NewsPojo> mNews;
    private String value = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_messages, container, false);
        setUI(view);
        return view;
    }

    void setUI(View view){
        tabLayout = view.findViewById(R.id.tab_layout);
        recycler = view.findViewById(R.id.recycler);
        mLinearNodata = view.findViewById(R.id.linear_nodata);
        mTVNodata = view.findViewById(R.id.tv_nodata);



        Bundle extras = getArguments();
        if (extras != null) {
            value = extras.getString("check");
        }

        mImplNewsPresenter = new ImplNewsPresenter(getContext(), this);
        if(value.equals("news")){
            mImplNewsPresenter.wsNewsList("CurrentNews");
        }else if(value.equals("story")){
            mImplNewsPresenter.wsInspiringStories("CurrentGetInspired");
        }

        addTabs();
    }

    private void setCustomView(String title) {
        View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.custom_tabs_title, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }

    private void addTabs() {
        if(value.equals("news")){
            setCustomView("Latest News");
            setCustomView("Library (Past news)");
        }else{
            setCustomView("New Stories / Achievement");
            setCustomView("Library (Past stories / Achievements)");
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    if(value.equals("news")){
                        mImplNewsPresenter.wsNewsList("CurrentNews");
                    }else if(value.equals("story")){
                        mImplNewsPresenter.wsInspiringStories("CurrentGetInspired");
                    }

                }else if(tab.getPosition() == 1){
                    if(value.equals("news")){
                        mImplNewsPresenter.wsNewsList("PastNews");
                    }else if(value.equals("story")){
                        mImplNewsPresenter.wsInspiringStories("PastGetInspired");
                    }
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

    @Override
    public void passDataToRecyclerView(ArrayList<NewsPojo> mNewsList) {
        mLinearNodata.setVisibility(View.GONE);
        mNews = mNewsList;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsAdapter = new NewsAdapter(getContext(), mNews);
        recycler.setAdapter(mNewsAdapter);
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void checkforNodata() {
        recycler.setVisibility(View.GONE);
        mLinearNodata.setVisibility(View.VISIBLE);
        if(value.equals("news")){
            mTVNodata.setText("No News Found");
        }else if(value.equals("story")){
            mTVNodata.setText("No Inspiring Story Found");
        }
    }
}

