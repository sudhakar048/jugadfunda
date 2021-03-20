package app.jugadfunda.home.sessions.sessionlist;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.jugadfunda.apiclient.ApiClient;
import app.jugadfunda.apiinterface.EndPointInterface;
import app.jugadfunda.home.pojo.SessionDetailsPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionListImpl implements ImplSessionListInterface {
    private Context mContext;
    private SessionListInterfaceView mSessionListInterfaceView;

    public SessionListImpl(Context mContext, SessionListInterfaceView mSessionListInterfaceView){
        this.mContext = mContext;
        this.mSessionListInterfaceView = mSessionListInterfaceView;
    }

    @Override
    public void wsSessionList(long mUserId) {
        EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsSessionList(mUserId).enqueue(new Callback<List<SessionDetailsPojo>>() {
            @Override
            public void onResponse(Call<List<SessionDetailsPojo>> call, Response<List<SessionDetailsPojo>> response) {
                ArrayList<SessionDetailsPojo> mList = (ArrayList<SessionDetailsPojo>) response.body();
                if(mList != null){
                    mSessionListInterfaceView.populateSessions(mList);
                }else{
                    mSessionListInterfaceView.showEmptyRecord();
                }
            }

            @Override
            public void onFailure(Call<List<SessionDetailsPojo>> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
