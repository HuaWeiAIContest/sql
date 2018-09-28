package com.xxx.demo.Controller;

import com.xxx.demo.Common.Response;
import com.xxx.demo.Entity.User;
import com.xxx.demo.Service.MailService;
import com.xxx.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.xxx.demo.Common.ResultGenerator.genFailResult;
import static com.xxx.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;


    @PostMapping("/add-user")
    public Response addUser (@RequestBody User thisUser){

       User newUser = userService.addUser(thisUser);
       if (newUser == null){
           return genFailResult("添加失败,可能是参数类型不匹配");
       }
       else {
           return genSuccessResult(newUser);
       }
    }

    @GetMapping("/get-user-by-id")
    public Response getUserByID (@RequestParam int userID){
        User thisUser = userService.searchUser(userID);
        if (thisUser == null){
            return genFailResult("获取失败");
        }
        else {
            return genSuccessResult(thisUser);
        }
    }

    @GetMapping("/get-verification-code")
    public Response getVerificationCode(@RequestParam String mail){
        String toReturn = mailService.sendMail(mail);
        if (toReturn != null){
            return genSuccessResult(toReturn);
        }
        else {
            return genFailResult("发送失败");
        }
    }
    @GetMapping("/hello")
    public Response hello(){
        return genSuccessResult ("Y");
    }
}
