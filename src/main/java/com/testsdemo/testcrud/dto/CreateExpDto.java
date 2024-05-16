package com.testsdemo.testcrud.dto;

import com.testsdemo.testcrud.models.ExpInfo;

public class CreateExpDto extends ExpInfo{

	public Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
  
}
