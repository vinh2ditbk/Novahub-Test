package com.example.books.service.impl;

import com.example.books.model.User;
import com.example.books.repository.UserRepository;
import com.example.books.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user){
		return userRepository.save(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
