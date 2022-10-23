package controller;

import com.jfoenix.controls.JFXDatePicker;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Barrow;
import model.BarrowDetail;
import view.tm.CartTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class IssueBookFormController {
    public TextField txtSearch;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtMail;
    public TextField txtContact;
    public TextField txtMemberId;
    public JFXDatePicker txtIssueDate;
    public JFXDatePicker txtReturnDate;
    public TextField txtSearchBook;
    public TextField txtBookName;
    public TextField txtLanguage;
    public TextField txtAuthor;
    public TextField txtCategory;
    public TableView<CartTM> tblBarrowBook;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colLanguage;
    public TableColumn colCategory;
    public TextField txtBookId;
    public Label lblBarrowId;
    public TextField txtTootleDate;
    ObservableList<CartTM> obList = FXCollections.observableArrayList();

    public void initialize() {

        setBarrowId();
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        disablePreviousDay(txtReturnDate);
        disablePreviousDay(txtIssueDate);

    }

    private void disablePreviousDay(JFXDatePicker datePicker) {
        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }

                };
            }
        };
        datePicker.setDayCellFactory(callB);
    }

    private void setBarrowId() {
        try {
            lblBarrowId.setText(new BarrowController().getBarrowId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        txtIssueDate.setValue(LocalDate.now());
    }


    public void searchMemberOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM member WHERE member_id ='" + txtSearch.getText() + "'";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            txtMemberId.setText(rst.getString(2));
            txtName.setText(rst.getString(3));
            txtNic.setText(rst.getString(7));
            txtMail.setText(rst.getString(8));
            txtContact.setText(rst.getString(9));

        } else {
            new Alert(Alert.AlertType.WARNING, "invalid Member Id").show();
        }
    }

    public void clearField() {
        txtSearch.clear();
        txtMemberId.clear();
        txtName.clear();
        txtNic.clear();
        txtMail.clear();
        txtContact.clear();
        txtSearch.requestFocus();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void clearBookDetail() {
        txtSearchBook.clear();
        txtBookId.clear();
        txtBookName.clear();
        txtCategory.clear();
        txtLanguage.clear();
        txtAuthor.clear();
        txtSearchBook.clear();

    }

    public void issueBookOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<BarrowDetail> barrows = new ArrayList<>();
        ArrayList<Barrow> barrow = new ArrayList<>();
        for (CartTM temp : obList
        ) {
            barrows.add(new BarrowDetail(
                    lblBarrowId.getText(),
                    temp.getBookId(),
                    temp.getMemberId(),
                    Integer.parseInt(txtTootleDate.getText()),
                    temp.getBookName(),
                    "Unavailable"

            ));
        }
        for (CartTM temp : obList
        ) {
            barrow.add(new Barrow(
                    lblBarrowId.getText(),
                    temp.getMemberId(),
                    txtIssueDate.getValue().toString(),
                    txtReturnDate.getValue().toString(),
                    temp.getMemberName(),
                    txtNic.getText(),
                    txtContact.getText(),
                    txtMail.getText(),
                    barrows
            ));
        }
        if (new BarrowController().saveBarrow(barrow)) {
            setBarrowId();
            new NotificationController().set("Done", "your Barrowed Succcesful", 4, 9);
            obList.clear();
        } else {
            new NotificationController().set("Error", "Select Valid Information", 4, 6);
        }
        clearField();
    }

    public void searchBookOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM book WHERE book_id ='" + txtSearchBook.getText() + "'";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            txtBookId.setText(rst.getString(1));
            txtBookName.setText(rst.getString(2));
            txtLanguage.setText(rst.getString(5));
            txtAuthor.setText(rst.getString(4));
            txtCategory.setText(rst.getString(7));
        } else {
            new NotificationController().set("Wrong", "Enter Valid Id", 4, 6);
        }
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        CartTM tm = new CartTM(
                txtBookId.getText(),
                txtBookName.getText(),
                txtLanguage.getText(),
                txtCategory.getText(),
                txtMemberId.getText(),
                txtName.getText()
        );
        obList.add(tm);
        tblBarrowBook.setItems(obList);
        clearBookDetail();

    }

    public void totalDateOnAction(ActionEvent actionEvent) {
        long date1 = txtIssueDate.getValue().toEpochDay();
        long date2 = txtReturnDate.getValue().toEpochDay();
        int totalDate = (int) Math.abs(date1 - date2);
        txtTootleDate.setText(String.valueOf(totalDate));
    }
}
