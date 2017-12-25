package com.example.books.service;

import com.example.books.model.User;

/**
 * Created by VINH_IT on 12/22/2017.
 */
public interface UserService {
	User saveUser(User user);
	User findByEmail(String email);
}
