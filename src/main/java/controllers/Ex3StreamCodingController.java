package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.FileManager;
import models.Stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Ex3StreamCodingController extends Controller {
    private FileManager fileManager;

    private Stream stream;
    @FXML
    public AnchorPane button;

    @FXML
    public Button streamCipherButton;

    @FXML
    public Button streamDecipherButton;

    @FXML
    public Button lfsrButton;

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
        fileManager = new FileManager();
    }

    @FXML
    public void lfsrButtonOnAction (ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex3LFSR.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex3LFSRController ex3LFSRController= loader.getController();
        ex3LFSRController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    @FXML
    public void streamDecipherButtonOnAction (ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex3StreamDecoding.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex3StreamDecodingController ex3StreamDecodingController = loader.getController();
        ex3StreamDecodingController.setMainController(mainController);
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
        fileManager = new FileManager();
        if (selectedFile == null) {
            System.out.println("Nie udało się załadować pliku");
            return;
        }
        try {
            fileManager.setPath(selectedFile.getPath());
            String content = fileManager.readFile();
            this.dataInputTextField.setText(content);

        } catch (IOException e) {
            System.out.println("niedziała");
        }
    }

    @FXML
    public void encrypt(ActionEvent actionEvent) {
        System.out.println("treść: " + this.dataInputTextField.getText());
        System.out.println("klucz: " + this.keyInputTextField.getText());
        System.out.println("szyfruje");
        stream = new Stream(this.keyInputTextField.getText());
        this.outputTextField.setText(stream.encryption(this.dataInputTextField.getText()));
    }


    public void saveFileButton(ActionEvent actionEvent) throws IOException {
        fileManager.writeFile(outputTextField.getText(), "output");
        System.out.println(outputTextField.getText());
        System.out.println("udało sie");

        String klucz = stream.getBinary_key();
        fileManager.writeFile(klucz, "key");
        System.out.println(klucz);
        System.out.println("udało sie");
    }
}
