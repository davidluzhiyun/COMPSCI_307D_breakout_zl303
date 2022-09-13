package breakout;

//Importation by Robert C. Duvall in Main.java (Project breakout)
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;


public class BreakoutObject {
  //useful constants
  public static final String RESOURCE_PATH = "/breakout/";

  //class variables
  private Node myNode;

  /**
   * Construct Default Ball with center at given coordinate
   */
  public BreakoutObject (double centerX, double centerY, String path, int height) {
    ImageView ImageViewObject = new ImageView(new Image(Ball.class.getResourceAsStream(path)));
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
   * Get the bounds of the object
   */
  public Bounds getObjectBound() {
    ImageView ImageViewObject = (ImageView) myNode;
    Bounds objectBound = ImageViewObject.getBoundsInParent();
    return objectBound;
  }
}
