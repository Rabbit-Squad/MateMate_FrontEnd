package org.matemate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KakaoRetrofit {
    private final static String SERVER_URL = "https://dapi.kakao.com/";
    private static Retrofit retrofit;

    public static Retrofit searchLocation() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
