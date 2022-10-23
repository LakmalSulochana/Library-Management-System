package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Category;
import view.tm.CategoryTM;

import java.sql.SQLException;

public class AddCategoryFormController {
    public JFXTextField txtCateId;
    public JFXTextField txtCateName;
    public TableView<CategoryTM> tblCategory;
    public TableColumn colCategoryId;
    public TableColumn colCategoryName;

    public void initialize() throws SQLException, ClassNotFoundException {
        dropCategoryToTable();
    }

    public void dropCategoryToTable() throws SQLException, ClassNotFoundException {
        ObservableList<CategoryTM> category = new categoryController().getCategoryList();
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("cateName"));
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("cateId"));
        tblCategory.setItems(category);
    }

    public void tableSelectOnAction(MouseEvent mouseEvent) {
        CategoryTM category = null;

        category = tblCategory.getSelectionModel().getSelectedItem();
        txtCateName.setText(category.getCateName());
        txtCateId.setText(category.getCateId());

    }

    public void addCateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Category category = new Category(
                txtCateName.getText(),
                txtCateId.getText()

        );
        if (new categoryController().saveCategory(category)) {
            new Alert(Alert.AlertType.CONFIRMATION, "DONE").show();
            dropCategoryToTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "ENTER VALID INFORMATION").show();
        }
    }

    public void deleteCateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CategoryTM category = tblCategory.getSelectionModel().getSelectedItem();
        String categoryName = category.getCateId();

        if (new categoryController().deleteCategory(categoryName)) {
            new Alert(Alert.AlertType.INFORMATION, "Delete Complete").show();
            dropCategoryToTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearField();
    }

    public void updateCateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CategoryTM category = new CategoryTM(
                txtCateName.getText(),
                txtCateId.getText()

        );
        if (new categoryController().updateCategory(category)) {
            new Alert(Alert.AlertType.CONFIRMATION, "DONE").show();
            dropCategoryToTable();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void clearField() {
        txtCateId.clear();
        txtCateName.clear();
        txtCateId.requestFocus();
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearField();
    }

}
