package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.BarrowTM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryController {
    public ObservableList<BarrowTM> getBarrowList() throws SQLException, ClassNotFoundException {
        ObservableList<BarrowTM> barrow = FXCollections.observableArrayList();
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM barrow_Detail").executeQuery();
        while (rst.next()) {
            barrow.add(new BarrowTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        return barrow;
    }
}
