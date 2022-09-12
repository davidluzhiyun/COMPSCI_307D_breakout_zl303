package breakout;

//Importation by Robert C. Duvall in Main.java (Project breakout)
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;

public class Ball {
  //class variables
  private Node myBall;
  private double[] myBallVelocity;

  //useful constants
  public static final int BALL_SIZE = 14;
  public static final double[] BALL_VELOCITY_INITIAL = {30, 30};
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";

  /**
   * Construct Default Ball with center at given coordinate
   */
  public Ball (double centerX, double centerY) {
    ImageView ImageViewBall = new ImageView(new Image(BALL_IMAGE));
    ImageViewBall.setFitWidth(BALL_SIZE);
    ImageViewBall.setFitHeight(BALL_SIZE);
    ImageViewBall.setX(centerX - ImageViewBall.getBoundsInLocal().getWidth() / 2);
    ImageViewBall.setY(centerY - ImageViewBall.getBoundsInLocal().getHeight() / 2);
    myBall = ImageViewBall;
    myBallVelocity = BALL_VELOCITY_INITIAL;
  }

}
