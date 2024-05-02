package edu.evgen.controllers;

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
        gridPane.setStyle("-fx-alignment: center;");


        //заполнение крайних полей игроков
        FightersReopsitory.getFighters().forEach(fighter -> {


            MenuItem menuItem = new MenuItem("delete this fighter");
            menuItem.setOnAction(e -> removeElement(fighter));
            ContextMenu contextMenu = new ContextMenu(menuItem);
            MenuItem menuItem2 = new MenuItem("Show wins");
            menuItem2.setOnAction(e -> {
                System.out.println(fighter.getWins());
            });
            contextMenu.getItems().add(menuItem2);


            TextField textField = createTextField(fighter.getId() + ". " + fighter.getName(), contextMenu);

            MenuItem menuItem1 = new MenuItem("delete this fighter");
            menuItem1.setOnAction(e -> removeElement(fighter));
            ContextMenu contextMenu1 = new ContextMenu(menuItem1);
            MenuItem menuItem3 = new MenuItem("Show wins");
            menuItem3.setOnAction(e -> {
                System.out.println(fighter.getWins());
            });
            contextMenu1.getItems().add(menuItem3);

            TextField textField1 = createTextField(textField.getText(), contextMenu1);

            textFields[0][fighter.getId() + 1] = textField;
            textFields[fighter.getId() + 1][0] = textField1;

            gridPane.add(textField, fighter.getId() + 1, 0);

            gridPane.add(textField1, 0, fighter.getId() + 1);
        });

        //заполнение диагонали
        for (int i = 0; i < FightersReopsitory.getFighters().size() + 1; i++) {
            textFields[i][i] = createStyledTextField("-fx-background-color: rgba(107, 49, 45, 0.7);");
            gridPane.add(textFields[i][i], i, i);
        }

        //заполнение полей счёта
        for (int i = 1; i < FightersReopsitory.getFighters().size() + 1; i++) {
            for (int j = 1; j < FightersReopsitory.getFighters().size() + 1; j++) {
                if (i != j) {
                    textFields[i][j] = createTextField();

                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem menuItem = new MenuItem(FightersReopsitory.getFighters().get(i - 1).getName() + " won!");
                    MenuItem menuItem1 = new MenuItem(FightersReopsitory.getFighters().get(j - 1).getName() + " won!");
                    int finalI = i - 1;
                    int finalJ = j - 1;
                    menuItem.setOnAction(e -> {
                        setWin(finalI, finalJ);
                    });
                    menuItem1.setOnAction(e -> {
                        setWin(finalJ, finalI);
                    });


                    contextMenu.getItems().add(menuItem);
                    contextMenu.getItems().add(menuItem1);

                    textFields[i][j].setContextMenu(contextMenu);

                    switch (FightersReopsitory.isWin(finalI, finalJ)) {
                        case WIN -> {
                            textFields[i][j].setText("Lose");
                            textFields[i][j].setStyle("-fx-background-color: rgba(225, 133, 133, 1);" + // Красный фон с прозрачностью 70%
                                    "-fx-text-fill: white;" + // Желтый цвет текста
                                    "-fx-font-size: 15px;" + // Размер шрифта 15
                                    "-fx-background-insets: 5 5 5 5;" + // Отступы внутри TextField
                                    "-fx-wrap-text: true;" + // Перенос текста на новую строку
                                    "-fx-alignment: center;");
                        }
                        case LOSE -> {
                            textFields[i][j].setText("Win");
                            textFields[i][j].setStyle("-fx-background-color: rgba(245, 245, 0, 1);" + // Красный фон с прозрачностью 70%
                                    "-fx-text-fill: black;" + // Желтый цвет текста
                                    "-fx-font-size: 15px;" + // Размер шрифта 15
                                    "-fx-background-insets: 5 5 5 5;" + // Отступы внутри TextField
                                    "-fx-wrap-text: true;" + // Перенос текста на новую строку
                                    "-fx-alignment: center;");
                        }
                        case NO -> textFields[i][j].setText("NO");
                    }

                    gridPane.add(textFields[i][j], i, j);
                }
            }
        }

        //Заполнение полей результата

        int size = FightersReopsitory.getFighters().size();

        textFields[size + 1][0] = createTextField("Win");
        gridPane.add(textFields[size + 1][0], size + 1, 0);

        textFields[size + 2][0] = createTextField("Position");
        gridPane.add(textFields[size + 2][0], size + 2, 0);

        for (int i = 1; i < size + 1; i++) {
            textFields[size + 1][i] = createTextField();

            textFields[size + 2][i] = createTextField();

            textFields[size + 1][i].setText(FightersReopsitory.getFighters().get(i - 1).getWins().size() + "");
            textFields[size + 2][i].setText(i + "");

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

    private TextField createTextField(String text) {
        TextField textField = createTextField();
        textField.setText(text);
        return textField;
    }

    private TextField createTextField(String text, ContextMenu contextMenu) {
        TextField textField = createTextField(text);
        textField.setContextMenu(contextMenu);
        return textField;
    }

    private TextField createStyledTextField(String style) {
        TextField textField = createTextField();
        textField.setStyle(
                "-fx-background-color: rgba(255, 0, 0, 0.7);" + // Красный фон с прозрачностью 70%
                        "-fx-text-fill: yellow;" + // Желтый цвет текста
                        "-fx-font-size: 15px;" + // Размер шрифта 15
                        "-fx-background-insets: 5 5 5 5;" + // Отступы внутри TextField
                        "-fx-wrap-text: true;" + // Перенос текста на новую строку
                        "-fx-alignment: center;" +
                        style
        );
        return textField;
    }

    private void removeElement(Fighter fighter) {
        FightersReopsitory.removeFighter(fighter);
        fill();
    }

    private void setWin(int winner, int looser) {
        FightersReopsitory.addWin(winner, looser);
        fill();
    }

}
