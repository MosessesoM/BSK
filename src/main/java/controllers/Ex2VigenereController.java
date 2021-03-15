package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.Caesar;
import models.FileManager;
import models.Vigenere;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Ex2VigenereController extends Controller {
    @FXML
    public AnchorPane button;

    @FXML
    public Button matrixShift3Button;

    @FXML
    public Button vigenereEncryptionButton;

    @FXML
    public Button ceasarCipherButton;

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
    public void ceasarCipherButtonOnAction (ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex2CeasarCipher.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex2CaesarController ex2CaesarController = loader.getController();
        ex2CaesarController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    @FXML
    public void matrixShift3ButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex2MatrixShiftC.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex2MatrixCController ex2MatrixCController = loader.getController();
        ex2MatrixCController.setMainController(mainController);
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
    public void pickFileButtonOnAction(ActionEvent actionEvent) {
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
    public void encrypt(ActionEvent actionEvent) {
        System.out.println("treść: " + this.dataInputTextField.getText());
        System.out.println("klucz: " + this.keyInputTextField.getText());
        System.out.println("szyfruje");
        Vigenere vigenere = new Vigenere(this.keyInputTextField.getText().toUpperCase());
        this.outputTextField.setText(vigenere.encryption(this.dataInputTextField.getText().toUpperCase()));
    }

    @FXML
    public void decrypt(ActionEvent actionEvent) {
        System.out.println("treść: " + this.dataInputTextField.getText());
        System.out.println("klucz: " + this.keyInputTextField.getText());
        System.out.println("deszyfruje");
        Vigenere vigenere = new Vigenere(this.keyInputTextField.getText().toUpperCase());
        this.outputTextField.setText(vigenere.decryption(this.dataInputTextField.getText().toUpperCase()));
    }

    public void saveFileButton(ActionEvent actionEvent) throws IOException {
        FileManager fw = new FileManager();
        fw.writeFile(outputTextField.getText());
        System.out.println(outputTextField.getText());
        System.out.println("udało sie");
    }
}
