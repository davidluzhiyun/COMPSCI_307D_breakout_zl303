package breakout;


import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PowerUp {
  //useful constants
  public static final int POWER_UP_SIZE = 25;
  public static final int POWER_UP_SPEED = 50;
  //class variables
  private Node myNode;
  private boolean active;

  public PowerUp(double X, double Y) {
    active = false;
    Rectangle myShape = new Rectangle(POWER_UP_SIZE,POWER_UP_SIZE, Color.RED);
    myShape.setX(X);
    myShape.setY(Y);
    myNode = myShape;
  }

  /**
   * Start processing the dropped power-up
   * put the node in a group for showing
   */
  public void activate (PowerUpManager manager) {
    active = true;
    manager.getMyGroup().getChildren().add(myNode);
  }
  /**
   * stop processing the powerup
   */
  public void deactivate () {
    active = false;
    ((Group) (myNode.getParent())).getChildren().remove(myNode);
  }

  /**
   * Handles stepping
   */
  public void step (Game game) {
    Rectangle RectangleNode = (Rectangle) myNode;
    if (active){
      collisionHandler(game);
      RectangleNode.setY(RectangleNode.getY()+ POWER_UP_SPEED * Game.SECOND_DELAY);
    }
  }

  /**
   * Auxiliary  method that handles collisions with the Paddle.
   */
  private void collisionHandler(Game game) {
    ImageView ImageViewPaddle = (ImageView) game.getMyPaddle().getMyNode();
    Bounds paddleBound = ImageViewPaddle.getBoundsInParent();
    boolean collisionStatus = paddleBound.intersects(myNode.getBoundsInParent());
    if(collisionStatus) {
      deactivate();
      powerup(game);
    }
    collisionStatus = (((Rectangle) myNode).getY() > game.getMyFieldEdge().getY());
    if (collisionStatus) {
      deactivate();
    }
  }
  /**
   *Auxiliary method that creates the power up effect with the Paddle.
   * Override for other types of powerup
   */
  private void powerup(Game game){
    game.getMyFieldEdge().powerup();
  }

}
