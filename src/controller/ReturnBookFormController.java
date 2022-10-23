package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.BarrowDetail;
import view.tm.BarrowTM;

import java.sql.SQLException;

public class ReturnBookFormController {

    public TableView<BarrowTM> tblBarrowDetail;
    public TableColumn colBarrowId;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colMemberId;
    public TableColumn colTotalDate;
    public JFXTextField txtBookId;
    public JFXTextField txtBookName;
    public JFXTextField txtMemberId;
    public JFXTextField txtTotalDate;
    public JFXTextField txtBarrowId;
    public TableColumn colStatus;
    public JFXTextField txtSearch;

    public void initialize() {
        try {
            dropBarrowDetailToTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void dropBarrowDetailToTable() throws SQLException, ClassNotFoundException {
        ObservableList<BarrowTM> barrow = new BarrowController().getBarrowList();
        colBarrowId.setCellValueFactory(new PropertyValueFactory<>("barrowId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colTotalDate.setCellValueFactory(new PropertyValueFactory<>("totalDay"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblBarrowDetail.setItems(barrow);
    }

    public void returnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        BarrowDetail detail = new BarrowDetail(
                txtBarrowId.getText(),
                txtBookId.getText(),
                txtMemberId.getText(),
                Integer.parseInt(txtTotalDate.getText()),
                txtBookName.getText(),
                "Available"
        );

        if (new ReturnController().updateStatus(detail)) {
            new NotificationController().set("Done", "Your Return Successful", 4, 5);
            dropBarrowDetailToTable();
            tblBarrowDetail.refresh();
        } else {
            new NotificationController().set("Wrong", "Enter Valid Information", 4, 6);
        }
        clearField();
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        BarrowTM barrow = tblBarrowDetail.getSelectionModel().getSelectedItem();
        txtBarrowId.setText(barrow.getBarrowId());
        txtBookId.setText(barrow.getBookId());
        txtBookName.setText(barrow.getBookName());
        txtMemberId.setText(barrow.getMemberId());
        txtTotalDate.setText("" + barrow.getTotalDay());
    }

    public void clearField() {
        txtBarrowId.clear();
        txtBookId.clear();
        txtBookName.clear();
        txtMemberId.clear();
        txtTotalDate.clear();
    }

    public void clearTxtOnAction(ActionEvent actionEvent) {
        clearField();
    }

    public void searchOnAction(KeyEvent keyEvent) {
    }
}
