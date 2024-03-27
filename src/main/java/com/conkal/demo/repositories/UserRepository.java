package com.conkal.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.conkal.demo.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}