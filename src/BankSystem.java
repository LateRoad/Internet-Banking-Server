import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.connection.Server;

import java.sql.SQLException;

public class BankSystem extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(root);
        window.setOnCloseRequest(event -> {
            closeProgram();
        });

        window.setScene(scene);
        window.setTitle("Bank System");
        window.show();
    }

    public static void main(String[] args) throws SQLException {
         launch(args);
    }

    private void closeProgram() {
        try {
            Server.getInstance().closeThreads();
            Server.getInstance().serverActive = false;
        } finally {
            window.close();
        }
    }
}
