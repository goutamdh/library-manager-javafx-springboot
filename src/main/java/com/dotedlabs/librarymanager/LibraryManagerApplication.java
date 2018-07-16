package com.dotedlabs.librarymanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagerApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
		launchApp(LibraryManagerApplication.class, args);
	}

}
