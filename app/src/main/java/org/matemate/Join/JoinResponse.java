package org.matemate.Join;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {
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
