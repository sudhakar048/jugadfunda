package app.jugadfunda.home.postquery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.Collections;
import app.jugadfunda.R;
import app.jugadfunda.home.adapter.CourseListSpinnerAdapter;
import app.jugadfunda.home.adapter.GeneralQueryListAdapter;
import app.jugadfunda.home.adapter.ModuleSpinnerAdapter;
import app.jugadfunda.home.adapter.CourseQueryListAdapter;
import app.jugadfunda.home.adapter.SubModuleSpinnerAdapter;
import app.jugadfunda.home.pojo.CourseList;
import app.jugadfunda.home.pojo.ModuleListPojo;
import app.jugadfunda.home.pojo.QueryListPojo;
import app.jugadfunda.home.pojo.SubModuleListPojo;
import app.jugadfunda.home.pojo.SubModulePojo;

public class PostQueryFragment extends Fragment implements View.OnClickListener, PostQueryInterfaceView {
    private LinearLayout mLinearpostQuery;
    private LinearLayout mLinearCourseQuery;
    private RecyclerView mRvCourseQueryList;
    private RecyclerView mRvGeneralQueryList;
    private RadioGroup mRvGroup;
    private RadioButton mRadio1;
    private RadioButton mRadio2;
    private TextView mTvQueryType;
    private SearchView mSearchView;
    private ArrayList<CourseList> mCourseList = null;
    private ArrayList<ModuleListPojo> mModuleList = null;
    private ArrayList<SubModuleListPojo> mSubModuleList = null;
    private ImplPostQuery mImplPostQuery = null;
    private Spinner mSpinnerCourseList;
    private Spinner mSpinnerModules;
    private Spinner mSpinnerSubModules;
    private TextInputEditText mTextInputEditText;
    private Button mBtnPostQuerySubmit;
    private Button mBtnPostQueryReset;
    private long uid = 0;
    private static long courseid = 0;
    private static long moduleid = 0;
    private static String submodulename = "";
    private SubModuleSpinnerAdapter submoduleadapter;
    private CourseQueryListAdapter courseAdapter;
    private GeneralQueryListAdapter generalAdapter;
    private ArrayList<QueryListPojo> mData;
    private ArrayList<QueryListPojo> mCourseListData;
    private ArrayList<QueryListPojo> mGeneralListData;
    private AlertDialog.Builder alert;
    private AlertDialog alertDialog;
    private Spinner mPopupSpinnerModuleList;
    private Spinner mPopupSpinnerSubModuleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView= inflater.inflate(R.layout.fragment_postquery, container, false);

        //initialzing UI
        setUI(itemView);

        return itemView;
    }

    //setting UI
    void setUI(View view){
        mLinearpostQuery = view.findViewById(R.id.linear_postquery);
        mLinearCourseQuery = view.findViewById(R.id.linear_coursequery);
        mRvCourseQueryList = view.findViewById(R.id.rv_coursequery);
        mRvGeneralQueryList = view.findViewById(R.id.rv_generalequery);
        mRvGroup = view.findViewById(R.id.rg_group);
        mRadio1 = mRvGroup.findViewById(R.id.radio1);
        mRadio2 = mRvGroup.findViewById(R.id.radio2);
        mTvQueryType = view.findViewById(R.id.tv_qureytype);
        mSearchView = view.findViewById(R.id.search_data);

        mRadio1.setChecked(true);

        //spinners
        mSpinnerCourseList = view.findViewById(R.id.spinner_selectcourse);
        mSpinnerModules = view.findViewById(R.id.spinner_selectmodule);
        mSpinnerSubModules = view.findViewById(R.id.spinner_selectsubmodule);

        //post query
        mTextInputEditText = view.findViewById(R.id.et_query);
        mBtnPostQuerySubmit = view.findViewById(R.id.btn_submit);
        mBtnPostQueryReset = view.findViewById(R.id.btn_reset);

        mImplPostQuery = new ImplPostQuery(getContext(),this);

        //calling listeners
        setListener(view);

        //Populating Query List
        SharedPreferences sh = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        uid = sh.getLong("uid",0);
        mImplPostQuery.wsPostedQueryList(uid);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImplPostQuery.wsCourseList();
            }
        },500);

        mSpinnerCourseList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseid = mCourseList.get(position).getCourseId();
                mImplPostQuery.wsModuleList(courseid,"Submit");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerModules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                moduleid = mModuleList.get(position).getMainmoduleid();
                mImplPostQuery.wsSubModuleList(moduleid,"Submit");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(mTvQueryType.getText().toString().toLowerCase().equals("course query")) {
                    courseAdapter.getFilter().filter(newText);
                }else if(mTvQueryType.getText().toString().toLowerCase().equals("general query")){
                    generalAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

    }


    //settting listeners
    void setListener(View view){
        view.findViewById(R.id.tv_postquery).setOnClickListener(this);
        view.findViewById(R.id.radio1).setOnClickListener(this);
        view.findViewById(R.id.radio2).setOnClickListener(this);
        view.findViewById(R.id.rg_group).setOnClickListener(this);
        view.findViewById(R.id.btn_submit).setOnClickListener(this);
        view.findViewById(R.id.btn_reset).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_postquery:
                submodulename = "";
                if(mRadio1.isChecked()){

                    mRvCourseQueryList.setVisibility(View.GONE);
                    mRvGeneralQueryList.setVisibility(View.GONE);
                    mLinearCourseQuery.setVisibility(View.VISIBLE);
                    mLinearpostQuery.setVisibility(View.VISIBLE);
                    mTvQueryType.setText(R.string.course_query);
                }else{
                    mRvCourseQueryList.setVisibility(View.GONE);
                    mRvGeneralQueryList.setVisibility(View.GONE);
                    mLinearCourseQuery.setVisibility(View.GONE);
                    mLinearpostQuery.setVisibility(View.VISIBLE);
                    mTvQueryType.setText(R.string.general_query);
                }
                mSearchView.setVisibility(View.GONE);

            break;

            case R.id.radio1:
               radio1();
                break;


            case R.id.radio2:
               radio2();
                break;

            case R.id.btn_submit:
                String check = validatePostQueryForm(courseid,moduleid,mTextInputEditText.getText().toString().trim(),mTvQueryType.getText().toString().toLowerCase());

               if(!check.equals("ok")){
                    Toast.makeText(getContext(),check,Toast.LENGTH_LONG).show();
                }else{
                    disableSubmitBtn();
                   if(mTvQueryType.getText().toString().toLowerCase().equals("course query")) {
                       submodulename = submodulename.substring(0, submodulename.length() - 1);
                   }
                   mImplPostQuery.wsPostQuery(courseid,moduleid,uid,mTextInputEditText.getText().toString(),submodulename, "Submit", mTvQueryType.getText().toString().toLowerCase());
                }
                break;

            case R.id.btn_reset:
                resetPostQueryForm(mTextInputEditText);
                break;

        }
    }

    @Override
    public void populateCourseList(ArrayList<CourseList> mList) {
        mCourseList = mList;
        CourseListSpinnerAdapter adapter = new CourseListSpinnerAdapter(getContext(), this,  mCourseList);
        mSpinnerCourseList.setAdapter(adapter);
    }

    @Override
    public void populateModuleList(ArrayList<ModuleListPojo> mList, String btnType) {
        mModuleList = mList;
        ModuleSpinnerAdapter adapter = new ModuleSpinnerAdapter(getContext(), mModuleList);
        if(btnType.equals("Submit")){
            mSpinnerModules.setAdapter(adapter);
        }else{
            mPopupSpinnerModuleList.setAdapter(adapter);
        }
    }

    @Override
    public void populateSubModuleList(ArrayList<SubModuleListPojo> mList, String btnType) {
        mSubModuleList = mList;
        submoduleadapter = new SubModuleSpinnerAdapter(getContext(), this, mSubModuleList);

        if(btnType.equals("Submit")){
            mSpinnerSubModules.setAdapter(submoduleadapter);
        }else{
            mPopupSpinnerSubModuleList.setAdapter(submoduleadapter);
        }

    }

    @Override
    public void populatePostedQueryList(ArrayList<QueryListPojo> mList) {
        mData = mList;

        Collections.sort(mData);
        ArrayList<QueryListPojo> mList1 = new ArrayList<>();
        ArrayList<QueryListPojo> mList2 = new ArrayList<>();

        for(QueryListPojo queryListPojo : mData){
            if(queryListPojo.getCourseid() != 0){
                mList1.add(queryListPojo);
            }else{
                mList2.add(queryListPojo);
            }
        }

        mCourseListData = mList1;

        mGeneralListData = mList2;

        //populating courselist
        mRvCourseQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
        courseAdapter = new CourseQueryListAdapter(getContext(),this, mCourseListData);
        mRvCourseQueryList.setAdapter(courseAdapter);

        //populating general list
        mRvGeneralQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
        generalAdapter = new GeneralQueryListAdapter(getContext(), this, mGeneralListData);
        mRvGeneralQueryList.setAdapter(generalAdapter);

    }

    @Override
    public void clearPostQueryForm(String mType) {
        mTextInputEditText.setText("");
        submodulename = "";
        courseid = 0;
        moduleid = 0;
        if(mType.equals("general query")){
            radio2();
        }else{
            radio1();
        }
    }

    @Override
    public void disableSubmitBtn() {
        mBtnPostQuerySubmit.setEnabled(false);
    }

    @Override
    public void enableSubmitBtn() {
        mBtnPostQuerySubmit.setEnabled(true);
    }

    @Override
    public void resetPostQueryForm(TextInputEditText mTIET) {
        mTIET.setText("");
        submodulename = "";
        courseid = 0;
        moduleid = 0;
    }

    @Override
    public void addSubmodule(String addStr, int pos) {
        submodulename += addStr+",";
        mSubModuleList.get(pos).setCheck(1);
        submoduleadapter.notifyDataSetChanged();
        mSpinnerSubModules.postInvalidate();
        mSpinnerSubModules.setAdapter(submoduleadapter);
    }

    @Override
    public void removeSubModule(String removeStr, int pos) {
            if(submodulename.contains(removeStr)){
                submodulename =submodulename.replace(removeStr+",", "");
                mSubModuleList.get(pos).setCheck(0);
                submoduleadapter.notifyDataSetChanged();
                mSpinnerSubModules.postInvalidate();
                mSpinnerSubModules.setAdapter(submoduleadapter);
            }
    }

    @Override
    public void resetCourseListAdapter(int pos, int mRating) {
        QueryListPojo qlp = mCourseListData.get(pos);
        qlp.setRating(mRating);
        mCourseListData.set(pos, qlp);

        mRvCourseQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
        courseAdapter = new CourseQueryListAdapter(getContext(),this, mCourseListData);
        mRvCourseQueryList.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
    }

    @Override
    public void resetGeneralListAdapter(int pos, int mRating) {
        QueryListPojo qlp = mGeneralListData.get(pos);
        qlp.setRating(mRating);
        mGeneralListData.set(pos, qlp);

        //populating general list
        mRvGeneralQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
        generalAdapter = new GeneralQueryListAdapter(getContext(), this, mGeneralListData);
        mRvGeneralQueryList.setAdapter(generalAdapter);
        generalAdapter.notifyDataSetChanged();
    }

    @Override
    public void wsRateUs(int mRating, String mStatus, int pos) {
        mImplPostQuery.wsRateUs(
                mData.get(pos).getQueryid(),
                mRating,
                uid,
                mStatus,
                pos);
    }




    String validatePostQueryForm(long courseid, long moduleid, String query, String querytype){
            if(querytype.equals("course query") && courseid == 0){
                return "Please Select Course";
            }else if(querytype.equals("course query") && moduleid == 0){
                return "Please Select Module";
            }else if(querytype.equals("course query") && submodulename.length() == 0){
                return "Please Select atleast one SubModule";
            }else if(query.isEmpty()){
                return "Query can't be Empty";
            }
        return "ok";
    }

    @Override
    public void radio1() {
        mRvCourseQueryList.setVisibility(View.VISIBLE);
        mRvGeneralQueryList.setVisibility(View.GONE);
        mLinearCourseQuery.setVisibility(View.VISIBLE);
        mLinearpostQuery.setVisibility(View.GONE);
        mSearchView.setVisibility(View.VISIBLE);
        mSearchView.setQuery("", false);
    }

    @Override
    public void radio2() {
        mRvCourseQueryList.setVisibility(View.GONE);
        mRvGeneralQueryList.setVisibility(View.VISIBLE);
        mLinearCourseQuery.setVisibility(View.GONE);
        mLinearpostQuery.setVisibility(View.GONE);
        mSearchView.setVisibility(View.VISIBLE);
        mSearchView.setQuery("", false);
    }

    @Override
    public void wsDeleteQuery(long mQueryId,String mStatus, int position) {
        mImplPostQuery.wsDeleteQuery(mQueryId, uid, position, mStatus);
    }

    @Override
    public void deletePos(int pos, String mStatus) {
        if(mStatus.toLowerCase().equals("course query")){
            mCourseListData.remove(pos);

            mRvCourseQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
            courseAdapter = new CourseQueryListAdapter(getContext(),this, mCourseListData);
            mRvCourseQueryList.setAdapter(courseAdapter);
            courseAdapter.notifyDataSetChanged();
        }else {
            mGeneralListData.remove(pos);

            mRvGeneralQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
            generalAdapter = new GeneralQueryListAdapter(getContext(), this, mGeneralListData);
            mRvGeneralQueryList.setAdapter(generalAdapter);
            generalAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updatePostedQuery(int pos, String mType) {
        submodulename = "";
        LayoutInflater li = LayoutInflater.from(getContext());
        View view = (LinearLayout)li.inflate(R.layout.layout_popupupdatequery, null);
        TextInputEditText et_query = view.findViewById(R.id.et_query);
        Spinner mPopupSpinnerCourses = view.findViewById(R.id.spinner_selectcourse);
        mPopupSpinnerModuleList = view.findViewById(R.id.spinner_selectmodule);
        mPopupSpinnerSubModuleList = view.findViewById(R.id.spinner_selectsubmodule);

        Button mBtnUpdate = view.findViewById(R.id.btn_update);
        Button mBtnClose = view.findViewById(R.id.btn_close);
        TextView mTvQueryType = view.findViewById(R.id.tv_qureytype);
        TextView mTvSelectedCourse = view.findViewById(R.id.tv_selectedcourse);
        TextView mTvSelectedModule = view.findViewById(R.id.tv_selectedmodule);
        TextView mTvSelectedSubModule = view.findViewById(R.id.tv_sm);
        LinearLayout mLinear =  view.findViewById(R.id.linear_coursequery);

        //keep selected Course at the top

        ArrayList<CourseList> spinner1List = mCourseList;
        QueryListPojo qlp = null;
        if(mType.toLowerCase().equals("general query")){
            qlp = mGeneralListData.get(pos);
        }else{
            qlp = mCourseListData.get(pos);
        }

        long queryId = qlp.getQueryid();
        CourseListSpinnerAdapter mPopupcourseadater = new CourseListSpinnerAdapter(getContext(), this,spinner1List);
        mPopupSpinnerCourses.setAdapter(mPopupcourseadater);

        et_query.setText(qlp.getQuery());

        if(mType.toLowerCase().equals("course query")){
            mLinear.setVisibility(View.VISIBLE);
            mTvQueryType.setText("Update Course Query");
            mTvSelectedCourse.setText(qlp.getCoursename());
            mTvSelectedModule.setText(qlp.getModulename());
            String submodules = splitSubModules(qlp.getSubmodules());
            mTvSelectedSubModule.setText(submodules);
        }else{
            mLinear.setVisibility(View.GONE);
            mTvQueryType.setText("Update General Query");
        }
        alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alertDialog = alert.show();


        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                resetPostQueryForm(et_query);
            }
        });

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check =  validatePostQueryForm(courseid,  moduleid, et_query.getText().toString(), mType.toLowerCase());
                if(!check.equals("ok")){
                    Toast.makeText(getContext(), check, Toast.LENGTH_LONG).show();
                }else{
                    mBtnUpdate.setEnabled(false);
                    mImplPostQuery.wsUpdatePostedQuery(queryId,courseid, moduleid, uid, et_query.getText().toString(), submodulename, "Update", mType);
                }
          }
        });

        mPopupSpinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseid = mCourseList.get(position).getCourseId();
                mImplPostQuery.wsModuleList(courseid, "Update");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mPopupSpinnerModuleList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                moduleid = mModuleList.get(position).getMainmoduleid();
                mImplPostQuery.wsSubModuleList(moduleid,"Update");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void dismissPopup() {
        alertDialog.dismiss();
    }

    String splitSubModules(ArrayList<SubModulePojo> mList){
        String subModules = "";
        for(SubModulePojo smp : mList){
            subModules = smp.getSubmodulename().split("###")[0]+",";
        }
        subModules = subModules.substring(0, subModules.length() - 1);

        return subModules;
    }
}
