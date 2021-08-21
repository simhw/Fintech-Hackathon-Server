package com.example.test.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	// 기본 생성자 
@AllArgsConstructor	// 모든 변수의 생성자 
@Builder
@Entity 
public class User {

	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// auto_increment
	private int id; 	

	@Column(nullable = false, length = 50, unique = true)
	private String email;
	
	@Column(nullable = false, length = 50) 
	private String password;

	@Column(nullable = false, length = 30)
	private String name; 

	@Column
	private int point;
}