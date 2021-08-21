package com.example.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.example.test.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
//	JpaRepository에 기본적인 CRUD 기능이 정의되어있다. 

	Optional<User> findByName(String name);
	
}
