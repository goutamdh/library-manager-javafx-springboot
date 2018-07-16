package com.dotedlabs.librarymanager.config.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dotedlabs.librarymanager.config.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID>{
	
}
