package Games.Chess.application;


import Games.Chess.ChessController;
import Games.Chess.chess.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Program {

    public static ChessPosition source;
    public static int winner = 0;

    public static ChessPosition target;

    public void setSource(String s){
        source = UI.readChessPosition(s);
    }
    public void setTarget(String s){
        target = UI.readChessPosition(s);
        play();
    }
    public static String p;
    public static ChessMatch chessMatch;
    public static void play() {

        Scanner sc = new Scanner(System.in);

        List<ChessPiece> captured = new ArrayList<>();

        if (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Target: ");

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (ChessController.block) return;

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if (ChessMatch.getPromoted() != null) {
                        System.out.print("Enter piece for promotion (B/N/R/Q): ");
                        String type = p;
                        while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
                            System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                            type = p;
                        }
                        chessMatch.replacePromotedPiece(type);
                }
                source = null;
            }

            catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        else{
            if (chessMatch.getCurrentPlayer() == Color.WHITE){
                winner = 1;
            }
            else winner = 2;
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);


    }
}