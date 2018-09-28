package com.xxx.demo.Controller;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.xxx.demo.Common.Response;
import com.xxx.demo.Entity.Message;
import com.xxx.demo.Service.GetAnswerService;
import com.xxx.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-message-record")
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

    @PostMapping("/add-qna")
    public Response getResponse(@RequestParam String kb,@RequestParam int userID ,@RequestBody String question,@RequestBody String answer){
        try {
            messageService.addMessage(new Message(userID,kb,question,new Date(),0));
            messageService.addMessage(new Message(userID,kb,answer,new Date(),1));
            return genSuccessResult("添加成功");
        }
        catch (Exception e) {
            return  genFailResult(e.getCause().getMessage());
        }
    }
}
