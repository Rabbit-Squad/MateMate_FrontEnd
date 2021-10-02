package org.matemate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceApi {
    @Headers({"Content-Type:application/json"})
    @POST("/signin")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/post")
    Call<PostResponse> addPost(@Body NewPostData data);

    @GET("/list")
    Call<ListResponse> getList();

    @GET("/list/{userIdx}")
    Call<ListResponse> getUserList(@Path("userIdx") int userIdx);

    @POST("/request/{postIdx}")
    Call<JoinResponse> sendRequest(@Path("postIdx") int postIdx, @Body RequestData data);

    @GET("/profile/{userIdx}")
    Call<ProfileResponse> getUserInfo(@Path("userIdx") int userIdx);

    @DELETE("profile/delete/{userIdx}")
    Call<DeleteResponse> deleteUser(@Path("userIdx") int userIdx);

    @GET("/request/mypost/{userIdx}")
    Call<NotificationResponse> getNotification(@Path("userIdx")int userIdx);

    @POST("/approval/{reqIdx}")
    Call<JoinResponse> approve(@Path("reqIdx") int reqIdx);
}