package com.dotedlabs.librarymanager.config.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotedlabs.librarymanager.config.entity.Book;
import com.dotedlabs.librarymanager.config.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	/**
	 * Add new book
	 * 
	 * @param book
	 * @return
	 */
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	/**
	 * Find all books
	 * 
	 * @return
	 */
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	/**
	 * Find details of a book
	 * 
	 * @param bookId
	 * @return
	 */
	public Book findById(UUID bookId) {
		return bookRepository.findOne(bookId);
	}
}
