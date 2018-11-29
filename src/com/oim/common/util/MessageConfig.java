package com.oim.common.util;

import java.io.Serializable;

public class MessageConfig implements Serializable {

    private String time;
    private String content;
    private User from;
    private User to;
    private  String type;
    public MessageConfig() {
        super();
    }
    @Override
    public String toString() {
        return "MessageConfig [time=" + time + ", content=" + content + ", from=" + from + ", to=" + to + ", type=" + type
                + "]";
    }
    public MessageConfig(String time, String content, User from, User to, String type) {
        super();
        this.time = time;
        this.content = content;
        this.from = from;
        this.to = to;
        this.type = type;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getFrom() {
        return from;
    }
    public void setFrom(User from) {
        this.from = from;
    }
    public User getTo() {
        return to;
    }
    public void setTo(User to) {
        this.to = to;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}

