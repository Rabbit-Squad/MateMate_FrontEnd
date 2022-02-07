package org.matemate.Interface;

import org.matemate.LocationFind.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface KakaoAPI {
    @GET("v2/local/search/keyword.json")
    Call<LocationResponse> searchLocation(@Header("Authorization") String key, @Query("query") String query);
}
