/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.HibernateUtil;

/**
 *
 * @author Sahan
 */
public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {

        try {
            stage = primaryStage;
            AnchorPane root = FXMLLoader.load(Main.class.getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Morapitiya Tea Factory");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void stop() {
        HibernateUtil.getSessionFactory().close();

    }

}
