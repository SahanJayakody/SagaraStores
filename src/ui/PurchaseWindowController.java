/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import static ui.LoginController.privilage;

/**
 * FXML Controller class
 *
 * @author SahaN
 */
public class PurchaseWindowController implements Initializable {

    @FXML
    private AnchorPane apnRight;
    @FXML
    private ToggleButton tglTeaPurchase;
    @FXML
    private ToggleGroup purchase;
    @FXML
    private ToggleButton tglSupplier;
    @FXML
    private ToggleButton tglTeaPrice;
    @FXML
    private ToggleButton tglSupplierPayble;
    @FXML
    private ToggleButton tglSupplierPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        tglTeaPurchase.setSelected(true);
//
//        tglSupplier.setDisable(!(privilage.get("Supplier_select")
//                || privilage.get("Supplier_insert")
//                || privilage.get("Supplier_update") || privilage.get("Supplier_delete")));
//        tglSupplierPayble.setDisable(!(privilage.get("SupplierPayble_select")
//                || privilage.get("SupplierPayble_insert")
//                || privilage.get("SupplierPayble_update") || privilage.get("SupplierPayble_delete")));
//
//        tglSupplierPayment.setDisable(!(privilage.get("Payment_select")
//                || privilage.get("Payment_insert")
//                || privilage.get("Payment_update") || privilage.get("Payment_delete")));
//
//        tglTeaPurchase.setDisable(!(privilage.get("TeaPurchase_select")
//                || privilage.get("TeaPurchase_insert")
//                || privilage.get("TeaPurchase_update") || privilage.get("TeaPurchase_delete")));
//
//        tglTeaPrice.setDisable(!(privilage.get("TeaPrice_select")
//                || privilage.get("TeaPrice_insert")
//                || privilage.get("TeaPrice_update") || privilage.get("TeaPrice_delete")));
//
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("TeaPurchase.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglTeaPurchaseAP(ActionEvent event) {
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("TeaPurchase.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglSupplierAP(ActionEvent event) {
        try {

            AnchorPane root = FXMLLoader.load(Main.class.getResource("Supplier.fxml"));
            apnRight.getChildren().clear();
            apnRight.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tglTeaPriceAP(ActionEvent event) {
//        try {
//
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("TeaPriceAlocation.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglSupplierPaybleAP(ActionEvent event) {
//        try {
//
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("SupplierPayble.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglSupplierPaymentAP(ActionEvent event) {
//        try {
//
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("PaymentManagement.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
