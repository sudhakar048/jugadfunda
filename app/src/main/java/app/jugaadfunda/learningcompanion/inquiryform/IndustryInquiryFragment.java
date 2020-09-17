package app.jugaadfunda.learningcompanion.inquiryform;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.inquiryform.industryenquiry.EnquiryFormFragment;
import app.jugaadfunda.learningcompanion.inquiryform.mom.MomFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndustryInquiryFragment extends Fragment {
    private TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_industry_inquiry, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);
        addTabs();
        return view;
    }

    private void addTabs() {
        setCustomView("Guidelines", R.drawable.ic_notifications);
        setCustomView("Inquiry", R.drawable.ic_enquiry);
        setCustomView("M.O.M", R.drawable.ic_notifications);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_image))
                        .setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
                addFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_image))
                        .setColorFilter(ContextCompat.getColor(getContext(), R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ((ImageView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.iv_image))
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
        addFragment(0);
    }

    private void setCustomView(String title, Integer image) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_items, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);
        ((ImageView) view.findViewById(R.id.iv_image)).setImageResource(image);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }

    private void addFragment(int position) {
        Fragment fragment = null;
        if (position == 0) fragment = new InquiryGuideLinesFragment();
        else if (position == 1) fragment = new EnquiryFormFragment();
        else if (position == 2) fragment = new MomFragment();

        if (fragment != null)
            getChildFragmentManager().beginTransaction().replace(R.id.container_industry, fragment).commit();
    }


}
