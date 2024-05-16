package com.testsdemo.testcrud.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsdemo.testcrud.dto.*;
import com.testsdemo.testcrud.models.EducationalInfomation;
import com.testsdemo.testcrud.models.ExpInfo;
import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.ExpRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class ExpService {
	@Autowired
	private ExpRepo expRepo;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private EntityManager entityManager;
	
	public Iterable<ExpInfo> getAllByUserid(int userId) {
		String sql ="SELECT e.*FROM exp_info e WHERE e.user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("userId",userId );
		
		return query.getResultList();
		//return this.eiRepo.findByUserId(userId);
	}
	
	public ExpInfo add(CreateExpDto e) {		
		try {
			User user = this.usersService.getById(e.getUserId());
			if(user == null) throw new Error("not found user");
			ExpInfo n = new ExpInfo(e.getStartAt(),e.getEndAt(),e.getName(),user);
			this.expRepo.save(n);
			return n;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Transactional
	public int delete(int userId,int id) {
		String sql ="delete from exp_info where id = :id and user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("id",id);
		query.setParameter("userId",userId);
		
		return query.executeUpdate();
	}
}
