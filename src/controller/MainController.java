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

    private Server server = null;

    @FXML
    public void startServer(ActionEvent actionEvent) {
        server = new Server();
        server.start();
        buttonStart.setDisable(true);
        buttonStop.setDisable(false);
        logs.insertText(0, "Server started.\n");
    }

    @FXML
    public void stopServer(ActionEvent actionEvent) {
        logs.insertText(0, "Server stopped.\n");
        server.interrupt();
        buttonStart.setDisable(false);
        buttonStop.setDisable(true);
    }

    @FXML
    public void showUserTable(ActionEvent actionEvent) {
        SceneSwitcher.toScene(rootPane, "../view/userRedactor.fxml");
    }
}
