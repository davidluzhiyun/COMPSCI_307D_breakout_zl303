package breakout;

//Importation by Robert C. Duvall in Main.java (Project breakout)
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class BreakoutEntities {
  //useful constants
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";

  //class variables
  private Node myNode;

  /**
   * Construct Default Ball with center at given coordinate
   */
  public BreakoutEntities(double centerX, double centerY, String path, int height) {
    ImageView ImageViewObject = new ImageView(new Image(BreakoutEntities.class.getResourceAsStream(path)));
    ImageViewObject.setPreserveRatio(true);
    ImageViewObject.setFitHeight(height);
    ImageViewObject.setX(centerX - ImageViewObject.getBoundsInLocal().getWidth() / 2);
    ImageViewObject.setY(centerY - ImageViewObject.getBoundsInLocal().getHeight() / 2);
    myNode = ImageViewObject;
  }
  /**
   * Get the coordinate of the center of the object
   */
  public double getCenterX (){
    ImageView ImageViewBall = (ImageView) myNode;
    double centerX = ImageViewBall.getX() + ImageViewBall.getBoundsInLocal().getWidth()/2;
    return centerX;
  }

  public double getCenterY (){
    ImageView ImageViewObject = (ImageView) myNode;
    double centerY = ImageViewObject.getY() + ImageViewObject.getBoundsInLocal().getHeight()/2;
    return centerY;
  }
  /**
   * Get the Node of the object
   */
  public Node getMyNode() {
    return myNode;
  }

  /**
   * Get the bounds of the object
   */
  public Bounds getObjectBound() {
    ImageView ImageViewObject = (ImageView) myNode;
    Bounds objectBound = ImageViewObject.getBoundsInParent();
    return objectBound;
  }


}
