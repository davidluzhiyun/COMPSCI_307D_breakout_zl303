package breakout;

import javafx.scene.image.ImageView;

public class FieldEdge {
  //useful constants
  public final static int SIZE = 400;
  //class variable
  private int X;
  private int Y;

  /**
   * Construct FieldEdge with given dimensions
   */
  public FieldEdge (int edgeX,int edgeY) {
    X = edgeX;
    Y = edgeY;
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
   * Handles collisions with the Ball. Ball gets reflected unless hitting the
   * lower edge, which ends the game
   */
  public boolean collisionHandler(Ball ball) {
    double ballX = ball.getCenterX();
    double ballY = ball.getCenterY();
    double[] ballVelocity = ball.getMyBallVelocity();
    if (ballY > X) {
      return false;
    }
    else {
      if (ballX < 0 || ballX > X) {
        ballVelocity[0] = - ballVelocity[0];
      }
      if (ballY < 0) {
        ballVelocity[1] = - ballVelocity[1];
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
    double paddleX = paddle.getCenterX();
    if (paddleX < 0){
      ImageViewPaddle.setX(X - ImageViewPaddle.getFitWidth()/2);
    }
    if (paddleX > X){
      ImageViewPaddle.setX(ImageViewPaddle.getFitWidth()/2);
    }
  }
}
