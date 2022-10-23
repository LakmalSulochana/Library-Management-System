package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Barrow;
import model.BarrowDetail;
import view.tm.BarrowTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarrowController {
    public ObservableList<BarrowTM> getBarrowList() throws SQLException, ClassNotFoundException {
        ObservableList<BarrowTM> barrowHistory = FXCollections.observableArrayList();
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM barrow_Detail WHERE status='" + "Unavailable" + "'").executeQuery();
        while (rst.next()) {
            barrowHistory.add(new BarrowTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return barrowHistory;
    }

    public String getBarrowId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM barrow ORDER BY barrow_id DESC LIMIT 1")
                .executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 10) {
                return "B-00" + tempId;
            } else if (tempId < 100) {
                return "B-0" + tempId;
            } else {
                return "B-" + tempId;
            }
        } else {
            return "B-000";
        }
    }

    public boolean saveBarrow(ArrayList<Barrow> barrow) {
        for (Barrow temp : barrow) {

            Connection con = null;
            try {
                con = DbConnection.getInstance().getConnection();
                con.setAutoCommit(false);
                PreparedStatement stm = con.prepareStatement("INSERT INTO barrow VALUES (?,?,?,?,?,?,?,?)");
                //System.out.println(barrow);

                stm.setObject(1, temp.getBarrowId());
                stm.setObject(2, temp.getMemberId());
                stm.setObject(3, temp.getBarrowDate());
                stm.setObject(4, temp.getReturnDate());
                stm.setObject(5, temp.getMemberName());
                stm.setObject(6, temp.getMemberNic());
                stm.setObject(7, temp.getContact());
                stm.setObject(8, temp.getEmail());
                if (stm.executeUpdate() > 0) {
                    if (saveBarrowDetail(temp.getBarrow())) {
                        con.commit();
                        return true;
                    } else {

                        con.rollback();
                        return false;
                    }
                } else {

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

        }
        return true;
    }

    public boolean saveBarrowDetail(List<BarrowDetail> barrow) throws SQLException, ClassNotFoundException {
        for (BarrowDetail temp : barrow
        ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO barrow_Detail VALUES(?,?,?,?,?,?)");
            stm.setObject(1, temp.getBarrowId());
            stm.setObject(2, temp.getBookId());
            stm.setObject(3, temp.getMemberId());
            stm.setObject(4, temp.getTotalDay());
            stm.setObject(5, temp.getBookName());
            stm.setObject(6, temp.getStatus());
            System.out.println(temp.getStatus());
            if (stm.executeUpdate() > 0) {
                if (updateStatus(temp.getBookId(), "Unavailable")) {

                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateStatus(String bookId, String status) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE book SET status ='" + status + "' WHERE  book_id='" + bookId + "'");
        return stm.executeUpdate() > 0;


    }

    public String getBarrowCount() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(book_id) FROM book WHERE status='" + "Unavailable" + "'").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

}
