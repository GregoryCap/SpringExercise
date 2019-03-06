package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.jdbc.DAOFactory;
import com.example.demo.model.UserDTO;

public class UserService {
	
	public List<UserDTO> getAll(){
		List<User> users = new UserDAO(DAOFactory.getInstance()).getAll();
		//Code metier
		
		//Conversion
		return users.stream().map(userEntity -> 
			new UserDTO(userEntity.getLogin(),
					userEntity.getLastName(),
					userEntity.getFirstName()))
		.collect(Collectors.toList());
	}
	
	public UserDTO getOne(String login){
		User user= new UserDAO(DAOFactory.getInstance()).getOne(login);
		//Code metier
		
		//Conversion
		return user == null ? null : new UserDTO(user.getLogin(),
				user.getLastName(),
				user.getFirstName());
	}
	
	
	
}
