package edu.evgen.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class MainController {
    Double maxAccidentValue = 0.9;
    Double minAccidentValue = 0.2;
    Double maxWarningValue = 0.7;
    Double minWarningValue = 0.4;

    @FXML
    Label labelMaxAccidentValue;
    @FXML
    Label labelMinAccidentValue;
    @FXML
    Label labelMaxWarningValue;
    @FXML
    Label labelMinWarningValue;
    @FXML
    TextField textFieldMaxWarningValue,
            textFieldMinAccidentValue,
            textFieldMaxAccidentValue,
            textFieldMinWarningValue,
            textFieldProgressValue;

    @FXML
    Button buttonAccept,
            buttonRunSimulation,
            buttonStopSimulation;
    Boolean run = true;
    @FXML
    ProgressBar progressBar;
    @FXML
    Random random = new Random();

    Thread progressThread = new Thread(this::progressBarRandomize);

    Thread progressCheckerThread = new Thread(this::progressBarChecker);

    @FXML

    private void initialize() {
        buttonAccept.setOnAction(this::buttonAcceptAction);

        progressThread.start();

        buttonRunSimulation.setOnAction(event -> run = true);
        buttonStopSimulation.setOnAction(event -> {
            run = false;
        });


        labelMaxAccidentValue.setText("maxAccidentValue(Double) " + maxAccidentValue);
        labelMinAccidentValue.setText("minAccidentValue(Double) " + minAccidentValue);
        labelMaxWarningValue.setText("maxWarningValue(Double) " + maxWarningValue);
        labelMinWarningValue.setText("minWarningValue(Double) " + minWarningValue);


        progressBar.setProgress(0.5);

        progressCheckerThread.start();

    }

    synchronized private void buttonAcceptAction(ActionEvent event) {
        if (!textFieldMaxAccidentValue.getText().isEmpty())
            maxAccidentValue = Double.valueOf(textFieldMaxAccidentValue.getText());
        if (!textFieldMinAccidentValue.getText().isEmpty())
            minAccidentValue = Double.valueOf(textFieldMinAccidentValue.getText());
        if (!textFieldMaxWarningValue.getText().isEmpty())
            maxWarningValue = Double.valueOf(textFieldMaxWarningValue.getText());
        if (!textFieldMinWarningValue.getText().isEmpty())
            minWarningValue = Double.valueOf(textFieldMinWarningValue.getText());
        labelMaxAccidentValue.setText("maxAccidentValue(Double) " + maxAccidentValue);
        labelMinAccidentValue.setText("minAccidentValue(Double) " + minAccidentValue);
        labelMaxWarningValue.setText("maxWarningValue(Double) " + maxWarningValue);
        labelMinWarningValue.setText("minWarningValue(Double) " + minWarningValue);

        if (!textFieldProgressValue.getText().isEmpty())
            progressBar.setProgress(Double.valueOf(textFieldProgressValue.getText()));
    }

    @SneakyThrows
    private void progressBarChecker() {
        while (true) {
            if (progressBar.getProgress() > maxAccidentValue)
                progressBarSetStyle("-fx-accent: red;");
            else if (progressBar.getProgress() > maxWarningValue)
                progressBarSetStyle("-fx-accent: yellow;");
            else if (progressBar.getProgress() < minAccidentValue)
                progressBarSetStyle("-fx-accent: red;");
            else if (progressBar.getProgress() < minWarningValue)
                progressBarSetStyle("-fx-accent: yellow;");
            else
                progressBarSetStyle("-fx-accent: green;");
            Thread.sleep(10);
        }
    }

    @SneakyThrows
    private void progressBarRandomize() {
        double progress = 0.5;
        int sign = -1;
        while (run) {
            synchronized (run) {
                if (progress % 0.5 == 0) {
                    System.out.println(progress);
                }
                System.out.println(progress + " % 0.5 = " + progress % 0.5);
                progressBar.setProgress(progress);
                progress += sign * 0.01;
                Thread.sleep(10);
                if (random.nextDouble() > 0.8)
                    sign = random.nextDouble() > 0.5 ? 1 : -1;
                if (progress >= 1.0)
                    sign = -1;
                else if (progress < 0.1)
                    sign = 1;
            }
        }
    }

    private void progressBarSetStyle(String style) {
        progressBar.setStyle("-fx-rotate: -90;" + style);
    }

}
