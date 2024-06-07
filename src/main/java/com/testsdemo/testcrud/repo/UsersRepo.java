package com.testsdemo.testcrud.repo;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.testsdemo.testcrud.models.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends CrudRepository<User, Integer> {

        @Query(value=" SELECT dbo.get_user_exp(:userId) as user_exp_detail",nativeQuery = true)
        public String getUserExpDetail(@Param("userId") int userId);
}
