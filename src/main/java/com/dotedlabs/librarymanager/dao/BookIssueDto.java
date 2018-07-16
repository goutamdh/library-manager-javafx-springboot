package com.dotedlabs.librarymanager.dao;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookIssueDto {
	private UUID bookId;
	private UUID memberId;
}
