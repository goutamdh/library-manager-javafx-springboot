package com.dotedlabs.librarymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dotedlabs.librarymanager.config.FxmlView;
import com.dotedlabs.librarymanager.config.StageManager;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class that manages application state
 */
public abstract class AbstractJavaFxApplicationSupport extends Application {
	private static String[] savedArgs;
	protected ConfigurableApplicationContext context;
	private StageManager stageManager;

	@Override
	public void init() throws Exception {
		context = SpringApplication.run(getClass(), savedArgs);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageManager = context.getBean(StageManager.class, stage);
		displayInitialScene();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		context.close();
	}

	/**
	 * Useful to override this method by sub-classes wishing to change the first
	 * Scene to be displayed on startup. Example: Functional tests on main window.
	 */
	protected void displayInitialScene() {
		// Load starting scene
		stageManager.switchScene(FxmlView.MAIN);
	}

	protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> appClass, String[] args) {
		AbstractJavaFxApplicationSupport.savedArgs = args;
		Application.launch(appClass, args);
	}
}