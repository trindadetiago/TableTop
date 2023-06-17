package Games.Connect4;

import Games.IGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Math.abs;

import Menu.*;

public class Connect4Controller implements Initializable, IGameController {
    public static Connect4 connect4;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button start_button;
    @FXML
    private Button col1, col2, col3, col4, col5, col6, col7;
    private final Button [] cols = new Button[7];
    @FXML
    private Label warning_message;
    @FXML
    private Circle circle_pointer;
    Color red = Color.RED;
    Color blue = Color.BLUE;
    Color [] colors = {red, blue};
    @FXML
    VBox vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7;
    VBox[] vboxes = new VBox[7];
    @FXML
    private Button confirmReturnButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Button returnButton;
    @FXML
    private Pane blankPane;
    public void startGame(){
        connect4 = new Connect4();
        setGame();
    }
    private void setGame(){
        start_button.setOpacity(0);
        start_button.setDisable(true);
        for(int i = 0;i<7;i++) {
            cols[i].setDisable(false);
            cols[i].setOpacity(.15);
        }
        circle_pointer.setOpacity(1);
        warning_message.setText("Inicie o jogo jogador 1!");
        warning_message.setLayoutY(42);
        warning_message.setScaleX(1);
        warning_message.setScaleY(1);
        backMenuButton.setOpacity(1);
        backMenuButton.setDisable(false);
    }
    public void askReturn(){
        confirmReturnButton.setOpacity(abs(confirmReturnButton.getOpacity()-1));
        confirmReturnButton.setDisable(confirmReturnButton.getOpacity() != 1);
    }
    public void openEndScreen(){
        returnButton.setOpacity(1);
        returnButton.setDisable(false);

        for (int j=0;j<7;j++) {
            vboxes[j].setOpacity(.5);
        }
        blankPane.setDisable(false);
    }
    public void closeEndScreen(){
        returnButton.setOpacity(0);
        returnButton.setDisable(true);
        for (int j=0;j<7;j++) {
            vboxes[j].setOpacity(1);
        }
        blankPane.setDisable(true);
    }
    public void returnMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu-inicial.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void finishGame() throws IOException {
        for(int i = 0;i<7;i++) cols[i].setDisable(true);
        warning_message.setLayoutY(54);
        warning_message.setScaleX(1.5);
        warning_message.setScaleY(1.5);
        statsPlayers();
        openEndScreen();
    }
    void statsPlayers() throws IOException {
        if (connect4.winner == 0) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].ties += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].ties += 1;
        }
        if (connect4.winner == 1) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].wins += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].losses += 1;
        }
        if (connect4.winner == 2) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].losses += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].wins += 1;
        }
        Main.GU.SaveUsersDirectly();
    }
    public void playMove(int move) throws IOException {
        String msg = connect4.play_move(move);
        warning_message.setText(msg);
        if (!Objects.equals(msg, "Jogada inválida. Tente outra!")) {
            addCircle();
            circle_pointer.setFill(colors[abs(connect4.currentPlayer - 2)]);
        }
        if (connect4.winner != -1) finishGame();
    }
    public void addCircle(){
        int [] last_play = connect4.last_play;
        int player = connect4.currentPlayer;
        if(player != -1 & last_play[0] != -1) {
            Circle circle = (Circle) vboxes[last_play[1]].getChildren().get(last_play[0]);
            circle.setOpacity(1);
            circle.setFill(colors[player - 1]);
        }
    }
    public void pointCircle(MouseEvent event){
        circle_pointer.setOpacity(1);
        double xposition = ((Button)event.getSource()).getLayoutX() + 24;
        circle_pointer.setLayoutX(xposition);
    }
    public void hideCircle(){
        circle_pointer.setOpacity(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start_button.setOpacity(1);
        start_button.setDisable(false);

        cols[0] = col1;
        cols[1] = col2;
        cols[2] = col3;
        cols[3] = col4;
        cols[4] = col5;
        cols[5] = col6;
        cols[6] = col7;

        vboxes[0] = vbox1;
        vboxes[1] = vbox2;
        vboxes[2] = vbox3;
        vboxes[3] = vbox4;
        vboxes[4] = vbox5;
        vboxes[5] = vbox6;
        vboxes[6] = vbox7;

        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                vboxes[j].getChildren().get(i).setOpacity(0);
                cols[j].setOpacity(0);
            }
        }
        circle_pointer.setOpacity(0);
    }
    public void col1() throws IOException {
        playMove(1);}
    public void col2() throws IOException {
        playMove(2);}
    public void col3() throws IOException {
        playMove(3);}
    public void col4() throws IOException {
        playMove(4);}
    public void col5() throws IOException {
        playMove(5);}
    public void col6() throws IOException {
        playMove(6);}
    public void col7() throws IOException {
        playMove(7);}
}
