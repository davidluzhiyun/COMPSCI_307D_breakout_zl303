package breakout;

import java.util.ArrayList;

public class PowerBrick extends Brick{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String POWER_BRICK_IMAGE = RESOURCE_PATH + "mystery.jpg";
  //class variables
  PowerUp myPowerUp;

  public PowerBrick(int X, int Y){
    super(X, Y, POWER_BRICK_IMAGE);
    myPowerUp = new PowerUp(X, Y);
  }

  /**
   * Add myPowerUp to the manager's list
   */
  @Override
  public void buildPowerUp(PowerUpManager manager){
    manager.getPowerUplist().add(myPowerUp);
  }
  /**
   * Use myPowerUp
   */
  @Override
  public void usePowerUp(PowerUpManager manager) {
    myPowerUp.activate(manager);
  }
}
