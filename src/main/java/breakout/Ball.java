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

public class Ball extends BreakoutObject{
  //useful constants
  public static final int BALL_SIZE = 14;
  public static final double[] BALL_VELOCITY_INITIAL = {30, 30};
  public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";

  //class variables
  private double[] myBallVelocity;


  /**
   * Construct Default Ball with center at given coordinate
   */
  public Ball (double centerX, double centerY) {
    super(centerX,centerY,BALL_IMAGE,BALL_SIZE);
    myBallVelocity = BALL_VELOCITY_INITIAL;
  }


  /**
   * Collision detector, returns an int. 0 means no collision between ball and object 1 means
   * collision on the top/bottom bound 2 means collision on the left/right bound, Determines the side
   * it hits by comparing the position angle of the ball relative to the center of the platform/brick
   * to that of the vertices of the platform/brick
   */
  public int collisionDetector(BreakoutObject object) {
    double ballCenterX = getCenterX();
    double ballCenterY = getCenterY();
    //https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Bounds.html
    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html
    //Works on both the walls and the platform because the Group walls and the Group root shares
    // the same coordinate system
    ImageView ImageViewObject = (ImageView) object.getMyNode();
    Bounds objectBound = object.getObjectBound();
    if (! objectBound.contains(ballCenterX,ballCenterY)) {
      return 0;
    }
    else {
      double objectCenterX = object.getCenterX();
      double objectCenterY = object.getCenterY();
      boolean region = Math.abs((ballCenterY - objectCenterY) / (ballCenterX - objectCenterX)) <= Math.abs(objectBound.getHeight() / objectBound.getWidth());
      if (region) {
        return 2;
      }
      else {
        return 1;
      }
    }
  }

  /**
   * Move the ball
   */
  public void step(double elapsedTime) {
    ImageView ImageViewBall = (ImageView) getMyNode();
    ImageViewBall.setX(ImageViewBall.getX() + myBallVelocity[0] * elapsedTime);
    ImageViewBall.setY(ImageViewBall.getY() + myBallVelocity[1] * elapsedTime);
  }
}
