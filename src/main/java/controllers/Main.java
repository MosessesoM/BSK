package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Caesar;
import models.Cipher;
import models.Type_C;
import models.Vigenere;

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


    public static void main(String[] args) {
        Cipher type_c = new Type_C("CONVENIENCE");
        System.out.println(type_c.decryption("HERE IS A SECRET MESSAGE ENCIPHERED BY TRANSPOSITION"));
        launch(args);
    }
}
