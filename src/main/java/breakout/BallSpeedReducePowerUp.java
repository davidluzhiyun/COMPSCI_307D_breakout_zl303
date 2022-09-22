package breakout;

import javafx.scene.paint.Color;

public class BallSpeedReducePowerUp extends PowerUp {

  public static final double SLOWING_FACTOR = 0.9;

  public BallSpeedReducePowerUp(double X, double Y){
    super(X, Y, Color.GRAY);
  }

  /**
   * Reduce ball speed
   */
  @Override
  protected void powerup(Game game) {
    double v[] = game.getMyBall().getMyBallVelocity();
    v[0] = v[0] * SLOWING_FACTOR;
    v[1] = v[1] * SLOWING_FACTOR;
  }
}
