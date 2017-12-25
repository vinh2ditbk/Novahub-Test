package com.example.books.listenner;

import com.example.books.model.Role;
import com.example.books.model.User;
import com.example.books.service.RoleService;
import com.example.books.service.UserService;
import com.example.books.utils.EnumApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//add roles
		addMasterRole();
		
		//add temp accounts
		addTempAccount();
	}
	
	private void addMasterRole() {
		//add 2 roles: ROLE_ADMIN & ROLE_USER
		if (roleService.findByName(EnumApp.EnumRole.ROLE_ADMIN.getName()) == null) {
			roleService.saveRole(new Role(EnumApp.EnumRole.ROLE_ADMIN.getName()));
		}
		
		if (roleService.findByName(EnumApp.EnumRole.ROLE_USER.getName()) == null) {
			roleService.saveRole(new Role(EnumApp.EnumRole.ROLE_USER.getName()));
		}
	}
	
	private void addTempAccount() {
		//add 2 account: admin & normal user
		// admin account has 2 roles: admin role & user role
		if (userService.findByEmail("admin@gmail.com") == null) {
			User admin = new User();
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("admin"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleService.findByName(EnumApp.EnumRole.ROLE_ADMIN.getName()));
			roles.add(roleService.findByName(EnumApp.EnumRole.ROLE_USER.getName()));
			admin.setRoles(roles);
			userService.saveUser(admin);
		}
		// normal account has 1 role only: user role
		if (userService.findByEmail("user@gmail.com") == null) {
			User normal = new User();
			normal.setEmail("user@gmail.com");
			normal.setPassword(passwordEncoder.encode("user"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleService.findByName(EnumApp.EnumRole.ROLE_USER.getName()));
			normal.setRoles(roles);
			userService.saveUser(normal);
		}
	}
	
}
