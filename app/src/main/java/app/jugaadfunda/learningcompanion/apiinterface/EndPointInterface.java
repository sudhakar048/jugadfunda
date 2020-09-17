package app.jugaadfunda.learningcompanion.apiinterface;

import java.util.List;

import app.jugaadfunda.learningcompanion.apiresponse.EventResponse;
import app.jugaadfunda.learningcompanion.apiresponse.FunCornerResponse;
import app.jugaadfunda.learningcompanion.apiresponse.QuestionListResponse;
import app.jugaadfunda.learningcompanion.apiresponse.QuizListResponse;
import app.jugaadfunda.learningcompanion.apiresponse.SigninResponse;
import app.jugaadfunda.learningcompanion.apiresponse.SignupResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface EndPointInterface {

    //for signup
    @POST("STBI/RecordingSignupContoller")
    @FormUrlEncoded
    Call<SignupResponse> wsSignup(@Field("uname") String mUserName,
                                 @Field("uemail") String mUserEmailid,
                                 @Field("udesig") String mDesignation,
                                 @Field("upwd") String mPwd,
                                  @Field("cupwd") String mCpd,
                                  @Field("ut") String mUserType);


    //for signin
    @POST("STBI/RecordingLoginController")
    @FormUrlEncoded
    Call<SigninResponse> wsSignin(@Field("email") String mUserEmailid,
                                  @Field("upwd") String mPwd);


    //for list of quiz
    @GET("Jugaadfunda-Admin/GetQuizListContoller")
    Call<List<QuizListResponse>> wsQuizlist();

    //for question list
    @POST("Jugaadfunda-Admin/GetAllQuestionsController")
    @FormUrlEncoded
    Call<List<QuestionListResponse>> wsQuestionList(@Field("qzid") long mQuizId);


    //for question list
    @POST("Jugaadfunda-Admin/AddAnswerController")
    @FormUrlEncoded
    Call<SignupResponse> wsAddAnswers(@Field("qzid") long mQuizId, @Field("quids") String mQuesId, @Field("optsids") String mOptionIds, @Field("uid") long mUserId);

    // show event list
    @GET("STBI/EventListShow")
    Call<List<EventResponse>> wsEventlist();

    // show event list
    @GET("Jugaadfunda-Admin/showLastImageController")
    Call<FunCornerResponse> wsLastCartoon(@Query("uid") long mUserId);

    //Capture Idea
    @Multipart
    @POST("Jugaadfunda-Admin/IdeaCaptureController")
    Call<SignupResponse> wsCaptureIdea(@Part("_id") RequestBody day, @Part("_it")RequestBody title, @Part("_idesc")RequestBody description, @Part("_iu")RequestBody unique, @Part("_ib")RequestBody better, @Part("_ifp")RequestBody futureproof, @Part("_itriggered")RequestBody triggered, @Part("_fpo")RequestBody fother, @Part("_ips")RequestBody psolving, @Part("uid")RequestBody userid, @Part MultipartBody.Part pdffile);

    @POST("Jugaadfunda-Admin/EnquiryFormController")
    @FormUrlEncoded
    Call<SignupResponse> wsAddEnquiry(@Field("_cname") String mCompanyName, @Field("_address") String mCompanyAddress, @Field("_oname") String mOwnerName, @Field("_city") String mCity, @Field("_pcode") int mPincode, @Field("_pno") String mPanNumber, @Field("_cemailid") String mCompanyEmailId, @Field("_cc") String mCompanyContact, @Field("_estabyear") String mEstablishYear, @Field("_noofdept") int mNoOfDept, @Field("_noofemp") int mNoOfEmp, @Field("_aturnover") String mAnnualTurnOver, @Field("_bdesc") String mBusinessDescription,@Field("keys") String mKeywords, @Field("_adhar") String mAadharNo, @Field("_se") String mSecEmailId, @Field("_sc") String mSecContact, @Field("_einfra") String mEfficeintInfra, @Field("_sales") String mSalesPromotion, @Field("_oandcm") String mOperationandCompliance, @Field("_trnd") String mTrndinnov, @Field("_cmgmt") String mContentmgmt, @Field("_itweb") String mItWeb, @Field("uid") long mUserId);

    @POST("Jugaadfunda-Admin/MOMController")
    @FormUrlEncoded
    Call<SignupResponse> wsMOM(@Field("mdate") String mMomDate,@Field("title") String mTitle,@Field("loc") String mLocation,@Field("stime") String mStarttime,@Field("etime") String mEndtime,@Field("aname") String mAttendeesName,@Field("pd") String mPoints,@Field("n") String mStudentName,@Field("cname") String mCollegeName,@Field("dname") String mDeptName,@Field("y") String mJoiningYear,@Field("uid") long mUserId);

    @POST("Jugaadfunda-Admin/AddFunCornerEmoji")
    @FormUrlEncoded
    Call<Boolean> wsAddEmoji(@Field("funid")long mFunId, @Field("emoji")int mEmoji, @Field("uid")long mUserId);

    @GET("Jugaadfunda-Admin/ShowAdvLastImageController")
    Call<FunCornerResponse> wsAdvertisement();
}
