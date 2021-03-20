package app.jugadfunda.home.captureidea;

import okhttp3.MultipartBody;

public interface CaptureIdeaInterfaceImpl {

    void wsCaptureIdea(String mDay, String mTitle, String mDescription, String mUnique, String mBetter, String mFProof, String mTriggered, String mFProofOther, String mPSolving, long mUserId, MultipartBody.Part mImage, String mType, int pos);

}
