package org.matemate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<ListData> data;


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ListData> getData() {
        return data;
    }
}
