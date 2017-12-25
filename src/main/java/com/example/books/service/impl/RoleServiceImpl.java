package com.example.books.service.impl;

import com.example.books.model.Role;
import com.example.books.repository.RoleRepostitory;
import com.example.books.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepostitory roleRepostitory;
	
	@Override
	public Role saveRole(Role role) {
		return roleRepostitory.save(role);
	}
	
	@Override
	public Role findByName(String name) {
		return roleRepostitory.findByName(name);
	}
}
