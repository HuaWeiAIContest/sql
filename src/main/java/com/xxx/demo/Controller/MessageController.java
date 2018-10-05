package com.xxx.demo.Controller;

import com.xxx.demo.Common.LUISEntity;
import com.xxx.demo.Common.LUISResponse;
import com.xxx.demo.Common.Response;
import com.xxx.demo.Entity.Message;
import com.xxx.demo.Entity.SimpleLUISResponse;
import com.xxx.demo.Service.GetAnswerService;
import com.xxx.demo.Service.MessageService;
import org.json.JSONObject;
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
    @PostMapping ("/get-answer-with-entity")
    public Response getAnswerWithEntity(@RequestBody String StringResponse){
        JSONObject JSONresponse = new JSONObject(StringResponse);
        System.out.println(JSONresponse.toString());
        LUISResponse response = new LUISResponse(JSONresponse);
        String intent = response.getTopIntent().getName();
        System.out.println(response.getTopIntent().getName());
        List<LUISEntity> entity = response.getEntities();
        String toReturnEntity;
        if (intent!=null)
            switch (intent){
                case "求夸奖":
                    return genSuccessResult("【轻笑】你很" + entity.get(0).getName() + "的。");
                default:
                    return genSuccessResult("【微微一笑，没有说话】");
            }
        else{
            return genFailResult("获取失败");
        }
    }
}
