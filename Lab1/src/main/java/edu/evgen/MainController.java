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

        hBoxFighters.getChildren().clear();
        FightersReopsitory.getFighters().forEach(fighter -> {

            TextField textField = new TextField();
            textField.setText(fighter.getId() + ". " + fighter.getName());
            textField.setEditable(false);
            textField.setStyle("-fx-background-color: rgba(255, 0, 0, 0.7);" + // Красный фон с прозрачностью 70%
                    "-fx-text-fill: yellow;" + // Желтый цвет текста
                    "-fx-font-size: 15px;" + // Размер шрифта 15
                    "-fx-background-insets: 5 5 5 5;" + // Отступы внутри TextField
                    "-fx-wrap-text: true;"); // Перенос текста на новую строку
            TextField textField1 = new TextField();
            textField1.setText(textField.getText());
            textField1.setEditable(textField.isEditable());
            textField1.setStyle(textField.getStyle());

            gridPane.add(textField, fighter.getId() + 1, 0);

            gridPane.add(textField1, 0, fighter.getId() + 1);
        });

        hBoxFighters.getChildren().add(gridPane);


    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
