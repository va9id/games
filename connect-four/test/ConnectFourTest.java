import org.junit.Assert;

/**
 * JUnit Test Cases for Connect Four
 * @author Vahid
 */
public class ConnectFourTest {

    ConnectFourModel model;

    @org.junit.Before
    public void setUp(){
        model = new ConnectFourModel();
    }

    @org.junit.After
    public void tearDown(){
        model = null;
    }

    /**
     * JUnit test for a horizontal Connect 4
     */
    @org.junit.Test
    public void playTestHorizontalWinner() {
        model.play(0, 4); //Red
        model.play(5, 0); //Yellow
        model.play(4, 3); // ...
        model.play(4, 1);
        model.play(2, 5);
        model.play(2, 4);
        model.play(1, 6);
        Assert.assertEquals(ConnectFourModel.Status.RED_WON, model.getStatus());
    }

    /**
     * JUnit test for a vertical Connect 4
     */
    @org.junit.Test
    public void playTestVerticalWinner() {
        model.play(0, 4); //Red
        model.play(5, 0); //Yellow
        model.play(4, 3); // ...
        model.play(0, 0);
        model.play(2, 5);
        model.play(0, 0);
        model.play(1, 5);
        model.play(1, 0);
        Assert.assertEquals(ConnectFourModel.Status.YELLOW_WON, model.getStatus());
    }

    /**
     * JUnit test for a descending diagonal Connect 4
     */
    @org.junit.Test
    public void playTestDescendingDiagonalWinner() {
        model.play(3, 3); //Red
        model.play(3, 2); //Yellow
        model.play(1, 2); //...
        model.play(5, 1);
        model.play(0, 1);
        model.play(0, 0);
        model.play(1, 1);
        model.play(0, 0);
        model.play(3, 4);
        model.play(0, 0);
        model.play(0, 0);
        Assert.assertEquals(ConnectFourModel.Status.RED_WON, model.getStatus());
    }

    /**
     * JUnit test for an ascending diagonal Connect 4
     */
    @org.junit.Test
    public void playTestAscendingDiagonalWinner() {
        model.play(2,3); //Red
        model.play(0,4); //Yellow
        model.play(0,4); //...
        model.play(5,5);
        model.play(3,6);
        model.play(2,5);
        model.play(2,5);
        model.play(2,6);
        model.play(2,6);
        model.play(2,0);
        model.play(2,6);
        Assert.assertEquals(ConnectFourModel.Status.RED_WON, model.getStatus());
    }
}