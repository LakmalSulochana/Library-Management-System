package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.tm.BarrowTM;

import java.sql.SQLException;

public class HistoryFormController {
    public TableColumn colBarrowId;
    public TableColumn bolBookId;
    public TableColumn colBookName;
    public TableColumn colMemberId;
    public TableColumn colTotalDate;
    public TableColumn colStatus;
    public TableView<BarrowTM> tblHistory;
    public TableColumn colBookId;
    public TableColumn colMemberName;
    public JFXTextField txtHistorySearch;
    ObservableList<BarrowTM> barrow = FXCollections.observableArrayList();

    public void initialize() {
        try {
            dropHistoryToTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropHistoryToTable() throws SQLException, ClassNotFoundException {
        barrow = new HistoryController().getBarrowList();
        colBarrowId.setCellValueFactory(new PropertyValueFactory<>("barrowId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colTotalDate.setCellValueFactory(new PropertyValueFactory<>("totalDay"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblHistory.setItems(barrow);

    }

    public void searchOnAction(KeyEvent keyEvent) {
    }
}
