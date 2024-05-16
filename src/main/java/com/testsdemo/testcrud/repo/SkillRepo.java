package com.testsdemo.testcrud.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testsdemo.testcrud.models.Skill;

@Repository
public interface SkillRepo extends CrudRepository<Skill,Integer> {
	
//		@Query("SELECT ei FROM educational_infomation ei WHERE ei.userId = ?1")
//		Iterable<EducationalInfomation> findByUserId(@Param("userId") int userId) ;
}
