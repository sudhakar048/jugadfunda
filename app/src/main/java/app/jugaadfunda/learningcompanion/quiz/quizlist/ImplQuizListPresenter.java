package app.jugaadfunda.learningcompanion.quiz.quizlist;

import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.QuizListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplQuizListPresenter implements QuizListInterfaceImpl{
    private Context mContext;
    private QuizListView quizListView;

    public ImplQuizListPresenter(Context mContext, QuizListView quizListView){
        this.mContext = mContext;
        this.quizListView = quizListView;
    }

    @Override
    public void wsQuizList() {
        EndPointInterface endPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        endPointInterface.wsQuizlist().enqueue(new Callback<List<QuizListResponse>>() {
            @Override
            public void onResponse(Call<List<QuizListResponse>> call, Response<List<QuizListResponse>> response) {
                if(response.body() != null){
                    ArrayList<QuizListResponse> list = (ArrayList<QuizListResponse>) response.body();
                    quizListView.passDatatoRecyclerView(list);
                }else{
                    quizListView.checkforNodata();
                }
            }

            @Override
            public void onFailure(Call<List<QuizListResponse>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
