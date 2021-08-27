package org.matemate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceApi {
    @Headers({"Content-Type:application/json"})
    @POST("/signin")
    Call<LoginResponse> userLogin(@Body LoginData data);
}
