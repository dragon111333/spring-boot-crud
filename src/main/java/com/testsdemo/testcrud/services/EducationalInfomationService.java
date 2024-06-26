package com.testsdemo.testcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsdemo.testcrud.dto.CreateEIDto;
import com.testsdemo.testcrud.models.EducationalInfomation;
import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.EducationalInfomationRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class EducationalInfomationService {
	@Autowired
	private EducationalInfomationRepo eiRepo;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private EntityManager entityManager;
	
	public Iterable<EducationalInfomation> getAllByUserid(int userId) {
		String sql ="SELECT * FROM educational_infomation ei WHERE ei.user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("userId",userId );
		
		return query.getResultList();
		//return this.eiRepo.findByUserId(userId);
	}
	
	public EducationalInfomation add(CreateEIDto e) {		
		try {
			User user = this.usersService.getById(e.getUserId());
			if(user == null) throw new Error("not found user");
			EducationalInfomation n = new EducationalInfomation(e.getYear(),e.getUniversityName(),user);
			this.eiRepo.save(n);
			return n;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Transactional
	public int delete(int userId,int id) {
		String sql ="delete from educational_infomation where id = :id and user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("id",id);
		query.setParameter("userId",userId);
		
		return query.executeUpdate();
	}
}
