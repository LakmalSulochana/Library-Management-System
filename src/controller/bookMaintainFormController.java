package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.BookTM;

import java.io.IOException;
import java.sql.SQLException;

public class bookMaintainFormController implements DominationTableLoadEvent {
    public AnchorPane contextBookMaintain;
    public TableView<BookTM> tblBook;
    public TableColumn colCategory;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colAuthorName;
    public TableColumn colBookLanguage;
    public TableColumn colReleasedDate;
    public TableColumn colDominationId;
    public JFXTextField txtBookId;
    public JFXTextField txtDonateID;
    public JFXTextField txtReleasedDate;
    public JFXTextField txtBookLanguage;
    public JFXTextField txtAuthorName;
    public JFXTextField txtBookName;
    public JFXTextField txtCategory;
    public JFXTextField txtTitle;
    public JFXTextField txtAddType;
    public Label lblAvailable;
    public TableColumn colStatus;
    public JFXButton btnDelete;
    public JFXButton btnAddCategory;

    public void initialize() throws SQLException, ClassNotFoundException {
        dropBookToTable();
        ObservableList<BookTM> book = new bookController().getBookList();
    }

    public void dropBookToTable() throws SQLException, ClassNotFoundException {
        ObservableList<BookTM> book = new bookController().getBookList();
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        colBookLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        colReleasedDate.setCellValueFactory(new PropertyValueFactory<>("releasedDate"));
        colDominationId.setCellValueFactory(new PropertyValueFactory<>("donateId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblBook.setItems(book);
    }

    public void manageCategoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/addCategoryForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Add Category");
        stage.show();
    }

    public void addBookOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addBookForm.fxml"));
        Parent parent = loader.load();
        AddBookFormController controller = loader.getController();
        controller.setEvent(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void loadDataToTable() throws SQLException, ClassNotFoundException {
        tblBook.setItems(new bookController().getBookList());
    }

    public void deleteBookOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        BookTM book = tblBook.getSelectionModel().getSelectedItem();
        String bookId = book.getBookId();
        if (new bookController().deleteBook(bookId)) {
            new NotificationController().set("Done", "Delete Successful", 4, 1);
            dropBookToTable();
            clearField();

        } else {
            new NotificationController().set("Wrong", "Select Valid Information", 4, 6);
        }
    }

    public void updateBookOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        BookTM book = new BookTM(
                txtBookId.getText(),
                txtBookName.getText(),
                txtTitle.getText(),
                txtAuthorName.getText(),
                txtBookLanguage.getText(),
                txtReleasedDate.getText(),
                txtCategory.getText(),
                txtDonateID.getText(),
                txtAddType.getText(),
                lblAvailable.getText()


        );
        if (new bookController().updateBook(book)) {
            new NotificationController().set("Done", "Information has been Successful", 4, 3);
            dropBookToTable();
        } else {
            new NotificationController().set("Error", "Select Information", 4, 6);
        }

    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        BookTM book = tblBook.getSelectionModel().getSelectedItem();
        txtCategory.setText(book.getCategoryName());
        txtBookId.setText(book.getBookId());
        txtBookName.setText(book.getBookName());
        txtAuthorName.setText(book.getAuthorName());
        txtBookLanguage.setText(book.getLanguage());
        txtReleasedDate.setText(book.getReleasedDate());
        txtDonateID.setText(book.getDonateId());
        txtTitle.setText(book.getTitle());
        txtAddType.setText(book.getAddType());

    }

    public void clearField() {
        txtCategory.clear();
        txtBookId.clear();
        txtBookName.clear();
        txtAuthorName.clear();
        txtBookLanguage.clear();
        txtReleasedDate.clear();
        txtDonateID.clear();
        txtCategory.requestFocus();
    }
}
