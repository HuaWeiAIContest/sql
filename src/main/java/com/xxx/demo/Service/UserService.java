package com.xxx.demo.Service;

import com.xxx.demo.Common.Hobby;
import com.xxx.demo.Entity.Users;
import com.xxx.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Users addUser (String mail, String password, String nickName, int age, int gender, Date birthday, Hobby hobby1, Hobby hobby2, Hobby hobby3){
        Users newUser = userRepository.addUser(mail,password, nickName,age, gender,birthday,hobby1,hobby2, hobby3);
        return newUser;
    }

    public Users searchUser (int userID){
        Users thisUser = userRepository.searchUserByID(userID);
        return thisUser;
    }
}
