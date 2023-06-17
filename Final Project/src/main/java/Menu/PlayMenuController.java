package Menu;

import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class PlayMenuController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    User [] users;
    @FXML
    private Label player1LossesLabel, player1NameLabel, player1TiesLabel, player1WinsLabel;
    @FXML
    private Label player2LossesLabel, player2NameLabel, player2TiesLabel, player2WinsLabel;
    @FXML
    void openWindowMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-inicial.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    int player1Index = -1;
    int player2Index = -1;
    int playersTotal;

    @FXML
    void player1Foward(ActionEvent event) {
        if (player1Index + 1 != player2Index) {
            if (player1Index < playersTotal - 1) {
                player1Index += 1;
            } else {
                player1Index = -1;
            }
        } else {
            if (player2Index == playersTotal - 1) {
                player1Index = -1;
            } else {
                player1Index += 2;
            }
        }

        if (player1Index != -1) setPlayer1();
        else setAnonimo(1);
    }
    @FXML
    void player2Foward(ActionEvent event){
        if (player2Index + 1 != player1Index){
            if(player2Index < playersTotal-1){
                player2Index += 1;
            }
            else{
                player2Index = -1;
            }
        }
        else{
            if(player1Index == playersTotal-1){
                player2Index = -1;
            }
            else{
                player2Index += 2;
            }
        }

        if (player2Index != -1) setPlayer2();
        else setAnonimo(2);
    }
    @FXML
    void player1Backward(ActionEvent event){
        if (player1Index - 1 != player2Index){
            if (player1Index > -1){
                player1Index -= 1;
            }
            else{
                if (player2Index != playersTotal-1) player1Index = playersTotal - 1;
                else player1Index = playersTotal - 2;
            }
        }
        else{
            if (player2Index == -1) player1Index = -1;
            else player1Index -= 2;
        }
        if (player1Index != -1) setPlayer1();
        else setAnonimo(1);
    }
    @FXML
    void player2Backward(ActionEvent event){
        if (player2Index - 1 != player1Index){
            if (player2Index > -1){
                player2Index -= 1;
            }
            else{
                if (player1Index != playersTotal-1) player2Index = playersTotal - 1;
                else player2Index = playersTotal - 2;
            }
        }
        else{
            if (player1Index == -1) player2Index = -1;
            else player2Index -= 2;
        }
        if (player2Index != -1) setPlayer2();
        else setAnonimo(2);
    }
    void setPlayer1(){
        User user = users[player1Index];
        player1NameLabel.setText(user.name);
        player1WinsLabel.setText(String.valueOf(user.wins));
        player1LossesLabel.setText(String.valueOf(user.losses));
        player1TiesLabel.setText(String.valueOf(user.ties));
    }
    void setPlayer2(){
        User user = users[player2Index];
        player2NameLabel.setText(user.name);
        player2WinsLabel.setText(String.valueOf(user.wins));
        player2LossesLabel.setText(String.valueOf(user.losses));
        player2TiesLabel.setText(String.valueOf(user.ties));
    }
    void setAnonimo(int player){
        if(player == 1) {
            player1NameLabel.setText("Anônimo");
            player1WinsLabel.setText(String.valueOf(0));
            player1LossesLabel.setText(String.valueOf(0));
            player1TiesLabel.setText(String.valueOf(0));
        }
        else {
            player2NameLabel.setText("Anônimo");
            player2WinsLabel.setText(String.valueOf(0));
            player2LossesLabel.setText(String.valueOf(0));
            player2TiesLabel.setText(String.valueOf(0));
        }
    }
    @FXML
    private Rectangle selector;
    @FXML
    private Label selectWarningLabel;
    @FXML
    void setChess(MouseEvent event) {
        if(!Objects.equals(Main.game, "chess")) {
            Main.game = "chess";
            selector.setOpacity(1);
            double posx = ((ImageView)event.getSource()).getLayoutX();
            selector.setLayoutX(posx);
            selectWarningLabel.setOpacity(0);
        }
        else {
            Main.game = "none";
            selector.setOpacity(0);
        }
    }
    @FXML
    void setConnect4(MouseEvent event) {
        if(!Objects.equals(Main.game, "connect4")) {
            Main.game = "connect4";
            selector.setOpacity(1);
            double posx = ((ImageView)event.getSource()).getLayoutX();
            selector.setLayoutX(posx);
            selectWarningLabel.setOpacity(0);
        }
        else {
            Main.game = "none";
            selector.setOpacity(0);
        }
    }
    @FXML
    void setTicTacToe(MouseEvent event) {
        if(!Objects.equals(Main.game, "tictactoe")) {
            Main.game = "tictactoe";
            selector.setOpacity(1);
            double posx = ((ImageView)event.getSource()).getLayoutX();
            selector.setLayoutX(posx);
            selectWarningLabel.setOpacity(0);
        }
        else {
            Main.game = "none";
            selector.setOpacity(0);
        }
    }
    @FXML
    void confirm(ActionEvent event) throws IOException {
        Main.player1Index = player1Index;
        Main.player2Index = player2Index;
        if (Objects.equals(Main.game, "connect4")) startConnect4(event);
        if (Objects.equals(Main.game, "chess")) startChess(event);
        if (Objects.equals(Main.game, "tictactoe")) startTicTacToe(event);
        if (Objects.equals(Main.game, "none")) selectWarningLabel.setOpacity(1);
    }
    void startConnect4(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GamesFXMLs/Connect4Board.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void startChess(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GamesFXMLs/ChessBoard.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void startTicTacToe(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GamesFXMLs/TicTacToeBoard.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users = Main.GU.users;
        playersTotal = users.length;
        if (playersTotal > 0) {
            player1Index = 0;
            player2Index = -1;
            setPlayer1();
            setAnonimo(2);
            if (playersTotal > 1) {
                player2Index = 1;
                setPlayer2();
            }
        }
        else{
            player1Index = -1;
            player2Index = -1;
        }
    }

}
