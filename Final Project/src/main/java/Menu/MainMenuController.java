package Menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.abs;

public class MainMenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void openWindowPlay(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-play.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openWindowUsers(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-users.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void quitGame(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }
    @FXML
    private ImageView UsersActiveButton,UsersBlackButton,exitActiveButon,exitBlackButton,playActiveButton,playBlackButton;

    @FXML
    void usersChange(MouseEvent event) {
        if (UsersActiveButton.getOpacity()==0){
            UsersActiveButton.setOpacity(1);
            UsersActiveButton.setDisable(false);
        }
        else{
            UsersActiveButton.setOpacity(0);
            UsersActiveButton.setDisable(true);
        }
    }
    @FXML
    void playChange(MouseEvent event) {
        if (playActiveButton.getOpacity()==0){
            playActiveButton.setOpacity(1);
            playActiveButton.setDisable(false);
        }
        else{
            playActiveButton.setOpacity(0);
            playActiveButton.setDisable(true);
        }
    }
    @FXML
    void exitChange(MouseEvent event) {
        if (exitActiveButon.getOpacity()==0){
            exitActiveButon.setOpacity(1);
            exitActiveButon.setDisable(false);
        }
        else{
            exitActiveButon.setOpacity(0);
            exitActiveButon.setDisable(true);
        }
    }

}