package breakout;


import javafx.scene.image.ImageView;

public class Brick extends AbstractBrick {

  /**
   * Construct Ball with left top corner at given coordinate
   */
  public Brick(int X, int Y){
    super(X,Y, BRICK_IMAGE);
  }

  /**
   * Powerup related stuff
   */
  @Override
  public void buildPowerUp(PowerUpManager manager){
    // do nothing
  }
  @Override
  public void usePowerUp(PowerUpManager manager) {
    //do nothing
  }

  @Override
  public void effect(Game game) {
    //do nothing
  }
}
