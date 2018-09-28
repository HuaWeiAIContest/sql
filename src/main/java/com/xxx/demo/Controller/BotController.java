package com.xxx.demo.Controller;

import com.xxx.demo.Common.Response;
import com.xxx.demo.Entity.Bot;
import com.xxx.demo.Service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.xxx.demo.Common.ResultGenerator.genFailResult;
import static com.xxx.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
public class BotController {
    @Autowired
    BotService botService;

    @PostMapping ("/add-bot")
    public Response addBot(@RequestBody Bot bot){
        Bot result = botService.addBot(bot);
        if (result != null){
            return genSuccessResult(result);
        }
        else {
            return genFailResult("添加失败");
        }
    }

    @GetMapping ("/get-introduction")
    public Response getIntroduction (@RequestParam int botID){
        String result = botService.getIntroduction(botID);
        if (result != null){
            return genSuccessResult(result);
        }
        else {
            return genFailResult("获取失败");
        }
    }
}
