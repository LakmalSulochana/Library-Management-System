package controller;

import db.DbConnection;
import model.BarrowDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnController {
    public boolean updateStatus(BarrowDetail barrowDetail) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement("UPDATE barrow_detail SET status=? WHERE barrow_id=? AND book_id=?");
            stm.setObject(1, barrowDetail.getStatus());
            stm.setObject(2, barrowDetail.getBarrowId());
            stm.setObject(3, barrowDetail.getBookId());

            if (stm.executeUpdate() > 0) {
                if (updateBook(barrowDetail.getBookId(), barrowDetail.getStatus())) {
                    System.out.println("save 1");
                    con.commit();
                    return true;
                } else {
                    System.out.println("save 2");
                    con.rollback();
                    return false;
                }
            } else {
                System.out.println("save 3");
                con.rollback();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

    public boolean updateBook(String bookId, String status) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE book SET status ='" + status + "' WHERE  book_id='" + bookId + "'");
        return stm.executeUpdate() > 0;
    }
}
