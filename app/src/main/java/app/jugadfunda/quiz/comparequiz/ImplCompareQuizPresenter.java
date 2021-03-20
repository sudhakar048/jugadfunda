package app.jugadfunda.quiz.comparequiz;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.apiresponse.QuestionListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplCompareQuizPresenter implements CompareQuizInterfaceImpl {
    private Context mContext;
    private CompareQuizInterfaceView mCompareQuizInterfaceView;

    public ImplCompareQuizPresenter(Context mContext, CompareQuizInterfaceView mCompareQuizInterfaceView){
        this.mContext = mContext;
        this.mCompareQuizInterfaceView = mCompareQuizInterfaceView;
    }

    @Override
    public void wsQuestionlist(long mQuizId) {
        EndPointInterface endPointInterface= ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsQuestionList(mQuizId).enqueue(new Callback<List<QuestionListResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionListResponse>> call, Response<List<QuestionListResponse>> response) {
                ArrayList<QuestionListResponse> list = (ArrayList<QuestionListResponse>) response.body();
                mCompareQuizInterfaceView.passDataToRecyclerView(list);
            }

            @Override
            public void onFailure(Call<List<QuestionListResponse>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
