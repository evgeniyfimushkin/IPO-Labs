package edu.evgen;

import edu.evgen.controllers.MainController;
import edu.evgen.fights.Fighter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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

        Fighter fighter = new Fighter("John");
        Field name = fighter.getClass().getDeclaredField("name");
        name.setAccessible(true);
        System.out.println(name.get(fighter));
        name.set(fighter,"Roman");
        System.out.println(name.get(fighter));
        System.out.println(fighter);
    }
}