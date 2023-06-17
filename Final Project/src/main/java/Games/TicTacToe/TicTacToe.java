package Games.TicTacToe;

import java.util.Scanner;
public class TicTacToe {
    public int [][] board = {{0,0,0},{0,0,0},{0,0,0}};
    public int [][] coded_board = {{7,8,9},{4,5,6},{1,2,3}};
    public int winner = 0;
    public int currentPlayer = 1;
    public boolean play(int move){
        if (winner == 0){
            //printBoard();
            //int move = getAction();
            if (!checkPossibility(move)) return false;
            putPiece(move);
            if (checkWin()) winner = currentPlayer;
            if (checkTie()) winner = -1;
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            printBoard();
        }
        if (winner != 0) {
            printBoard();
            System.out.printf("\nVencedor é %d", winner);
        }
        return true;
    }
    public void printBoard(){
        for (int i = 0; i < 3; i++){
            System.out.print("\n");
            for (int j = 0; j < 3; j++){
                System.out.print(board[i][j]);
            }
        }
    }
    public int getAction(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nDigite o número da casa jogador %d", currentPlayer);
        return sc.nextInt();
    }
    public boolean checkPossibility(int place){ //true - can; false - cannot;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (coded_board[i][j] == place){
                    return (board[i][j] == 0);
                }
            }
        }
        return false;
    }
    public void putPiece(int place){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (coded_board[i][j] == place){
                    board[i][j] = currentPlayer;
                }
            }
        }
    }
    public boolean checkWin(){
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return true;
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }

        return false;
    }
    public boolean checkTie(){ //true - tie; false - not tie;
        int count = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (board[i][j] != 0) count++;
            }
        }
        if (count == 9) return true;
        return false;
    }
}
