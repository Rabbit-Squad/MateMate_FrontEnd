package org.matemate;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ServiceApi {
    @Headers({"Content-Type : application/json"})
    @GET("/signin")
    Call<LoginResponse> userLogin(@Body LoginData data);
}
