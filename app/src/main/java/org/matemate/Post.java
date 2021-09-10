package org.matemate;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;

public class Post {
    /*
    @SerializedName("nickname")
    private String nickname;

    @SerializedName("deadline")
    private String deadline;

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
*/

    String nickname;
    String title;
    String deadline;
    String location;
    String content;
    int id;
    int min_num;
    int cur_num;
    int closed;

    public Post(int id, String nickname, String deadline, String location, int min_num, int cur_num, String title, String content, int closed) {
        this.id = id;
        this.nickname = nickname;
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

    public String getDeadline() {
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

    public int getId() {return id;}
}