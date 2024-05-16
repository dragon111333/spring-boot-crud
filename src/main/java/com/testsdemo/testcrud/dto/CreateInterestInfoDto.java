package com.testsdemo.testcrud.dto;

import com.testsdemo.testcrud.models.InterestInfo;

public class CreateInterestInfoDto extends InterestInfo{

	public Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
  
}
