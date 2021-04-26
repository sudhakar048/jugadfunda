package app.jugadfunda.quiz.questions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.apiresponse.SignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplStartQuizPresenter implements StartQuizInterfaceImpl {

    private Context mContext;
    private StartQuizInterfaceView mStartQuizInterfaceView;

    public ImplStartQuizPresenter(Context mContext, StartQuizInterfaceView mStartQuizInterfaceView){
        this.mContext = mContext;
        this.mStartQuizInterfaceView = mStartQuizInterfaceView;
    }

    @Override
    public void wsQuestionlist(long mQuizId) {
        EndPointInterface endPointInterface= ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsQuestionList(mQuizId).enqueue(new Callback<List<QuestionListResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionListResponse>> call, Response<List<QuestionListResponse>> response) {
                ArrayList<QuestionListResponse> list = (ArrayList<QuestionListResponse>) response.body();
               if(list != null){
                   mStartQuizInterfaceView.passList(list);
               }else{
                   mStartQuizInterfaceView.showEmptyData();
               }
            }

            @Override
            public void onFailure(Call<List<QuestionListResponse>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsAddAnswers(long mQuizId, JSONArray list, String mobilenumber, int minutetime, int secondtime) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mobilenumber = Base64.getEncoder().encodeToString(mobilenumber.getBytes());
        }
        EndPointInterface endPointInterface= ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsAddAnswers(
                mQuizId,
                list.toString(),
                mobilenumber,
                minutetime,
                secondtime).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.body() != null){
                    SignupResponse signupResponse = response.body();
                    mStartQuizInterfaceView.clearData();
                    Toast.makeText(mContext,""+signupResponse.getResult(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
