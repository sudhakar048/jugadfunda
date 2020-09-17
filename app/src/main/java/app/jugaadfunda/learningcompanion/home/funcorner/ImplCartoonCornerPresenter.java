package app.jugaadfunda.learningcompanion.home.funcorner;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.FunCornerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplCartoonCornerPresenter implements CartoonCornerInterfaceImpl {
    private Context mContext;
    private CartoonCornerInterfaceView mCartoonCornerInterfaceView;

    public ImplCartoonCornerPresenter(Context mContext, CartoonCornerInterfaceView mCartoonCornerInterfaceView){
        this.mContext = mContext;
        this.mCartoonCornerInterfaceView = mCartoonCornerInterfaceView;
    }

    @Override
    public void wsFunCorner(long mUserId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsLastCartoon(mUserId).enqueue(new Callback<FunCornerResponse>() {
            @Override
            public void onResponse(Call<FunCornerResponse> call, Response<FunCornerResponse> response) {
                if(response.body() != null){
                FunCornerResponse funCornerResponse = response.body();
                mCartoonCornerInterfaceView.populateData(funCornerResponse);
                }else{
                    mCartoonCornerInterfaceView.checkforNodata();
                }

            }

            @Override
            public void onFailure(Call<FunCornerResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsAddEmoji(long mFunId, int mEmoji, long mUserId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsAddEmoji(
                mFunId,
                mEmoji,
                mUserId
        ).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body() != null){
                    boolean flag = response.body();
                    if(flag){
                        mCartoonCornerInterfaceView.updateCount(mEmoji);
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void wsAdvertisement() {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsAdvertisement().enqueue(new Callback<FunCornerResponse>() {
            @Override
            public void onResponse(Call<FunCornerResponse> call, Response<FunCornerResponse> response) {
                if(response.body() != null){
                    FunCornerResponse fcr = response.body();
                    mCartoonCornerInterfaceView.setAdvertisement(fcr.getCartoon());
                }
            }

            @Override
            public void onFailure(Call<FunCornerResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
