package com.testsdemo.testcrud.config;

import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class AppConfig {

    @Autowired
    private UsersRepo usersRepo;
    private final int TARGET_ID = 1;

    @Bean
    @Scope("singleton")
    public User userTemp(){
        System.out.println("::::::::::::: GET USER TEMP TO CACHE :::::::::::::");
        return this.usersRepo.findById(TARGET_ID).get();
   }

   @Bean
   @Scope("singleton")
   public Iterable<User> userListTemp(){
       System.out.println("::::::::::::: GET USER TEMP TO CACHE WITH LIST :::::::::::::");
       return  this.usersRepo.findAll();
   }

}
