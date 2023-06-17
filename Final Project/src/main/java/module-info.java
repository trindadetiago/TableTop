module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens Menu to javafx.fxml;
    exports Menu;
    opens Users to javafx.fxml;
    exports Users;
    opens Games.Connect4 to javafx.fxml;
    exports Games.Connect4;
    opens Games.Chess to javafx.fxml;
    exports Games.Chess;
    opens Games.TicTacToe to javafx.fxml;
    exports Games.TicTacToe;


    opens GamesFXMLs to javafx.fxml;
    opens ChessPiecesImagens to javafx.fxml;
    opens TicTacToeImagens to javafx.fxml;
}