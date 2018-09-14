package com.xxx.demo.Controller;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.xxx.demo.Common.Response;
import com.xxx.demo.Entity.Message;
import com.xxx.demo.Service.GetAnswerService;
import com.xxx.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.xxx.demo.Common.ResultGenerator.genFailResult;
import static com.xxx.demo.Common.ResultGenerator.genSuccessResult;

@RestController
@CrossOrigin
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    GetAnswerService getAnswerService;
    @GetMapping("/getMessageRecord")
    public Response getMessageRecord(@RequestParam int userID,@RequestParam int botID)
    {
        List<Message> messageList = messageService.getMessageRecord(userID,botID);
        if (messageList == null){
            return genFailResult("无记录或查询失败");
        }
        else {
            return genSuccessResult(messageList);
        }
    }

    @GetMapping("/getResponse")
    public Response getResponse(@RequestParam String kb,@RequestParam int userID ,@RequestParam String question){
        try {
            String response = "";
            response = getAnswerService.GetAnswers(kb,question);
            //messageService.addMessage(new Message(userID,kb,question,new Date(),0));
            //messageService.addMessage(new Message(userID,kb,response,new Date(),1));
            System.out.print("www");
            if (response == null)
                return genFailResult("没有获得结果");
            else{
                return genSuccessResult(response);
            }

        }
        catch (Exception e) {
            return  genFailResult(e.getCause().getMessage());
        }
    }
}
