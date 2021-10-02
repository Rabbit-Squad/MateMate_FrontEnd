package org.matemate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//내 게시글 신청자 조회
public class NotificationResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<NotificationData> data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<NotificationData> getData() {
        return data;
    }
}
