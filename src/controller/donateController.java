package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Donate;
import view.tm.DominationTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class donateController {
    public boolean saveDominator(Donate dominator) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO donate VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1, dominator.getDonateId());
        stm.setObject(2, dominator.getPersonName());
        stm.setObject(3, dominator.getPersonAddress());
        stm.setObject(4, dominator.getMobile());
        stm.setObject(5, dominator.getNic());
        stm.setObject(6, dominator.getDate());
        stm.setObject(7, dominator.getEmail());


        return stm.executeUpdate() > 0;
    }

    public boolean updateDominator(DominationTM donate) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE donate SET person_name=?,person_address=?, person_mobile=?, person_nic=?,date=?,email=? WHERE donate_id=? ");
        stm.setObject(1, donate.getPersonName());
        stm.setObject(2, donate.getPersonAddress());
        stm.setObject(3, donate.getMobile());
        stm.setObject(4, donate.getNic());
        stm.setObject(5, donate.getDate());
        stm.setObject(6, donate.getEmail());
        stm.setObject(7, donate.getDonateId());


        return stm.executeUpdate() > 0;
    }

    public boolean deleteDominator(String donateId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM donate WHERE donate_id='" + donateId + "'").executeUpdate() > 0;
    }

    public ObservableList<DominationTM> getDominator() throws SQLException, ClassNotFoundException {
        ObservableList<DominationTM> dominator = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM donate";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            dominator.add(new DominationTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            ));
        }
        return dominator;
    }

    public List<String> getAllDonationId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM donate").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }

        return ids;
    }

    public String getDonorCount() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(donate_id) FROM donate").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public String getDonateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM donate ORDER BY donate_id DESC LIMIT 1")
                .executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 10) {
                return "DON-00" + tempId;
            } else if (tempId < 100) {
                return "DON-0" + tempId;
            } else {
                return "DON-" + tempId;
            }
        } else {
            return "DON-001";
        }
    }
}
