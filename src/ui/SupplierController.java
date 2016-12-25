/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.DaoException;
import dao.SupplierDao;
import dao.WholesaleCustomerDao;
import entity.Supplier;
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
public class SupplierController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Module Data">
//Color Indication of Input Controls
    private String valid;
    private String invalid;
    private String updated;
    private String initial;

    //Binding with the Form
    private Supplier supplier;

    //Update Identification
    private Supplier oldSupplier;

    //Table Row, Page Selected
    private int page;
    private int row;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FXML-Data">
    @FXML
    private AnchorPane apnMain;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtShopName;
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
    private TableView<Supplier> tblWholesalecustomer;
    @FXML
    private TableColumn<Supplier, String> colName;
    @FXML
    private TableColumn<Supplier, String> colShopName;
    @FXML
    private TableColumn<Supplier, String> colContact;
    @FXML
    private TableColumn<Supplier, String> colAddress;
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

        supplier = new Supplier();
        oldSupplier = null;

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

        fillTable(SupplierDao.getAll());
        pagination.setCurrentPageIndex(0);

    }

    private void dissableButtons(boolean select, boolean insert, boolean update, boolean delete) {
        btnAdd.setDisable(insert || !privilage.get("Supplier_insert"));
        btnUpdate.setDisable(update || !privilage.get("Supplier_update"));
        btnDelete.setDisable(delete || !privilage.get("Supplier_delete"));

        txtSearchName.setDisable(select || !privilage.get("Supplier_select"));

        btnSearchClear.setDisable(select || !privilage.get("Supplier_select"));

    }

    private void setStyle(String style) {

        txtName.setStyle(style);
        txtShopName.setStyle(style);

        txtMobile.setStyle(style);
        txtLand.setStyle(style);
        txtAddress.setStyle(style);

//        if (!txtAddress.getChildrenUnmodifiable().isEmpty()) {
//            ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(style);
//        }
    }

    private void fillTable(ObservableList<Supplier> suppliers) {

        if (privilage.get("Supplier_select") && suppliers != null && suppliers.size() != 0) {

            int rowsCount = 8;
            int pageCount = ((suppliers.size() - 1) / rowsCount) + 1;
            pagination.setPageCount(pageCount);

            pagination.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    int start = pageIndex * rowsCount;
                    int end = pageIndex == pageCount - 1 ? suppliers.size() : pageIndex * rowsCount + rowsCount;
                    tblWholesalecustomer.getItems().clear();
                    tblWholesalecustomer.setItems(FXCollections.observableArrayList(suppliers.subList(start, end)));
                    return tblWholesalecustomer;
                }
            });

        } else {

            pagination.setPageCount(1);
            tblWholesalecustomer.getItems().clear();
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Data-Settings">
    @FXML
    private void txtNameKR(KeyEvent event) {
        if (supplier.setName(txtName.getText().trim())) {
            if (oldSupplier != null && !supplier.getName().equals(oldSupplier.getName())) {
                txtName.setStyle(updated);
            } else {
                txtName.setStyle(valid);
            }
        } else {
            txtName.setStyle(invalid);
        }
    }

    @FXML
    private void txtShopNameKR(KeyEvent event) {
        if (supplier.setShopname(txtName.getText().trim())) {
            if (oldSupplier != null && !supplier.getShopname().equals(oldSupplier.getShopname())) {
                txtShopName.setStyle(updated);
            } else {
                txtShopName.setStyle(valid);
            }
        } else {
            txtShopName.setStyle(invalid);
        }
    }

    @FXML
    private void txtAddressKR(KeyEvent event) {
        if (supplier.setAddress(txtAddress.getText().trim())) {
            if (oldSupplier != null && !supplier.getAddress().equals(oldSupplier.getAddress())) {
                //   ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(updated);
                txtAddress.setStyle(updated);
            } else {
                // ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(valid);
                txtAddress.setStyle(valid);
            }
        } else {
            //  ((ScrollPane) txtAddress.getChildrenUnmodifiable().get(0)).getContent().setStyle(invalid);
            txtAddress.setStyle(invalid);
        }
    }

    @FXML
    private void txtMobileKR(KeyEvent event) {
        if (supplier.setContact1(txtMobile.getText().trim())) {
            if (oldSupplier != null && !supplier.getContact1().equals(oldSupplier.getContact1())) {
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
        if (supplier.setContact2(txtLand.getText())) {
            if (oldSupplier != null && oldSupplier.getContact2() != null && supplier.getContact2() != null && oldSupplier.getContact2().equals(supplier.getContact2())) {
                txtLand.setStyle(valid);
            } else if (oldSupplier != null && oldSupplier.getContact2() != supplier.getContact2()) {
                txtLand.setStyle(updated);
            } else {
                txtLand.setStyle(valid);
            }
        } else {
            txtLand.setStyle(invalid);
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

        if (supplier.getName() == null) {
            errors = errors + "Name \t\tis Invalid\n";
        }
        if (supplier.getShopname() == null) {
            errors = errors + "Shop Name \t\tis Invalid\n";
        }

        if (supplier.getAddress() == null) {
            errors = errors + "Address \t\tis Invalid\n";
        }

        if (supplier.getContact1() == null) {
            errors = errors + "Contact No 1. \tis Invalid\n";
        }

        if (txtLand.getText() != null && !supplier.setContact2(txtLand.getText().trim())) {
            errors = errors + "Contact No 2. \t\tis Invalid\n";
        }

        return errors;

    }

    private String getUpdates() {

        String updates = "";

        if (oldSupplier != null) {

            if (supplier.getName() != null && !supplier.getName().equals(oldSupplier.getName())) {
                updates = updates + oldSupplier.getName() + " chnaged to " + supplier.getName() + "\n";
            }

            if (!supplier.getAddress().equals(oldSupplier.getAddress())) {
                updates = updates + oldSupplier.getAddress() + " chnaged to " + supplier.getAddress() + "\n";
            }

            if (!(oldSupplier.getContact2() != null
                    && supplier.getContact2() != null
                    && oldSupplier.getContact2().equals(supplier.getContact2()))) {
                if (oldSupplier.getContact2() != supplier.getContact2()) {
                    updates = updates + oldSupplier.getContact2()
                            + " chnaged to " + supplier.getContact2() + "\n";
                }
            }

            if (!supplier.getContact1().equals(oldSupplier.getContact1())) {
                updates = updates + oldSupplier.getContact1() + " chnaged to " + supplier.getContact1() + "\n";
            }

            if (!supplier.getShopname().equals(oldSupplier.getShopname())) {
                updates = updates + oldSupplier.getShopname() + " chnaged to " + supplier.getShopname() + "\n";
            }

        }

        return updates;
    }

    private void fillForm() {
        if (tblWholesalecustomer.getSelectionModel().getSelectedItem() != null) {
            dissableButtons(false, true, false, false);
            setStyle(valid);

            oldSupplier = SupplierDao.getById(tblWholesalecustomer.getSelectionModel().getSelectedItem().getId());
            supplier = SupplierDao.getById(tblWholesalecustomer.getSelectionModel().getSelectedItem().getId());

            txtName.setText(supplier.getName());
            txtShopName.setText(supplier.getShopname());
            txtAddress.setText(supplier.getAddress());

            txtMobile.setText(supplier.getContact1());
            txtLand.setText(supplier.getContact2());

            page = pagination.getCurrentPageIndex();
            row = tblWholesalecustomer.getSelectionModel().getSelectedIndex();
        }
    }

    @FXML
    private void btnAddAP(ActionEvent event) {
        String errors = getErrors();

        if (errors.isEmpty()) {

            String confermation = "Are you sure you need to add this Supplier with following details\n "
                    + "\nName :         \t\t" + supplier.getName()
                    + "\nShop Name :         \t\t" + supplier.getShopname()
                    + "\nAddress :       \t\t" + supplier.getAddress()
                    + "\nContact No 1 :    \t\t" + supplier.getContact1()
                    + "\nContact No 2 :         \t\t" + supplier.getContact2();

            Action action = Dialogs.create().title("Confirm Message").masthead("Supplier Add").message(confermation).showConfirm();
            if (action.toString().equals("DialogAction.YES")) {

                try {
                    SupplierDao.add(supplier);
                    Notifications.create().title("Successs").text("Supplier " + supplier.getName() + " saved.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                    loadForm();
                    loadTable();
                    pagination.setCurrentPageIndex(pagination.getPageCount() - 1);
                    tblWholesalecustomer.getSelectionModel().select(tblWholesalecustomer.getItems().size() - 1);

                } catch (DaoException ex) {
                    Notifications.create().title("Un-Successs").text("Supplier " + supplier.getName() + " not saved. \n Try Again.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();

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

                Action action = Dialogs.create().title("Confirm Message").masthead("Supplier Update").message(updates).showConfirm();
                if (action.toString().equals("DialogAction.YES")) {

                    SupplierDao.update(supplier);
                    Notifications.create().title("Successs").text("Supplier " + supplier.getName() + " updated.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                    loadForm();
                    loadTable();

                    pagination.setCurrentPageIndex(page);
                    tblWholesalecustomer.getSelectionModel().select(row);

                }
            } else {
                Dialogs.create().title("Information Message").masthead("Supplier Update").message("Nothing Updated").showWarning();
            }
        } else {

            Dialogs.create().title("Error Message").masthead("Supplier Update").message(getErrors()).showError();

        }
    }

    @FXML
    private void btnDeleteAP(ActionEvent event) {
        if (getUpdates().isEmpty() && getErrors().isEmpty()) {

            Action action = Dialogs.create().title("Supplier Delete").masthead(supplier.getName() + " Delete ?").message("Do you need to delete this supplier").showConfirm();

            if (action.toString().equals("DialogAction.YES")) {

                SupplierDao.delete(supplier);
                Notifications.create().title("Successs").text("Supplier " + supplier.getName() + " deleted.").position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(5.0)).showInformation();
                loadForm();
                loadTable();
                pagination.setCurrentPageIndex(page);
                tblWholesalecustomer.getSelectionModel().select(row);

            }
        } else {
            Dialogs.create().title("Supplier Delete").masthead(oldSupplier.getName() + " Delete ?").message("You can't delete\nSome of the fields are updated").showInformation();

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

            fillTable(SupplierDao.getAllByName(name));

        } else {

            fillTable(SupplierDao.getAll());
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
