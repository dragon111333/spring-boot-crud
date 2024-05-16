package com.testsdemo.testcrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testsdemo.testcrud.dto.CreateExpDto;
import com.testsdemo.testcrud.dto.ResponseDto;
import com.testsdemo.testcrud.models.EducationalInfomation;
import com.testsdemo.testcrud.models.ExpInfo;
import com.testsdemo.testcrud.services.ExpService;  

@RestController
@RequestMapping(value = "/api/exp", produces = "application/json")
public class ExpController {
	
	@Autowired
	private ExpService expService;
	
	@GetMapping(path="/{userId}")
	public ResponseDto getByUserId(@PathVariable int userId) {
		try {
			Iterable<ExpInfo> users = expService.getAllByUserid(userId);
		
			return new ResponseDto(true,"ok",users);
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}
	}
	
	@PostMapping(path="")
	@ResponseBody
	public ResponseDto getByUserId(@RequestBody CreateExpDto  e) {
		try {
			ExpInfo n = expService.add(e);
			if(n == null) throw new Error("add error");
			return new ResponseDto(true,"ok");
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}
	}
	
	@DeleteMapping(path="/{userId}/{id}")
	@ResponseBody
	public ResponseDto deleteById(@PathVariable int userId , @PathVariable int id) {
		try {
			int result = expService.delete(userId,id);
			if(result == 0) throw new Error("delete error");
			return new ResponseDto(true,"ok");
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}	
	}

}
