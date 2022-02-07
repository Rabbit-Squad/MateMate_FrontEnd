package org.matemate.Participate;

import com.google.gson.annotations.SerializedName;

public class RequestData {
    @SerializedName("userId")
    public int userId;

    @SerializedName("content")
    public String content;

    @SerializedName("arrive_time")
    public String arrive_time;

    public RequestData(int userId, String content, String arrive_time) {
        this.userId = userId;
        this.content = content;
        this.arrive_time = arrive_time;
    }
}
