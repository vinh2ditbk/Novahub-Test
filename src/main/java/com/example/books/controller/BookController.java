package com.example.books.controller;

import com.example.books.model.Book;
import com.example.books.model.User;
import com.example.books.service.BookService;
import com.example.books.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by VINH_IT on 12/22/2017.
 */

@Controller
public class BookController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login() {
		if(getEmail() != null) {
			return "redirect:/book/list";
		}
		
		return "login";
	}
	
	@RequestMapping(value =  {"/book", "/book/list"}, method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String listBook(Model model) {
		List<Book> books = bookService.findAllBook();
		model.addAttribute("books", books);
		
		return "/book/list_book";
	}
	
	@RequestMapping(value = "/book/create", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String createBook() {
		return "/book/create_book";
	}
	
	@RequestMapping(value = "/book/edit/{strId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String editBook(Model model, @PathVariable String strId) {
		int id = -1;
		if(!checkIdIsNumber(strId)) {
			model.addAttribute("id_invalid", true);
			return "forward:/book/list";
		}
		id = Integer.parseInt(strId);
		Book book = bookService.findById(id);
		if(book == null) {
			model.addAttribute("id_invalid", true);
			return "forward:/book/list";
		}
		User currentUser = userService.findByEmail(getEmail());
		if(book.getUser().getId() != currentUser.getId()) {
			model.addAttribute("invalid_by_edit", true);
			return "forward:/book/list";
		} else {
			model.addAttribute("book", book);
			return "/book/edit_book";
		}
	}
	
	@RequestMapping(value = "/book/detail/{strId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String detailBook(Model model, @PathVariable String strId) {
		int id = -1;
		if(!checkIdIsNumber(strId)) {
			model.addAttribute("id_invalid", true);
			return "forward:/book/list";
		}
		id = Integer.parseInt(strId);
		Book book = bookService.findById(id);
		if(book == null) {
			model.addAttribute("id_invalid", true);
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		
		return "/book/detail_book";
	}
	
	@RequestMapping(value = "/book/delete/{strId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String deleteBook(Model model, @PathVariable String strId) {
		int id = -1;
		if(!checkIdIsNumber(strId)) {
			model.addAttribute("id_invalid", true);
			return "forward:/book/list";
		}
		id = Integer.parseInt(strId);
		Book book = bookService.findById(id);
		if(book == null) {
			model.addAttribute("id_invalid", true);
			return "forward:/book/list";
		}
		User currentUser = userService.findByEmail(getEmail());
		if(book.getUser().getId() != currentUser.getId()) {
			model.addAttribute("invalid_by_delete", true);
			return "forward:/book/list";
		} else {
			bookService.deleteBook(id);
			return "redirect:/book/list";
		}
	}
	
	@RequestMapping(value = "/book/save", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String saveBook(HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		Book book = mapDataOfBook(params, request, true);
		book.setCreatedAt(new Timestamp(new Date().getTime()));
		book.setUser(userService.findByEmail(getEmail()));
		bookService.saveBook(book);
		
		return "redirect:/book/list";
	}
	
	@RequestMapping(value = "/book/update", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String updateBook(Model model, HttpServletRequest request) {
		Enumeration<String> params = request.getParameterNames();
		Book book = mapDataOfBook(params, request, false);
		Book currentBook = bookService.findById(book.getId());
		
		// check user have changed information or not
		if(currentBook.getTitle().equals(book.getTitle()) && currentBook.getAuthor().equals(book.getAuthor())
		&& currentBook.getDescription().equals(book.getDescription())) {
			model.addAttribute("not_updated", true);
			model.addAttribute("book", book);
			
			return "forward:/book/edit/";
		}
		book.setUpdatedAt(new Timestamp(new Date().getTime()));
		book.setUser(userService.findByEmail(getEmail()));
		bookService.updateBook(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getUpdatedAt());
		
		return "redirect:/book/list";
	}
	
	@RequestMapping(value = "/book/edit", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String reUpdateBook(Model model, HttpServletRequest request) {
		return "book/edit_book";
	}
	
	private String getEmail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = null;
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		}
		
		return email;
	}
	
	private boolean checkIdIsNumber(String strId) {
		try {
			Integer.parseInt(strId);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private Book mapDataOfBook(Enumeration<String> params, HttpServletRequest request, boolean isCreateNewBook) {
		Book book = new Book();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if(!isCreateNewBook) {
				if(param.equals("id")) {
					book.setId(Integer.parseInt(request.getParameter(param)));
				}
			}
			if(param.equals("title")) {
				book.setTitle(request.getParameter(param));
			}
			if(param.equals("author")) {
				book.setAuthor(request.getParameter(param));
			}
			if(param.equals("description")) {
				book.setDescription(request.getParameter(param));
			}
		}
		return book;
	}
}
