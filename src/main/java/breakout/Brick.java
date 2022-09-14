package breakout;


import javafx.scene.image.ImageView;

public class Brick extends BreakoutObject{
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String BRICK_IMAGE = RESOURCE_PATH + "wall.png";
  public static final int BRICK_SIZE = 25;
  //class variables

  /**
   * Construct Ball with left top corner at given coordinate
   */
  public Brick(int X, int Y){
    super(0,0,BRICK_IMAGE,BRICK_SIZE);
    ImageView ImageViewBrick = (ImageView) getMyNode();
    ImageViewBrick.setX(X);
    ImageViewBrick.setY(Y);
  }

}
