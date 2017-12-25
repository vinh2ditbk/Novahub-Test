package com.example.books.service;

import com.example.books.model.Book;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by VINH_IT on 12/22/2017.
 */
public interface BookService {
	Book saveBook(Book book);
	Book findById(int id);
	List<Book> findAllBook();
	void updateBook(int id, String title, String author, String description, Timestamp timestamp);
	void deleteBook(int id);
}
