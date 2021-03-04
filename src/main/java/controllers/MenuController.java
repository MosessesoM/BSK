package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController extends Controller{

    private MainController mainController;

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
        System.exit(-1);
    }

    @FXML
    public void exercise1ButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex1RailFence.fxml"));
        Pane anchorPane = loader.load();
        MainController ex1Controller = new MainController();
        ex1Controller.setScreen(anchorPane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
   /* @FXML
    public void exercise2ButtonOnAction() throws IOException {

    }
    @FXML
    public void exercise3ButtonOnAction() throws IOException {

    }
    @FXML
    public void exercise4ButtonOnAction() throws IOException {

    }*/

}
