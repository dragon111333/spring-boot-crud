package com.testsdemo.testcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsdemo.testcrud.dto.CreateEIDto;
import com.testsdemo.testcrud.models.EducationalInfomation;
import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.EducationalInfomationRepo;

import jakarta.persistence.EntityManager;

@Service
public class EducationalInfomationService {
	@Autowired
	private EducationalInfomationRepo eiRepo;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private EntityManager entityManager;
	
	public Iterable<EducationalInfomation> getAllByUserid(int userId) {
		String sql ="SELECT ei.id,ei.user_id,ei.university_name,ei.year FROM educational_infomation ei WHERE ei.user_id = %d".formatted(userId);
		System.out.println("SQL : %s".formatted(sql));
		return this.entityManager.createNativeQuery(sql).getResultList();
		//return this.eiRepo.findByUserId(userId);
	}
	
	public EducationalInfomation add(CreateEIDto e) {		
		User user = this.usersService.getById(e.getUserId());
		EducationalInfomation n = new EducationalInfomation(e.getId(),e.getYear(),e.getUniversityName(),user);
		this.eiRepo.save(n);
		return n;
	}
}
