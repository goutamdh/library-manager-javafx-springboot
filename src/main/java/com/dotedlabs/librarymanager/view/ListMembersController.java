package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.service.MemberService;
import com.dotedlabs.librarymanager.utils.NotificationUtility;
import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

@Component
public class ListMembersController implements Initializable {
	@Lazy
	@Autowired
	MemberService memberService;
	
	@FXML
    private AnchorPane rootPane;
	
	@FXML
    private Button searchBtn;
	
	@FXML
    private TextField searchMemberField;

    @FXML
    private TableView<Member> tableList;

    @FXML
    private TableColumn<Member, String> idCol;

    @FXML
    private TableColumn<Member, String> nameCol;

    @FXML
    private TableColumn<Member, String> contactCol;

    @FXML
    private TableColumn<Member, String> emailCol;
    
    ObservableList<Member> memberList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initCol();
		loadData();
	}
	
	private void initCol() {
		searchMemberField.clear();
		searchBtn.setDisable(true);
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
	}
	
	/**
	 * Load data in the table
	 */
	private void loadData() {
		memberList.clear();
		tableList.setItems(memberList);
		for (Member member : memberService.findAll()) {
			memberList.add(member);
		}
		tableList.setItems(memberList);
	}
	
	@FXML
    void onClear(ActionEvent event) {
		searchMemberField.clear();
		loadData();
    }

    @FXML
    void onSearch(ActionEvent event) {
    	searchMember();
    }
    
    @FXML
    void onEnterPressed(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER) {
			searchMember();
		}
    }
    
    @FXML
    void onEnableSearch(KeyEvent event) {
    	if (!StringUtils.isEmpty(searchMemberField.getText())) {
			searchBtn.setDisable(false);
		} else {
			searchBtn.setDisable(true);
		}
    }
	
	
	/**
	 * Remove a member
	 * 
	 * @param event
	 */
	@FXML
	void handleDelete(ActionEvent event) {
		Member selectedMember = tableList.getSelectionModel().getSelectedItem();
		int selectedIndex = tableList.getSelectionModel().getSelectedIndex();
		if (null == selectedMember) {
			NotificationUtility.error("No member selected", "Error in delete");
		} else {
			boolean delete = NotificationUtility
					.showConfirm("Are you sure you want to delete " + selectedMember.getName() + "?", "Confirm delete");
			if (delete) {
				memberList.remove(selectedIndex);
				memberService.delete(selectedMember.getId());
			}
		}
	}

	
	private void searchMember() {
		System.out.println(searchMemberField.getText());
		if (!StringUtils.isEmpty(searchMemberField.getText())) {
			memberList.clear();
			tableList.setItems(memberList);
			for (Member member : memberService.findByKeyword(searchMemberField.getText())) {
				memberList.add(member);
			}
			tableList.setItems(memberList);
		} else {
			loadData();
		}
	}
}
