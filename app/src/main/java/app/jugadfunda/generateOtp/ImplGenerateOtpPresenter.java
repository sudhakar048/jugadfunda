package app.jugadfunda.generateOtp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.GenerateOtpResponse;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.VerifyOtpResponse;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.StateList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplGenerateOtpPresenter implements GenerateOtpImpl {
    private Context mContext;
    private GenerateOtpView mGenerateOtpView;

    public ImplGenerateOtpPresenter(Context mContext, GenerateOtpView mGenerateOtpView){
        this.mContext = mContext;
        this.mGenerateOtpView = mGenerateOtpView;
    }

    @Override
    public void generateOtp(String mobilenumber, long qzid) {
         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
        }

        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsGenerateOtp(
                mobilenumber,
                qzid
        ).enqueue(new Callback<GenerateOtpResponse>() {
            @Override
            public void onResponse(Call<GenerateOtpResponse> call, Response<GenerateOtpResponse> response) {
                Log.d("onResponse", "onResponse() called with: call = [" + call + "], response = [" + response.body() + "]");
                GenerateOtpResponse res = response.body();

                if(res.getMsg().length() > 4) {
                    mGenerateOtpView.showMsg(res.getMsg());
                }else{
                    mGenerateOtpView.generateOtp(res.getMsg());
                }
            }

            @Override
            public void onFailure(Call<GenerateOtpResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void verifyOtp(String firstname,String middlename, String lastname, String gender, String dob, String mobilenumber, String emailId, int stateid, int districtid, long centerid, long instituteid, long quizid) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
            emailId = Base64.getEncoder().encodeToString(emailId.getBytes());
        }

        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsVerifyOtp(
                firstname,
                middlename,
                lastname,
                gender,
                dob,
                mobilenumber,
                emailId,
                stateid,
                districtid,
                centerid,
                instituteid,
                quizid
        ).enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                VerifyOtpResponse res = response.body();
                if(res.isFlag()){
                    mGenerateOtpView.movetoQuizActivity();
                    mGenerateOtpView.clearForm();
                }
                Toast.makeText(mContext, res.getRes(), Toast.LENGTH_LONG).show();
             }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void populateStates() {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsStateList().enqueue(new Callback<List<StateList>>() {
            @Override
            public void onResponse(Call<List<StateList>> call, Response<List<StateList>> response) {
                if(response.body() !=null){
                    ArrayList<StateList> stateLists = (ArrayList<StateList>) response.body();
                    mGenerateOtpView.populateStates(stateLists);
                }
            }

            @Override
            public void onFailure(Call<List<StateList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void populateDistricts(long stateid) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsDistrictList(stateid).enqueue(new Callback<List<DistrictList>>() {
            @Override
            public void onResponse(Call<List<DistrictList>> call, Response<List<DistrictList>> response) {
                if(response.body() !=null){
                    ArrayList<DistrictList> districtLists = (ArrayList<DistrictList>) response.body();
                    mGenerateOtpView.populateDistricts(districtLists);
                }
            }

            @Override
            public void onFailure(Call<List<DistrictList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void populateCenters(long stateid, long districtid) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsCenterList(stateid, districtid).enqueue(new Callback<List<CenterList>>() {
            @Override
            public void onResponse(Call<List<CenterList>> call, Response<List<CenterList>> response) {
                if(response.body() !=null){
                    ArrayList<CenterList> centerLists = (ArrayList<CenterList>) response.body();
                    mGenerateOtpView.populateCenters(centerLists);
                }
            }

            @Override
            public void onFailure(Call<List<CenterList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void populateInstitutes(long centerid) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsInstituteList(centerid).enqueue(new Callback<List<InstituteList>>() {
            @Override
            public void onResponse(Call<List<InstituteList>> call, Response<List<InstituteList>> response) {
                if(response.body() !=null){
                    ArrayList<InstituteList> instituteLists = (ArrayList<InstituteList>) response.body();
                    mGenerateOtpView.populateInstitutes(instituteLists);
                }
            }

            @Override
            public void onFailure(Call<List<InstituteList>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
