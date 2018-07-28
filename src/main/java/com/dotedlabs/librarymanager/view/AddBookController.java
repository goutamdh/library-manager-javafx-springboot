/**
 * Sample Skeleton for 'AddBook.fxml' Controller Class
 */

package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.StageManager;
import com.dotedlabs.librarymanager.config.entity.Book;
import com.dotedlabs.librarymanager.config.service.BookService;
import com.dotedlabs.librarymanager.events.EditBookEvent;
import com.dotedlabs.librarymanager.events.RefreshedBookEvent;
import com.dotedlabs.librarymanager.utils.NotificationUtility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

@Component
public class AddBookController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@Lazy
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@FXML
    private AnchorPane rootPane;

	@Lazy
	@Autowired
	private BookService bookService;

	@FXML
	private TextField title;

	@FXML
	private TextField author;

	@FXML
	private TextField publisher;
	
	private Boolean isCreateMode = Boolean.TRUE;
	
	private Book selectedBookToEdit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initForm();
	}

	private void initForm() {
		title.clear();
		author.clear();
		publisher.clear();
	}

	@FXML
	void addBook(ActionEvent event) {
		if (title.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()) {
			NotificationUtility.error("All fields are mandatory", "Invalid entry");
		} else {
			// @formatter:off
			Book book = Book.builder()
					.title(title.getText())
					.author(author.getText())
					.publisher(publisher.getText())
					.build();
			// @formatter:on
			
			if (!isCreateMode) {
				book.setId(selectedBookToEdit.getId());
			}
			
			book = bookService.addBook(book);
			
			if (isCreateMode) {
				NotificationUtility.info("Saved book " + book.getTitle(), "Success");
				// Clear all data
				initForm();
			} else {
				NotificationUtility.info("Updated book " + book.getTitle(), "Success");
				eventPublisher.publishEvent(new RefreshedBookEvent(book));
				cancel(event);
			}
		}
	}

	@FXML
	void cancel(ActionEvent event) {	
		stageManager.close(rootPane.getScene());
	}
	
	/**
	 * Handle edit operations
	 * 
	 * @param editBookEvent
	 */
	@EventListener
	public void handleEdit(EditBookEvent editBookEvent) {
		isCreateMode = Boolean.FALSE;
		selectedBookToEdit = editBookEvent.getBookToEdit();
		title.setText(selectedBookToEdit.getTitle());
		author.setText(selectedBookToEdit.getAuthor());
		publisher.setText(selectedBookToEdit.getPublisher());
	}

}
