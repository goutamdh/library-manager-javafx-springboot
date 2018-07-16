/**
 * 
 */
package com.dotedlabs.librarymanager.config;

import com.dotedlabs.librarymanager.utils.CommonUtility;

/**
 * @author sandeepknair
 *
 */
public enum FxmlView {
	LOGIN {
		@Override
		String getTitle() {
			return CommonUtility.getTextFromBundle("app.login.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/Login.fxml";
		}
	},
	MAIN {
		@Override
		String getTitle() {
			return CommonUtility.getTextFromBundle("app.ui.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/Main.fxml";
		}
	},
	ADD_BOOK {
		@Override
		String getTitle() {
			return CommonUtility.getTextFromBundle("app.ui.add-book.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/AddBook.fxml";
		}
	},
	BOOK_LIST {
		@Override
		String getTitle() {
			return CommonUtility.getTextFromBundle("app.ui.list-books.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/ListBooks.fxml";
		}
	},
	ADD_MEMBER {
		@Override
		String getTitle() {
			return CommonUtility.getTextFromBundle("app.ui.add-member.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/AddMember.fxml";
		}
	},
	MEMBER_LIST {
		@Override
		String getTitle() {
			return CommonUtility.getTextFromBundle("app.ui.list-members.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/ListMembers.fxml";
		}
	},;

	abstract String getTitle();

	abstract String getFxmlFile();
}
