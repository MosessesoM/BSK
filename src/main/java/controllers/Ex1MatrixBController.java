package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Ex1MatrixBController extends Controller{

    @FXML
    public AnchorPane button;

    @FXML
    public Button railFenceButton;

    @FXML
    public Button matrixShift1Button;

    @FXML
    public Button matrixShift2Button;

    @FXML
    public Button menuButton;

    @FXML
    public TextField dataInputTextField;

    @FXML
    public TextField keyInputTextField;

    @FXML
    public TextField outputTextField;

    @FXML
    public Button encryptButton;

    @FXML
    public Button decryptButton;

    @FXML
    void initialize() {
    }

    @FXML
    public void matrixAButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex1MatrixShiftA.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex1MatrixAController ex1MatrixAController = loader.getController();
        ex1MatrixAController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }
    @FXML
    public void railButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex1RailFence.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex1RailController ex1RailController = loader.getController();
        ex1RailController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }
    @FXML
    public void menuButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/menu.fxml"));
        AnchorPane anchorPane = loader.load();
        MenuController menuController = loader.getController();
        menuController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

}
