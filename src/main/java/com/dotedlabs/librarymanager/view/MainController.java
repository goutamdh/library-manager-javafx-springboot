package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.FxmlView;
import com.dotedlabs.librarymanager.config.StageManager;
import com.dotedlabs.librarymanager.config.entity.Book;
import com.dotedlabs.librarymanager.config.entity.BookIssue;
import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.service.BookIssueService;
import com.dotedlabs.librarymanager.config.service.BookService;
import com.dotedlabs.librarymanager.config.service.MemberService;
import com.dotedlabs.librarymanager.dao.BookIssueDto;
import com.dotedlabs.librarymanager.utils.NotificationUtility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

@Component
public class MainController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;

	@Lazy
	@Autowired
	private BookIssueService bookIssueService;

	@Lazy
	@Autowired
	private BookService bookService;

	@Lazy
	@Autowired
	private MemberService memberService;

	@FXML
	private StackPane rootPane;

	@FXML
	private TextField bookIdSearch;

	@FXML
	private TextField memberIdSearch;

	@FXML
	private Text author;

	@FXML
	private Text publisher;

	@FXML
	private Text member;

	@FXML
	private Text contact;

	private Book selectedBook;

	private Member selectedMember;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initForm();
	}

	private void initForm() {
		bookIdSearch.clear();
		memberIdSearch.clear();
		author.setText("");
		publisher.setText("");
		member.setText("");
		contact.setText("");
		selectedBook = null;
		selectedMember = null;
	}

	@FXML
	void loadAddBook(ActionEvent event) {
		stageManager.openStackedScene(FxmlView.ADD_BOOK);
	}

	@FXML
	void loadAddMember(ActionEvent event) {
		stageManager.openStackedScene(FxmlView.ADD_MEMBER);
	}

	@FXML
	void loadBookTable(ActionEvent event) {
		stageManager.openStackedScene(FxmlView.BOOK_LIST);
	}

	@FXML
	void loadMemberTable(ActionEvent event) {
		stageManager.openStackedScene(FxmlView.MEMBER_LIST);
	}

	@FXML
	void loadBookDetails(ActionEvent event) {
		if (bookIdSearch.getText().isEmpty()) {
			NotificationUtility.error("Please enter book id", "Invalid entry");
			return;
		}
		try {
			UUID bookId = UUID.fromString(bookIdSearch.getText());
			selectedBook = bookService.findById(bookId);
			if (null == selectedBook) {
				NotificationUtility.error("Your id didnt match any result", "Not found");
			} else {
				author.setText(selectedBook.getAuthor());
				publisher.setText(selectedBook.getPublisher());
			}
		} catch (Exception e) {
			NotificationUtility.showErrorTrace(e);
		}
	}

	@FXML
	void loadMemberDetails(ActionEvent event) {
		if (memberIdSearch.getText().isEmpty()) {
			NotificationUtility.error("Please enter member id", "Invalid entry");
			return;
		}
		try {
			UUID memberId = UUID.fromString(memberIdSearch.getText());
			selectedMember = memberService.findById(memberId);
			if (null == selectedMember) {
				NotificationUtility.error("Your id didnt match any result", "Not found");
			} else {
				member.setText(selectedMember.getName());
				contact.setText(selectedMember.getContact());
			}
		} catch (Exception e) {
			NotificationUtility.showErrorTrace(e);
		}
	}

	@FXML
	void issueBook(ActionEvent event) {
		// @formatter:off
    	BookIssueDto bookIssueDto = BookIssueDto.builder()
    			.bookId(selectedBook.getId())
    			.memberId(selectedMember.getId())
    			.build();
    	// @formatter:on
		BookIssue bookIssue = bookIssueService.issueBook(bookIssueDto);
		if (null == bookIssue) {
			NotificationUtility.error("Couldn't issue the book", "Issue error");
		} else {
			NotificationUtility.info("Book issued to " + selectedMember.getName(), "Issue success");
			initForm();
		}
	}

	@FXML
	void quit(ActionEvent event) {
		stageManager.close(rootPane.getScene());
	}

	@FXML
	void showAbout(ActionEvent event) {
		NotificationUtility.showAbout();
	}

	@FXML
	void showSettings(ActionEvent event) {
		NotificationUtility.comingSoon();
	}

	@FXML
	void handleMaximize(ActionEvent event) {
		stageManager.setMaximized(true);
	}

	@FXML
	void handleFullscreen(ActionEvent event) {
		stageManager.setFullScreen(true);
	}
}
