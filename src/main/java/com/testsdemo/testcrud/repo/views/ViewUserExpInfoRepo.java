package com.testsdemo.testcrud.repo.views;

import com.testsdemo.testcrud.models.views.ViewUserExpInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewUserExpInfoRepo extends CrudRepository<ViewUserExpInfo,Integer> {

    @Query(value="SELECT u FROM ViewUserExpInfo  u WHERE u.userId = :userId")
    List<ViewUserExpInfo> findByUserId(@Param("userId") Integer userId);

    @Query(value="SELECT u.* FROM vw_user_exp as u WHERE u.user_id = :userId",nativeQuery = true)
    List<ViewUserExpInfo> findByUserIdNative(@Param("userId") Integer userId);

}
