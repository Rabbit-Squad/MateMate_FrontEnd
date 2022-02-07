package org.matemate.Setting;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("email")
    public String email;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("status")
    public int status;

    @SerializedName("message")
    public String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
