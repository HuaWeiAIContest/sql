package com.xxx.demo.Controller;

import com.xxx.demo.Common.Random;
import com.xxx.demo.Common.Response;
import com.xxx.demo.Common.SendMail;
import com.xxx.demo.Entity.User;
import com.xxx.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.xxx.demo.Common.ResultGenerator.genFailResult;
import static com.xxx.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;



    @PostMapping("/add-user")
    public Response addUser (@RequestBody User thisUser){
        User newUser = userService.addUser(thisUser);
        if (newUser == null){
            return genFailResult("添加失败，该邮箱已被注册过");
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
        String vcode = Random.getRandomNumString(6);
        try{
            SendMail sendMail = new SendMail("tql_tql","HuaWei2018","tql_tql@163.com","tql","www");
            sendMail.send("local",mail, vcode,"www");
            return genSuccessResult(vcode);
        }
        catch (Exception e){
            return genFailResult(e.getMessage());
        }
    }

    @PostMapping("/set-user-info")
    public Response setUserInfo(@RequestParam String nickName, @RequestParam Date birthday,@RequestParam int age,@RequestParam int gender,@RequestParam String hobby){

        try {
            userService.updateUser(nickName,birthday,age,gender, hobby);
            return genSuccessResult();
        }
        catch (Exception e){
            return genFailResult("更新失败");
        }
    }

    @GetMapping ("/user-login")
    public Response getPassword (@RequestParam String mail,@RequestParam String password){

        try{
            String correctPassword = userService.getPassword(mail);
            if (correctPassword.equals(password)){
                return genSuccessResult(true);
            }
            else {
                return genSuccessResult(false);
            }
        }
        catch (Exception e){
            return genFailResult("未知错误");
        }

    }

    @GetMapping("/hello")
    public Response hello(){
        return genSuccessResult ("Y");
    }
}
