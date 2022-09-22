package breakout;

import javafx.scene.image.ImageView;

public abstract class AbstractBrick extends BreakoutEntities{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String BRICK_IMAGE = RESOURCE_PATH + "wall.png";

  public static final int BRICK_X_SIZE = 25;
  public static final int BRICK_Y_SIZE = 25;

  //class variables

  /**
   * Construct Ball with left top corner at given coordinate
   */
  public AbstractBrick(int X, int Y, String image){
    super(0,0,image, BRICK_Y_SIZE);
    ImageView ImageViewBrick = (ImageView) getMyNode();
    ImageViewBrick.setX(X);
    ImageViewBrick.setY(Y);
  }

  /**
   * Powerup related stuff
   */
  protected abstract void buildPowerUp(PowerUpManager manager);
    // do nothing
  protected abstract void usePowerUp(PowerUpManager manager);
    //do nothing
  /**
   * Bricks own effect
   */
  protected abstract void effect(Game game);
}
