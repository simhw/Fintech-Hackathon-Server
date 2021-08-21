package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.User;
import com.example.test.service.UserService;
// 자동으로 @ResponseBody가 붙는다. 
@RestController
public class UserApiController {


	@Autowired
	private UserService userService;


	@GetMapping("/api/test/{id}")
	public User getUserController(@PathVariable int id, Model model){

		return userService.getUser(id);
	}


	@PostMapping("/api/test/")
	public String postUserController(User user) {

		userService.postUser(user);
		return "post 요청";

	}

}
