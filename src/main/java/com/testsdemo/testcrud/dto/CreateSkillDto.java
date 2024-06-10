package com.testsdemo.testcrud.dto;

import com.testsdemo.testcrud.models.Skill;

public class CreateSkillDto extends Skill{

	public Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
