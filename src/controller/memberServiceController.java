package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Member;
import view.tm.MemberTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class memberServiceController {
    public static List<MemberTM> searchMember(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM member WHERE CONCAT(member_id,nic,email,contact) LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<MemberTM> member = new ArrayList<>();
        while (rs.next()) {
            member.add(new MemberTM(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)
            ));
        }
        return member;
    }

    /*private FileInputStream fis;
    private File file;
    private TableLoadEvent event;
    private FileChooser fileChooser;
    //private File file;
    private Stage stage;
    private Desktop desktop=Desktop.getDesktop();
    private Image image;*/
    public boolean saveMember(Member member) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, member.getDate());
        stm.setObject(2, member.getMemberId());
        stm.setObject(3, member.getName());
        stm.setObject(4, member.getAddress());
        stm.setObject(5, member.getSex());
        stm.setObject(6, member.getAge());
        stm.setObject(7, member.getNic());
        stm.setObject(8, member.getEmail());
        stm.setObject(9, member.getContact());


        return stm.executeUpdate() > 0;
    }

    public boolean deleteMember(String memberId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM member WHERE member_id='" + memberId + "'").executeUpdate() > 0;
    }

    public boolean updateUsers(MemberTM member) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE member SET date=?, name=?, address=?, sex=?, age=?, nic=?, email=?, contact=? WHERE member_id=? ");
        stm.setObject(1, member.getDate());
        stm.setObject(2, member.getMemberName());
        stm.setObject(3, member.getAddress());
        stm.setObject(4, member.getSex());
        stm.setObject(5, member.getAge());
        stm.setObject(6, member.getNic());
        stm.setObject(7, member.getEmail());
        stm.setObject(8, member.getContact());
        stm.setObject(9, member.getMemberId());

        return stm.executeUpdate() > 0;

    }

    public ObservableList<MemberTM> getMemberList() throws SQLException, ClassNotFoundException {
        ObservableList<MemberTM> member = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM member";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            member.add(new MemberTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return member;
    }

    public String getMemberCount() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(member_id) FROM member").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public String getMemberId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM member ORDER BY member_id DESC LIMIT 1")
                .executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(2).split("-")[1]);
            tempId += 1;
            if (tempId < 10) {
                return "M-00" + tempId;
            } else if (tempId < 100) {
                return "M-0" + tempId;
            } else {
                return "M-" + tempId;
            }
        } else {
            return "M-001";
        }
    }
}
