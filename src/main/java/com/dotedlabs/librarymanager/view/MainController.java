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
import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.service.BookService;
import com.dotedlabs.librarymanager.config.service.MemberService;
import com.dotedlabs.librarymanager.utils.NotificationUtility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

@Component
public class MainController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@Lazy
	@Autowired
	private BookService bookService;
	
	@Lazy
	@Autowired
	private MemberService memberService;
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
        	Book book = bookService.findById(bookId);
        	if (null == book) {
    			NotificationUtility.error("Your id didnt match any result", "Not found");
    		} else {			
    			author.setText(book.getAuthor());
    			publisher.setText(book.getPublisher());
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
        	Member memberFound = memberService.findById(memberId);
        	if (null == memberFound) {
    			NotificationUtility.error("Your id didnt match any result", "Not found");
    		} else {			
    			member.setText(memberFound.getName());
    			contact.setText(memberFound.getContact());
    		}
		} catch (Exception e) {
			NotificationUtility.showErrorTrace(e);
		}
    }
}
