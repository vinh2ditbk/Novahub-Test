package com.example.books.repository;

import com.example.books.model.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Repository
public interface RoleRepostitory extends CrudRepository<Role, Integer> {
	Role save(Role role);
	Role findByName(String name);
}
