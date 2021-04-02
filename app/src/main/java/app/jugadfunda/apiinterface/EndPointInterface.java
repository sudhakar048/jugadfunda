package app.jugadfunda.apiinterface;

import java.util.ArrayList;
import java.util.List;
import app.jugadfunda.apiresponse.EventResponse;
import app.jugadfunda.apiresponse.FunCornerResponse;
import app.jugadfunda.apiresponse.GenerateOtpResponse;
import app.jugadfunda.apiresponse.IndustryRegistration;
import app.jugadfunda.apiresponse.InstituteList;
import app.jugadfunda.apiresponse.LinkedIndustryList;
import app.jugadfunda.apiresponse.MoMResponse;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.apiresponse.QuizListResponse;
import app.jugadfunda.apiresponse.SigninResponse;
import app.jugadfunda.apiresponse.SignupResponse;
import app.jugadfunda.apiresponse.VerifyOtpResponse;
import app.jugadfunda.home.pojo.AddReport;
import app.jugadfunda.home.pojo.CenterList;
import app.jugadfunda.home.pojo.CourseList;
import app.jugadfunda.home.pojo.DeleteQuery;
import app.jugadfunda.home.pojo.DistrictList;
import app.jugadfunda.home.pojo.ModuleListPojo;
import app.jugadfunda.home.pojo.QueryListPojo;
import app.jugadfunda.home.pojo.RatingPojo;
import app.jugadfunda.home.pojo.SessionDetailsPojo;
import app.jugadfunda.home.pojo.SessionResult;
import app.jugadfunda.home.pojo.StateList;
import app.jugadfunda.home.pojo.SubModuleListPojo;
import app.jugadfunda.inquiryform.pojo.CaptureProblemPojo;
import app.jugadfunda.inquiryform.pojo.MomPojo;
import app.jugadfunda.login.pojo.RadioLogin;
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

    //for signin
    @POST("STBI/MobileLogin")
    @FormUrlEncoded
    Call<SigninResponse> wsSignin(@Field("email") String mUserEmailid,
                                  @Field("password") String mPwd,
                                  @Field("m") String mType,
                                  @Field("t") String mToken);


    //for guest user signin
    @POST("STBI/RecordingLoginController")
    @FormUrlEncoded
    Call<RadioLogin> wsRadioSignin(@Field("email") String mUserEmailid,
                                   @Field("upwd") String mPwd);


    //for list of quiz
    @GET("Jugaadfunda_Admin/GetQuizListContoller")
    Call<List<QuizListResponse>> wsQuizlist(@Query("mb") String mobilrNumber);

    //for question list
    @POST("Jugaadfunda_Admin/GetAllQuestionsController")
    @FormUrlEncoded
    Call<List<QuestionListResponse>> wsQuestionList(@Field("qzid") long mQuizId);


    //for question list
    @POST("Jugaadfunda_Admin/AddAnswerController")
    @FormUrlEncoded
    Call<SignupResponse> wsAddAnswers(@Field("qzid") long mQuizId, @Field("quids") String mQuesId, @Field("optsids") String mOptionIds, @Field("uid") long mUserId, @Field("mb") String mMobileNumber);

    // show event list
    @GET("STBI/EventListShow")
    Call<List<EventResponse>> wsEventlist(@Query("modulecode") String mModuleCode);

    // show event list
    @GET("Jugaadfunda_Admin/showLastImageController")
    Call<FunCornerResponse> wsLastCartoon(@Query("uid") long mUserId);

    //Capture Idea
    @Multipart
    @POST("Jugaadfunda_Admin/IdeaCaptureController")
    Call<SignupResponse> wsCaptureIdea(@Part("_id") RequestBody day, @Part("_it")RequestBody title, @Part("_idesc")RequestBody description, @Part("_iu")RequestBody unique, @Part("_ib")RequestBody better, @Part("_ifp")RequestBody futureproof, @Part("_itriggered")RequestBody triggered, @Part("_fpo")RequestBody fother, @Part("_ips")RequestBody psolving, @Part("mt")RequestBody mtype,@Part("uid")RequestBody userid, @Part MultipartBody.Part pdffile);

    @POST("Jugaadfunda_Admin/EnquiryFormController")
    @FormUrlEncoded
    Call<SignupResponse> wsAddEnquiry(@Field("_cname") String mCompanyName, @Field("_address") String mCompanyAddress, @Field("_oname") String mOwnerName, @Field("_city") String mCity, @Field("_pcode") int mPincode, @Field("_pno") String mPanNumber, @Field("_cemailid") String mCompanyEmailId, @Field("_cc") String mCompanyContact, @Field("_estabyear") String mEstablishYear, @Field("_noofdept") int mNoOfDept, @Field("_noofemp") int mNoOfEmp, @Field("_aturnover") String mAnnualTurnOver, @Field("_bdesc") String mBusinessDescription,@Field("keys") String mKeywords, @Field("_adhar") String mAadharNo, @Field("_se") String mSecEmailId, @Field("_sc") String mSecContact, @Field("_einfra") String mEfficeintInfra, @Field("_sales") String mSalesPromotion, @Field("_oandcm") String mOperationandCompliance, @Field("_trnd") String mTrndinnov, @Field("_cmgmt") String mContentmgmt, @Field("_itweb") String mItWeb,@Field("mt")String module, @Field("uid") long mUserId);

    @POST("Jugaadfunda_Admin/MOMController")
    @FormUrlEncoded
    Call<MoMResponse> wsMOM(@Field("mdate") String mMomDate, @Field("title") String mTitle, @Field("loc") String mLocation, @Field("stime") String mStarttime, @Field("etime") String mEndtime, @Field("aname") String mAttendeesName, @Field("pd") String mPoints, @Field("n") String mStudentName, @Field("cname") String mCollegeName, @Field("dname") String mDeptName, @Field("y") String mJoiningYear, @Field("mt") String mType, @Field("uid") long mUserId);

    @POST("Jugaadfunda_Admin/AddFunCornerEmoji")
    @FormUrlEncoded
    Call<Boolean> wsAddEmoji(@Field("funid")long mFunId, @Field("emoji")int mEmoji, @Field("e")String mEmailId);

    @GET("Jugaadfunda_Admin/ShowAdvLastImageController")
    Call<FunCornerResponse> wsAdvertisement();

    @POST("STBI/UpdateDeviceTokenController")
    @FormUrlEncoded
    Call<Boolean> wsUpdateToken(@Field("uid")long userId, @Field("mn")String moduleType, @Field("t")String mToken);

    @POST("STBI/SessionDetailsController")
    @FormUrlEncoded
    Call<List<SessionDetailsPojo>> wsSessionList(@Field("mid")long mUserId);

    @POST("STBI/ViewSessionDetailsController")
    @FormUrlEncoded
    Call<SessionDetailsPojo> wsSessionDetails(@Field("sessionId")long mSessionId, @Field("mentorId")long mMentorId);

    @POST("STBI/UpdateResponseStatusController")
    @FormUrlEncoded
    Call<SessionResult> wsAcceptReject(@Field("sessionId")long mSessionId, @Field("mentorId")long mMentorId, @Field("status")String mStatus, @Field("resreason")String mReason);

    @POST("STBI/AddReportController")
    @FormUrlEncoded
    Call<AddReport> wsAddReport(@Field("sessionId")long mSessionId, @Field("mentorId")long mMentorId, @Field("attendees")String mAttendees, @Field("summary") String mSummery, @Field("suggession") String mSuggestion, @Field("link") String mLink);

    @POST("STBI/UpdateReasonController")
    @FormUrlEncoded
    Call<SessionResult> wsUpdateSessionStatus(@Field("sessionId")long mSessionIkd, @Field("mentorId")long mMentorId, @Field("status")String mStatus, @Field("attendreason")String mReason);

    @POST("STBI/UpdateReasonController")
    @FormUrlEncoded
    Call<SessionResult> wsUpdateStatus(@Field("sessionId")long mSessionIkd, @Field("mentorId")long mMentorId, @Field("status")String mStatus);


    @GET("STBI/GetCourseListController")
    Call<ArrayList<CourseList>> wsCourseList();

    @GET("STBI/UpgradeMySkillFetchMainModulList")
    Call<ArrayList<ModuleListPojo>> wsModuleList(@Query("courseid") String mCourseId);

    @GET("STBI/UpgradeMySkillFetchSubModuleList")
    Call<ArrayList<SubModuleListPojo>> wsSubModuleList(@Query("mid") String mModuleId);

    @POST("STBI/AddPostedQueryController")
    @FormUrlEncoded
    Call<AddReport> wsAddQuery(@Field("courseid")long mCourseId, @Field("moduleid") long mModuleId, @Field("umsid") long mUmsId, @Field("query") String mQuery, @Field("submoduleids") String mSubModuleIds, @Field("btn") String mBtns);

    @POST("STBI/AddPostedQueryController")
    @FormUrlEncoded
    Call<AddReport> wsUpdateQuery(@Field("queryid")long mQueryId,@Field("courseid")long mCourseId, @Field("moduleid") long mModuleId, @Field("umsid") long mUmsId, @Field("query") String mQuery, @Field("submoduleids") String mSubModuleIds, @Field("btn") String mBtns);

    @POST("STBI/PostedQueryListController")
    @FormUrlEncoded
    Call<ArrayList<QueryListPojo>> wsPostedQueryList(@Field("umsid") long mUmsId);

    @POST("STBI/UpdateRatingController")
    @FormUrlEncoded
    Call<RatingPojo> wsRateUs(@Field("queryid") long mQueryId, @Field("rating") int mRating,  @Field("umsid") long mUmsId);

    @POST("STBI/DeleteQueryController")
    @FormUrlEncoded
    Call<DeleteQuery> wsDeleteQuery(@Field("queryid")long mQueryId, @Field("umsid") long mUmsId);

    @POST("Jugaadfunda_Admin/GetMomListAsperModule")
    @FormUrlEncoded
    Call<ArrayList<MomPojo>> wsGetMomList(@Field("uid")long mUserId, @Field("mt") String mModuleType);

    @POST("Jugaadfunda_Admin/DeleteMOMController")
    @FormUrlEncoded
    Call<SignupResponse> wsDeleteMom(@Field("momid")long mMoMId);

    @POST("STBI/AddProblemStatementController")
    @FormUrlEncoded
    Call<CaptureProblemPojo> wsCaptureProblem(@Field("industryid")long mIndustryId, @Field("keywords") String mKeyWords, @Field("whatcp") String mWhatcp, @Field("whycp") String mWhycp, @Field("wherecp") String mWherecp, @Field("whocp") String mWhocp, @Field("whencp") String mWhencp, @Field("howcp") String mHowcp, @Field("observecp") String mObservecp, @Field("ideal") String mIdeal, @Field("reality") String mReality, @Field("consequences") String mConsequences, @Field("outcome") String mOutcome, @Field("resources") String mResources, @Field("skills") String mSkills, @Field("skillreq") String mSkillreq, @Field("graduation") String mGraduation, @Field("priority") String mPriority, @Field("maxapplicants") int mMaxapplicants , @Field ("category") String mCategory, @Field("bdomain") String mBdomain, @Field("domains") String mDomains, @Field("subdomains") String mSubdomains, @Field("module") String mModule, @Field("bdomainother") String mBdomainother, @Field("psdomainother") String mPsdomainother, @Field("btn") String mBtn);

    @GET("STBI/GetStateListController")
    Call<List<StateList>> wsStateList();

    @GET("STBI/GetDistrictListController")
    Call<List<DistrictList>> wsDistrictList(@Query("sid")long mSid);

    @POST("STBI/CenterListController")
    @FormUrlEncoded
    Call<List<CenterList>> wsCenterList(@Field("sid") long mSid, @Field("did") long mDid);

    @POST("STBI/UpgradeMyBusiness")
    @FormUrlEncoded
    Call<IndustryRegistration> wsCallIndustryRegistration(@Field("cn")String mCompanyName, @Field("cpn")String mGstnnumber, @Field("on") String mOwnername, @Field("code") String mContactCode, @Field("c")String mContact, @Field("email") String mEmailId, @Field("module") String mModule, @Field("an") String mOwnerAadhaar, @Field("coname") String mCoordinatorName, @Field("cocode")String cocode, @Field("copmobilenumber")String mCoContact, @Field("coemail")String mCoOrEmailId, @Field("coadharnumber") String mCoAadhaar, @Field("someDate")String mDate, @Field("dept") String mDepartment, @Field("emp") String mEmp, @Field("annual")String mAnnualTurnover, @Field("address")String mAddress, @Field("about")String mAboutCompany, @Field("city")String mCity, @Field("pincode")String mPinCode, @Field("check") String mCheck, @Field("centerid")long mCenterId, @Field("instituteid")long mInstituteId, @Field("sid") int mStateId, @Field("did") int mDistrictId, @Field("tenkeys")String mTenKeywords, @Field("institute")String mInstituteName, @Field("maindomain")String mDomain, @Field("subdomain")String mSubDomain, @Field("userid")long mUserId, @Field("courseid")long mCourseId);

    @POST("STBI/ListInstituteController")
    @FormUrlEncoded
    Call<List<InstituteList>> wsInstituteList(@Field("cid")long mCenterId);

    @GET("STBI/GeLinktIndustryDetailsController")
    Call<List<LinkedIndustryList>> wsLiknedIndustryList(@Query("userid") long mUserId, @Query("module") String mModuleType);

    @POST("STBI/GenerateOtpController")
    @FormUrlEncoded
    Call<GenerateOtpResponse> wsGenerateOtp(@Field("mb")String mMobileNumber, @Field("qzid")long mQuizId);

    @POST("STBI/VerifyOtpController")
    @FormUrlEncoded
    Call<VerifyOtpResponse> wsVerifyOtp(@Field("fname")String mFirstname, @Field("mname")String mMiddlename, @Field("lname")String mLastname, @Field("gen")String mGender, @Field("dob")String mDob, @Field("e")String mEmailId, @Field("mb")String mMobileNumber, @Field("sid")int mStateId, @Field("did")int mDistrictId, @Field("cid")long mCenterId, @Field("insid")long mInstituteId,@Field("qzid")long mQuizId);
}
