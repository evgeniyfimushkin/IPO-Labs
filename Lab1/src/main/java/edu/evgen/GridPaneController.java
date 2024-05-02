package edu.evgen;

import edu.evgen.fights.Fighter;
import edu.evgen.fights.FightersReopsitory;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GridPaneController {
    HBox hBoxFighters;
    GridPane gridPane;
    TextField[][] textFields;
    ContextMenu emptyContextMenu;

    public GridPaneController(HBox hBoxFighters) {
        this.hBoxFighters = hBoxFighters;
        emptyContextMenu = new ContextMenu();
    }

    public void fill() {
        hBoxFighters.getChildren().clear();

        textFields = new TextField[FightersReopsitory.getFighters().size() + 1 + 2][FightersReopsitory.getFighters().size() + 2];

        gridPane = new GridPane();


        //заполнение крайних полей игроков
        FightersReopsitory.getFighters().forEach(fighter -> {


            MenuItem menuItem = new MenuItem("delete this fighter");
            menuItem.setOnAction(e -> removeElement(fighter));
            ContextMenu contextMenu = new ContextMenu(menuItem);

            TextField textField = createTextField();
            textField.setText(fighter.getId() + ". " + fighter.getName());
            textField.setContextMenu(contextMenu);

            MenuItem menuItem1 = new MenuItem("delete this fighter");
            menuItem1.setOnAction(e -> removeElement(fighter));
            ContextMenu contextMenu1 = new ContextMenu(menuItem1);


            TextField textField1 = createTextField();
            textField1.setText(textField.getText());
            textField1.setContextMenu(contextMenu1);

            textFields[0][fighter.getId() + 1] = textField;
            textFields[fighter.getId() + 1][0] = textField1;

            gridPane.add(textField, fighter.getId() + 1, 0);

            gridPane.add(textField1, 0, fighter.getId() + 1);
        });

        //заполнение диагонали
        for (int i = 0; i < FightersReopsitory.getFighters().size() + 1; i++) {
            textFields[i][i] = createTextField();
            gridPane.add(textFields[i][i], i, i);
        }

        //заполнение полей счёта
        for (int i = 1; i < FightersReopsitory.getFighters().size() + 1; i++) {
            for (int j = 1; j < FightersReopsitory.getFighters().size() + 1; j++) {
                if (i != j) {
                    textFields[i][j] = createTextField();

                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem menuItem = new MenuItem("Set score" + (i - 1) + (j - 1));
                    int finalI = i;
                    int finalJ = j;
                    menuItem.setOnAction(e -> {
                        setScore(finalI, finalJ);
                    });
                    contextMenu.getItems().add(menuItem);
                    textFields[i][j].setContextMenu(contextMenu);


                    gridPane.add(textFields[i][j], i, j);
                }
            }
        }

        //Заполнение полей результата

        int size = FightersReopsitory.getFighters().size();

        textFields[size + 1][0] = createTextField();
        textFields[size + 1][0].setText("Побед");
        gridPane.add(textFields[size + 1][0], size + 1, 0);

        textFields[size + 2][0] = createTextField();
        textFields[size + 2][0].setText("Рейтинг");
        gridPane.add(textFields[size + 2][0], size + 2, 0);

        for (int i = 1; i < size + 1; i++) {
            textFields[size + 1][i] = createTextField();

            textFields[size + 2][i] = createTextField();


            gridPane.add(textFields[size + 1][i], size + 1, i);
            gridPane.add(textFields[size + 2][i], size + 2, i);
        }


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

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setEditable(false);
        textField.setStyle(
                "-fx-background-color: rgba(255, 0, 0, 0.7);" + // Красный фон с прозрачностью 70%
                        "-fx-text-fill: yellow;" + // Желтый цвет текста
                        "-fx-font-size: 15px;" + // Размер шрифта 15
                        "-fx-background-insets: 5 5 5 5;" + // Отступы внутри TextField
                        "-fx-wrap-text: true;" + // Перенос текста на новую строку
                        "-fx-alignment: center;"
        );
        textField.setPrefHeight(40);
        textField.setContextMenu(emptyContextMenu);
        return textField;
    }

    private void removeElement(Fighter fighter) {
        FightersReopsitory.removeFighter(fighter);
        fill();
    }

    private void setScore(int i, int j) {
    }
}
