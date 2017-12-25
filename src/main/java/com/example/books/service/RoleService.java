package com.example.books.service;

import com.example.books.model.Role;

/**
 * Created by VINH_IT on 12/22/2017.
 */
public interface RoleService {
	Role saveRole(Role role);
	Role findByName(String name);
}
