package app.jugaadfunda.learningcompanion.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.home.adapter.NotificationsRecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        tabLayout = view.findViewById(R.id.tab_notifications);
        addTabs();
        setRecyclerView(view);
        return view;
    }

    private void addTabs() {
        setCustomTabView("5");
        setCustomTabView("10");
        setCustomTabView("15");
        setCustomTabView("51");
        setCustomTabView("51");
        setCustomTabView("51");
        setCustomTabView("51");
        setCustomTabView("51");
        setCustomTabView("51");
    }

    private void setCustomTabView(String count) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_notification_tab_items, null);
        ((TextView) view.findViewById(R.id.tv_count)).setText(count);
//        ((ImageView) view.findViewById(R.id.iv_image)).setImageResource(image);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }

    private void setRecyclerView(View view) {
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new NotificationsRecyclerAdapter());
    }

}
