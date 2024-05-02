package edu.evgen;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class MainController {

    private Stage stage;
    @FXML
    TextField fightersTextField;
    @FXML
    HBox HBox;

    @FXML
    @SneakyThrows
    private void initialize() {
        fightersTextField.setFocusTraversable(false);
        HBox.setStyle("-fx-background-image: url('/img.jpg');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1280 720;" +
                "-fx-background-position: center center;");
        fightersTextField.setStyle("-fx-background-image: url('/blood.png');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1280 720;" +
                "-fx-background-position: center center;" +
                "-fx-text-fill: yellow;");
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
