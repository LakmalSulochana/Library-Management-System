package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;
import view.tm.CategoryTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class categoryController {
    public boolean saveCategory(Category category) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO category VALUES(?,?)";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setObject(1, category.getCategoryName());
        pstm.setObject(2, category.getCategoryId());


        return pstm.executeUpdate() > 0;
    }

    public boolean updateCategory(CategoryTM category) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE category SET category_id=? WHERE category_name=?");


        pstm.setObject(1, category.getCateId());
        pstm.setObject(2, category.getCateName());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCategory(String cateName) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM category WHERE category_id='" + cateName + "'").executeUpdate() > 0;
    }

    public ObservableList<CategoryTM> getCategoryList() throws SQLException, ClassNotFoundException {
        ObservableList<CategoryTM> category = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM category";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();

        while (rst.next()) {
            category.add(new CategoryTM(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        return category;
    }

    public List<String> getAllCategoryName() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM category").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
}
