package com.xxx.demo.Service;

import com.xxx.demo.Entity.Bot;
import com.xxx.demo.Repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("botService")
public class BotService {
    @Autowired
    BotRepository botRepository;

    public Bot addBot(Bot bot){
        try {
            return botRepository.save(bot);
        }
        catch (Exception e){
            return null;
        }
    }

    public String getBot(int botID){
        try{
            return botRepository.getBot(botID);
        }
        catch (Exception e){
            return null;
        }
    }

    public String getIntroduction(String name){
        try{
            return botRepository.getIntroduction(name);
        }
        catch (Exception e){
            return null;
        }
    }
}
