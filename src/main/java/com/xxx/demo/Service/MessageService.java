package com.xxx.demo.Service;

import com.xxx.demo.Entity.Message;
import com.xxx.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> getMessageRecord (int user_id,int bot_id){
        List<Message> messageList= messageRepository.getMessageRecord(user_id,bot_id);
        return messageList;
    }
    public Boolean addMessage(Message message){
        try {
            messageRepository.save(message);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
}
