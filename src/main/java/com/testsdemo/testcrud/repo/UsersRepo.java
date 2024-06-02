package com.testsdemo.testcrud.repo;

import org.springframework.data.repository.CrudRepository;

import com.testsdemo.testcrud.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<User, Integer> {

}
