import java.util.ArrayList;

/**
 * Model for Connect Four Game
 * @author Vahid
 */
public class ConnectFourModel {
    public static final int ROW = 6;
    public static final int COL = 7;
    private static final char RED = 'R';
    private static final char YELLOW = 'Y';

    public enum Status {RED_WON, YELLOW_WON, GAME_ENDED_IN_A_TIE}

    private final char[][] board;
    private boolean turn;
    private Status status;

    private ArrayList<ConnectFourView> views;

    /**
     * Constructor for the Model
     */
    public ConnectFourModel(){
        board = new char[ROW][COL];
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                board[i][j] = ' ';
            }
        }
        this.turn = true; //RED is first turn
        this.status = Status.GAME_ENDED_IN_A_TIE;
        this.views = new ArrayList<>();
    }

    /**
     * Get the current turn
     * @return  true if red player's turn, false for yellow player's turn
     */
    public boolean getTurn() {
        return turn;
    }

    /**
     * Get the current status of the game
     * @return Status of the game
     */
    public Status getStatus(){ return status; }

    /**
     * Change the turn of the players
     */
    private void changeTurn() {
        turn = !turn;
    }

    /**
     * Add a view to the model
     * @param view the view associated with the model
     */
    public void addConnectFourView(ConnectFourView view){
        this.views.add(view);
    }

    /**
     * Restarts the game by clearing the board and initializing the turn to red
     */
    public void restartGame(){
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                board[i][j] = ' ';
            }
        }
        this.turn = true; //Make RED first turn again
        for (ConnectFourView view: this.views){
            view.handleRestartGame(this);
        }
    }

    /**
     * Update the status of the game if there is a winner
     */
    private void updateStatus(){
        if(checkVerticalWinner() || checkHorizontalWinner() || checkDiagonalWinner()){
            for(ConnectFourView view: this.views){
                view.handleWinner(this.status);
            }
        }
    }

    /**
     * Helper method used to check what colour won the game
     * @return true if board index is red or yellow, false otherwise
     */
    private boolean validColour(int x, int y){
        if(board[x][y] == RED){
            this.status = Status.RED_WON;
            return true;
        }
        else if(board[x][y] == YELLOW){
            this.status = Status.YELLOW_WON;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Check if there was a connect 4 in a column of the board
     * @return true if there was a connect 4 in a column of the board
     */
    private boolean checkVerticalWinner(){
        for(int i = 0; i < ROW - 3 ; i++){
            for(int j = 0; j < COL; j++){
                if(board[i][j] == board[i+1][j] && board[i+2][j] == board[i+3][j] && board[i][j] == board[i+3][j]){
                    boolean gameOver = validColour(i, j);
                    if(gameOver) return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if there was a connect 4 in a row of the board
     * @return true if there was a connect 4 in a row of the board
     */
    private boolean checkHorizontalWinner(){
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL - 3; j++){
                if(board[i][j] == board[i][j+1] && board[i][j+2] == board[i][j+3] && board[i][j] == board[i][j+3]){
                    boolean gameOver = validColour(i, j);
                    if(gameOver) return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if there was a connect 4 in a diagonal of the board
     * @return true if there was a connect 4 in a diagonal of the board
     */
    private boolean checkDiagonalWinner(){
        // Ascending Diagonals
        for(int i = 3 ; i < ROW; i++) {
            for (int j = 0; j < COL - 3; j++) {
                if (board[i][j] == board[i-1][j+1] && board[i-2][j+2] == board[i-3][j+3] && board[i][j] == board[i-3][j+3]){
                    boolean gameOver = validColour(i, j);
                    if(gameOver) return true;
                }
            }
        }
        // Descending Diagonals
        for(int i = 3; i < ROW; i++) {
            for (int j = 3; j < COL; j++) {
                if (board[i][j] == board[i-1][j-1] && board[i-2][j-2] == board[i-3][j-3] && board[i][j] == board[i-3][j-3]){
                    boolean gameOver = validColour(i, j);
                    if(gameOver) return true;
                }
            }
        }
        return false;
    }

    /**
     * Populates the board with the turn players piece
     * @param x Horizontal coordinate of mouse click on the board
     * @param y Vertical coordinate of mouse click on the board
     */
    public void play(int x, int y){
        if(board[x][y] != ' ') return;  //If you click on an already clicked on piece
        //Loop from bottom row of column we clicked on, up to the first empty row
        for(int i = ROW - 1; i >=0; i--){
            if(board[i][y] == ' '){
                x = i;
                break;
            }
        }
        board[x][y] = turn? RED : YELLOW;
        for(ConnectFourView view: this.views){
            view.handleTurn(new ConnectFourEvent(this, x, y));
        }
        updateStatus();
        changeTurn();
    }

}
