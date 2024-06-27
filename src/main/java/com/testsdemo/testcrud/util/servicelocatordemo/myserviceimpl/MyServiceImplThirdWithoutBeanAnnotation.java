package com.testsdemo.testcrud.util.servicelocatordemo.myserviceimpl;

import com.testsdemo.testcrud.util.servicelocatordemo.MyService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("MyServiceImplThirdWithoutBeanAnnotation")
public class MyServiceImplThirdWithoutBeanAnnotation implements MyService {
    public HashMap<String, String> doit(){
        System.out.println("DO IT AT > "+this.getClass().getName());
        HashMap<String, String> result = new HashMap<>();
        result.put("serviceClass",this.getClass().getName());
        result.put("message" , "this is service 3");
        return result;
    }
}
