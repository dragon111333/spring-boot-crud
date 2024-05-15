package com.testsdemo.testcrud.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testsdemo.testcrud.models.EducationalInfomation;

@Repository
public interface EducationalInfomationRepo extends CrudRepository<EducationalInfomation,Integer> {
	
//		@Query("SELECT ei FROM educational_infomation ei WHERE ei.userId = ?1")
//		Iterable<EducationalInfomation> findByUserId(@Param("userId") int userId) ;
}
