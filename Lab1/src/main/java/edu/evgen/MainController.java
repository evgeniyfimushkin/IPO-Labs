package edu.evgen;

import edu.evgen.fights.FightersReopsitory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainController {

    private Stage stage;
    @FXML
    TextField fightersTextField;
    @FXML
    HBox hBox, hBoxFighters;
    @FXML
    VBox vBox;
    GridPaneController gridPaneController;

    @FXML
    @SneakyThrows
    private void initialize() {

        fightersTextField.setFocusTraversable(false);
        hBox.setStyle("-fx-background-image: url('/img.jpg');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1280 720;" +
                "-fx-background-position: center center;");
        fightersTextField.setStyle("-fx-background-image: url('/blood.png');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1280 720;" +
                "-fx-background-position: center center;" +
                "-fx-text-fill: yellow;");
        fightersTextField.setOnAction(this::fightersTextFieldHandler);


        gridPaneController = new GridPaneController(hBoxFighters);
    }

    @SneakyThrows
    private void fightersTextFieldHandler(ActionEvent event) {
        String[] fighters = fightersTextField.getText()
                .replace(" ", "")
                .split(",");
        FightersReopsitory.addFighter(fighters);
        fightersTextField.clear();

        gridPaneController.fill();
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
