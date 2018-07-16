package com.dotedlabs.librarymanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dotedlabs.librarymanager.config.entity.Member;
import com.dotedlabs.librarymanager.config.service.MemberService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

@Component
public class ListMembersController implements Initializable {
	@Lazy
	@Autowired
	MemberService memberService;
	
	@FXML
    private AnchorPane rootPane;

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
		memberList.clear();
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
	}
	
	private void loadData() {
		for (Member member : memberService.findAll()) {
			memberList.add(member);
		}
		tableList.getItems().setAll(memberList);
	}

}
