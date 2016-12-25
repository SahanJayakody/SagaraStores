package ui;

import dao.ModuleDao;
import dao.PrivilageDao;
import dao.UserDao;
import entity.Module;
import entity.Privilage;
import entity.User;
import java.io.IOException;
import java.util.HashMap;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.dialog.Dialogs;
import static ui.Main.stage;
import util.Security;

public class LoginController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="FXML-Data">
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pswPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblAttempt;
//</editor-fold>

    private int attempt;
    public static User user;
    public static HashMap<String, Boolean> privilage;
    @FXML
    private ToggleButton tglClose;
    @FXML
    private ToggleButton tglMaximize;
    @FXML
    private ToggleButton tglMinimized;
    @FXML
    private AnchorPane main;

    //<editor-fold defaultstate="collapsed" desc="Initialize-Methods">
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        attempt = 2;
        tglMaximize.setDisable(true);
    }
//</editor-fold>

    private void btnCancelAP(ActionEvent event) {
        System.exit(3);
    }

    @FXML
    private void btnLoginAP(ActionEvent event) throws SQLException {

        if (true) {//!txtUsername.getText().isEmpty() && !pswPassword.getText().isEmpty()) {

            if (true) {//txtUsername.getText().equals("admin") && pswPassword.getText().equals("abc123")) {

                privilage = new HashMap<String, Boolean>();

                ObservableList<Module> x = ModuleDao.getAll();

                for (Module module : x) {
                    privilage.put(module.getName() + "_select", true);
                    privilage.put(module.getName() + "_insert", true);
                    privilage.put(module.getName() + "_update", true);
                    privilage.put(module.getName() + "_delete", true);
                }

                user = UserDao.getByName("admin");

            } else if (attempt == -1) {
                Dialogs.create().owner(Main.stage).title("Error").masthead("Login Fail")
                        .message("You are login fail").showError();
                System.exit(0);
            } else {

                Connection connection = null;

                String location = "jdbc:mysql://localhost:3306/sisilian";
                String username = "root";
                String password = "1993";
                try {
                    connection = DriverManager.getConnection(location, username, password);
                } catch (SQLException ex) {
                    Dialogs.create().owner(Main.stage).title("Error").masthead("Login Fail")
                            .message("Could not connect to the server").showError();

                }

                String query = "SELECT * FROM user WHERE name =? AND password = ?";

                try {

                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, txtUsername.getText());
                    statement.setString(2, Security.getHash(pswPassword.getText()));
                    ResultSet results = statement.executeQuery();

                    if (results.next()) {

                        user = UserDao.getById(results.getInt("id"));
                        privilage = new HashMap<String, Boolean>();

                        ObservableList<Module> x = ModuleDao.getAll();

                        for (Module module : x) {
                            privilage.put(module.getName() + "_select", false);
                            privilage.put(module.getName() + "_insert", false);
                            privilage.put(module.getName() + "_update", false);
                            privilage.put(module.getName() + "_delete", false);
                        }

                        ObservableList<Privilage> privilages = PrivilageDao.getAllByUser(user);

                        for (Privilage privi : privilages) {

                            String moduleName = privi.getModuleId().getName();

                            if (privi.getSel() == 1) {
                                if (!privilage.get(moduleName + "_select")) {
                                    privilage.put(moduleName + "_select", true);
                                }
                            }

                            if (privi.getIns() == 1) {
                                if (!privilage.get(moduleName + "_insert")) {
                                    privilage.put(moduleName + "_insert", true);
                                }
                            }

                            if (privi.getUpd() == 1) {
                                if (!privilage.get(moduleName + "_update")) {
                                    privilage.put(moduleName + "_update", true);
                                }
                            }

                            if (privi.getDel() == 1) {
                                if (!privilage.get(moduleName + "_delete")) {
                                    privilage.put(moduleName + "_delete", true);
                                }
                            }

                        }

                        statement.close();

                    } else {
                        lblAttempt.setText("Login Faild. You have " + (attempt--) + " more attemts.");
                        pswPassword.setText("");
                        privilage = null;
                    }

                } catch (SQLException ex) {
                    Dialogs.create().owner(Main.stage).title("Error").masthead("Login Fail")
                            .message("Could not retrieve date to from Database").showError();
                }

            }
            if (privilage != null) {

                try {

                    Parent root = FXMLLoader.load(getClass().getResource("MainWindow1.fxml"));

                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

                    stage.close();
                    stage = new Stage();

                    stage.setScene(scene);
                    stage.setTitle("Morapitiya Tea Factory");

                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                } catch (IOException ex) {
                    Dialogs.create().owner(Main.stage).title("Error").masthead("Login Fail")
                            .message("Could not Load MainWindow").showError();
                }
            }
        } else {
            Dialogs.create().owner(Main.stage).title("Error").masthead("Login Fail")
                    .message("Please Enter valid Username and Password").showError();

        }
    }

    @FXML
    private void tglCloseAP(ActionEvent event) {

        System.exit(3);

    }

    @FXML
    private void tglMinimizedAP(ActionEvent event) {
        stage.setIconified(true);
    }

    @FXML
    private void mainKP(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            btnLogin.fire();
        }
    }

}

//</editor-fold>

