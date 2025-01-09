import java.util.EventObject;

/**
 * Connect Four Event
 * @author Vahid
 */
public class ConnectFourEvent extends EventObject {

    private int x;
    private int y;

    /**
     * Constructor of the Event
     * @param model the model of the game
     * @param x the row of the board that the player clicked on
     * @param y the column of the board that the player click on
     */
    public ConnectFourEvent(ConnectFourModel model , int x, int y){
        super(model);
        this.x = x;
        this.y = y;
    }

    /**
     * Get the X value of the event
     * @return the x-coordinate of the event
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y value of the event
     * @return the y-coordinate of the event
     */
    public int getY() {
        return y;
    }
}
