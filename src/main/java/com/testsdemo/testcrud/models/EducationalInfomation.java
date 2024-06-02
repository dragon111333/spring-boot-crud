package com.testsdemo.testcrud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EducationalInfomation {
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer id;
	  
	  private String year;
	  
	  @Column(name="university_name")
	  private String universityName;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "user_id")
	  @JsonBackReference
	  private User user;
	  
	public EducationalInfomation() {
		
	}
	  	  	 	  
	public EducationalInfomation(String year, String universityName,User user) {
		super();
		this.year = year;
		this.universityName = universityName;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	 
}
