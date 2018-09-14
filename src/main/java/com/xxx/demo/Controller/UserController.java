package com.xxx.demo.Controller;

import com.xxx.demo.Common.Hobby;
import com.xxx.demo.Common.Response;
import com.xxx.demo.Entity.Users;
import com.xxx.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static com.xxx.demo.Common.ResultGenerator.genFailResult;
import static com.xxx.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public Response addUser (@RequestParam String mail, @RequestParam String password, @RequestParam String nickName, @RequestParam int age, @RequestParam int gender, @RequestParam String birthday, @RequestParam String hobby1,@RequestParam String hobby2,@RequestParam String hobby3){
       Hobby h1,h2,h3;
       ZoneId zoneId = ZoneId.systemDefault();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d", Locale.ENGLISH);
       LocalDate date ;
       Date dateLast;
       try {
           date =LocalDate.parse(birthday, formatter);
           ZonedDateTime zdt = date.atStartOfDay(zoneId);
           dateLast =Date.from(zdt.toInstant());
       }
       catch (Exception e){
           return genFailResult("日期格式有误，请按照 yyyy.MM.d格式");
       }
       try{
           h1 = Hobby.valueOf(hobby1);
           h2 = Hobby.valueOf(hobby2);
           h3 = Hobby.valueOf(hobby3);
       }
       catch (Exception e){
           return genFailResult("Hobby类型不匹配");
       }
       Users newUser = userService.addUser(mail,password, nickName,age, gender,dateLast,h1,h2, h3);
       if (newUser == null){
           return genFailResult("添加失败,可能是参数类型不匹配");
       }
       else {
           return genSuccessResult(newUser);
       }
    }

    @GetMapping("/getUserByID")
    public Response getUserByID (@RequestParam int userID){
        Users thisUser = userService.searchUser(userID);
        if (thisUser == null){
            return genFailResult("获取失败");
        }
        else {
            return genSuccessResult(thisUser);
        }
    }

    @GetMapping("/hello")
    public Response hello(){
        return genSuccessResult ("Y");
    }
}
