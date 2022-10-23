package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.Book;

import java.sql.SQLException;
import java.util.List;

public class AddBookFormController {
    public JFXTextField txtBookId;
    public JFXTextField txtBookName;
    public JFXTextField txtAuthorName;
    public JFXTextField txtLanguage;
    public JFXTextField txtReleaseDate;
    public JFXTextField txtTitle;
    public JFXButton cancelButtonOnAction;
    public JFXButton saveButtonOnAction;
    public JFXComboBox<String> cmbSelectCategory;
    public JFXComboBox<String> cmbAddType;
    public JFXComboBox<String> cmbDominatorId;
    public Label lblAvailable;
    public Label lblDonator;
    public JFXToggleButton btnChose;
    public RadioButton btnChosee;
    String addType;

    private DominationTableLoadEvent event;

    public void initialize() {
        cmbDominatorId.setDisable(true);
        lblDonator.setDisable(true);
        cmbAddType.getItems().addAll(
                "donation", "non-donation");
        cmbAddType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            addType = newValue;
        });

        try {
            loadCategoryName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadDominatorId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButtonOnAction.getScene().getWindow();
        stage.close();
    }

    public void saveButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // List<Barrow> books=new ArrayList<>();
        Book book = new Book(
                addType,
                cmbDominatorId.getValue(),
                txtBookId.getText(),
                txtBookName.getText(),
                cmbSelectCategory.getValue(),
                txtTitle.getText(),
                txtAuthorName.getText(),
                txtLanguage.getText(),
                txtReleaseDate.getText(),
                lblAvailable.getText()


        );

        System.out.println(book);
        if (new bookController().saveBook(book)) {
            new NotificationController().set("Done", "Book Added Successful", 4, 9);
            event.loadDataToTable();
            clearField();
        } else {
            new NotificationController().set("Wrong", "Enter Valid Information", 4, 6);
        }
    }

    private void loadCategoryName() throws SQLException, ClassNotFoundException {
        List<String> categoryName = new categoryController().getAllCategoryName();
        cmbSelectCategory.getItems().addAll(categoryName);
    }

    private void loadDominatorId() throws SQLException, ClassNotFoundException {
        List<String> categoryName = new donateController().getAllDonationId();
        cmbDominatorId.getItems().addAll(categoryName);
    }

    public void setEvent(DominationTableLoadEvent event) {
        this.event = event;
    }

    public void addModeOnAction(ActionEvent actionEvent) {
        if (cmbAddType.getValue().equals("non-donation")) {
            cmbDominatorId.setDisable(true);
            lblDonator.setDisable(true);
        } else {
            cmbAddType.getValue().equals("donation");
            cmbDominatorId.setDisable(false);
            lblDonator.setDisable(false);
        }
    }

    public void addChoseOnAction(ActionEvent actionEvent) {
        if (btnChosee.equals(true)) {
            saveButtonOnAction.setDisable(false);
        } else {
            btnChosee.equals(false);
            saveButtonOnAction.setDisable(true);
        }
    }

    private void clearField() {
        txtBookId.clear();
        txtBookName.clear();
        txtAuthorName.clear();
        txtLanguage.clear();
        txtTitle.clear();
        txtReleaseDate.clear();
        cmbAddType.setValue("");
        cmbDominatorId.setValue("");
        cmbSelectCategory.setValue("");
        txtBookName.requestFocus();
    }
}
