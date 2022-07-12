/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Characters.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;


/**
 * @author byrdh
 */
public class Main extends Application {

    public static Scene sceneGame;

    public static boolean begin = false;
    public static Stage stage;
    public static boolean undead = false;
    public static boolean saloon = false;
    public static int numPlayers = 3;


    @Override
    public void start(Stage stage) throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("IntroFXML.fxml"));
        Scene scene1 = new Scene(root1);
        stage.setScene(scene1);
        stage.show();
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        Main.stage = stage;
    }


    public static void startGame() throws Exception {

        Parent root = FXMLLoader.load(FXMLController.class.getResource("FXML.fxml"));
        sceneGame = new Scene(root);
        stage.close();
        stage.setScene(sceneGame);
        stage.show();
        stage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });


    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
