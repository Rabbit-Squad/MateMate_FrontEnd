package org.matemate.Participate;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse {
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
