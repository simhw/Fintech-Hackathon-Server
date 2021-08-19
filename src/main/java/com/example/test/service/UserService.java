package com.example.test.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 회원정보출력
	//@Transactional()
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	// 회원가입 
	@Transactional()
	public void postUser(User user) {
		user.setPoint(0);
		userRepository.save(user);
	}

}
