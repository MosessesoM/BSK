package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController extends Controller{

    @FXML
    public Button exitButton;

    @FXML
    public Button exercise1Button;

    @FXML
    public Button exercise2Button;

    @FXML
    public Button exercise3Button;

    @FXML
    public Button exercise4Button;

    @FXML
    void initialize() {
    }

    @FXML
    public void exitButtonOnAction (ActionEvent actionEvent){
        Platform.exit();
    }

    @FXML
    public void exercise1ButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex1RailFence.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex1Controller ex1Controller= loader.getController();
        ex1Controller.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    @FXML
    public void exercise2ButtonOnAction() throws IOException {

    }
    @FXML
    public void exercise3ButtonOnAction() throws IOException {

    }
    @FXML
    public void exercise4ButtonOnAction() throws IOException {

    }

}
