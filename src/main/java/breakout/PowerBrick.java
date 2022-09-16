package breakout;

public class PowerBrick extends Brick{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String POWER_BRICK_IMAGE = RESOURCE_PATH + "mystery.jpg";
  //class variables

  public PowerBrick(int X, int Y){
    super(X, Y, POWER_BRICK_IMAGE);
  }

  public void buildPowerup(PowerUpManager manager){
    // do nothing
  }
}
