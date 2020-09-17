package app.jugaadfunda.learningcompanion.inquiryform;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import app.jugaadfunda.R;
import app.jugaadfunda.learningcompanion.inquiryform.adapter.IndustryInquiryAdapter;
import app.jugaadfunda.learningcompanion.inquiryform.pojo.GuideLinesPojo;

/**
 * A simple {@link Fragment} subclass.
 */
public class InquiryGuideLinesFragment extends Fragment implements View.OnClickListener {
    private ViewPager2 viewPager2;
    private ArrayList<GuideLinesPojo> guidelist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inquiry_guideline, container, false);
        setPager(view);

        return view;
    }



    private void setPager(View view) {
        viewPager2 = view.findViewById(R.id.pager);

        //setting data in container
        initData();

        viewPager2.setAdapter(new IndustryInquiryAdapter(getContext(),guidelist));
        DotsIndicator indicator = view.findViewById(R.id.dots_indicator);
        indicator.setViewPager2(viewPager2);

        //calling listener function
        setListener(view);
   }

    void setListener(View view){
        view.findViewById(R.id.tv_next).setOnClickListener(this);
        view.findViewById(R.id.tv_back).setOnClickListener(this);
    }

    void initData(){
        guidelist = new ArrayList<>();
        guidelist.add(new GuideLinesPojo("Industry / Gram Panchayat / School Interation Guideline", R.string.industry_grampanchayat_industry_interactionguidelines));
        guidelist.add(new GuideLinesPojo("Key Challenges faced in various Sectors", R.string.keychallengefacedinvarioussector));
        guidelist.add(new GuideLinesPojo("Business / Technical", R.string.business_technical));
        guidelist.add(new GuideLinesPojo("Sales, Marketing, Revenue Generation", R.string.sales_marketing_revenuegeneration));
        guidelist.add(new GuideLinesPojo("Finance, Funding and Grants", R.string.finance_funding_grants));
        guidelist.add(new GuideLinesPojo("Innovations / RND", R.string.innovations_rnd));
        guidelist.add(new GuideLinesPojo("Tips for writing Minutes of Meetings", R.string.tipsforwriting_minutesofmeeting));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_next:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                break;

            case R.id.tv_back:
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()-1);
                break;
        }
    }
}
