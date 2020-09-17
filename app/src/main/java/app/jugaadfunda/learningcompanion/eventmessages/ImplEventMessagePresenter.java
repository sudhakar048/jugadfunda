package app.jugaadfunda.learningcompanion.eventmessages;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.EventResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplEventMessagePresenter implements EventInterfaceImpl {
   private Context mContext;
   private EventInterfaceView mEventInterfaceView;

   public ImplEventMessagePresenter(Context mContext, EventInterfaceView mEventInterfaceView){
       this.mContext = mContext;
       this.mEventInterfaceView = mEventInterfaceView;
   }

    @Override
    public void wsEventList() {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsEventlist().enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(Call<List<EventResponse>> call, Response<List<EventResponse>> response) {
                if(response.body() != null){
                    ArrayList<EventResponse> mEventList = (ArrayList<EventResponse>) response.body();
                    mEventInterfaceView.setEventListtoAdapter(mEventList);
                }else{
                        mEventInterfaceView.checkforNodata();
               }
            }

            @Override
            public void onFailure(Call<List<EventResponse>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }
}