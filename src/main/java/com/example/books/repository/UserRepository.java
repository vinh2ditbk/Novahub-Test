package com.example.books.repository;

import com.example.books.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
	User findByEmail(@Param("email") String email);
}
