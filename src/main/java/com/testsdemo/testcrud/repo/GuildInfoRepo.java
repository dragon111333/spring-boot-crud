package com.testsdemo.testcrud.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testsdemo.testcrud.models.GuildInfo;

@Repository
public interface GuildInfoRepo extends CrudRepository<GuildInfo,Integer> {
	
//		@Query("SELECT ei FROM educational_infomation ei WHERE ei.userId = ?1")
//		Iterable<EducationalInfomation> findByUserId(@Param("userId") int userId) ;
}
