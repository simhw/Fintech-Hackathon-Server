package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.User;
import com.example.test.service.UserService;
// 자동으로 @ResponseBody가 붙는다. 
@RestController
public class UserApiController {
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/test/")
	public List<User> getUserController(){
		
		
		// 자바 객체를 반환하게 되면 MessageConverter가 자동 작동힌다. 
		// Jackson 라이브러리는 호출해서 user object를 JSON 형태로 변환해 브라우저에 반환한다. 
		return userService.getUsers();
		
	}
	
	
	@PostMapping("/api/test/")
	public String postUserController(User user) {
		
		userService.postUser(user);
		return "post 요청";
		
	}

}
