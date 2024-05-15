package com.testsdemo.testcrud.repo;

import org.springframework.data.repository.CrudRepository;

import com.testsdemo.testcrud.models.User;

public interface UsersRepo extends CrudRepository<User, Integer> {

}
