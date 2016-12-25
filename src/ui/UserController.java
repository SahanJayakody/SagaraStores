/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.DaoException;

import dao.RoleDao;
import dao.UserDao;

import entity.Role;
import entity.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;
import static ui.LoginController.privilage;
import static ui.LoginController.user;

/**
 * FXML Controller class
 *
 * @author SahaN
 */
public class UserController implements Initializable {

//<editor-fold defaultstate="collapsed" desc="FXML-Data">
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pswPassword;
    @FXML
    private PasswordField pswRetypePassword;

    @FXML
    private ListView<Role> lstLeft;
    @FXML
    private ListView<Role> lstRight;
    @FXML
    private Button btnRightAll;
    @FXML
    private Button btnRight;
    @FXML
    private Button btnLeft;
    @FXML
    private Button btnLeftAll;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<User> tblUser;
    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private Button btnSearchClear;
    @FXML
    private ComboBox<Role> cmbSearchRole;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Module-Data">
    //Color Indication of Input Controls
    private String valid;
    private String invalid;
    private String updated;
    private String initial;

    //Binding with the Form
    private User user;

    //Update Identification
    private User oldUser;

    //Table Row, Page Selected
    private int page;
    private int row;

//</editor-fold>   
//<editor-fold defaultstate="collapsed" desc="Initialize-Methods">
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadForm();
        loadTable();
    }

    private void loadForm() {

        initial = Style.initial;
        valid = Style.valid;
        invalid = Style.invalid;
        updated = Style.updated;

        user = new User();
        oldUser = null;

//       
        txtUsername.setText("");
        pswPassword.setText("");
        pswRetypePassword.setText("");

        lstLeft.setItems(RoleDao.getAll());
        lstRight.getItems().clear();

        dissableButtons(false, false, true, true);
        validateList();
        setStyle(initial);

    }

    private void loadTable() {

        cmbSearchRole.setItems(RoleDao.getAll());
        cmbSearchRole.getSelectionModel().select(-1);

        colUsername.setCellValueFactory(new PropertyValueFactory("name"));

        fillTable(UserDao.getAll());
        pagination.setCurrentPageIndex(0);

    }

    private void dissableButtons(boolean select, boolean insert, boolean update, boolean delete) {
        btnAdd.setDisable(insert || !privilage.get("User_insert"));
        btnUpdate.setDisable(update || !privilage.get("User_update"));
        btnDelete.setDisable(delete || !privilage.get("User_delete"));

        cmbSearchRole.setDisable(select || !privilage.get("User_select"));

        btnSearchClear.setDisable(select || !privilage.get("User_select"));

        btnLeft.setDisable(true);
        btnLeftAll.setDisable(true);
    }

    private void setStyle(String style) {

        lstLeft.setStyle(style);
        lstRight.setStyle(style);

        txtUsername.setStyle(style);
        pswPassword.setStyle(style);
        pswRetypePassword.setStyle(style);

    }

    private void fillTable(ObservableList<User> users) {

        if (privilage.get("User_select") && users != null && users.size() != 0) {

            int rowsCount = 4;
            int pageCount = ((users.size() - 1) / rowsCount) + 1;
            pagination.setPageCount(pageCount);

            pagination.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    int start = pageIndex * rowsCount;
                    int end = pageIndex == pageCount - 1 ? users.size() : pageIndex * rowsCount + rowsCount;
                    tblUser.getItems().clear();
                    tblUser.setItems(FXCollections.observableArrayList(users.subList(start, end)));
                    return tblUser;
                }
            });

        } else {

            pagination.setPageCount(1);
            tblUser.getItems().clear();
        }

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Data-Setting">
    private void validateList() {

        if (user.setRoleList(lstRight.getItems())) {
            lstRight.setStyle(valid);
        } else {
            lstRight.setStyle(invalid);
        }

        if (oldUser != null && user.getRoleList() != null && !(user.getRoleList().containsAll(oldUser.getRoleList()) && oldUser.getRoleList().containsAll(user.getRoleList()))) {
            lstRight.setStyle(updated);
        }

        if (lstLeft.getItems().isEmpty()) {
            btnRight.setDisable(true);
            btnRightAll.setDisable(true);
            btnLeft.setDisable(false);
            btnLeftAll.setDisable(false);
        } else if (lstRight.getItems().isEmpty()) {
            btnRight.setDisable(false);
            btnRightAll.setDisable(false);
            btnLeft.setDisable(true);
            btnLeftAll.setDisable(true);
        } else {
            btnRight.setDisable(false);
            btnRightAll.setDisable(false);
            btnLeft.setDisable(false);
            btnLeftAll.setDisable(false);

        }

    }

    @FXML
    private void txtUsernameKR(KeyEvent event) {
        if (user.setName(txtUsername.getText().trim()) && UserDao.getByName(txtUsername.getText().trim()) == null) {
            if (oldUser != null && !user.getName().equals(oldUser.getName())) {
                txtUsername.setStyle(updated);
            } else {
                txtUsername.setStyle(valid);
            }
        } else if (oldUser != null && user.getName() != null && user.getName().equals(oldUser.getName())) {
            txtUsername.setStyle(valid);
        } else {
            txtUsername.setStyle(invalid);
        }
    }

    @FXML
    private void pswPasswordKR(KeyEvent event) {
        pswRetypePassword.setText("");
        pswRetypePassword.setStyle(initial);
        if (user.setPassword(pswPassword.getText().trim())) {
            if (oldUser != null && !user.getPassword().equals(oldUser.getPassword())) {
                pswPassword.setStyle(updated);
            } else {
                pswPassword.setStyle(valid);
            }
        } else {
            pswPassword.setStyle(invalid);
        }
    }

    @FXML
    private void pswRetypePasswordKR(KeyEvent event) {
        if (pswPassword.getText().trim().equals(pswRetypePassword.getText().trim())) {
            pswRetypePassword.setStyle(valid);

        } else {
            pswRetypePassword.setStyle(invalid);
        }

    }

    @FXML
    private void btnRightAllAP(ActionEvent event) {
        lstRight.setItems(RoleDao.getAll());
        lstLeft.getItems().clear();
        validateList();
    }

    @FXML
    private void btnRightAP(ActionEvent event) {
        lstRight.getItems().addAll(lstLeft.getSelectionModel().getSelectedItems());
        lstLeft.getItems().removeAll(lstLeft.getSelectionModel().getSelectedItems());
        validateList();
    }

    @FXML
    private void btnLeftAP(ActionEvent event) {
        lstLeft.getItems().addAll(lstRight.getSelectionModel().getSelectedItems());
        lstRight.getItems().removeAll(lstRight.getSelectionModel().getSelectedItems());
        validateList();

    }

    @FXML
    private void btnLeftAllAP(ActionEvent event) {
        lstLeft.setItems(RoleDao.getAll());
        lstRight.getItems().clear();
        validateList();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Form-Operation">
    private String getErrors() {
        String errors = "";
        try {

            if (user.getName() == null) {
                errors = errors + "Username \t\tis Invalid\n";
            }
            if (user.getPassword() == null) {
                errors = errors + "Password \t\tis Invalid\n";
            }

            if (!pswPassword.getText().equals(pswRetypePassword.getText())) {
                errors = errors + "Confirm Password \t\tis Mismatch\n";
            }
            if (user.getRoleList() == null) {
                errors = errors + "Roles            \t\tare Not Selected\n";
            }

        } catch (Exception e) {
            System.out.println("\n\n----------------------Error Checking Error---------------------------------------------------\n");
            System.out.println(e.getClass());
            System.out.println("\n-------------------------------------------------------------------------\n\n");
            JOptionPane.showMessageDialog(null, e.getClass(), "Error checking Error", JOptionPane.ERROR_MESSAGE);
        }
        return errors;

    }

    private String getUpdates() {

        String updates = "";

        try {

            if (oldUser != null) {

                if (user.getName() != null && !user.getName().equals(oldUser.getName())) {
                    updates = updates + oldUser.getName() + " chnaged to " + user.getName() + "\n";
                }

                if (user.getPassword() != null && !user.getPassword().equals(oldUser.getPassword())) {
                    updates = updates + " Password chnaged  ";
                }

                if (user.getRoleList() != null && !user.getRoleList().equals(oldUser.getRoleList())) {
                    updates = updates + oldUser.getRoleList().toString() + " chnaged to " + user.getRoleList().toString() + "\n";
                }

            }
        } catch (Exception e) {
            System.out.println("\n\n----------------------Update Checking Error---------------------------------------------------\n");
            System.out.println(e.getClass());
            System.out.println("\n-------------------------------------------------------------------------\n\n");
            JOptionPane.showMessageDialog(null, e.getClass(), "Update checking Error", JOptionPane.ERROR_MESSAGE);
        }

        return updates;
    }

    private void fillForm() {
        if (tblUser.getSelectionModel().getSelectedItem() != null) {
            dissableButtons(false, true, false, false);
            setStyle(valid);

            oldUser = UserDao.getById(tblUser.getSelectionModel().getSelectedItem().getId());
            user = UserDao.getById(tblUser.getSelectionModel().getSelectedItem().getId());

            txtUsername.setText(user.getName());
            pswPassword.setText("********");
            pswRetypePassword.setText("********");

            lstRight.setItems(FXCollections.observableArrayList(user.getRoleList()));
            lstLeft.setItems(RoleDao.getAll());
            lstLeft.getItems().removeAll(FXCollections.observableArrayList(user.getRoleList()));

            validateList();

            page = pagination.getCurrentPageIndex();
            row = tblUser.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    private void btnAddAP(ActionEvent event) {

        String errors = getErrors();

        if (errors.isEmpty()) {

            String confermation = "Are you sure you need to add this Employee with following details\n "
                    + "\nUsername :         \t\t" + user.getName()
                    + "\nRoles :      \t\t" + user.getRoleList().toString();

            Action action = Dialogs.create().title("Confirm Message").masthead("Employee Add").message(confermation).showConfirm();
            if (action.toString().equals("DialogAction.YES")) {

                try {

                    UserDao.add(user);
                    Notifications.create().title("Successs").text("User " + user.getName() + " saved.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                    loadForm();
                    fillTable(UserDao.getAll());
                    pagination.setCurrentPageIndex(pagination.getPageCount() - 1);
                    tblUser.getSelectionModel().select(tblUser.getItems().size() - 1);

                } catch (DaoException ex) {
                    Notifications.create().title("Un-Successs").text("User " + user.getName() + " not saved. \n Try Again.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();

                }

            }

        } else {

            Dialogs.create().title("Error Message").masthead("User Detail Error").message(errors).showError();

        }
    }

    @FXML
    private void btnClearAP(ActionEvent event) {
        Action action = Dialogs.create().styleClass("dlg").title("Confirm Message").masthead("Clear Form").message("Are you sure you need to clear the Form?").showConfirm();

        if (action.toString().equals("DialogAction.YES")) {

            loadForm();

        }
    }

    @FXML
    private void btnUpdateAP(ActionEvent event) {

        String errors = getErrors();

        if (errors.isEmpty()) {

            String updates = getUpdates();

            if (!updates.isEmpty()) {

                Action action = Dialogs.create().title("Confirm Message").masthead("Employee Update").message(updates).showConfirm();
                if (action.toString().equals("DialogAction.YES")) {

                    UserDao.update(user);
                    Notifications.create().title("Successs").text("Employee " + user.getName() + " updated.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();

                    loadTable();
                    loadForm();
                    pagination.setCurrentPageIndex(page);
                    tblUser.getSelectionModel().select(row);

                }
            } else {
                Dialogs.create().title("Information Message").masthead("User Update").message("Nothing Updated").showWarning();
            }
        } else {

            Dialogs.create().title("Error Message").masthead("User Update").message(getErrors()).showError();

        }
    }

    @FXML
    private void btnDeleteAP(ActionEvent event) {

        if (getUpdates().isEmpty() && getErrors().isEmpty()) {

            Action action = Dialogs.create().title("User Delete").masthead(user.getName() + " Delete ?").message("Do you need to delete this user").showConfirm();

            if (action.toString().equals("DialogAction.YES")) {

                UserDao.delete(user);
                Notifications.create().title("Successs").text("Employee " + user.getName() + " deleted.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                loadForm();
                loadTable();
                pagination.setCurrentPageIndex(page);
                tblUser.getSelectionModel().select(row);

            }
        } else {
            Dialogs.create().title("User Delete").masthead(oldUser.getName() + " Delete ?").message("You can't delete\nSome of the fields are updated").showInformation();

        }

    }

    @FXML
    private void tblUserMC(MouseEvent event) {
        fillForm();
    }

    @FXML
    private void tblUserKR(KeyEvent event) {
        fillForm();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Search-Methods">
    private void updateTable() {

        Role role = cmbSearchRole.getSelectionModel().getSelectedItem();
        boolean srole = cmbSearchRole.getSelectionModel().getSelectedIndex() != -1;

        if (srole) {

            fillTable(UserDao.getAllByRole(role));

        } else {
            fillTable(UserDao.getAll());
        }

    }

    @FXML
    private void btnSearchClearAP(ActionEvent event) {

        Action action = Dialogs.create().title("Confirm Message").masthead("Clear Form").message("Are you sure you need to clear the Table Search and the Form?").showConfirm();

        if (action.toString().equals("DialogAction.YES")) {
            loadTable();
        }
    }

    @FXML
    private void cmbSearchRoleAP(ActionEvent event) {

        updateTable();
    }

    private void txtSearchEmpNameKR(KeyEvent event) {
        updateTable();
    }
//</editor-fold>

}
