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

import com.testsdemo.testcrud.dto.CreateGuildInfoDto;
import com.testsdemo.testcrud.dto.ResponseDto;
import com.testsdemo.testcrud.models.GuildInfo;
import com.testsdemo.testcrud.services.GuildInfoService;  

@RestController
@RequestMapping(value = "/api/guild", produces = "application/json")
public class GuildInfoController {
	
	@Autowired
	private GuildInfoService guildInfoService;
	
	@GetMapping(path="/{userId}")
	public ResponseDto getByUserId(@PathVariable int userId) {
		try {
			Iterable<GuildInfo> users = guildInfoService.getAllByUserid(userId);
		
			return new ResponseDto(true,"ok",users);
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}
	}
	
	@PostMapping(path="")
	@ResponseBody
	public ResponseDto getByUserId(@RequestBody List<CreateGuildInfoDto>  e) {
		try {
			for(CreateGuildInfoDto eResult : e) {
				GuildInfo n = guildInfoService.add(eResult);
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
			int result = guildInfoService.delete(userId,id);
			if(result == 0) throw new Error("delete error");
			return new ResponseDto(true,"ok");
		}catch(Exception ex) {
			System.out.println("ERROR->");
			ex.printStackTrace();
		    return new ResponseDto(false,"error"); 
		}	
	}

}
