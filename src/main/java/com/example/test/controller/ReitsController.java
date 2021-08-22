package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.Reits;
import com.example.test.service.ReitsService;

@RestController
public class ReitsController {
	
	@Autowired
	private ReitsService reitsService;
	
	@GetMapping("/api/reits")
	public List<Reits> getReitsList(){
		
		return reitsService.getReits();
	}
}