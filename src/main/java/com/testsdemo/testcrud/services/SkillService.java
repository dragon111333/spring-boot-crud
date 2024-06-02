package com.testsdemo.testcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testsdemo.testcrud.dto.CreateExpDto;
import com.testsdemo.testcrud.dto.CreateSkillDto;
import com.testsdemo.testcrud.models.ExpInfo;
import com.testsdemo.testcrud.models.Skill;
import com.testsdemo.testcrud.models.User;
import com.testsdemo.testcrud.repo.SkillRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class SkillService {
	@Autowired
	private SkillRepo skillRepo;
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private EntityManager entityManager;

	public Iterable<Skill> getAll(){
		try{
			return this.skillRepo.findAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Iterable<Skill> getAllByUserid(int userId) {
		String sql ="SELECT e.*FROM skill e WHERE e.user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("userId",userId );
		
		return query.getResultList();
		//return this.eiRepo.findByUserId(userId);
	}
	
	public Skill add(CreateSkillDto e) {		
		try {
			User user = this.usersService.getById(e.getUserId());
			if(user == null) throw new Error("not found user");
			Skill n = new Skill(e.getName(),e.getPoint(),user);
			this.skillRepo.save(n);
			return n;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Transactional
	public int delete(int userId,int id) {
		String sql ="delete from skill where id = :id and user_id = :userId";
		System.out.println("SQL : %s".formatted(sql));
		
		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter("id",id);
		query.setParameter("userId",userId);
		
		return query.executeUpdate();
	}
}
