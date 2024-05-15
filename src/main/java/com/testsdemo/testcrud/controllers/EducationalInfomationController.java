package com.testsdemo.testcrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testsdemo.testcrud.dto.CreateEIDto;
import com.testsdemo.testcrud.dto.ResponseDto;
import com.testsdemo.testcrud.models.EducationalInfomation;
import com.testsdemo.testcrud.services.EducationalInfomationService;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;  

@RestController
@RequestMapping(value = "/api/educations", produces = "application/json")
public class EducationalInfomationController {
	
	@Autowired
	private EducationalInfomationService eiService;
	
	@GetMapping(path="/{userId}")
	public ResponseDto getByUserId(@PathVariable int userId) {
		try {
			Iterable<EducationalInfomation> users = eiService.getAllByUserid(userId);
		
			return new ResponseDto(true,"ok",users);
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}
	}
	
	@PostMapping(path="/")
	@ResponseBody
	public ResponseDto getByUserId(@RequestBody CreateEIDto ei) {
		try {
			EducationalInfomation n = eiService.add(ei);
			return new ResponseDto(true,"ok",n);
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}
	}

}
