package controller;

import com.jfoenix.controls.JFXButton;
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
import model.Donate;
import util.Validation;
import view.tm.DominationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddDominatorFormController {

    public JFXTextField txtNIC;
    public JFXTextField txtMobile;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtId;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colMobile;
    public TableColumn colNIC;
    public TableColumn colDate;
    public TableView<DominationTM> tblDominator;
    public Label lblDate;
    public JFXButton addAndBackBtn;
    public AnchorPane contextDominator;
    public JFXTextField txtEmail;
    public TableColumn colEmail;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    Pattern memberName = Pattern.compile("^[A-z ]{3,30}$");
    Pattern address = Pattern.compile("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$");
    Pattern mobileNumber = Pattern.compile("^[0-9]{10}$");
    Pattern nic = Pattern.compile("^[0-9]{9}[v]|[0-9]{12}$");
    Pattern email = Pattern.compile("^[a-za-z0-9+.-]+@(.+)$");


    public void initialize() throws SQLException, ClassNotFoundException {
        txtId.requestFocus();
        dropDominatorToTable();
        getDate();
        getValidation();
        setDonorId();
    }

    public void getValidation() {
        map.put(txtName, memberName);
        map.put(txtAddress, address);
        map.put(txtMobile, mobileNumber);
        map.put(txtNIC, nic);
        map.put(txtEmail, email);
    }

    public void dropDominatorToTable() throws SQLException, ClassNotFoundException {
        ObservableList<DominationTM> dominator = new donateController().getDominator();
        colId.setCellValueFactory(new PropertyValueFactory<>("donateId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("personName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("personAddress"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblDominator.setItems(dominator);
    }

    public void getDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd EE");
        lblDate.setText(f.format(date));
    }

    public void addButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Donate dominator = new Donate(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtMobile.getText(),
                txtNIC.getText(),
                lblDate.getText(),
                txtEmail.getText()

        );
        if (new donateController().saveDominator(dominator)) {
            new NotificationController().set("Done", "Donor add Successful", 4, 9);
            dropDominatorToTable();
            clearField();
        } else {
            new NotificationController().set("Error", "Enter Valid Information", 4, 6);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DominationTM dominator = tblDominator.getSelectionModel().getSelectedItem();
        String dominatorName = dominator.getDonateId();

        if (new donateController().deleteDominator(dominatorName)) {
            new NotificationController().set("Delete", "Complete", 4, 1);
            dropDominatorToTable();
        } else {
            new NotificationController().set("Error", "Please Select Information", 4, 6);
        }
        clearField();
    }

    private void clearField() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtNIC.clear();
        txtEmail.clear();
        txtId.requestFocus();

    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DominationTM donate = new DominationTM(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtMobile.getText(),
                txtNIC.getText(),
                lblDate.getText(),
                txtEmail.getText()


        );
        if (new donateController().updateDominator(donate)) {
            new NotificationController().set("Updated", "Your information Has been successful", 4, 3);
            dropDominatorToTable();
            clearField();
        } else {
            new NotificationController().set("Error", "Select Information", 4, 6);
        }
    }

    public void clearTextOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void addToFeildOnAction(MouseEvent mouseEvent) {
        DominationTM donate = null;

        donate = tblDominator.getSelectionModel().getSelectedItem();
        txtId.setText(donate.getDonateId());
        txtName.setText(donate.getPersonName());
        txtAddress.setText(donate.getPersonAddress());
        txtNIC.setText(donate.getNic());
        txtMobile.setText(donate.getMobile());
        txtEmail.setText(donate.getEmail());
        lblDate.setText(donate.getDate());
    }

    public void ValidateOnAction(KeyEvent keyEvent) {
        addAndBackBtn.setDisable(true);
        Object reply = Validation.validate(map, addAndBackBtn);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (reply instanceof TextField) {
                TextField error = (TextField) reply;
                error.requestFocus();

            } else if (reply instanceof Boolean) {
                new NotificationController().set("Done", "All Information Are Correct", 4, 9);
            }
        }
    }

    private void setDonorId() {
        try {
            txtId.setText(new donateController().getDonateId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
