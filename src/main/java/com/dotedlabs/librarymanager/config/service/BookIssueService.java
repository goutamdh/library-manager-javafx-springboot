/**
 * 
 */
package com.dotedlabs.librarymanager.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotedlabs.librarymanager.config.entity.Book;
import com.dotedlabs.librarymanager.config.entity.BookIssue;
import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.repository.BookIssueRepository;
import com.dotedlabs.librarymanager.config.repository.BookRepository;
import com.dotedlabs.librarymanager.config.repository.MemberRepository;
import com.dotedlabs.librarymanager.dao.BookIssueDto;
import com.dotedlabs.librarymanager.exception.LibraryException;

/**
 * @author sandeepknair
 *
 */
@Service
public class BookIssueService {

	@Autowired
	BookIssueRepository bookIssueRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	MemberRepository memberRepository;

	/**
	 * Issue a new book
	 * 
	 * @param bookIssueDto
	 * @return
	 */
	public BookIssue issueBook(BookIssueDto bookIssueDto) {
		Book book = bookRepository.findOne(bookIssueDto.getBookId());
		Member member = memberRepository.findOne(bookIssueDto.getMemberId());

		if (null == book || member == null) {
			throw new LibraryException("Invalid request");
		}

		// @formatter:off
		BookIssue bookIssue = BookIssue.builder()
				.book(book)
				.member(member)
				.build();
		// @formatter:on
		bookIssue = bookIssueRepository.save(bookIssue);
		return bookIssue;
	}
}
