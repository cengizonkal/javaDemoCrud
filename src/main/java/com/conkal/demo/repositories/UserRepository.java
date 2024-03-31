package com.conkal.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.conkal.demo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<User> searchUsers(@Param("search") String search);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(String email, String password);


    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(String username);

}