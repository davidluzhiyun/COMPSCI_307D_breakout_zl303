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
  private Node myNode;
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
    ImageView ImageViewBall = new ImageView(new Image(Ball.class.getResourceAsStream(BALL_IMAGE)));
    ImageViewBall.setFitWidth(BALL_SIZE);
    ImageViewBall.setFitHeight(BALL_SIZE);
    ImageViewBall.setX(centerX - ImageViewBall.getBoundsInLocal().getWidth() / 2);
    ImageViewBall.setY(centerY - ImageViewBall.getBoundsInLocal().getHeight() / 2);
    myNode = ImageViewBall;
    myBallVelocity = BALL_VELOCITY_INITIAL;
  }

  /**
   * Get the coordinate of the center of the ball
   */
  public double getCenterX (){
    ImageView ImageViewBall = (ImageView) myNode;
    double centerX = ImageViewBall.getX() + ImageViewBall.getBoundsInLocal().getWidth()/2;
    return centerX;
  }

  public double getCenterY (){
    ImageView ImageViewBall = (ImageView) myNode;
    double centerY = ImageViewBall.getY() + ImageViewBall.getBoundsInLocal().getHeight()/2;
    return centerY;
  }

  /**
   * Collision detector, returns an int. 0 means no collision between ball and object 1 means
   * collision on the top/bottom bound 2 means collision on the left/right bound, Determines the side
   * it hits by comparing the position angle of the ball relative to the center of the platform/brick
   * to that of the vertices of the platform/brick
   */
  public int collisionDetector(Node object) {
    double ballX = getCenterX();
    double ballY = getCenterY();
    //https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Bounds.html
    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html
    //Works on both the walls and the platform because the Group walls and the Group root shares
    // the same coordinate system
    ImageView ImageViewObject = (ImageView)object;
    Bounds objectBound = ImageViewObject.getBoundsInParent();
    if (! objectBound.contains(ballX,ballY)) {
      return 0;
    }
    else {
      double objectX = ImageViewObject.getX() + objectBound.getWidth() / 2;
      double objectY = ImageViewObject.getY() + objectBound.getHeight() / 2;
      boolean region = Math.abs((ballY - objectY) / (ballX - objectX)) <= Math.abs(objectBound.getHeight() / objectBound.getWidth());
      if (region) {
        return 2;
      }
      else {
        return 1;
      }
    }
  }
}
