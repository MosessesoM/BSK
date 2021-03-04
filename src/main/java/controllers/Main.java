package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Matrix;
import models.Type_A;
import models.Type_B;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
        primaryStage.setTitle("ENIGMA");
        primaryStage.setScene(new Scene(root, 875, 640));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //Matrix matrix = new Type_B("CONVENIENCE");
        //System.out.println(matrix.decryption("HECRN CEYI ISEP SGDI RNTO AAES RMPN SSRO EEBT ETIA EEHS"));
        launch(args);
    }
}
