package com.testsdemo.testcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsdemo.testcrud.dto.CreateInterestInfoDto;
import com.testsdemo.testcrud.models.InterestInfo;
import com.testsdemo.testcrud.models.InterestInfo;
import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.InterestInfoRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class InterestInfoService {
	@Autowired
	private InterestInfoRepo interestInfoRepo;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private EntityManager entityManager;
	
	public Iterable<InterestInfo> getAllByUserid(int userId) {
		String sql ="SELECT e.*FROM interest_info e WHERE e.user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("userId",userId );
		
		return query.getResultList();
		//return this.eiRepo.findByUserId(userId);
	}
	
	public InterestInfo add(CreateInterestInfoDto e) {		
		try {
			User user = this.usersService.getById(e.getUserId());
			if(user == null) throw new Error("not found user");
			InterestInfo n = new InterestInfo(e.getName(),user);
			this.interestInfoRepo.save(n);
			return n;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Transactional
	public int delete(int userId,int id) {
		String sql ="delete from interest_info where id = :id and user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("id",id);
		query.setParameter("userId",userId);
		
		return query.executeUpdate();
	}
}
