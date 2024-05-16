package com.testsdemo.testcrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testsdemo.testcrud.dto.CreateInterestInfoDto;
import com.testsdemo.testcrud.dto.ResponseDto;
import com.testsdemo.testcrud.models.InterestInfo;
import com.testsdemo.testcrud.services.InterestInfoService;  

@RestController
@RequestMapping(value = "/api/interest-info", produces = "application/json")
public class InterestInfoController {
	
	@Autowired
	private InterestInfoService interestInfoService;
	
	@GetMapping(path="/{userId}")
	public ResponseDto getByUserId(@PathVariable int userId) {
		try {
			Iterable<InterestInfo> users = interestInfoService.getAllByUserid(userId);
		
			return new ResponseDto(true,"ok",users);
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}
	}
	
	@PostMapping(path="")
	@ResponseBody
	public ResponseDto getByUserId(@RequestBody List<CreateInterestInfoDto>  e) {
		try {
			for(CreateInterestInfoDto eResult : e) {
				InterestInfo n = interestInfoService.add(eResult);
				if(n == null) throw new Error("add error");
			}
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
			int result = interestInfoService.delete(userId,id);
			if(result == 0) throw new Error("delete error");
			return new ResponseDto(true,"ok");
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}	
	}

}
