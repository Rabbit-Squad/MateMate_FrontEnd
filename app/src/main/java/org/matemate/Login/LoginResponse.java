package org.matemate.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int status;

    @SerializedName("userIdx")
    private int userIdx;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public String getMessage() {
        return message;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public int getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
