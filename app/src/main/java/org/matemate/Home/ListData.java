package org.matemate.Home;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;

public class ListData {
    @SerializedName("nickname")
    private String nickname;

    @SerializedName("id")
    private int id;

    @SerializedName("deadline")
    private Timestamp deadline;

    @SerializedName("location")
    private String location;

    @SerializedName("min_num")
    private int min_num;

    @SerializedName("cur_num")
    private int cur_num;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("closed")
    private int closed;

    public ListData(String nickname, int id, Timestamp deadline, String location, int min_num, int cur_num, String title, String content, int closed) {
        this.nickname = nickname;
        this.id = id;
        this.deadline = deadline;
        this.location = location;
        this.min_num = min_num;
        this.cur_num = cur_num;
        this.title = title;
        this.content = content;
        this.closed = closed;
    }

    public String getNickname() {
        return nickname;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public String getLocation() {
        return location;
    }

    public int getMin_num() {
        return min_num;
    }

    public int getCur_num() {
        return cur_num;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getClosed() {
        return closed;
    }

    public int getId() {
        return id;
    }
}
