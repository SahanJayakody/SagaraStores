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
public class AdminWindowController implements Initializable {

    @FXML
    private ToggleButton tglUser;
    @FXML
    private ToggleButton tglPrivilage;
    @FXML
    private AnchorPane apnRight;
    @FXML
    private ToggleGroup admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        tglEmployee.setSelected(true);
//
//        tglEmployee.setDisable(!(privilage.get("Employee_select")
//                || privilage.get("Employee_insert")
//                || privilage.get("Employee_update") || privilage.get("Employee_delete")));
//
//        tglUser.setDisable(!(privilage.get("User_select")
//                || privilage.get("User_insert")
//                || privilage.get("User_update") || privilage.get("User_delete")));
//
//        tglPrivilage.setDisable(!(privilage.get("Privilage_select")
//                || privilage.get("Privilage_insert")
//                || privilage.get("Privilage_update") || privilage.get("Privilage_delete")));
//
//        tglDesignation.setDisable(!(privilage.get("Designation_select")
//                || privilage.get("Designation_insert")
//                || privilage.get("Designation_update") || privilage.get("Designation_delete")));
//
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("Employee.fxml"));
//            apnRight.getChildren().clear();
//            apnRight.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void tglUserAP(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("User.fxml"));
            apnRight.getChildren().clear();
            apnRight.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tglPrivilageAP(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("Privilage.fxml"));
            apnRight.getChildren().clear();
            apnRight.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
   

}
