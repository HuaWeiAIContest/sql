package com.xxx.demo.Entity;

import javax.persistence.*;

@Entity(name = "bot")
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bot_id")
    int botID;

    @Column
    String name;

    @Column (name = "introduction ",columnDefinition = "char(200) character set utf8")
    String introduction;

    public Bot(String name, String introduction) {
        this.name = name;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "botID=" + botID +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public int getBotID() {
        return botID;
    }

    public void setBotID(int botID) {
        this.botID = botID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bot(String introduction) {
        this.introduction = introduction;
    }

    public Bot() {
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
