package app.jugadfunda.apiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit mRetrofit;
    private static final String BASE_URL = "http://192.168.0.104:8080/  ";

    public static Retrofit getmRetrofitInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return mRetrofit;
    }

}
