package com.tusharpatil.spring.service;

import java.util.List;

import com.tusharpatil.spring.model.Book;

public interface BookService {
	
	//save the record
		long save(Book book);
		
		//get the book objetc
		Book get(long id);
		
		//get all records
		List<Book> list();
		
		//update the record
		void update(long id,Book book);
		
		//delete record
		void delete(long id);
}
