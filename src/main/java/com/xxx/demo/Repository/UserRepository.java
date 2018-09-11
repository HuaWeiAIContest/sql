package com.xxx.demo.Repository;

import com.xxx.demo.Common.Hobby;
import com.xxx.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{

    @Transactional
    @Modifying
    @Query (value = "insert into user set mail = ?1,password = ?2, nick_name = ?3, age = ?4, gender = ?5, birthday = ?6, hobby1 = ?7, hobby2 = ?8,hobby3 = ?9 ",nativeQuery = true)
    public Users addUser(String mail, String password, String nickName, int age, int gender, Date birthday, Hobby hobby1, Hobby hobby2, Hobby hobby3);

    @Query (value = "SELECT * from user where user_id = ?1",nativeQuery = true)
    public Users searchUserByID (int ID);


}
