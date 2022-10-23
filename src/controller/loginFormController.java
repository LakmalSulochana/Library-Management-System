package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class loginFormController {
    public JFXTextField txtUserId;
    public JFXPasswordField txtPassword;
    public AnchorPane contextLoginPage;

    public void loginButtonOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        User login = new User(txtUserId.getText(), txtPassword.getText());
        String log = new userSaveController().login(login);

        if (log.equals("Admin")) {
            URL resource = getClass().getResource("../view/adminForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) contextLoginPage.getScene().getWindow();
            window.setScene(new Scene(load));
        } else if (log.equals("User")) {
            URL resource = getClass().getResource("../view/employeeForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) contextLoginPage.getScene().getWindow();
            window.setScene(new Scene(load));

        } else {
            new NotificationController().set("Wrong", "Invalid Information", 4, 12);
        }
    }
}
