package breakout;

import javafx.scene.image.ImageView;

public class FieldEdge {
  //useful constants
  public final static int SIZE = 400;
  //class variable
  private int X;
  private int Y;
  private boolean powered;
  /**
   * Construct FieldEdge with given dimensions
   */
  public FieldEdge (int edgeX,int edgeY) {
    X = edgeX;
    Y = edgeY;
    powered = false;
  }
  public FieldEdge() {
    this(SIZE,SIZE);
  }
  /**
   * Get X and Y
   */
  public int getX() {
    return X;
  }

  public int getY() {
    return Y;
  }

  /**
   * Power up. Bottom edge reflects Ball now
   */
  public void powerup(){
    powered = true;
  }

  /**
   * Handles collisions with the Ball. Ball gets reflected unless hitting the
   * lower edge, which ends the game
   */
  public boolean collisionHandler(Ball ball) {
    double ballX = ball.getCenterX();
    double ballY = ball.getCenterY();
    double[] ballVelocity = ball.getMyBallVelocity();
    if ((!powered) && ballY > Y){
      return false;
    }
    else {
      if (ballX < 0 || ballX > X) {
        ballVelocity[0] = - ballVelocity[0];
      }
      if (ballY < 0 || ballY > Y) {
        ballVelocity[1] = - ballVelocity[1];
        if (ballY > Y){
          powered = false;
        }
      }
      return true;
    }
  }
  /**
   * Handles collisions with the Paddle. Paddle gets reflected unless hitting the
   * lower edge, which ends the game
   */
  public void collisionHandler(Paddle paddle) {
    ImageView ImageViewPaddle = (ImageView) paddle.getMyNode();
    double paddleX = ImageViewPaddle.getX();
    if (paddleX < 0){
      ImageViewPaddle.setX(X);
    }
    if (paddleX > X){
      ImageViewPaddle.setX(0);
    }
  }
}
