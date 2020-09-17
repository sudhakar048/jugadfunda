package app.jugaadfunda.learningcompanion.home.captureidea;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.jugaadfunda.learningcompanion.apiclient.ApiClient;
import app.jugaadfunda.learningcompanion.apiinterface.EndPointInterface;
import app.jugaadfunda.learningcompanion.apiresponse.SignupResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImplCaptureIdeaPresenter  implements CaptureIdeaInterfaceImpl{

    private Context mContext;
    private CaptureIdeaInterfaceView mCaptureIdeaInterfaceView;

    public ImplCaptureIdeaPresenter(Context mContext, CaptureIdeaInterfaceView mCaptureIdeaInterfaceView){
        this.mContext = mContext;
        this.mCaptureIdeaInterfaceView = mCaptureIdeaInterfaceView;
    }

    @Override
    public void wsCaptureIdea(String mDay, String mTitle, String mDescription, String mUnique, String mBetter, String mFProof, String mTriggered, String mFProofOther, String mPSolving, long mUserId, MultipartBody.Part mImage, int pos) {
        RequestBody rbmDay = null;
        RequestBody rbmTitle = null;
        RequestBody rbmDescription = null;
        RequestBody rbmUnique = null;
        RequestBody rbmBetter = null;
        RequestBody rbmFProof = null;
        RequestBody rbmTriggered = null;
        RequestBody rbmFProofOther = null;
        RequestBody rbmPSolving = null;
        RequestBody rbmUserId = null;
        try {
            rbmDay = RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mDay,"UTF-8"));

            rbmTitle= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mTitle,"UTF-8"));

            rbmDescription= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mDescription,"UTF-8"));

            rbmUnique= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mUnique,"UTF-8"));

            rbmBetter= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mBetter,"UTF-8"));

            rbmFProof= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mFProof,"UTF-8"));

            rbmTriggered= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mTriggered,"UTF-8"));

            rbmFProofOther= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mFProofOther,"UTF-8"));

            rbmPSolving= RequestBody.create(MediaType.parse("multipart/form-data"), URLEncoder.encode(mPSolving,"UTF-8"));

            rbmUserId= RequestBody.create(MediaType.parse("multipart/form-data"), ""+mUserId);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   EndPointInterface mEndPointInterface = ApiClient.getmRetrofitInstance().create(EndPointInterface.class);
        mEndPointInterface.wsCaptureIdea(
                rbmDay,
                rbmTitle,
                rbmDescription,
                rbmUnique,
                rbmBetter,
                rbmFProof,
                rbmTriggered,
                rbmFProofOther,
                rbmPSolving,
                rbmUserId,
                mImage
        ).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                SignupResponse submitidea = response.body();
                if(submitidea.isFlag()){
                    mCaptureIdeaInterfaceView.clearWeekIdea();
                }
                Toast.makeText(mContext,submitidea.getResult(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(mContext,"Unable to connect internet. Pls try again after some time",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
