package org.matemate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String SERVER_URL = BuildConfig.server_url;
    private static Retrofit retrofit = null;

    private RetrofitClient() {

    }

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("HH:mm:ss")
                .setLenient()
                .create();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                        .baseUrl(SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
        }
        return retrofit;
    }
}
