package com.testsdemo.testcrud.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testsdemo.testcrud.models.User;

@Repository
public interface UsersRepo extends CrudRepository<User, Integer> {

}
