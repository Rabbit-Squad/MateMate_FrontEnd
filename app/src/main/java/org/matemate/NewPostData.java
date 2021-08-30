package org.matemate;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class NewPostData {
    @SerializedName("userId")
    private int userId;

    @SerializedName("deadline")
    private Time deadline;

    @SerializedName("location")
    private String location;

    @SerializedName("min_num")
    private int min_num;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public NewPostData(int userId, Time deadline, String location, int min_num, String title, String content) {
        this.userId = userId;
        this.deadline = deadline;
        this.location = location;
        this.min_num = min_num;
        this.title = title;
        this.content = content;
    }
}
