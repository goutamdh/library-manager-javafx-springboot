/**
 * 
 */
package com.dotedlabs.librarymanager.events;

import com.dotedlabs.librarymanager.config.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sandeepknair
 * @param <T>
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshedBookEvent {
	private Book book;
}
