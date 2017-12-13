package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.entity.PermissionType;
import logic.entity.User;
import model.AppModel;

public class LoginController {


    private AppModel model = AppModel.getInstance();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public Button loginButton;


    @FXML
    public void login(ActionEvent event) {
        try {
            User currentUser = this.model.getUserData(this.username.getText(), this.password.getText());
            if (currentUser != null && currentUser.getPermission() == PermissionType.ADMIN) {
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("../view/main.fxml").openStream());
                Scene scene = new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("Таблица клиентов");
                userStage.setResizable(false);
                loginButton.getScene().getWindow().hide();
                userStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
