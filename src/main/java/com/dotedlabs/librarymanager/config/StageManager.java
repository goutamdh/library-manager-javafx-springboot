package com.dotedlabs.librarymanager.config;

import java.util.Objects;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StageManager {
	private final Stage primaryStage;
	private final SpringFXMLLoader springFXMLLoader;

	public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
		this.springFXMLLoader = springFXMLLoader;
		this.primaryStage = stage;
	}

	public void switchScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		show(viewRootNodeHierarchy, view.getTitle());
	}

	public void openStackedScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		showStacked(viewRootNodeHierarchy, view.getTitle());
	}
	
	public void close(Scene scene) {
		scene.getWindow().hide();
	}

	private void show(final Parent rootnode, String title) {
		log.info("Starting application");
		Scene scene = prepareScene(rootnode);
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();

		try {
			primaryStage.show();
			log.info("Application launched");
		} catch (Exception exception) {
			handleException("Unable to show scene for title" + title, exception);
		}
	}

	private void showStacked(final Parent parent, String title) {
		log.info("Launching stacked scene");
		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setTitle(title);
		stage.setScene(new Scene(parent));
		stage.sizeToScene();
		stage.centerOnScreen();
		stage.show();
		log.info("Stacked scene launched");
	}

	private Scene prepareScene(Parent rootnode) {
		Scene scene = primaryStage.getScene();
		if (scene == null) {
			scene = new Scene(rootnode);
		}
		scene.setRoot(rootnode);
		return scene;
	}

	/**
	 * Loads the object hierarchy from a FXML document and returns to root node of
	 * that hierarchy.
	 *
	 * @return Parent root node of the FXML document hierarchy
	 */
	private Parent loadViewNodeHierarchy(String fxmlFilePath) {
		Parent rootNode = null;
		try {
			rootNode = springFXMLLoader.load(fxmlFilePath);
			Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
		} catch (Exception exception) {
			handleException("Unable to load FXML view" + fxmlFilePath, exception);
		}
		return rootNode;
	}

	/**
	 * Function to handle error logs
	 * 
	 * @param errorMsg
	 * @param exception
	 */
	private void handleException(String errorMsg, Exception exception) {
		log.error(errorMsg, exception, exception.getCause());
		Platform.exit();
	}
}
