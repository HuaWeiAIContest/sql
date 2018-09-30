package com.xxx.demo.Repository;

import com.xxx.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    @Transactional
    @Modifying
    @Query (value = "insert into user set mail = ?1,password = ?2, nick_name = ?3, age = ?4, gender = ?5, birthday = ?6, hobby = ?7",nativeQuery = true)
    public User addUser(String mail, String password, String nickName, int age, int gender, Date birthday, String hobby);

    @Query (value = "SELECT * from user where mail = ?1",nativeQuery = true)
    public User searchUserByMail (String mail);

    @Transactional
    @Modifying
    @Query(value = "update user set nick_name= ?2, birthday = ?3, age = ?4, gender = ?5, hobby = ?6 where mail= ?1",nativeQuery = true)
    public void setUserInfo(String mail,String nickName,Date birthday,int age,int gender, String hobby);

    @Query(value = "select password from user where mail = ?1",nativeQuery = true)
    public String getPassword(String mail);

    @Transactional
    @Modifying
    @Query(value = "update user set favourite = ?2 where mail = ?1",nativeQuery = true)
    public void changeFavourite(String mail,String favourite);

    @Query (value = "select favourite from user where mail = ?1",nativeQuery = true)
    public String getFavourite(String mail);

    @Transactional
    @Modifying
    @Query (value = "update user set password = ?2 where mail = ?1",nativeQuery = true)
    public void changePassword(String mail,String password);

    @Query (value = "select mail from user where user_id = ?1 ",nativeQuery = true)
    public String getMail(int userID);
}
