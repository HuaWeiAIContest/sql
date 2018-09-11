package com.xxx.demo.Entity;

import com.xxx.demo.Common.Hobby;

import javax.persistence.*;
import java.util.Date;

@Entity (name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    int userID;

    @Column
    String mail;

    @Column
    String password;

    @Column(name = "nick_name")
    String nickName;

    @Column
    int age;

    @Column
    int gender;

    @Column
    Date birthday;

    @Column
    Hobby hobby1,hobby2,hobby3;

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", hobby1=" + hobby1 +
                ", hobby2=" + hobby2 +
                ", hobby3=" + hobby3 +
                '}';
    }

    public Users() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Hobby getHobby1() {
        return hobby1;
    }

    public void setHobby1(Hobby hobby1) {
        this.hobby1 = hobby1;
    }

    public Hobby getHobby2() {
        return hobby2;
    }

    public void setHobby2(Hobby hobby2) {
        this.hobby2 = hobby2;
    }

    public Hobby getHobby3() {
        return hobby3;
    }

    public void setHobby3(Hobby hobby3) {
        this.hobby3 = hobby3;
    }

    public Users(String mail, String password, String nickName, int age, int gender, Date birthday, Hobby hobby1, Hobby hobby2, Hobby hobby3) {
        this.mail = mail;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
        this.hobby1 = hobby1;
        this.hobby2 = hobby2;
        this.hobby3 = hobby3;
    }
}
