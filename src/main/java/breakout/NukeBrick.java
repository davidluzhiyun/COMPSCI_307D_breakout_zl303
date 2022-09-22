package breakout;

public class NukeBrick extends AbstractBrick{
  public static final String NUKE_BRICK_IMAGE = RESOURCE_PATH + "nuke.png";
  public NukeBrick(int X, int Y){
    super(X, Y, NUKE_BRICK_IMAGE);
  }
  @Override
  public void buildPowerUp(PowerUpManager manager) {
    //do nothing
  }

  @Override
  public void usePowerUp(PowerUpManager manager) {
    //do nothing
  }

  @Override
  public void effect(Game game) {
    game.setMyLevel(game.getMyLevel()+1);
    game.restart();
  }
}
