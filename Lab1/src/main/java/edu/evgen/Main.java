package edu.evgen;

import edu.evgen.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScene.fxml"));
        stage.setScene(new Scene(loader.load()));
        MainController controller = loader.getController();
        controller.setStage(stage);
        stage.setResizable(false);
        stage.show();

    }
}