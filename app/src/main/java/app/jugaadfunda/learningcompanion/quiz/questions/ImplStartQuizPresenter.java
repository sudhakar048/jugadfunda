package app.jugaadfunda.learningcompanion.quiz.questions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.QuestionListResponse;
import app.jugaadfunda.learningcompanion.apiresponse.SignupResponse;
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

                Log.e("list",""+list);
                mStartQuizInterfaceView.passDataToRecyclerView(list);
            }

            @Override
            public void onFailure(Call<List<QuestionListResponse>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsAddAnswers(long mQuizId, String mQuestions, String mOptions, long mUserId) {
        EndPointInterface endPointInterface= ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsAddAnswers(
                mQuizId,
                mQuestions,
                mOptions,
                mUserId).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse signupResponse = response.body();
                if(signupResponse.isFlag()){
                    mStartQuizInterfaceView.serAdapter();
                }
                Toast.makeText(mContext,""+signupResponse.getResult(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
