package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.BookTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashBoardController {
    public static java.util.List<BookTM> searchBook(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM book WHERE CONCAT(book_name,book_id,category_name,book_language) LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<BookTM> book = new ArrayList<>();

        while (rs.next()) {
            book.add(new BookTM(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(7),
                    rs.getString(5),
                    rs.getString(10)
            ));
        }
        return book;
    }

    public ObservableList<BookTM> getBookToDashBoard() throws SQLException, ClassNotFoundException {
        ObservableList<BookTM> list = FXCollections.observableArrayList();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT book_id,book_name,title,book_language,category_name,status FROM book");
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            list.add(new BookTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));

        }
        return list;
    }

    public String getBookCount() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(book_id) FROM book WHERE status='" + "Available" + "'").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }
}
