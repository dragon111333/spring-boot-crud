package com.testsdemo.testcrud.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GuildInfo {
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer id;
	  	  
	  
	  private String name;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  private User user;
	  
	public GuildInfo() {
		
	}
	
	
	public GuildInfo(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	 
}
