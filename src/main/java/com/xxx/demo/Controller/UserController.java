package com.xxx.demo.Controller;

import com.xxx.demo.Common.Random;
import com.xxx.demo.Common.Response;
import com.xxx.demo.Common.SendMail;
import com.xxx.demo.Entity.User;
import com.xxx.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

import static com.xxx.demo.Common.ResultGenerator.genFailResult;
import static com.xxx.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Value("${version}")
    private String version;

    @PostMapping("/add-user")
    public Response addUser (@RequestBody User thisUser){
        thisUser.setFavourite("");
        User newUser = userService.addUser(thisUser);

        if (newUser == null){
            return genFailResult("添加失败，该邮箱已被注册过");
        }
        else {
            return genSuccessResult(newUser);
        }
    }

    @GetMapping("/get-user-by-mail")
    public Response getUserByID (@RequestParam String mail){
        User thisUser = userService.searchUser(mail);
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
    public Response setUserInfo(@RequestBody User user){

        try {
            userService.updateUser(user.getMail(),user.getNickName(),user.getBirthday(),user.getAge(),user.getGender(), user.getHobby());
            return genSuccessResult();
        }
        catch (Exception e){
            return genFailResult("更新失败");
        }
    }

    @PostMapping ("/user-login")
    public Response getPassword (@RequestBody User user){

        try{
            String correctPassword = userService.getPassword(user.getMail());
            if (correctPassword.equals(user.getPassword())){
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

    @PostMapping("/change-password")
    public Response changePassword(@RequestBody User user){
        userService.changePassword(user.getMail(),user.getPassword());
        String vcode = Random.getRandomNumString(6);
        try{
            SendMail sendMail = new SendMail("tql_tql","HuaWei2018","tql_tql@163.com","tql","www");
            sendMail.send("local",user.getMail(), vcode,"www");
            return genSuccessResult(vcode);
        }
        catch (Exception e){
            return genFailResult("发送验证邮件失败，请重试");
        }
    }


    @PostMapping ("/change-favourite")//只用到了userid和favourite
    public Response changeFavourite(@RequestBody User user){
        String current = userService.getFavourite(user.getMail());
        String[] splitCurrent = current.split(",");
        String newFavourite=current;
        boolean result=true;
        ArrayList<String> splitCurrentList = new ArrayList<>();
        for (int i=0;i<splitCurrent.length;i++){
            splitCurrentList.add(splitCurrent[i]);
        }
        if (!splitCurrentList.contains(user.getFavourite())){
            newFavourite += "," + user.getFavourite();
            result = userService.changeFavourite(user.getMail(), newFavourite);
        }
        if (!result){
            return genFailResult("添加收藏失败，请稍后再试");
        }
        else {
            return genSuccessResult(newFavourite);
        }
    }

    @GetMapping ("/get-favourite")
    public Response getFavourite (@RequestParam String mail){
        String favourite = userService.getFavourite(mail);
        if (favourite == null){
            return genFailResult("获取失败");
        }
        else{
            return genSuccessResult(favourite);
        }
    }

    @PostMapping ("/delete-favourite")
    public Response deleteFavourite(@RequestBody User toDelete){
        String current = userService.getFavourite(toDelete.getMail());
        String[] splitCurrent = current.split(",");
        String afterFavourite="";
        ArrayList<String> splitCurrentList = new ArrayList<>();
        for (int i=0;i<splitCurrent.length;i++){
            splitCurrentList.add(splitCurrent[i]);
        }
        splitCurrentList.remove(toDelete.getFavourite());
        for (int i=0;i<splitCurrentList.size();i++){
            afterFavourite+=splitCurrentList.get(i)+",";
        }
        boolean result = userService.changeFavourite(toDelete.getMail(),afterFavourite);
        if (result){
            return genSuccessResult(afterFavourite);
        }
        else {
            return genFailResult("取消收藏失败");
        }
    }


    @GetMapping("/get-version")
    public Response getVersion(){
        return genSuccessResult (version);
    }
}
