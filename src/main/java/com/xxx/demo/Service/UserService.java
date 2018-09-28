package com.xxx.demo.Service;

import com.xxx.demo.Entity.User;
import com.xxx.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser (User user){
        User newUser = userRepository.save(user);
        return newUser;
    }

    public User searchUser (int userID){
        User thisUser = userRepository.searchUserByID(userID);
        return thisUser;
    }
}
