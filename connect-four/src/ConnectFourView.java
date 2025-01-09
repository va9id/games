import javax.swing.*;
import java.awt.*;

/**
 * View for Connect Four Game
 * @author Vahid
 */
public class ConnectFourView extends JFrame implements ConnectFourInterface{

    private JButton[][] buttons;
    private JButton restartGameButton;

    /**
     * Constructor for the View
     */
    public ConnectFourView(){
        super("Connect Four");

        this.setLayout(new GridLayout(7, 7, 5, 5));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);

        ConnectFourModel model = new ConnectFourModel();
        model.addConnectFourView(this);

        this.buttons = new JButton[ConnectFourModel.ROW][ConnectFourModel.COL];
        this.restartGameButton = new JButton("Restart");

        this.restartGameButton.addActionListener(e->model.restartGame());

        JButton endGameButton = new JButton("End Game");
        endGameButton.addActionListener(e->handleEndGameButton());

        ConnectFourController controller = new ConnectFourController(model);

        for(int i = 0; i < ConnectFourModel.ROW; i++) {
            for (int j = 0; j < ConnectFourModel.COL; j++) {
                JButton button = new JButton();
                button.setActionCommand(i + " " + j);
                button.addActionListener(controller);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createBevelBorder(1, Color.BLUE, Color.BLUE));
                this.buttons[i][j] = button;
                this.add(button);
            }
        }
        this.add(endGameButton);
        this.add(restartGameButton);
        this.setVisible(true);
    }

    /**
     * Handles each turn that is played
     * @param event the event generated through each player's click
     */
    @Override
    public void handleTurn(ConnectFourEvent event) {
        ConnectFourModel m = (ConnectFourModel) event.getSource();
        if (m.getTurn()){
            buttons[event.getX()][event.getY()].setBackground(Color.RED);
        }
        else{
            buttons[event.getX()][event.getY()].setBackground(Color.YELLOW);
        }
    }

    /**
     * Handles the game when a player wins by connecting 4 pieces
     * @param status the status of the game
     */
    @Override
    public void handleWinner(ConnectFourModel.Status status) {
        String[] options = {"End Session", "Play Again"};
        String[] result = String.valueOf(status).split("_");
        StringBuilder message = new StringBuilder();
        for(String word: result){
            message.append(word).append(" ");
        }
        int choice = JOptionPane.showOptionDialog(this, message , "Game Over",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);
        if(choice != 0){
            restartGameButton.doClick();
        }
        else{
            System.exit(0);
        }
    }

    /**
     * Handles when the game is restarted
     * @param model the model of the game
     */
    @Override
    public void handleRestartGame(ConnectFourModel model) {
        for(int i = 0; i < ConnectFourModel.ROW; i++){
            for(int j = 0; j < ConnectFourModel.COL; j++){
                this.buttons[i][j].setBackground(null);
            }
        }
    }

    /**
     * Handles when the player decides to abruptly end the game
     */
    @Override
    public void handleEndGameButton() {
        System.exit(0);
    }
}
