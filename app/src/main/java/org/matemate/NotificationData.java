package org.matemate;

import com.google.gson.annotations.SerializedName;

public class NotificationData {
    @SerializedName("title")
    private String title;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("arrive_time")
    private String arrive_time;

    public NotificationData(String title, String nickname, int id, String content, String arrive_time) {
        this.title = title;
        this.nickname = nickname;
        this.id = id;
        this.content = content;
        this.arrive_time = arrive_time;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public String getTitle() {
        return title;
    }

    public int getId() { return id; }
}
