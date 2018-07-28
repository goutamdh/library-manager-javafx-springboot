package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.StageManager;
import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.service.MemberService;
import com.dotedlabs.librarymanager.utils.NotificationUtility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

@Component
public class AddMemberController implements Initializable {
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@Lazy
	@Autowired
	private MemberService memberService;
	
	@FXML
    private AnchorPane rootPane;
	
	@FXML
    private TextField name;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	initForm();
	}
    
    private void initForm() {
    	name.clear();
    	contact.clear();
    	email.clear();
	}

    @FXML
    void addMember(ActionEvent event) {
    	if (name.getText().isEmpty() || contact.getText().isEmpty() || email.getText().isEmpty()) {
			NotificationUtility.error("All fields are mandatory", "Invalid entry");
		} else {
			// @formatter:off
			Member member = Member.builder()
					.name(name.getText())
					.contact(contact.getText())
					.email(email.getText())
					.build();
			// @formatter:on
			try {
				member = memberService.addMember(member);				
				NotificationUtility.info("Saved member " + member.getName(), "Success");
				// Clear all data
				initForm();
			} catch (Exception e) {
				NotificationUtility.showErrorTrace(e);
			}
		}
    }

    @FXML
	void cancel(ActionEvent event) {	
		stageManager.close(rootPane.getScene());
	}

}
