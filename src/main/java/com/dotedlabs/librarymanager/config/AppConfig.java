/**
 * 
 */
package com.dotedlabs.librarymanager.config;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.dotedlabs.librarymanager.utils.CommonConstants;

import javafx.stage.Stage;

/**
 * @author sandeepknair
 *
 */
@Configuration
public class AppConfig {
	@Autowired
	SpringFXMLLoader springFXMLLoader;

	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle(CommonConstants.RESOURCE_BUNDLE_PATH);
	}

	// Stage only created after Spring context bootstap
	@Lazy(value = true)
	@Bean
	public StageManager stageManager(Stage stage) throws IOException {
		return new StageManager(springFXMLLoader, stage);
	}

}
