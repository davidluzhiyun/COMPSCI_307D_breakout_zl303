package breakout;

//Importation by Robert C. Duvall in Main.java (Project breakout)
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;

public class Ball extends BreakoutObject{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final int BALL_SIZE = 14;
  public static final double[] BALL_VELOCITY_INITIAL = {30, 30};
  public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";

  //class variables
  private double[] myBallVelocity;


  /**
   * Construct Ball with center at given coordinate
   */
  public Ball (double centerX, double centerY, double[] initialVelocity) {
    super(centerX,centerY,BALL_IMAGE,BALL_SIZE);
    assert initialVelocity.length == 2;
    myBallVelocity = initialVelocity.clone();
  }
  public Ball (double centerX, double centerY) {
    this(centerX,centerY,BALL_VELOCITY_INITIAL);
  }


  /**
   * Collision detector, returns an int. 0 means no collision between ball and object 1 means
   * collision on the top/bottom bound 2 means collision on the left/right bound, Determines the side
   * it hits by comparing the position angle of the ball relative to the center of the platform/brick
   * to that of the vertices of the platform/brick
   */
  public int collisionDetector(BreakoutObject object) {
    double ballCenterX = this.getCenterX();
    double ballCenterY = this.getCenterY();
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
   * Get the ball's velocity array
   */
  public double[] getMyBallVelocity() {
    return myBallVelocity;
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
