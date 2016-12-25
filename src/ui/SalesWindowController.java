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
public class SalesWindowController implements Initializable {

    @FXML
    private AnchorPane apnRight;
    @FXML
    private ToggleButton tglConsumer;

    @FXML
    private ToggleButton tglSalesOrder;

    @FXML
    private ToggleButton tglPayment;
    @FXML
    private ToggleGroup sales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        tglSalesOrder.setSelected(true);
//
//        tglConsumer.setDisable(!(privilage.get("Consumer_select")
//                || privilage.get("Consumer_insert")
//                || privilage.get("Consumer_update") || privilage.get("Consumer_delete")));
//
//        tglPayment.setDisable(!(privilage.get("ConsumerPayment_select")
//                || privilage.get("ConsumerPayment_insert")
//                || privilage.get("ConsumerPayment_update") || privilage.get("ConsumerPayment_delete")));
//
//        tglSalesOrder.setDisable(!(privilage.get("TeaSalesOrder_select")
//                || privilage.get("TeaSalesOrder_insert")
//                || privilage.get("TeaSalesOrder_update") || privilage.get("TeaSalesOrder_delete")));
//
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("TeaSalesManagement.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglConsumerAP(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("Wholesalecustomer.fxml"));
            apnRight.getChildren().clear();
            apnRight.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tglSalesOrderAP(ActionEvent event) {
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("TeaSalesManagement.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglPaymentAP(ActionEvent event) {
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("ConsumerPayment.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
