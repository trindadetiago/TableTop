package Games.TicTacToe;
import Games.IGameController;
import Menu.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable, IGameController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private TicTacToe ttt;
    @FXML
    private ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9;
    private ImageView[] imageViews = new ImageView[9];
    private Image circle;
    private Image x;
    private int [] used = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private boolean isOver = false;
    private Image player(){
        if(ttt.currentPlayer==1) return x;
        else return circle;
    }
    @FXML
    void play1(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(1)){
            i1.setOpacity(1);
            i1.setImage(player());
            used[0]=1;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play2(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(2)){
            i2.setOpacity(2);
            i2.setImage(player());
            used[1]=2;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play3(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(3)){
            i3.setOpacity(3);
            i3.setImage(player());
            used[2]=3;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play4(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(4)){
            i4.setOpacity(4);
            i4.setImage(player());
            used[3]=4;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play5(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(5)){
            i5.setOpacity(5);
            i5.setImage(player());
            used[4]=5;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play6(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(6)){
            i6.setOpacity(6);
            i6.setImage(player());
            used[5]=6;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play7(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(7)){
            i7.setOpacity(7);
            i7.setImage(player());
            used[6]=7;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play8(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(8)){
            i8.setOpacity(8);
            i8.setImage(player());
            used[7]=8;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }

    @FXML
    void play9(MouseEvent event) throws IOException {
        if(ttt.winner==0 & ttt.play(9)){
            i9.setOpacity(9);
            i9.setImage(player());
            used[8]=9;
        }
        if(ttt.winner!=0 & !isOver) {
            isOver=true;
            endGame();
        }
    }
    void statsPlayers() throws IOException {
        if (ttt.winner == -1) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].ties += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].ties += 1;
        }
        if (ttt.winner == 1) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].wins += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].losses += 1;
        }
        if (ttt.winner == 2) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].losses += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].wins += 1;
        }
        Main.GU.SaveUsersDirectly();
    }
    @FXML
    private Button returnButton;
    @FXML
    private Pane blankPane;
    @FXML
    private Label winMessage;
    @FXML
    private ImageView boardImage;
    public void endGame() throws IOException {
        //statsPlayers();
        openEndScreen();
    }
    public void openEndScreen(){
        returnButton.setOpacity(1);
        returnButton.setDisable(false);
        blankPane.setDisable(false);
        for(int i=0;i<9;i++){
            if(used[i] != -1) imageViews[i].setOpacity(.5);
        }
        if(ttt.winner==1) winMessage.setText("PARABÉNS JOGADOR 1! VOCÊ VENCEU");
        if(ttt.winner==2) winMessage.setText("PARABÉNS JOGADOR 2! VOCÊ VENCEU");
        else winMessage.setText("DEU VELHA... OS DOIS GANHAM!!... ");
        winMessage.setOpacity(1);
        boardImage.setOpacity(.3);
    }
    public void closeEndScreen(){
        returnButton.setOpacity(0);
        returnButton.setDisable(true);
        blankPane.setDisable(true);
        for(int i=0;i<9;i++){
            if(used[i] != -1) imageViews[i].setOpacity(1);
        }
        winMessage.setOpacity(0);
        boardImage.setOpacity(1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ttt = new TicTacToe();

        imageViews[0]=i1;
        imageViews[1]=i2;
        imageViews[2]=i3;
        imageViews[3]=i4;
        imageViews[4]=i5;
        imageViews[5]=i6;
        imageViews[6]=i7;
        imageViews[7]=i8;
        imageViews[8]=i9;

        for(int i=0;i<9;i++){
            imageViews[i].setOpacity(0);
        }
        try {
            circle = new Image(getClass().getResource("/TicTacToeImagens/circle-icon.png").openStream());
            x = new Image(getClass().getResource("/TicTacToeImagens/X-icon.png").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        winMessage.setOpacity(0);
    }

    @Override
    public void returnMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu-inicial.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}