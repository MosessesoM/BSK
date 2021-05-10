package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.DES;
import models.LFSR;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Main extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setStage(primaryStage);
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/main.fxml"));
        AnchorPane anchorPane =loader.load();

        Scene scene=new Scene(anchorPane,875,675);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ENIGMA");
        primaryStage.setHeight(675);
        primaryStage.setWidth(875);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        DES des = new DES();
        String encrypted=des.encryption("Hello World");
        System.out.println(des.getBinaryInput("Hello World"));
        System.out.println(encrypted);
        String key=des.getGenerated_key();
        DES des1 = new DES();
        des1.setGenerated_key(key);
        String decrypted = des1.decryption(encrypted);
        System.out.println(decrypted);
        launch(args);
    }
}
