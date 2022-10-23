package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.MemberTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class memberDetailController implements TableLoadEvent {
    public TableView<MemberTM> tblMemberDetail;
    public TableColumn colDate;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colContact;
    public TableColumn colAge;
    public TableColumn colEmail;
    public TableColumn colSex;
    public JFXTextField txtMemberId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtTeleNumber;
    public JFXTextField txtEmail;
    public JFXTextField txtAge;
    public JFXComboBox<String> cmbSex;
    public JFXTextField txtDate;
    public ImageView imageMember;
    public AnchorPane contextMemberDetail;
    public TableColumn colImage;
    public JFXButton btnDelete;
    public JFXButton btnIssueCard;
    public JFXTextField txtSearch;
    String sex;

    public void initialize() throws SQLException, ClassNotFoundException {
        dropMemberToTable();
        cmbSex.getItems().addAll(
                "male", "female");
        cmbSex.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            sex = newValue;
        });
    }

    public void dropMemberToTable() throws SQLException, ClassNotFoundException {
        ObservableList<MemberTM> member = new memberServiceController().getMemberList();
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblMemberDetail.setItems(member);
    }

    public void deleteMemberOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        MemberTM member = tblMemberDetail.getSelectionModel().getSelectedItem();
        String memberName = member.getMemberId();
        if (new memberServiceController().deleteMember(memberName)) {
            new Alert(Alert.AlertType.INFORMATION, "Delete Complete").show();
            dropMemberToTable();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        fieldClear();
    }

    public void updateMemberDetailOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        MemberTM member = new MemberTM(
                txtDate.getText(),
                txtMemberId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                sex,
                txtAge.getText(),
                txtNIC.getText(),
                txtEmail.getText(),
                txtTeleNumber.getText()


        );
        if (new memberServiceController().updateUsers(member)) {
            new Alert(Alert.AlertType.CONFIRMATION, "DONE").show();
            dropMemberToTable();
            fieldClear();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void studentRegisterFormOnAction(ActionEvent actionEvent) throws IOException {
        //newwwwwwwwww
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/memberRegisterForm.fxml"));
        Parent parent = loader.load();
        MemberRegisterFormController controller = loader.getController();
        controller.setEvent(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();

    }

    @Override
    public void loadData() throws SQLException, ClassNotFoundException {
        tblMemberDetail.setItems(new memberServiceController().getMemberList());
    }

    public void tableClickOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        MemberTM member = null;

        member = tblMemberDetail.getSelectionModel().getSelectedItem();
        txtMemberId.setText(member.getMemberId());
        txtName.setText(member.getMemberName());
        txtAddress.setText(member.getAddress());
        txtNIC.setText(member.getNic());
        txtTeleNumber.setText(member.getContact());
        txtAge.setText(member.getAge());
        txtEmail.setText(member.getEmail());
        cmbSex.setValue(member.getSex());
        txtDate.setText(member.getDate());
        //showMemberImage(member.getMemberId());
    }

    public void fieldClear() {
        txtDate.clear();
        txtMemberId.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtTeleNumber.clear();
        txtAge.clear();
        txtEmail.clear();
    }

    public void clearFeildOnAction(ActionEvent actionEvent) {
        fieldClear();
    }


    public void searchMemberOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<MemberTM> member = memberServiceController.searchMember(txtSearch.getText());
        ObservableList<MemberTM> tableData = FXCollections.observableArrayList();
        for (MemberTM member1 : member) {
            tableData.add(new MemberTM(
                    member1.getDate(),
                    member1.getMemberId(),
                    member1.getMemberName(),
                    member1.getAddress(),
                    member1.getSex(),
                    member1.getAge(),
                    member1.getNic(),
                    member1.getEmail(),
                    member1.getContact()
            ));

        }
        tblMemberDetail.setItems(tableData);
    }
}
