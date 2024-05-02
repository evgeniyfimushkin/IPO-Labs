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
    HBox hBox;
    @FXML
    VBox vBox;

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
    }

    @SneakyThrows
    private void fightersTextFieldHandler(ActionEvent event) {
        String[] fighters = fightersTextField.getText()
                .replace(" ", "")
                .split(",");
        FightersReopsitory.addFighter(fighters);
        fightersTextField.clear();

        GridPane gridPane = new GridPane();

        vBox.getChildren().clear();
        FightersReopsitory.getFighters().forEach(fighter -> {
            TextField textField = new TextField();
            textField.setText(fighter.getName());
            textField.setEditable(false);

            TextField textField1 = new TextField();
            textField1.setText(fighter.getName());
            textField1.setEditable(false);
            gridPane.add(textField,fighter.getId()+1,0);

            gridPane.add(textField1,0,fighter.getId()+1);
        });

        vBox.getChildren().add(gridPane);
        VBox.setVgrow(gridPane, Priority.ALWAYS);


    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
