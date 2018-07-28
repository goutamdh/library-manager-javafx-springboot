package com.dotedlabs.librarymanager.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import org.springframework.util.StringUtils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class NotificationUtility {
	/**
	 * Function to show notification messages
	 * 
	 * @param message
	 * @param header
	 */
	public static void info(String message, String header) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		message(message, header, alert);
	}

	/**
	 * Function to show warning messages
	 * 
	 * @param message
	 * @param header
	 */
	public static void warn(String message, String header) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		message(message, header, alert);
	}

	/**
	 * function to show error messages
	 * 
	 * @param message
	 * @param header
	 */
	public static void error(String message, String header) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		message(message, header, alert);
	}

	/**
	 * Message to display based on the type
	 * 
	 * @param message
	 * @param header
	 * @param alert
	 */
	private static void message(String message, String header, Alert alert) {
		if (!StringUtils.isEmpty(header)) {
			alert.setHeaderText(header);
		}
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Message to show any stack trace error
	 * 
	 * @param e
	 */
	public static void showErrorTrace(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error trace");
		alert.setHeaderText(e.getMessage());
		alert.getDialogPane().setPrefSize(480, 400);

		VBox dialogPaneContent = new VBox();
		Label label = new Label("Stack Trace:");
		TextArea textArea = new TextArea();
		textArea.setWrapText(true);
		textArea.setText(getStackTrace(e));

		dialogPaneContent.getChildren().addAll(label, textArea);

		// Set content for Dialog Pane
		alert.getDialogPane().setContent(dialogPaneContent);

		alert.showAndWait();
	}

	public static void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Library Manager");
		alert.setHeaderText("Library Manager - Beta");

		VBox dialogPaneContent = new VBox();
		TextArea textArea = new TextArea();
		Label label = new Label("Product details:");
		textArea.setText("This is a beta software");
		dialogPaneContent.getChildren().addAll(label, textArea);
		// Set content for Dialog Pane
		alert.getDialogPane().setContent(dialogPaneContent);
		alert.showAndWait();
	}

	/**
	 * Function to show confirmation dialog
	 * 
	 * @param message
	 * @param header
	 * @return
	 */
	public static boolean showConfirm(String message, String header) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm");
		alert.setHeaderText(message);
		Optional<ButtonType> answer = alert.showAndWait();
		return answer.get() == ButtonType.OK ? true : false;
	}

	/**
	 * Show coming soon message
	 */
	public static void comingSoon() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Coming soon");
		message("You have tried to access a feature that is currently not implemented. Please check back again later",
				"Feature not yet implemented", alert);
	}

	private static String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String s = sw.toString();
		return s;
	}

}
