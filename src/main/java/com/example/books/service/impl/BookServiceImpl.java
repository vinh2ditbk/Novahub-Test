package com.example.books.service.impl;

import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public Book findById(int id) {
		return bookRepository.findOne(id);
	}
	
	@Override
	public List<Book>  findAllBook() {
		return (List<Book>) bookRepository.findAll();
	}
	
	@Override
	public void updateBook(int id, String title, String author, String description, Timestamp timestamp) {
		bookRepository.updateBook(id, title, author, description, timestamp);
	}
	
	@Override
	public void deleteBook(int id) {
		bookRepository.delete(id);
	}
}
