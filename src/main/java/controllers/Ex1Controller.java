package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Ex1Controller extends Controller{

    public MainController mainController;

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
}
