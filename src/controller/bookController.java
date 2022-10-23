package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import view.tm.BookTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bookController {
    public boolean saveBook(Book book) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?,?)");
        pstm.setObject(1, book.getBookId());
        pstm.setObject(2, book.getBookName());
        pstm.setObject(3, book.getTitle());
        pstm.setObject(4, book.getAuthorName());
        pstm.setObject(5, book.getLanguage());
        pstm.setObject(6, book.getDate());
        pstm.setObject(7, book.getCategoryName());
        pstm.setObject(8, book.getDonateId());
        pstm.setObject(9, book.getAddType());
        pstm.setObject(10, book.getStatus());
        return pstm.executeUpdate() > 0;

    }


    public boolean deleteBook(String bookId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM book WHERE book_id='" + bookId + "'").executeUpdate() > 0;

    }

    public boolean updateBook(BookTM book) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE book SET book_name=?,  title=?, author_name=?, book_language=?, relesed_date=?, category_name=?, donate_id=?, add_type=?,status=? WHERE  book_id=? ");
        stm.setObject(1, book.getBookName());
        stm.setObject(2, book.getTitle());
        stm.setObject(3, book.getAuthorName());
        stm.setObject(4, book.getLanguage());
        stm.setObject(5, book.getReleasedDate());
        stm.setObject(6, book.getCategoryName());
        stm.setObject(7, book.getDonateId());
        stm.setObject(8, book.getAddType());
        stm.setObject(9, book.getStatus());
        stm.setObject(10, book.getBookId());

        return stm.executeUpdate() > 0;
    }

    public ObservableList<BookTM> getBookList() throws SQLException, ClassNotFoundException {
        ObservableList<BookTM> book = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM book";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            book.add(new BookTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }
        return book;
    }
}
