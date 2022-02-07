package org.matemate.WritePost;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
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
}
