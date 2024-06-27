package com.testsdemo.testcrud.util.servicelocatordemo.myserviceimpl;

import com.testsdemo.testcrud.util.servicelocatordemo.MyService;

import java.util.HashMap;

public class MyServiceImplFirst implements MyService {
    public HashMap<String, String> doit(){
        System.out.println("DO IT AT > "+this.getClass().getName());
        HashMap<String, String> result = new HashMap<>();

        result.put("service",this.getClass().getName());
        result.put("message" , "this is service 1");

        return result;
    }
}
