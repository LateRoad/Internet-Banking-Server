package controller;

import entity.PermissionType;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.LoginModel;

public class LoginController {


    LoginModel loginModel = new LoginModel();


    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public Button loginButton;
    @FXML
    private Label loginStatus;


    @FXML
    public void login(ActionEvent event) {
        try {
            User currentUser = this.loginModel.getUserData(this.username.getText(), this.password.getText());
            if (currentUser != null && currentUser.getPermission() == PermissionType.ADMIN) {
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("../view/main.fxml").openStream());
                Scene scene = new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("User Dashboard");
                userStage.setResizable(false);
                loginButton.getScene().getWindow().hide();
                userStage.show();
            } else {
                this.loginStatus.setText("Wrong Credential");
            }
        } catch (Exception e) {
            this.loginStatus.setText("Wrong Credential");
            e.printStackTrace();
        }
    }
}
