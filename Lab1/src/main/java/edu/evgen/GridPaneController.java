package edu.evgen;

import edu.evgen.fights.FightersReopsitory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GridPaneController {
    HBox hBoxFighters;
    GridPane gridPane;
    TextField[][] textFields;

    public GridPaneController(HBox hBoxFighters) {
        this.hBoxFighters = hBoxFighters;
    }

    public void fill() {
        textFields = new TextField[FightersReopsitory.getFighters().size() + 1][FightersReopsitory.getFighters().size() + 1];

        gridPane = new GridPane();

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

            textFields[0][fighter.getId() + 1] = textField;
            textFields[fighter.getId() + 1][0] = textField1;

            gridPane.add(textField, fighter.getId() + 1, 0);

            gridPane.add(textField1, 0, fighter.getId() + 1);
        });

        hBoxFighters.getChildren().add(gridPane);

        for (int row = 0; row < textFields.length; row++) {
            for (int col = 0; col < textFields[row].length; col++) {
                if (textFields[row][col] != null)
                    System.out.print(textFields[row][col].getText() + " ");
                else System.out.print("0. null ");
            }
            System.out.println(); // Переход на новую строку для следующей строки
        }

    }
}
