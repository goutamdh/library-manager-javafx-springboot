package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.StageManager;
import com.dotedlabs.librarymanager.config.entity.Book;
import com.dotedlabs.librarymanager.config.service.BookService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		tableList.getItems().setAll(bookList);
	}

}
