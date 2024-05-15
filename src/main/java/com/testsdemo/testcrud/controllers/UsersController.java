package com.testsdemo.testcrud.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testsdemo.testcrud.dto.ResponseDto;
import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.UsersRepo;
import com.testsdemo.testcrud.services.UsersService;

@RestController()
@RequestMapping(value = "/api", produces = "application/json")
public class UsersController {
	
    @Autowired
    private UsersService usersService;
    
	@GetMapping("/users")
	@ResponseBody
	public ResponseDto getAllUsers() {
		try {
			System.out.println("get user-->");
			Iterable<User> users = usersService.getAll();
			return new ResponseDto(true,"ok",users);
		}catch(Exception ex) {
		    return new ResponseDto(false,"error");

		}
	}
	@PostMapping(path="/users") 
	@ResponseBody 
	public ResponseDto addNewUser (@RequestBody User user) {
		try {
			System.out.print("New User : %s %s".formatted(user.getName(),user.getLastname()));
			
		    User n = usersService.add(user);
		    
		    return new ResponseDto(true,"ok",n);
		}catch(Exception ex) {
			ex.printStackTrace();
		    return new ResponseDto(false,"error");
		}
	}
	
	@PatchMapping(path="/users/{userId}")
	@ResponseBody
	public ResponseDto updateUser(@PathVariable int userId,@RequestBody User user) {
		try {
			System.out.print("Edit User [%d] : %s %s".formatted(userId,user.getName(),user.getLastname()));
			
			User n = usersService.update(userId,user);
		    
		    return new ResponseDto(true,"ok",n);
		}catch(Exception ex) {
			ex.printStackTrace();
		    return new ResponseDto(false,"error");
		}
	}

    
    @GetMapping("/users/test-dto")
    @ResponseBody
    public ResponseDto testResponseDto(){
        try{
            ArrayList<HashMap<String,Object>> users = new ArrayList<HashMap<String,Object>>();
            int max = 10;

            for(int index = 0 ; index < max ;index++){
                HashMap<String,Object> user = new HashMap<String,Object>();
                user.put("id",index);
                user.put("name","test"+index);
                users.add(user);
            }
      
            ResponseDto responseDto = new ResponseDto();

            responseDto.status = true;
            responseDto.message = "ok";
            responseDto.data = users;
            return responseDto  ;
        }catch(Exception ex){
            return null;
        }
    }
  
}
