package Games.Chess.chess;

import Games.Chess.boardgame.BoardException;

public class ChessException extends BoardException {
    private static final long serialVersionUID = 1L;
    public ChessException(String msg){
        super(msg);
    }
}
