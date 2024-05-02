package edu.evgen;

import edu.evgen.fights.FightersReopsitory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
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
        fightersTextField.setOnAction(this::fightersTextFieldHandler);
    }

    private void fightersTextFieldHandler(ActionEvent event) {
        String[] fighters = fightersTextField.getText()
                .replace(" ", "")
                .split(",");
        FightersReopsitory.addFighter(fighters);
        fightersTextField.clear();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
