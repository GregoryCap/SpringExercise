package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
	private String login;
	
	private String lastName;
	
	private String firstName;
	
}
