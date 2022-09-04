package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Feel free to completely change this code or delete it entirely.
 * Modified from Main.java by Robert C. Duvall
 * Structure based on ExampleAnimation by Robert C. Duvall
 * @author David Lu
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Example JavaFX Animation";
    public static final int SIZE = 400;
    // many resources may be in the same shared folder
    // note, leading slash means automatically start in "src/main/resources" folder
    // note, Java always uses forward slash, "/", (even for Windows)
    public static final String RESOURCE_PATH = "/breakout/";
    public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";
    public static final String WALL_IMAGE = RESOURCE_PATH + "wall.png";
    public static final int BALL_SIZE = 15;
    public static final int WALL_SIZE = 30;
    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        ImageView ball = new ImageView(new Image(BALL_IMAGE));
        ball.setFitWidth(BALL_SIZE);
        ball.setFitHeight(BALL_SIZE);
        ball.setX(SIZE / 2 - ball.getBoundsInLocal().getWidth() / 2);
        ball.setY(SIZE / 2 - ball.getBoundsInLocal().getHeight() / 2);

        //Testing area
        Image wall_image = new Image(WALL_IMAGE);
        ImageView wall1 = new ImageView(wall_image);
        ImageView wall2 = new ImageView(wall_image);
        wall1.setFitHeight(WALL_SIZE);
        wall1.setFitWidth(WALL_SIZE);
        wall2.setFitWidth(WALL_SIZE);
        wall2.setFitHeight(WALL_SIZE);
        wall1.setX(0);
        wall1.setY(0);
        wall2.setX(wall1.getBoundsInLocal().getWidth());
        wall2.setY(0);
        Group walls = new Group(wall1,wall2);
        //

        Group root = new Group(ball,walls);
        //
        Scene scene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
    }


    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
