package Games.Connect4;

public class Connect4 {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final int EMPTY = -1;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    int[][] board;
    private final int[] columnHeights;

    int currentPlayer;

    public int winner = -1;
    public int [] last_play = new int[2];
    public Connect4() {
        board = new int[ROWS][COLUMNS];
        columnHeights = new int[COLUMNS];
        currentPlayer = PLAYER_1;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }
    public String play_move(int move) {

        int column = move - 1;

        if (column < 0 || column >= COLUMNS || columnHeights[column] == ROWS) {
            last_play[0] = -1;
            last_play[1] = -1;
            return "Jogada inválida. Tente outra!";
        }

        int row = ROWS - 1 - columnHeights[column];
        board[row][column] = currentPlayer;
        columnHeights[column]++;
        last_play[0] = row;
        last_play[1] = column;
        if (checkForWin(row, column)) {
            if(currentPlayer == PLAYER_1) winner = 1;
            if(currentPlayer == PLAYER_2) winner = 2;
            currentPlayer = (currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1;
            return "Jogador " + ((currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1) + " venceu! Parabéns!";
        }

        if (checkForTie()) {
            winner = 0;
            return "Empate! Pode-se dizer que os dois venceram...";
        }

        currentPlayer = (currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1;
        return "Jogador " + currentPlayer + ", faça sua jogada! ";
    }

    private boolean checkForWin(int row, int col) {
        int count = 0;
        for (int c = 0; c < COLUMNS; c++) {
            if (board[row][c] == currentPlayer) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        count = 0;
        for (int r = 0; r < ROWS; r++) {
            if (board[r][col] == currentPlayer) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        count = 0;
        int startRow = row - Math.min(row, col);
        int startCol = col - Math.min(row, col);
        for (int r = startRow, c = startCol; r < ROWS && c < COLUMNS; r++, c++) {
            if (board[r][c] == currentPlayer) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        startRow = row + Math.min(ROWS - 1 - row, col);
        startCol = col - Math.min(ROWS - 1 - row, col);
        for (int r = startRow, c = startCol; r >= 0 && c < COLUMNS; r--, c++) {
            if (board[r][c] == currentPlayer) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }

    private boolean checkForTie() {
        for (int col = 0; col < COLUMNS; col++) {
            if (columnHeights[col] < ROWS) {
                return false;
            }
        }
        return true;
    }

}
