package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.BookTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class EmployeeFormController {
    public AnchorPane contexDashBoard;
    public AnchorPane contextAdmin;
    public Label lblTotalBook;
    public Label lblTotalMember;
    public Label lblTotalMember1;
    public TableView<BookTM> tblDashBoard;
    public TableColumn colBookId;
    public TableColumn colBookName;
    public TableColumn colTitle;
    public TableColumn colCategory;
    public TableColumn colStatus;
    public TableColumn colLanguage;
    public JFXTextField txtSearch;
    public Label lblBarrowBook;
    public Label lblTotalDonors;
    public Label lblTime;
    public Label lblDate;
    Date currentDate = new Date();


    public void initialize() {
        currentTime();
        currentDay();
        try {
            loadDashBoardTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    search(newValue);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });*/

        try {
            lblTotalBook.setText(new DashBoardController().getBookCount());
            lblTotalMember.setText(new memberServiceController().getMemberCount());
            lblBarrowBook.setText(new BarrowController().getBarrowCount());
            lblTotalDonors.setText(new donateController().getDonorCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void currentTime() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("KK:mm:ss a ");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void currentDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd EE");
        lblDate.setText(dateFormat.format(currentDate));
    }

    public void txtSearch(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<BookTM> books = DashBoardController.searchBook(txtSearch.getText());
        ObservableList<BookTM> tableData = FXCollections.observableArrayList();
        for (BookTM book : books) {
            tableData.add(new BookTM(
                    book.getBookId(),
                    book.getBookName(),
                    book.getTitle(),
                    book.getLanguage(),
                    book.getCategoryName(),
                    book.getStatus()
            ));

        }
        tblDashBoard.setItems(tableData);
    }

    public void loadDashBoardTable() throws SQLException, ClassNotFoundException {
        ObservableList<BookTM> book = new DashBoardController().getBookToDashBoard();
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblDashBoard.setItems(book);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/loginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) contextAdmin.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/adminForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) contexDashBoard.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void memberDetailOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/memberDetailForm.fxml"));
        Parent parent = loader.load();
        contexDashBoard.getChildren().clear();
        contexDashBoard.getChildren().add(parent);
        memberDetailController controller = loader.getController();
        controller.btnDelete.setDisable(true);
    }

    public void bookAndCategeroyMaintailOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/bookMaintainForm.fxml"));
        Parent parent = loader.load();
        contexDashBoard.getChildren().clear();
        contexDashBoard.getChildren().add(parent);
        bookMaintainFormController controller = loader.getController();
        controller.btnDelete.setDisable(true);
        controller.btnAddCategory.setDisable(true);
    }

    public void issueBookOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/issueBookForm.fxml");
        Parent load = FXMLLoader.load(resource);
        contexDashBoard.getChildren().clear();
        contexDashBoard.getChildren().add(load);
    }

    public void returnBookOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/returnBookForm.fxml");
        Parent load = FXMLLoader.load(resource);
        contexDashBoard.getChildren().clear();
        contexDashBoard.getChildren().add(load);
    }

    public void IssueHistoryOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/historyForm.fxml");
        Parent load = FXMLLoader.load(resource);
        contexDashBoard.getChildren().clear();
        contexDashBoard.getChildren().add(load);
    }


    public void addDominatorOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/addDominatorForm.fxml"));
        Parent parent = loader.load();
        contexDashBoard.getChildren().clear();
        contexDashBoard.getChildren().add(parent);
        AddDominatorFormController controller = loader.getController();
        controller.btnDelete.setDisable(true);
        controller.btnUpdate.setDisable(true);
    }

}
