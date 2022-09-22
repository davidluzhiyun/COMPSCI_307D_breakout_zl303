package breakout;

public class PowerBrick extends AbstractBrick{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String POWER_BRICK_IMAGE = RESOURCE_PATH + "mystery.jpg";
  //class variables
  PowerUp myPowerUp;

  public PowerBrick(int X, int Y, int type) {
    super(X, Y, POWER_BRICK_IMAGE);
    switch (type) {
      case 2: myPowerUp = new LifeSaverPowerUp(X, Y);
        break;
      case 3: myPowerUp = new LifeAddPowerUp(X, Y);
        break;
      case 4: myPowerUp = new BallSpeedReducePowerUp(X, Y);
        break;
      default: myPowerUp = new LifeSaverPowerUp(X, Y);
        break;
    }
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

  @Override
  protected void effect(Game game) {
    //Do Nothing
  }
}
