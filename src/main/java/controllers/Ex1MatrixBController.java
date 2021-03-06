package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.FileManager;
import models.Type_B;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
    @FXML
    public void pickFileButtonOnAction (ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile == null) {
            System.out.println("Nie udało się załadować pliku");
            return;
        }
        try {
            String content = Files.readString(selectedFile.toPath());
            this.dataInputTextField.setText(content);

        } catch (IOException e) {
            System.out.println("niedziałą");
        }
    }
    @FXML
    public void encrypt (ActionEvent actionEvent) {
        System.out.println("treść: " + this.dataInputTextField.getText());
        System.out.println("klucz: " + this.keyInputTextField.getText());
        System.out.println("szyfruje");
        Type_B matrixB = new Type_B(this.keyInputTextField.getText().toUpperCase());
        this.outputTextField.setText(matrixB.encryption(this.dataInputTextField.getText().toUpperCase()));
    }
    @FXML
    public void decrypt (ActionEvent actionEvent) {
        System.out.println("treść: " + this.dataInputTextField.getText());
        System.out.println("klucz: " + this.keyInputTextField.getText());
        System.out.println("deszyfruje");
        Type_B matrixB = new Type_B(this.keyInputTextField.getText().toUpperCase());
        this.outputTextField.setText(matrixB.decryption(this.dataInputTextField.getText().toUpperCase()));
    }
    public void saveFileButton (ActionEvent actionEvent) throws IOException {
        FileManager fw = new FileManager();
        fw.writeFile(outputTextField.getText(), "output");
        System.out.println(outputTextField.getText());
        System.out.println("udało sie");
    }
}


