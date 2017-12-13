package logic.switcher;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SceneSwitcher {

    public static void toScene(Pane scenePane, String fxmlPath) {
        FadeTransition sceneAnimation = new FadeTransition();
        sceneAnimation.setDuration(Duration.millis(100));
        sceneAnimation.setNode(scenePane);
        sceneAnimation.setFromValue(1);
        sceneAnimation.setToValue(0);
        sceneAnimation.setOnFinished(event -> {
            try {
                new SceneSwitcher().loadNextScene(scenePane, fxmlPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        sceneAnimation.play();
    }

    private void loadNextScene(Pane scenePane, String fxmlPath) throws IOException {
        Parent fxmlRoot = (AnchorPane) FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene newScene = new Scene(fxmlRoot);
        Stage mainStage = (Stage) scenePane.getScene().getWindow();
        mainStage.setScene(newScene);
    }

    public static void loadScene(Pane scenePane) {
        scenePane.setOpacity(0);
        FadeTransition sceneAnimation = new FadeTransition();
        sceneAnimation.setDuration(Duration.millis(600));
        sceneAnimation.setNode(scenePane);
        sceneAnimation.setFromValue(0);
        sceneAnimation.setToValue(1);
        sceneAnimation.play();
    }
}