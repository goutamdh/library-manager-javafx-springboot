/**
 * 
 */
package com.dotedlabs.librarymanager.config;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;

/**
 * @author sandeepknair
 *
 */
@Component
public class SpringFXMLLoader {
	private final ResourceBundle resourceBundle;
	private final ApplicationContext context;

	@Autowired
	public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
		this.context = context;
	}

	public FXMLLoader load(String fxmlPath) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		// Spring now FXML Controller Factory
		fxmlLoader.setControllerFactory(context::getBean);
		fxmlLoader.setResources(resourceBundle);
		fxmlLoader.setLocation(getClass().getResource(fxmlPath));
		return fxmlLoader;
	}
}