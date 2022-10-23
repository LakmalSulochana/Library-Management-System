package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Member;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Validation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class MemberRegisterFormController {
    public JFXTextField txtMemberId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNicNumber;
    public JFXTextField txtMobile;
    public JFXTextField txtEmail;
    public JFXComboBox<String> cmbSex;
    public JFXTextField txtAge;
    public JFXTextField txtDate;
    public AnchorPane contextStudentRegister;
    public Button cancelBtn;
    public Label lblDate;
    public ImageView imgMember;
    public JFXButton btnAdd;
    String memberSex;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern memberName = Pattern.compile("^[A-z ]{3,30}$");
    Pattern address = Pattern.compile("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$");
    Pattern nic = Pattern.compile("^[0-9]{9}[v]|[0-9]{12}$");
    Pattern mobileNumber = Pattern.compile("^[0-9]{10}$");
    Pattern email = Pattern.compile("^[a-za-z0-9+.-]+@(.+)$");
    private TableLoadEvent event;

    public void initialize() throws SQLException, ClassNotFoundException {
        txtName.requestFocus();
        btnAdd.setDisable(true);
        getValidation();
        getDate();
        setMemberId();
        cmbSex.getItems().addAll(
                "male", "female");
        cmbSex.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            memberSex = newValue;
        });

    }

    public void getValidation() {
        map.put(txtName, memberName);
        map.put(txtAddress, address);
        map.put(txtNicNumber, nic);
        map.put(txtMobile, mobileNumber);
        map.put(txtEmail, email);
    }

    public void getDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    public void addMemberOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Member member = new Member(
                lblDate.getText(),
                txtMemberId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                memberSex,
                txtAge.getText(),
                txtNicNumber.getText(),
                txtEmail.getText(),
                txtMobile.getText()

        );
        if (new memberServiceController().saveMember(member)) {
            new NotificationController().set("Done", "Successful,", 4, 9);
            event.loadData();
            try {
                JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/memberCard.jrxml"));
                JasperReport compileReport = JasperCompileManager.compileReport(design);

                String date = lblDate.getText();
                String id = txtMemberId.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String email = txtEmail.getText();
                HashMap map = new HashMap();
                map.put("date", date);
                map.put("memberId", id);
                map.put("memberName", name);
                map.put("address", address);
                map.put("email", email);
                JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource());
                JasperViewer.viewReport(jasperPrint, false);

            } catch (JRException e) {
                e.printStackTrace();
            }
            clearField();
        } else {
            new NotificationController().set("Wrong", "Please Enter Valid Information", 4, 6);
        }
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //newwwwwww
    public void setEvent(TableLoadEvent event) {
        this.event = event;
    }

    public void validateOnAction(KeyEvent keyEvent) {
        btnAdd.setDisable(true);
        Object reply = Validation.validate(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (reply instanceof TextField) {
                TextField error = (TextField) reply;
                error.requestFocus();

            } else if (reply instanceof Boolean) {
                new NotificationController().set("Done", "You can go", 4, 9);
            }
        }
    }

    public void clearField() {
        txtMemberId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtNicNumber.clear();
        txtAge.clear();
        cmbSex.setValue("");
        txtMemberId.requestFocus();
    }

    private void setMemberId() {
        try {
            txtMemberId.setText(new memberServiceController().getMemberId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


