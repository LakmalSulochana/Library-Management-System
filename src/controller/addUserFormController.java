package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.User;
import util.Validation;
import view.tm.UserTM;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class addUserFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtUserId;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephoneNumber;
    public JFXTextField txtRole;
    public JFXTextField txtPassword;
    public TableView<UserTM> tblUserDetail;
    public TableColumn colUserId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTelephoneNumber;
    public TableColumn colRole;
    public JFXComboBox<String> cmbRole;
    public AnchorPane contextAddUser;
    public JFXButton btnAdd;
    public Label lblBarcode;
    public Label lblBarCode;
    public JFXTextField txtNIC;
    public TableColumn colNic;
    String userRole;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    Pattern userName = Pattern.compile("^[A-z ]{2,30}$");
    Pattern address = Pattern.compile("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$");
    Pattern contact = Pattern.compile("^[0-9]{10}$");
    Pattern nic = Pattern.compile("^[0-9]{9}[v]|[0-9]{12}$");
    Pattern password = Pattern.compile("^.*(?=.{6,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");

    public void initialize() throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        getValidate();
        dropUserToTable();
        cmbRole.getItems().addAll(
                "Admin", "User");
        cmbRole.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            userRole = newValue;
        });

    }

    public void getValidate() {
        map.put(txtUserName, userName);
        map.put(txtAddress, address);
        map.put(txtTelephoneNumber, contact);
        map.put(txtNIC, nic);
        map.put(txtPassword, password);
    }

    public void dropUserToTable() throws SQLException, ClassNotFoundException {
        ObservableList<UserTM> user = new userSaveController().getUserList();
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("telePhoneNumber"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        tblUserDetail.setItems(user);
    }


    public void addUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        User user = new User(
                txtUserId.getText(),
                txtUserName.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtTelephoneNumber.getText()),
                userRole,
                txtPassword.getText(),
                txtNIC.getText()
        );
        if (new userSaveController().saveUser(user)) {
            new NotificationController().set("Done", "New User added Successful", 4, 9);
            dropUserToTable();

        } else {
            new NotificationController().set("Wrong", "Please Enter Valid Information", 4, 12);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserTM user = new UserTM(
                txtUserId.getText(),
                txtUserName.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtTelephoneNumber.getText()), userRole,
                txtPassword.getText(),
                txtNIC.getText()
        );
        if (new userSaveController().updateUsers(user)) {
            new NotificationController().set("Done", "Your Information Has been Updated", 4, 3);
            dropUserToTable();
            clearField();
        } else {
            new NotificationController().set("Wrong", "Enter Valid Information", 4, 12);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserTM user = tblUserDetail.getSelectionModel().getSelectedItem();
        String userName = user.getUserId();

        if (new userSaveController().deleteUsers(userName)) {
            new NotificationController().set("Done", "Your Information has been Deleted", 4, 1);
            dropUserToTable();
        } else {
            new NotificationController().set("Wrong", "Please Select Information", 4, 12);
        }
        clearField();
    }

    public void tblMouseOnAction(MouseEvent mouseEvent) {
        UserTM user = null;

        user = tblUserDetail.getSelectionModel().getSelectedItem();
        txtUserId.setText(user.getUserId());
        txtUserName.setText(user.getUserName());
        txtAddress.setText(user.getAddress());
        txtTelephoneNumber.setText("" + user.getTelePhoneNumber());
        cmbRole.setValue(user.getRole());
        txtPassword.setText(user.getPassword());
    }

    private void clearField() {
        txtUserId.clear();
        txtUserName.clear();
        txtAddress.clear();
        txtTelephoneNumber.clear();
        txtPassword.clear();
        cmbRole.setValue("");
        txtUserId.requestFocus();
    }

    public void clearfieldOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void validateCheckOnAction(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object reply = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (reply instanceof TextField) {
                TextField error = (TextField) reply;
                error.requestFocus();

            } else if (reply instanceof Boolean) {
                new NotificationController().set("Done", "Your Information Correct", 4, 9);
            }
        }
    }


}
