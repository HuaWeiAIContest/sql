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

    @Query (value = "SELECT * from user where user_id = ?1",nativeQuery = true)
    public User searchUserByID (int ID);

    @Transactional
    @Modifying
    @Query(value = "update user set nick_name= ?1, birthday = ?2, age = ?3, gender = ?4, hobby = ?5 where user_id = ?1",nativeQuery = true)
    public void setUserInfo(String nickName,Date birthday,int age,int gender, String hobby);

    @Query(value = "select password from user where mail = ?1",nativeQuery = true)
    public String getPassword(String mail);
}
