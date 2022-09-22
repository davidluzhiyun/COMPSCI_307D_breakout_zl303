package breakout;

import javafx.scene.paint.Color;

public class LifeAddPowerUp extends PowerUp{
  public LifeAddPowerUp(double X, double Y){
    super(X, Y, Color.GREEN);
  }

  /**
   *
   * Adds 1 live
   */
  @Override
  protected void powerup(Game game) {
    game.getMyLifeCount().increaseLife();
  }
}
