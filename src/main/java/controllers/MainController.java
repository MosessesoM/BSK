package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController extends Controller {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize() throws IOException {
        loadMenu();
    }

    public void loadMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/menu.fxml"));
        AnchorPane anchorPane = loader.load();
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(anchorPane);
    }

    public void setScreen(Pane pane) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(pane);
    }

}
