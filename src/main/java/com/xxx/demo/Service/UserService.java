package com.xxx.demo.Service;

import com.xxx.demo.Entity.User;
import com.xxx.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser (User user){
        try{
            User newUser = userRepository.save(user);
            return newUser;
        }
        catch (Exception e){
            return null;
        }
    }

    public User searchUser (int userID){
        User thisUser = userRepository.searchUserByID(userID);
        return thisUser;
    }

    public void updateUser(String nickName, Date birthday, int age, int gender, String hobby){
        userRepository.setUserInfo(nickName,birthday,age,gender,hobby);
    }

    public String getPassword (String mail){
        return userRepository.getPassword(mail);
    }
}
