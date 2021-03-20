package app.jugadfunda.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit mRetrofit;
    private static final String BASE_URL = "http://192.168.0.104:8080/";

    public static Retrofit getmRetrofitInstance(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return mRetrofit;
    }

}
