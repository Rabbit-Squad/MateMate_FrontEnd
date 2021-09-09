package org.matemate;

import org.matemate.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoAPI {
    @GET("v2/local/search/keyword.json")
    Call<LocationResponse> searchLocation(@Header("Authorization") String key, @Query("query") String query);
}
