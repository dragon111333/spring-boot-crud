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
public class ExpInfo {
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer id;
	  	  
	  @Column(name="start_at")
	  private LocalDateTime startAt;
	  
	  @Column(name="end_at")
	  private LocalDateTime endAt;
	  
	  private String name;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  private User user;
	  
	public ExpInfo() {
		
	}
	


	public ExpInfo(LocalDateTime startAt, LocalDateTime endAt, String name, User user) {
		super();
		this.startAt = startAt;
		this.endAt = endAt;
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



	public LocalDateTime getStartAt() {
		return startAt;
	}



	public void setStartAt(LocalDateTime startAt) {
		this.startAt = startAt;
	}



	public LocalDateTime getEndAt() {
		return endAt;
	}



	public void setEndAt(LocalDateTime endAt) {
		this.endAt = endAt;
	}
	
	
	 
}
