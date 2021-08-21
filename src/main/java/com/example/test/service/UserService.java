package com.example.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	

	@Transactional()
	public User getUser(int id) {
		return userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("아이디 오류 ");
		});
	}

	
	// 회원가입 
	@Transactional()
	public void postUser(User user) {
		user.setPoint(0);
		userRepository.save(user);
	}
}
