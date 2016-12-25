/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.DaoException;
import dao.WholesaleCustomerDao;

import entity.Wholesalecustomer;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;
import static ui.LoginController.privilage;

/**
 * FXML Controller class
 *
 * @author SahaN
 */
public class WholesalecustomerController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Module Data">
//Color Indication of Input Controls
    private String valid;
    private String invalid;
    private String updated;
    private String initial;

    //Binding with the Form
    private Wholesalecustomer wholesalecustomer;

    //Update Identification
    private Wholesalecustomer oldWholesalecustomer;

    //Table Row, Page Selected
    private int page;
    private int row;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FXML-Data">
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtAddress;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtLand;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearchName;

    @FXML
    private Button btnSearchClear;
    @FXML
    private Pagination pagination;
    @FXML
    private TableView<Wholesalecustomer> tblWholesalecustomer;
    @FXML
    private TableColumn<Wholesalecustomer, String> colName;
    @FXML
    private TableColumn<Wholesalecustomer, String> colShopName;
    @FXML
    private TableColumn<Wholesalecustomer, String> colContact;
    @FXML
    private TableColumn<Wholesalecustomer, String> colAddress;
    @FXML
    private AnchorPane apnMain;
    @FXML
    private TextField txtShopName;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialize-Methods">
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadForm();
        loadTable();
    }

    private void loadForm() {

        initial = Style.initial;
        valid = Style.valid;
        invalid = Style.invalid;
        updated = Style.updated;

        wholesalecustomer = new Wholesalecustomer();
        oldWholesalecustomer = null;

        txtName.setText("");
        txtShopName.setText("");
        txtAddress.setText("");

        txtMobile.setText("");
        txtLand.setText("");

        dissableButtons(false, false, true, true);
        setStyle(initial);

    }

    private void loadTable() {

        txtSearchName.setText("");

        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colShopName.setCellValueFactory(new PropertyValueFactory("shopname"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact1"));

        fillTable(WholesaleCustomerDao.getAll());
        pagination.setCurrentPageIndex(0);

    }

    private void dissableButtons(boolean select, boolean insert, boolean update, boolean delete) {
        btnAdd.setDisable(insert || !privilage.get("Wholesalecustomer_insert"));
        btnUpdate.setDisable(update || !privilage.get("Wholesalecustomer_update"));
        btnDelete.setDisable(delete || !privilage.get("Wholesalecustomer_delete"));

        txtSearchName.setDisable(select || !privilage.get("Wholesalecustomer_select"));

        btnSearchClear.setDisable(select || !privilage.get("Wholesalecustomer_select"));

    }

    private void setStyle(String style) {

        txtName.setStyle(style);
        txtShopName.setStyle(style);

        txtMobile.setStyle(style);
        txtLand.setStyle(style);

        if (!txtAddress.getChildrenUnmodifiable().isEmpty()) {
            ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(style);
        }

    }

    private void fillTable(ObservableList<Wholesalecustomer> wholesalecustomers) {

        if (privilage.get("Wholesalecustomer_select") && wholesalecustomers != null && wholesalecustomers.size() != 0) {

            int rowsCount = 8;
            int pageCount = ((wholesalecustomers.size() - 1) / rowsCount) + 1;
            pagination.setPageCount(pageCount);

            pagination.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    int start = pageIndex * rowsCount;
                    int end = pageIndex == pageCount - 1 ? wholesalecustomers.size() : pageIndex * rowsCount + rowsCount;
                    tblWholesalecustomer.getItems().clear();
                    tblWholesalecustomer.setItems(FXCollections.observableArrayList(wholesalecustomers.subList(start, end)));
                    return tblWholesalecustomer;
                }
            });

        } else {

            pagination.setPageCount(1);
            tblWholesalecustomer.getItems().clear();
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Data Settings">
    @FXML
    private void txtNameKR(KeyEvent event) {
        if (wholesalecustomer.setName(txtName.getText().trim())) {
            if (oldWholesalecustomer != null && !wholesalecustomer.getName().equals(oldWholesalecustomer.getName())) {
                txtName.setStyle(updated);
            } else {
                txtName.setStyle(valid);
            }
        } else {
            txtName.setStyle(invalid);
        }
    }

    @FXML
    private void txtAddressKR(KeyEvent event) {
        if (wholesalecustomer.setAddress(txtAddress.getText().trim())) {
            if (oldWholesalecustomer != null && !wholesalecustomer.getAddress().equals(oldWholesalecustomer.getAddress())) {
                ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(updated);
            } else {
                ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(valid);
            }
        } else {
            ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(invalid);
        }
    }

    @FXML
    private void txtMobileKR(KeyEvent event) {

        if (wholesalecustomer.setContact1(txtMobile.getText().trim())) {
            if (oldWholesalecustomer != null && !wholesalecustomer.getContact1().equals(oldWholesalecustomer.getContact1())) {
                txtMobile.setStyle(updated);
            } else {
                txtMobile.setStyle(valid);
            }
        } else {
            txtMobile.setStyle(invalid);
        }
    }

    @FXML
    private void txtLandKR(KeyEvent event) {

        if (wholesalecustomer.setContact2(txtLand.getText())) {
            if (oldWholesalecustomer != null && oldWholesalecustomer.getContact2() != null && wholesalecustomer.getContact2() != null && oldWholesalecustomer.getContact2().equals(wholesalecustomer.getContact2())) {
                txtLand.setStyle(valid);
            } else if (oldWholesalecustomer != null && oldWholesalecustomer.getContact2() != wholesalecustomer.getContact2()) {
                txtLand.setStyle(updated);
            } else {
                txtLand.setStyle(valid);
            }
        } else {
            txtLand.setStyle(invalid);
        }
    }

    @FXML
    private void txtShopNameKR(KeyEvent event) {
        if (wholesalecustomer.setShopname(txtName.getText().trim())) {
            if (oldWholesalecustomer != null && !wholesalecustomer.getShopname().equals(oldWholesalecustomer.getShopname())) {
                txtShopName.setStyle(updated);
            } else {
                txtShopName.setStyle(valid);
            }
        } else {
            txtShopName.setStyle(invalid);
        }
    }

    @FXML
    private void apnMainKP(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            btnAdd.fire();
        }

    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Form-Operation">

    private String getErrors() {
        String errors = "";

        if (wholesalecustomer.getName() == null) {
            errors = errors + "Name \t\tis Invalid\n";
        }
        if (wholesalecustomer.getShopname() == null) {
            errors = errors + "Shop Name \t\tis Invalid\n";
        }

        if (wholesalecustomer.getAddress() == null) {
            errors = errors + "Address \t\tis Invalid\n";
        }

        if (wholesalecustomer.getContact1() == null) {
            errors = errors + "Contact No 1. \tis Invalid\n";
        }

        if (txtLand.getText() != null && !wholesalecustomer.setContact2(txtLand.getText().trim())) {
            errors = errors + "Contact No 2. \t\tis Invalid\n";
        }

        return errors;

    }

    private String getUpdates() {

        String updates = "";

        if (oldWholesalecustomer != null) {

            if (wholesalecustomer.getName() != null && !wholesalecustomer.getName().equals(oldWholesalecustomer.getName())) {
                updates = updates + oldWholesalecustomer.getName() + " chnaged to " + wholesalecustomer.getName() + "\n";
            }

            if (!wholesalecustomer.getAddress().equals(oldWholesalecustomer.getAddress())) {
                updates = updates + oldWholesalecustomer.getAddress() + " chnaged to " + wholesalecustomer.getAddress() + "\n";
            }

            if (!(oldWholesalecustomer.getContact2() != null
                    && wholesalecustomer.getContact2() != null
                    && oldWholesalecustomer.getContact2().equals(wholesalecustomer.getContact2()))) {
                if (oldWholesalecustomer.getContact2() != wholesalecustomer.getContact2()) {
                    updates = updates + oldWholesalecustomer.getContact2()
                            + " chnaged to " + wholesalecustomer.getContact2() + "\n";
                }
            }

            if (!wholesalecustomer.getContact1().equals(oldWholesalecustomer.getContact1())) {
                updates = updates + oldWholesalecustomer.getContact1() + " chnaged to " + wholesalecustomer.getContact1() + "\n";
            }

            if (!wholesalecustomer.getShopname().equals(oldWholesalecustomer.getShopname())) {
                updates = updates + oldWholesalecustomer.getShopname() + " chnaged to " + wholesalecustomer.getShopname() + "\n";
            }

        }

        return updates;
    }

    private void fillForm() {
        if (tblWholesalecustomer.getSelectionModel().getSelectedItem() != null) {
            dissableButtons(false, true, false, false);
            setStyle(valid);

            oldWholesalecustomer = WholesaleCustomerDao.getById(tblWholesalecustomer.getSelectionModel().getSelectedItem().getId());
            wholesalecustomer = WholesaleCustomerDao.getById(tblWholesalecustomer.getSelectionModel().getSelectedItem().getId());

            txtName.setText(wholesalecustomer.getName());
            txtShopName.setText(wholesalecustomer.getShopname());
            txtAddress.setText(wholesalecustomer.getAddress());

            txtMobile.setText(wholesalecustomer.getContact1());
            txtLand.setText(wholesalecustomer.getContact2());

            page = pagination.getCurrentPageIndex();
            row = tblWholesalecustomer.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    private void btnAddAP(ActionEvent event) {

        String errors = getErrors();

        if (errors.isEmpty()) {

            String confermation = "Are you sure you need to add this Wholesalecustomer with following details\n "
                    + "\nName :         \t\t" + wholesalecustomer.getName()
                    + "\nShop Name :         \t\t" + wholesalecustomer.getShopname()
                    + "\nAddress :       \t\t" + wholesalecustomer.getAddress()
                    + "\nContact No 1 :    \t\t" + wholesalecustomer.getContact1()
                    + "\nContact No 2 :         \t\t" + wholesalecustomer.getContact2();

            Action action = Dialogs.create().title("Confirm Message").masthead("Wholesalecustomer Add").message(confermation).showConfirm();
            if (action.toString().equals("DialogAction.YES")) {

                try {
                    WholesaleCustomerDao.add(wholesalecustomer);
                    Notifications.create().title("Successs").text("Wholesalecustomer " + wholesalecustomer.getName() + " saved.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                    loadForm();
                    loadTable();
                    pagination.setCurrentPageIndex(pagination.getPageCount() - 1);
                    tblWholesalecustomer.getSelectionModel().select(tblWholesalecustomer.getItems().size() - 1);

                } catch (DaoException ex) {
                    Notifications.create().title("Un-Successs").text("Wholesalecustomer " + wholesalecustomer.getName() + " not saved. \n Try Again.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();

                }

            }

        } else {

            Dialogs.create().title("Error Message").masthead("Wholesalecustomer Detail Error").message(errors).showError();

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

                Action action = Dialogs.create().title("Confirm Message").masthead("Wholesalecustomer Update").message(updates).showConfirm();
                if (action.toString().equals("DialogAction.YES")) {

                    WholesaleCustomerDao.update(wholesalecustomer);
                    Notifications.create().title("Successs").text("Wholesalecustomer " + wholesalecustomer.getName() + " updated.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                    loadForm();
                    loadTable();

                    pagination.setCurrentPageIndex(page);
                    tblWholesalecustomer.getSelectionModel().select(row);

                }
            } else {
                Dialogs.create().title("Information Message").masthead("Wholesalecustomer Update").message("Nothing Updated").showWarning();
            }
        } else {

            Dialogs.create().title("Error Message").masthead("Wholesalecustomer Update").message(getErrors()).showError();

        }

    }

    @FXML
    private void btnDeleteAP(ActionEvent event) {
        if (getUpdates().isEmpty() && getErrors().isEmpty()) {

            Action action = Dialogs.create().title("Wholesalecustomer Delete").masthead(wholesalecustomer.getName() + " Delete ?").message("Do you need to delete this wholesalecustomer").showConfirm();

            if (action.toString().equals("DialogAction.YES")) {

                WholesaleCustomerDao.delete(wholesalecustomer);
                Notifications.create().title("Successs").text("Wholesalecustomer " + wholesalecustomer.getName() + " deleted.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                loadForm();
                loadTable();
                pagination.setCurrentPageIndex(page);
                tblWholesalecustomer.getSelectionModel().select(row);

            }
        } else {
            Dialogs.create().title("Wholesalecustomer Delete").masthead(oldWholesalecustomer.getName() + " Delete ?").message("You can't delete\nSome of the fields are updated").showInformation();

        }
    }

    @FXML
    private void tblWholesalecustomerMC(MouseEvent event) {
        fillForm();
    }

    @FXML
    private void tblWholesalecustomerKR(KeyEvent event) {
        fillForm();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Search-Methods">
    private void updateTable() {

        String name = txtSearchName.getText().trim();
        boolean sname = !name.isEmpty();

        if (sname) {

            fillTable(WholesaleCustomerDao.getAllByName(name));

        } else {

            fillTable(WholesaleCustomerDao.getAll());
        }

    }

    @FXML
    private void txtSearchNameKR(KeyEvent event) {
        updateTable();
    }

   

    @FXML
    private void btnSearchClearAP(ActionEvent event) {
        Action action = Dialogs.create().title("Confirm Message").masthead("Clear Form").message("Are you sure you need to clear the Table Search and the Form?").showConfirm();

        if (action.toString().equals("DialogAction.YES")) {
            loadTable();
        }
    }
//</editor-fold>

}
