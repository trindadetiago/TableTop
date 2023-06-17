
package Games.Chess;

import Games.Chess.chess.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Games.Chess.application.Program;
import javafx.stage.Stage;

public class ChessController implements Initializable, IGameController {

    //Chamando a classe principal do xadrez do terminal
    static Program program;
    static ChessMatch chessMatch;

    //Declaração das imagens

    @FXML
    private ImageView checkmate;

    @FXML
    private ImageView BB_1;

    @FXML
    private ImageView BB_2;

    @FXML
    private ImageView BK;

    @FXML
    private ImageView BK_1;

    @FXML
    private ImageView BK_2;

    @FXML
    private ImageView BP_1;

    @FXML
    private ImageView BP_2;

    @FXML
    private ImageView BP_3;

    @FXML
    private ImageView BP_4;

    @FXML
    private ImageView BP_5;

    @FXML
    private ImageView BP_6;

    @FXML
    private ImageView BP_7;

    @FXML
    private ImageView BP_8;

    @FXML
    private ImageView BQ;

    @FXML
    private ImageView BR_1;

    @FXML
    private ImageView BR_2;

    @FXML
    private ImageView WB_1;

    @FXML
    private ImageView WB_2;

    @FXML
    private ImageView WK;

    @FXML
    private ImageView WK_1;

    @FXML
    private ImageView WK_2;

    @FXML
    private ImageView WP_1;

    @FXML
    private ImageView WP_2;

    @FXML
    private ImageView WP_3;

    @FXML
    private ImageView WP_4;

    @FXML
    private ImageView WP_5;

    @FXML
    private ImageView WP_6;

    @FXML
    private ImageView WP_7;

    @FXML
    private ImageView WP_8;

    @FXML
    private ImageView WQ;

    @FXML
    private ImageView WR_1;

    @FXML
    private ImageView WR_2;

    @FXML
    private ImageView prom_BB;

    @FXML
    private ImageView prom_BK;

    @FXML
    private ImageView prom_BQ;

    @FXML
    private ImageView prom_BR;

    @FXML
    private ImageView prom_WB;

    @FXML
    private ImageView prom_WK;

    @FXML
    private ImageView prom_WQ;

    @FXML
    private ImageView prom_WR;


    //Relacionando as coordenadas de cada quadrado com uma posição do tabuleiro
    int[] positions_x = {0, 60, 120, 180, 240, 300, 360, 420};
    char[] coord_x = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    int[] positions_y = {60, 120, 180, 240, 300, 360, 420, 480};
    int[] coord_y = {8, 7, 6, 5, 4, 3, 2, 1};


    //Relacionando as coordenadas do jogo com cada peão para promoção
    int [] promoted_coor = { 120 ,180, 240, 300};
    char [] pieces_promoted_y = { 'B','N', 'R', 'Q'};

    //Array com todas as peças
    ImageView[] allPieces = new ImageView[32];
    ImageView[] whitePawns = new ImageView[8];
    ImageView[] blackPawns = new ImageView[8];

    ImageView piece;
    ImageView promove;

    //Strings que vão receber as coodernadas do xadrez e transformá-las em uma posição existente no tabuleiro
    private String s;
    private char p;

    double posXtemp;
    double posYtemp;

    public static boolean block = false;
    public boolean promoting = false;
    public boolean isPawn = false;
    public ImageView my_piece;
    @FXML
    //Função responsável por selecionar uma peça para ser movida
    void setPiece(MouseEvent event) {
        char let = '\0';
        int num = 0;

        piece = (ImageView) event.getSource();
        double posX = piece.getLayoutX();
        double posY = piece.getLayoutY();
        posXtemp = posX;
        posYtemp = posY;

        for (int i = 0; i < 8; i++) {
            if (positions_x[i] == posX) {
                let = coord_x[i];
            }
            if (positions_y[i] == posY) {
                num = coord_y[i];
            }
        }
        if(checkIfIsPawn(posX,posY)) isPawn=true;
        else isPawn=false;
        my_piece=piece;

        s = String.valueOf(let) + num;
        System.out.println("Source: " + s);

        program.setSource(s);
    }
    boolean checkIfIsKing(){
        int posxW = (int) WK.getLayoutX();
        int posyW = (int) WK.getLayoutY();
        int posxB = (int) BK.getLayoutX();
        int posyB = (int) BK.getLayoutY();
        if(posXtemp == posxW & posYtemp == posyW) return true;
        if(posXtemp == posxB & posYtemp == posyB) return true;
        return false;
    }
    boolean checkIfThereIsPiece(double posx, double posy){
        for (int i = 0; i < 32; i++){
            if(allPieces[i].getLayoutX()==posx & allPieces[i].getLayoutY()==posy) return true;
        }
        return false;
    }
    double tX;
    double tY;
    boolean checkIfCanPromote(double posX, double posY){//60^ , 480v  60
        if(!isPawn) return false;
        if(posY != 60 & posY != 480) return false;
        if(my_piece.getLayoutX() != posX & my_piece.getLayoutX() != posX+60 & my_piece.getLayoutX() != posX-60)return false;
        if(my_piece.getLayoutX()==posX & checkIfThereIsPiece(posX, posY)) return false;
        if(my_piece.getLayoutX()+60==posX & !checkIfThereIsPiece(posX, posY)) return false;
        if (my_piece.getLayoutX()-60==posX & !checkIfThereIsPiece(posX, posY)) return false;
        if(chessMatch.getCurrentPlayer()== Color.WHITE) {
            if (my_piece.getLayoutY() == 120) {
                tX=posX;
                tY=posY;
                return true;
            }
        }
        if(chessMatch.getCurrentPlayer()== Color.BLACK) {
            if (my_piece.getLayoutY() == 420) {
                tX=posX;
                tY=posY;
                return true;
            }
        }
        return false;
    }
    boolean checkIfIsPawn(double posx, double posy){
        for (int i = 0;i<8;i++){
            if (whitePawns[i].getLayoutX()==posx & whitePawns[i].getLayoutY()==posy) return true;
            if (blackPawns[i].getLayoutX()==posx & blackPawns[i].getLayoutY()==posy) return true;
        }
        return false;
    }
    @FXML
    //Função responsável por mover a peça selecionada
    void bringObject(MouseEvent event) throws IOException {
       if(Program.source == null) return;
       if(chessMatch.getCheck() & !checkIfIsKing()) return;
       if(promoting) return;
       if(!chessMatch.getCheckMate()) {
            block = false;

            if (piece != null) {
                double posX, posY;
                if(event == null){
                    posX = tX;
                    posY = tY;
                    tX=-1;
                    tY=-1;
                }
                else {
                    posX = ((Rectangle) event.getSource()).getLayoutX();
                    posY = ((Rectangle) event.getSource()).getLayoutY();
                }

                char let = '\0';
                int num = 0;

                for (int i = 0; i < 8; i++) {
                    if (positions_x[i] == posX) {
                        let = coord_x[i];
                    }
                    if (positions_y[i] == posY) {
                        num = coord_y[i];
                    }
                    s = String.valueOf(let) + num;
            }
            System.out.println("Target: " + s);

            if(tX != -1) {
                if (checkIfCanPromote(posX, posY)) {
                    promoting = true;
                    enableProm();
                    return;
                }
            }
            //Fazendo com que as peças apareçam só no momento da promoção
            program.setTarget(s);
            if (block) return;

            //Fazendo com que as peças movidas não influenciem no jogo
            for (int i = 0; i < 32; i++) {
                if (allPieces[i].getLayoutX() == posX && allPieces[i].getLayoutY() == posY) {
                    if (allPieces[i].getLayoutX() == posX && allPieces[i].getLayoutY() == posY) {
                        allPieces[i].setLayoutX(10000);
                        allPieces[i].setLayoutX(10000);
                        allPieces[i].setDisable(true);
                        allPieces[i].setOpacity(0);
                    }
                }
            }
            //Movendo a peça

            piece.setLayoutX(posX);
            piece.setLayoutY(posY);

            if (chessMatch.getCurrentPlayer() == Color.WHITE & !block) {
                disableBlacks();
            }
        if (chessMatch.getCurrentPlayer() == Color.BLACK & !block) {
            disableWhites();
        }
        }
           //program.setTarget(s);
    }
    else{
       checkmate.setOpacity(1);    //Mostrando o botão de checkmate
       statsPlayers();
        }
    }
    void statsPlayers() throws IOException {
        if (Program.winner == 0) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].ties += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].ties += 1;
        }
        if (Program.winner == 1) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].wins += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].losses += 1;
        }
        if (Program.winner == 2) {
            if (Main.player1Index != -1) Main.GU.users[Main.player1Index].losses += 1;
            if (Main.player2Index != -1) Main.GU.users[Main.player2Index].wins += 1;
        }
        Main.GU.SaveUsersDirectly();
    }
    @FXML
    //Função responsável pela promoção do peão
    void promote(MouseEvent event) throws IOException {
        if(!promoting)return;
        promove = (ImageView) event.getSource();
        double posX = promove.getLayoutX();
        for (int i = 0; i < 4; i++) {
            if (promoted_coor[i] == posX) {
                p = pieces_promoted_y[i];
                Program.p = String.valueOf(p);

                System.out.println("Promoted to: " + p);
                if (p == 'R') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/white_rook.png").openStream()));
                }
                if (p == 'B') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/white_bishop.png").openStream()));
                }
                if (p == 'N') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/white_knight.png").openStream()));
                }
                if (p == 'Q') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/white_queen.png").openStream()));
                }
            }
            if (chessMatch.getCurrentPlayer() == Color.BLACK) {
                if (p == 'R') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/black_rook.png").openStream()));
                }
                if (p == 'B') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/black_bishop.png").openStream()));
                }
                if (p == 'N') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/black_knight.png").openStream()));
                }
                if (p == 'Q') {
                    piece.setImage(new Image(ChessController.class.getResource("/ChessPiecesImagens/black_queen.png").openStream()));
                }
            }
        }
        promoting=false;
        disableProm();
        bringObject(null);
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void returnMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu-inicial.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    //Função que inicializa o programa
    public void initialize(URL url, ResourceBundle resourceBundle) {
        program = new Program();
        chessMatch = new ChessMatch();
        Program.chessMatch = chessMatch;

        //Criando uma Array com todas as peças em ImageView
        allPieces[0] = WR_1;
        allPieces[1] = WR_2;
        allPieces[2] = WB_1;
        allPieces[3] = WB_2;
        allPieces[4] = WK;
        allPieces[5] = WK_1;
        allPieces[6] = WK_2;
        allPieces[7] = WQ;
        allPieces[8] = WP_1;
        allPieces[9] = WP_2;
        allPieces[10] = WP_3;
        allPieces[11] = WP_4;
        allPieces[12] = WP_5;
        allPieces[13] = WP_6;
        allPieces[14] = WP_7;
        allPieces[15] = WP_8;
        allPieces[16] = BR_1;
        allPieces[17] = BR_2;
        allPieces[18] = BB_1;
        allPieces[19] = BB_2;
        allPieces[20] = BK;
        allPieces[21] = BK_1;
        allPieces[22] = BK_2;
        allPieces[23] = BQ;
        allPieces[24] = BP_1;
        allPieces[25] = BP_2;
        allPieces[26] = BP_3;
        allPieces[27] = BP_4;
        allPieces[28] = BP_5;
        allPieces[29] = BP_6;
        allPieces[30] = BP_7;
        allPieces[31] = BP_8;

        disableBlacks();
        disableProm();

        whitePawns[0] = WP_1;
        whitePawns[1] = WP_2;
        whitePawns[2] = WP_3;
        whitePawns[3] = WP_4;
        whitePawns[4] = WP_5;
        whitePawns[5] = WP_6;
        whitePawns[6] = WP_7;
        whitePawns[7] = WP_8;
        blackPawns[0] = BP_1;
        blackPawns[1] = BP_2;
        blackPawns[2] = BP_3;
        blackPawns[3] = BP_4;
        blackPawns[4] = BP_5;
        blackPawns[5] = BP_6;
        blackPawns[6] = BP_7;
        blackPawns[7] = BP_8;
    }
    void disableBlacks(){
        BR_1.setDisable(true);
        BR_2.setDisable(true);
        BB_1.setDisable(true);
        BB_2.setDisable(true);
        BK.setDisable(true);
        BK_1.setDisable(true);
        BK_2.setDisable(true);
        BQ.setDisable(true);
        BP_1.setDisable(true);
        BP_2.setDisable(true);
        BP_3.setDisable(true);
        BP_4.setDisable(true);
        BP_5.setDisable(true);
        BP_6.setDisable(true);
        BP_7.setDisable(true);
        BP_8.setDisable(true);

        WR_1.setOpacity(1);
        WR_2.setOpacity(1);
        WB_1.setOpacity(1);
        WB_2.setOpacity(1);
        WK.setOpacity(1);
        WK_1.setOpacity(1);
        WK_2.setOpacity(1);
        WQ.setOpacity(1);
        WP_1.setOpacity(1);
        WP_2.setOpacity(1);
        WP_3.setOpacity(1);
        WP_4.setOpacity(1);
        WP_5.setOpacity(1);
        WP_6.setOpacity(1);
        WP_7.setOpacity(1);
        WP_8.setOpacity(1);

        WR_1.setDisable(false);
        WR_2.setDisable(false);
        WB_1.setDisable(false);
        WB_2.setDisable(false);
        WK.setDisable(false);
        WK_1.setDisable(false);
        WK_2.setDisable(false);
        WQ.setDisable(false);
        WP_1.setDisable(false);
        WP_2.setDisable(false);
        WP_3.setDisable(false);
        WP_4.setDisable(false);
        WP_5.setDisable(false);
        WP_6.setDisable(false);
        WP_7.setDisable(false);
        WP_8.setDisable(false);

        BR_1.setOpacity(0.8);
        BR_2.setOpacity(0.8);
        BB_1.setOpacity(0.8);
        BB_2.setOpacity(0.8);
        BK.setOpacity(0.8);
        BK_1.setOpacity(0.8);
        BK_2.setOpacity(0.8);
        BQ.setOpacity(0.8);
        BP_1.setOpacity(0.8);
        BP_2.setOpacity(0.8);
        BP_3.setOpacity(0.8);
        BP_4.setOpacity(0.8);
        BP_5.setOpacity(0.8);
        BP_6.setOpacity(0.8);
        BP_7.setOpacity(0.8);
        BP_8.setOpacity(0.8);
    }
    void disableWhites(){
        WR_1.setDisable(true);
        WR_2.setDisable(true);
        WB_1.setDisable(true);
        WB_2.setDisable(true);
        WK.setDisable(true);
        WK_1.setDisable(true);
        WK_2.setDisable(true);
        WQ.setDisable(true);
        WP_1.setDisable(true);
        WP_2.setDisable(true);
        WP_3.setDisable(true);
        WP_4.setDisable(true);
        WP_5.setDisable(true);
        WP_6.setDisable(true);
        WP_7.setDisable(true);
        WP_8.setDisable(true);

        WR_1.setOpacity(0.5);
        WR_2.setOpacity(0.5);
        WB_1.setOpacity(0.5);
        WB_2.setOpacity(0.5);
        WK.setOpacity(0.5);
        WK_1.setOpacity(0.5);
        WK_2.setOpacity(0.5);
        WQ.setOpacity(0.5);
        WP_1.setOpacity(0.5);
        WP_2.setOpacity(0.5);
        WP_3.setOpacity(0.5);
        WP_4.setOpacity(0.5);
        WP_5.setOpacity(0.5);
        WP_6.setOpacity(0.5);
        WP_7.setOpacity(0.5);
        WP_8.setOpacity(0.5);

        BR_1.setDisable(false);
        BR_2.setDisable(false);
        BB_1.setDisable(false);
        BB_2.setDisable(false);
        BK.setDisable(false);
        BK_1.setDisable(false);
        BK_2.setDisable(false);
        BQ.setDisable(false);
        BP_1.setDisable(false);
        BP_2.setDisable(false);
        BP_3.setDisable(false);
        BP_4.setDisable(false);
        BP_5.setDisable(false);
        BP_6.setDisable(false);
        BP_7.setDisable(false);
        BP_8.setDisable(false);

        BR_1.setOpacity(1);
        BR_2.setOpacity(1);
        BB_1.setOpacity(1);
        BB_2.setOpacity(1);
        BK.setOpacity(1);
        BK_1.setOpacity(1);
        BK_2.setOpacity(1);
        BQ.setOpacity(1);
        BP_1.setOpacity(1);
        BP_2.setOpacity(1);
        BP_3.setOpacity(1);
        BP_4.setOpacity(1);
        BP_5.setOpacity(1);
        BP_6.setOpacity(1);
        BP_7.setOpacity(1);
        BP_8.setOpacity(1);

    }
    void disableProm(){

        prom_WB.setDisable(true);
        prom_WK.setDisable(true);
        prom_WR.setDisable(true);
        prom_WQ.setDisable(true);

        prom_WB.setOpacity(0);
        prom_WK.setOpacity(0);
        prom_WR.setOpacity(0);
        prom_WQ.setOpacity(0);

        prom_BB.setDisable(true);
        prom_BK.setDisable(true);
        prom_BR.setDisable(true);
        prom_BQ.setDisable(true);

        prom_BB.setOpacity(0);
        prom_BK.setOpacity(0);
        prom_BR.setOpacity(0);
        prom_BQ.setOpacity(0);
    }
    void enableProm(){
        if(WB_1.getLayoutY() == 120 || WP_2.getLayoutY()== 120 ||WP_3.getLayoutY()== 120 ||WP_4.getLayoutY()== 120
                ||WP_5.getLayoutY()== 120 ||WP_6.getLayoutY()== 120 ||WP_7.getLayoutY()== 120 ||WP_8.getLayoutY()== 120){
            prom_WB.setDisable(false);
            prom_WK.setDisable(false);
            prom_WR.setDisable(false);
            prom_WQ.setDisable(false);

            prom_WB.setOpacity(1);
            prom_WK.setOpacity(1);
            prom_WR.setOpacity(1);
            prom_WQ.setOpacity(1);

        }
        if(BB_1.getLayoutY() == 420 || BP_2.getLayoutY()== 420 ||BP_3.getLayoutY()== 420 ||BP_4.getLayoutY()== 420
                ||BP_5.getLayoutY()== 420 ||BP_6.getLayoutY()== 420 ||BP_7.getLayoutY()== 420 ||BP_8.getLayoutY()== 420){
            prom_BB.setDisable(false);
            prom_BK.setDisable(false);
            prom_BR.setDisable(false);
            prom_BQ.setDisable(false);

            prom_BB.setOpacity(1);
            prom_BK.setOpacity(1);
            prom_BR.setOpacity(1);
            prom_BQ.setOpacity(1);

        }
        if(WP_1.getLayoutY() != 120 && WP_2.getLayoutY()!= 120 && WP_3.getLayoutY()!= 120 && WP_4.getLayoutY()!= 120
                && WP_5.getLayoutY()!= 120 && WP_6.getLayoutY()!= 120 && WP_7.getLayoutY()!= 120 && WP_8.getLayoutY() != 120){
            prom_WB.setDisable(true);
            prom_WK.setDisable(true);
            prom_WR.setDisable(true);
            prom_WQ.setDisable(true);

            prom_WB.setOpacity(0);
            prom_WK.setOpacity(0);
            prom_WR.setOpacity(0);
            prom_WQ.setOpacity(0);


        }
        if(BP_1.getLayoutY() != 420 && BP_2.getLayoutY() != 420 && BP_3.getLayoutY() != 420 && BP_4.getLayoutY() != 420
                && BP_5.getLayoutY() != 420 && BP_6.getLayoutY() != 420 && BP_7.getLayoutY() != 420 && BP_8.getLayoutY() != 420) {
            prom_BB.setDisable(true);
            prom_BK.setDisable(true);
            prom_BR.setDisable(true);
            prom_BQ.setDisable(true);

            prom_BB.setOpacity(0);
            prom_BK.setOpacity(0);
            prom_BR.setOpacity(0);
            prom_BQ.setOpacity(0);
        }
    }
}
