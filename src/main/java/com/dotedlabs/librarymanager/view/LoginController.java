/**
 * 
 */
package com.dotedlabs.librarymanager.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.FxmlView;
import com.dotedlabs.librarymanager.config.StageManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author sandeepknair
 *
 */
@Component
public class LoginController {
	@Lazy
	@Autowired
	private StageManager stageManager;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	public void initialize() {
		usernameField.setText("");
		passwordField.setText("");
	}

	@FXML
	public void onSignup() {
		initialize();
		System.out.println("Clicked on signup " + usernameField.getText());
		this.stageManager.switchScene(FxmlView.MAIN);
	}

}
