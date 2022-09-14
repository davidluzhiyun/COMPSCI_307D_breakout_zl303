package breakout;

//Importation by Robert C. Duvall in Main.java (Project breakout)
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class Paddle extends BreakoutObject{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String PADDLE_IMAGE = RESOURCE_PATH + "paddle.png";
  public static final int PADDLE_HEIGHT = 14;
  public static final int PADDLE_SPEED = 8;
  public static final int PADDLE_CENTER_Y = 350;
  //class variables

  /**
   * Constructs Paddle with center at given position
   */
  public Paddle (double centerX, double centerY) {
    super(centerX,centerY,PADDLE_IMAGE,PADDLE_HEIGHT);
  }
  public Paddle(double centerX) {
    this(centerX, PADDLE_CENTER_Y);
  }


  /**
   * Handles collisions with the ball.
   * Hitting top/bottom reflect ball normally.
   * Hitting sides reverse the velocity of the ball.
   * Behavior inspired by the original game.
   */
  public void collisionHandler(Ball ball) {
    int collisionStatus = ball.collisionDetector(this);
    double[] ballVelocity = ball.getMyBallVelocity();
    switch (collisionStatus) {
      case 0:
        return;
      case 1:
        ballVelocity[1] = - ballVelocity[1];
        break;
      case 2:
        ballVelocity[0] = - ballVelocity[0];
        ballVelocity[1] = - ballVelocity[1];
        break;
    }
  }

  //Base on code by Robert C. Duvall in ExampleAnimation.java
  // What to do each time a key is pressed
  public void handleKeyInput (KeyCode code) {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    ImageView ImageViewPaddle = (ImageView) getMyNode();
    switch (code) {
      case RIGHT -> ImageViewPaddle.setX(ImageViewPaddle.getX() + PADDLE_SPEED);
      case LEFT -> ImageViewPaddle.setX(ImageViewPaddle.getX() - PADDLE_SPEED);
    }
  }
}
