package org.matemate;

public class Post {
    String name;
    String title;
    String location;
    String time;
    int curNum;
    int minNum;
    boolean closed;

    public Post(String name, String title, String location, int curNum, int minNum, String time, boolean closed) {
        this.name = name;
        this.title = title;
        this.location = location;
        this.curNum = curNum;
        this.minNum = minNum;
        this.time = time;
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCurNum() {
        return curNum;
    }

    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

}
