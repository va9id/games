/**
 * Interface for the View to implement
 * @author Vahid
 */
public interface ConnectFourInterface {

    /**
     * Handle each player's turn
     * @param event event of the event generated through each player's click
     */
    void handleTurn(ConnectFourEvent event);

    /**
     * Handle when a player wins the game
     * @param status status of the game
     */
    void handleWinner(ConnectFourModel.Status status);

    /**
     * Handle restarting the game
     * @param model the model of the game
     */
    void handleRestartGame(ConnectFourModel model);

    /**
     * Handle the user being able to end the game
     */
    void handleEndGameButton();

}
