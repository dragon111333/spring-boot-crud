package com.testsdemo.testcrud.dto;

import com.testsdemo.testcrud.models.EducationalInfomation;
import com.testsdemo.testcrud.models.User;

public class CreateEIDto extends EducationalInfomation {

	public Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
