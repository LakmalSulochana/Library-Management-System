package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import view.tm.UserTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userSaveController {
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO user VALUES (?,?,?,?,?,md5(?),?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1, user.getUserId());
        stm.setObject(2, user.getUserName());
        stm.setObject(3, user.getAddress());
        stm.setObject(4, user.getMobile());
        stm.setObject(5, user.getRole());
        stm.setObject(6, user.getPassword());
        stm.setObject(7, user.getNic());

        return stm.executeUpdate() > 0;


    }

    public boolean deleteUsers(String userId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM user WHERE user_id='" + userId + "'").executeUpdate() > 0;
    }

    public String login(User log) throws SQLException, ClassNotFoundException {
        String userId = log.getUserId();
        String password = log.getPassword();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  user WHERE  user_id=? AND user_password=md5(?) ");
        stm.setObject(1, userId);
        stm.setObject(2, password);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            String userRole = rst.getString(5);
            return userRole;
        } else {
            return "";
        }

    }

    public boolean updateUsers(UserTM user) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE user SET user_name=?, user_address=?,mobile=?, user_role=?,user_password=?,nic=? WHERE user_id=? ");

        stm.setObject(1, user.getUserName());
        stm.setObject(2, user.getAddress());
        stm.setObject(3, user.getTelePhoneNumber());
        stm.setObject(4, user.getRole());
        stm.setObject(5, user.getPassword());
        stm.setObject(6, user.getNic());
        stm.setObject(7, user.getUserId());
        return stm.executeUpdate() > 0;
    }

    public ObservableList<UserTM> getUserList() throws SQLException, ClassNotFoundException {
        ObservableList<UserTM> users = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM user";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            users.add(new UserTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return users;
    }

}
