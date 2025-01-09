import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for Connect Four Game
 * @author Vahid
 */
public class ConnectFourController implements ActionListener {
    ConnectFourModel model;

    /**
     * Constructor for the Controller
     * @param model the Model of the game
     */
    public ConnectFourController(ConnectFourModel model){
        this.model = model;
    }

    /**
     * Gets the coordinates of where the player clicked and plays
     * @param e event of button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] coordinates = e.getActionCommand().split(" ");
        model.play(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }
}
