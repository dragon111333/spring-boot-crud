package com.testsdemo.testcrud.config;

import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.UsersRepo;
import com.testsdemo.testcrud.util.servicelocatordemo.*;
import com.testsdemo.testcrud.util.servicelocatordemo.myserviceimpl.MyServiceImplFirst;
import com.testsdemo.testcrud.util.servicelocatordemo.myserviceimpl.MyServiceImplSecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class AppConfig {

    @Autowired
    private UsersRepo usersRepo;
    private final int TARGET_ID = 1;

    @Bean
    public MyService myService(){
        return new MyServiceImplFirst();
    }

    @Bean
    public MyService myService2(){
        return new MyServiceImplSecond();
    }

    @Bean
    public ServiceLocatorFactoryBean serviceLocatorFactoryBean(){
        ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(MyServiceLocator.class);
        return bean;
    }

    @Bean
    @Scope("singleton")
    public User userTemp(){
        try{
            System.out.println("::::::::::::: GET USER TEMP TO CACHE :::::::::::::");
            return this.usersRepo.findById(TARGET_ID).get();
        }catch(Exception ex){
            ex.printStackTrace();
            return new User();
        }
   }

   @Bean
   @Scope("singleton")
   public Iterable<User> userListTemp(){
        try{
            System.out.println("::::::::::::: GET USER TEMP TO CACHE WITH LIST :::::::::::::");
            return  this.usersRepo.findAll();
        }catch(Exception ex){
            ex.printStackTrace();
            return (Iterable<User>) new ArrayList<User>();
        }
   }

}
