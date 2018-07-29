package com.dotedlabs.librarymanager.config.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_issues")
public class BookIssue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	protected UUID id;
	
	@OneToOne
    @JoinColumn(name = "book_id")
	private Book book;
	
	@OneToOne
    @JoinColumn(name = "member_id")
	private Member member;
}
