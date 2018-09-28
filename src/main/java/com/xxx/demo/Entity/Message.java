package com.xxx.demo.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "message")
@Table (name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "message_id")
    int id;

    @Column(name = "user_id")
    int userID;

    @Column (name = "bot_id")
    String botID;

    @Column(columnDefinition = "char(200) character set utf8")
    String context;

    @Column (name = "send_time")
    Date sendTime;

    @Column
    int status;

    public Message(int userID, String botID, String context, Date sendTime, int status) {
        this.userID = userID;
        this.botID = botID;
        this.context = context;
        this.sendTime = sendTime;
        this.status = status;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBotID() {
        return botID;
    }

    public void setBotID(String botID) {
        this.botID = botID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userID=" + userID +
                ", botID=" + botID +
                ", context='" + context + '\'' +
                ", sendTime=" + sendTime +
                ", status=" + status +
                '}';
    }
}
