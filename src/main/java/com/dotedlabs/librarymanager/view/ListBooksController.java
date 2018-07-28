package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.FxmlView;
import com.dotedlabs.librarymanager.config.StageManager;
import com.dotedlabs.librarymanager.config.entity.Book;
import com.dotedlabs.librarymanager.config.service.BookService;
import com.dotedlabs.librarymanager.events.EditBookEvent;
import com.dotedlabs.librarymanager.events.RefreshedBookEvent;
import com.dotedlabs.librarymanager.utils.NotificationUtility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

@Component
public class ListBooksController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;

	@Lazy
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Lazy
	@Autowired
	private BookService bookService;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TableView<Book> tableList;

	@FXML
	private TableColumn<Book, String> idCol;

	@FXML
	private TableColumn<Book, String> titleCol;

	@FXML
	private TableColumn<Book, String> authorCol;

	@FXML
	private TableColumn<Book, String> publishCol;

	@FXML
	private TableColumn<Book, Boolean> availabilityCol;

	ObservableList<Book> bookList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initCol();
		loadData();
	}

	private void initCol() {
		bookList.clear();
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		publishCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		availabilityCol.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
	}

	private void loadData() {
		for (Book book : bookService.findAll()) {
			bookList.add(book);
		}
		tableList.setItems(bookList);
	}

	@FXML
	void handleEdit(ActionEvent event) {
		Book selectedBook = tableList.getSelectionModel().getSelectedItem();
		if (null == selectedBook) {
			NotificationUtility.error("No book selected", "Error in edit");
		} else {
			stageManager.openStackedScene(FxmlView.EDIT_BOOK);
			eventPublisher.publishEvent(new EditBookEvent(selectedBook));
		}
	}

	@FXML
	void handleDelete(ActionEvent event) {
		Book selectedBook = tableList.getSelectionModel().getSelectedItem();
		int selectedIndex = tableList.getSelectionModel().getSelectedIndex();
		if (null == selectedBook) {
			NotificationUtility.error("No book selected", "Error in delete");
		} else {
			boolean delete = NotificationUtility
					.showConfirm("Are you sure you want to delete " + selectedBook.getTitle() + "?", "Confirm delete");
			if (delete) {
				bookList.remove(selectedIndex);
				bookService.delete(selectedBook.getId());
			}
		}
	}

	/**
	 * Function to handle after update process of updating the table
	 * 
	 * @param refreshedBookEvent
	 */
	@EventListener
	public void handleUpdate(RefreshedBookEvent refreshedBookEvent) {
		for (ListIterator<Book> iterator = bookList.listIterator(); iterator.hasNext();) {
			Book book = iterator.next();
			if (book.getId() == refreshedBookEvent.getBook().getId()) {
				bookList.set(iterator.previousIndex(), refreshedBookEvent.getBook());
				break;
			}
		}
	}
}
