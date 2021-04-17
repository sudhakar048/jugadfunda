package app.jugadfunda.login.news;

import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.login.pojo.NewsPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplNewsPresenter implements NewsInterfaceImpl{
    private Context mContext;
    private NewsInterfaceView mNewsInterfaceView;

    public ImplNewsPresenter(Context mContext, NewsInterfaceView mNewsInterfaceView){
        this.mContext = mContext;
        this.mNewsInterfaceView = mNewsInterfaceView;
    }

    @Override
    public void wsNewsList(String mParam) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsNewslist(
                mParam
        ).enqueue(new Callback<List<NewsPojo>>() {
            @Override
            public void onResponse(Call<List<NewsPojo>> call, Response<List<NewsPojo>> response) {
                if(response.body() != null){
                    ArrayList<NewsPojo> mList = (ArrayList<NewsPojo>) response.body();
                    mNewsInterfaceView.passDataToRecyclerView(mList);
                }else{
                    mNewsInterfaceView.checkforNodata();
               }
            }

            @Override
            public void onFailure(Call<List<NewsPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsInspiringStories(String mParam) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsStorylist(
                mParam
        ).enqueue(new Callback<List<NewsPojo>>() {
            @Override
            public void onResponse(Call<List<NewsPojo>> call, Response<List<NewsPojo>> response) {
                if(response.body() != null){
                    ArrayList<NewsPojo> mList = (ArrayList<NewsPojo>) response.body();
                    mNewsInterfaceView.passDataToRecyclerView(mList);
                }else{
                    mNewsInterfaceView.checkforNodata();
                }
            }

            @Override
            public void onFailure(Call<List<NewsPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
