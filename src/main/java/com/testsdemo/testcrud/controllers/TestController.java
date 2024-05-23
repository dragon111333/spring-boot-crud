package com.testsdemo.testcrud.controllers;

import com.testsdemo.testcrud.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping("/kub")
    public ResponseDto index(){
        try{
            HashMap<String, String> data = new HashMap<String,String>();
            data.put("name","test");
            return new ResponseDto(true,"ok",data);
        }catch(Exception ex){
            System.out.println("ERROR");
            ex.printStackTrace();
            return null;

        }
    }
}
