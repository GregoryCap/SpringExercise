package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;

@RequestMapping
@RestController
public class TestController {
	@Value("${test}")
	private String testProp;
	
	@GetMapping("users")
	public List<UserDTO> getAll() {
		return new UserService().getAll();
	}
	
	@GetMapping("users/{login}")
	public UserDTO getOne(@PathVariable("login")String login) {
		UserDTO userew =  new UserService().getOne(login);
		userew.setLastName(testProp);
		return userew;
	}
	
}
