package controller;

import connection.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import switcher.SceneSwitcher;

public class MainController {
    @FXML
    public AnchorPane rootPane;
    @FXML
    public TextArea logs;
    @FXML
    public Button buttonStart;
    @FXML
    public Button buttonStop;
    @FXML
    public Button buttonViewUsers;
    @FXML
    public Text serverStatus;
    @FXML
    public Text dbStatus;


    @FXML
    public void startServer(ActionEvent actionEvent) {
        Server.getInstance().start();
        buttonStart.setDisable(true);
        buttonStop.setDisable(false);
    }

    @FXML
    public void stopServer(ActionEvent actionEvent) {
        Server.getInstance().interrupt();
        buttonStart.setDisable(false);
        buttonStop.setDisable(true);
    }

    @FXML
    public void showUserTable(ActionEvent actionEvent) {
        SceneSwitcher.toScene(rootPane, "../view/userRedactor.fxml");
    }
}
