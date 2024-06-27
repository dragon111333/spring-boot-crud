package com.testsdemo.testcrud.util.servicelocatordemo.myserviceimpl;

import com.testsdemo.testcrud.util.servicelocatordemo.MyService;

import java.util.HashMap;

public class MyServiceImplSecond implements MyService {
    public HashMap<String, String> doit(){
        System.out.println("DO IT AT > "+this.getClass().getName());
        HashMap<String, String> result = new HashMap<>();
        result.put("serviceClass",this.getClass().getName());
        result.put("message" , "this is service 2");
        return result;
    }
}
