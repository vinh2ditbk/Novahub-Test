package com.example.books.repository;

import com.example.books.model.Book;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	@Modifying
	@Transactional
	@Query(value = "UPDATE books SET title = :title, author = :author, description = :description, " +
			"updated_at = :timestamp WHERE id = :id", nativeQuery = true)
	void updateBook(@Param("id") int id, @Param("title") String title, @Param("author") String author,
			@Param("description") String description, @Param("timestamp") Timestamp timestamp);
}
