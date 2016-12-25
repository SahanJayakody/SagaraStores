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
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static ui.Main.stage;

import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;
import static ui.LoginController.privilage;
import static ui.LoginController.user;

/**
 * FXML Controller class
 *
 * @author SahaN
 */
public class MainWindow1Controller implements Initializable {

    @FXML
    private ImageView imgHome;
    @FXML
    private AnchorPane apnMain;
    @FXML
    private Group mnuProduction;
    @FXML
    private Group mnuSales;
    @FXML
    private Group mnuReport;
    @FXML
    private Group mnuAdmin;
    @FXML
    private Group mnuTransport;
    @FXML
    private ImageView imgLogOut;
    @FXML
    private Label lblLogin;
    @FXML
    private ImageView imgLogin;
    @FXML
    private Group mnuPurchase;
    @FXML
    private Label lblMenuName;
    @FXML
    private BorderPane root;
    @FXML
    private ToggleButton tglClose;
    @FXML
    private ToggleButton tglMaximize;
    @FXML
    private ToggleButton tglminimize;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        tglMaximize.setDisable(true);
//
////        if (user.getEmployeeId().getImage() != null) {
////            imgLogin.setImage(new Image(new ByteArrayInputStream(user.getEmployeeId().getImage())));
////        } else {
////            imgLogin.setImage((new Image("/image/user.png")));
////        }
//        lblLogin.setText(user.getName());
//
////        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
////        Node popupContent = datePickerSkin.getPopupContent();
////        root.setCenter(popupContent);
//        mnuAdmin.setDisable(!(privilage.get("Employee_select")
//                || privilage.get("User_select")
//                || privilage.get("Privilage_select") || privilage.get("Designation_select")));
    }

    @FXML
    private void mnuPurchaseMC(MouseEvent event) {
        imgHome.setImage((new Image("/image/home.png")));
        lblMenuName.setText("Purchase");
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("PurchaseWindow.fxml"));
            apnMain.getChildren().clear();
            apnMain.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mnuProductionMC(MouseEvent event) {
//        imgHome.setImage((new Image("/image/home.png")));
//        lblMenuName.setText("Production");
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("ProductionWindow.fxml"));
//            apnMain.getChildren().clear();
//            apnMain.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void mnuSalesMC(MouseEvent event) {
        imgHome.setImage((new Image("/image/home.png")));
        lblMenuName.setText("Sales");
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("SalesWindow.fxml"));
            apnMain.getChildren().clear();
            apnMain.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mnuReportMC(MouseEvent event) {
//        imgHome.setImage((new Image("/image/home.png")));
//        lblMenuName.setText("Report");
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("ReportWindow.fxml"));
//            apnMain.getChildren().clear();
//            apnMain.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void mnuAdminMC(MouseEvent event) {
        imgHome.setImage((new Image("/image/home.png")));
        lblMenuName.setText("Admin");
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("AdminWindow.fxml"));
            apnMain.getChildren().clear();
            apnMain.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void mnuTransportMC(MouseEvent event) {
//        imgHome.setImage((new Image("/image/home.png")));
//        lblMenuName.setText("Transport");
//        try {
//            AnchorPane root = FXMLLoader.load(Main.class.getResource("TransportWindow.fxml"));
//            apnMain.getChildren().clear();
//            apnMain.getChildren().add(root);
//        } catch (IOException ex) {
//            Logger.getLogger(MainWindow1Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void imgHomeMC(MouseEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(Main.class.getResource("MainWindow1.fxml"));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

            stage.setTitle("Morapitiya Tea Factory");
            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void imgLogOutMC(MouseEvent event) {

        Main main = new Main();
        main.start(new Stage());
        ((Stage) apnMain.getScene().getWindow()).close();
    }

    @FXML
    private void tglCloseAP(ActionEvent event) {
        Action action = Dialogs.create().title("Confirm Message").masthead("System Close").message("Are u sure u want to close the system").showConfirm();
        if (action.toString().equals("DialogAction.YES")) {

            System.exit(3);
        }
    }

    @FXML
    private void tglMaximizeAP(ActionEvent event) {

    }

    @FXML
    private void tglminimizeAP(ActionEvent event) {
        stage.setIconified(true);
    }

}
