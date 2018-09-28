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
public class BaseController {
    @Autowired
    BotService botService;

    @GetMapping ("/check-update")
    public Response addBot(){
        Response response = new Response();
        response.setMessage("1.0");
        return response;
    }
}
