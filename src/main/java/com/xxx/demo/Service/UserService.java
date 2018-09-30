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

    public User searchUser (String mail){
        User thisUser = userRepository.searchUserByMail(mail);
        return thisUser;
    }

    public void updateUser(String mail,String nickName, Date birthday, int age, int gender, String hobby){
        userRepository.setUserInfo(mail,nickName,birthday,age,gender,hobby);
    }

    public String getPassword (String mail){
        return userRepository.getPassword(mail);
    }

    public boolean changePassword(String mail, String password){
        try {
            userRepository.changePassword(mail,password);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public String getMail (int userID){
        try {
            return userRepository.getMail(userID);
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean changeFavourite(String mail,String favourite){
        try {
            userRepository.changeFavourite(mail,favourite);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public String getFavourite (String mail){
            String favourite = userRepository.getFavourite(mail);
            return favourite;
    }
}
