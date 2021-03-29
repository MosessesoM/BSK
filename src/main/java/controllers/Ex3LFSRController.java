package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.FileManager;
import models.LFSR;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Ex3LFSRController extends Controller {
    private Boolean isRunning;
    int[] l;

    StringBuilder key;

    private FileManager fileManager;
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
    public Button stopButton;

    @FXML
    public Button startButton;

    @FXML
    void initialize() {
        fileManager = new FileManager();
    }

    @FXML
    public void streamCipherButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/ex3StreamCoding.fxml"));
        AnchorPane anchorPane = loader.load();
        Ex3StreamCodingController ex3StreamCodingController = loader.getController();
        ex3StreamCodingController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    @FXML
    public void streamDecipherButtonOnAction(ActionEvent actionEvent) throws IOException {
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
        if (selectedFile == null) {
            System.out.println("Nie udało się załadować pliku");
            return;
        }
        try {
            fileManager.setPath(selectedFile.getPath());
            String content = fileManager.readFile();
            this.dataInputTextField.setText(content);

        } catch (IOException e) {
            System.out.println("niedziałą");
        }
    }

    @FXML
    public void startButtonOnAction(ActionEvent actionEvent) {
        System.out.println("wielomian: " + this.dataInputTextField.getText());
        System.out.println("szyfruje");
        LFSR lfsr = new LFSR(this.dataInputTextField.getText());
        key= new StringBuilder();
       l = lfsr.firstline();
        if (t == null) {
            t = new Thread(new Runnable() {
                public void run() {
                    while (!t.isInterrupted()) {
                        key.append(Character.forDigit(lfsr.result(l),10));
                        l = lfsr.shift(l);
                        System.out.println("halo");
                    }
                }
            });
        }
        t.start();

    }


    @FXML
    public void stopButtonOnAction(ActionEvent actionEvent) {
        if (t != null) {
            t.interrupt();
            System.out.println("STOP PLS");
            outputTextField.setText(key.toString());
        }
    }

    Thread t;

    public void saveFileButton(ActionEvent actionEvent) throws IOException {
        fileManager.writeFile(outputTextField.getText(), "output");
        System.out.println(outputTextField.getText());
        System.out.println("udało sie");
    }
}
