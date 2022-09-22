package breakout;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LifeCount {
  //useful constants
  public static final int DEFAULT_LIVES = 3;
  public static final int DEFAULT_FONT_SIZE = 36;
  //class variable
  private int myLives;
  //The displayed text
  private Node myNode;
  private double X;
  private double Y;

  /**
   * Constructor, display lives with center at given coordinates
   */
  public LifeCount (double centerX, double centerY) {
    X = centerX;
    Y = centerY;
    myLives = DEFAULT_LIVES;
    myNode = textDisplay("Live(s): 3",centerX,centerY);
  }

  /**
   * Handles what happen when you missed the ball,return if you are still alive
   */
  public boolean failHandler() {
    myLives -= 1;
    Group root = (Group) myNode.getParent();
    if (myLives > 0) {
      root.getChildren().remove(myNode);
      myNode = textDisplay("Live(s): " + myLives,X,Y);
      root.getChildren().add(myNode);
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Increase lives for Cheating,utilizes failureHandler
   */
  public void increaseLife () {
    myLives += 2;
    failHandler();
  }

  /**
   * Auxiliary method for returning text for displaying
   * */
  private static Text textDisplay(String displayString, double centerX, double centerY){
    Text displayText = new Text(displayString);
    Font f = new Font(DEFAULT_FONT_SIZE);
    displayText.setFont(f);
    Bounds textBounds = displayText.getBoundsInParent();
    displayText.setX(centerX - textBounds.getWidth() / 2);
    displayText.setY(centerY - textBounds.getHeight() / 2);
    return displayText;
  }
  /**
   * Get node
   */
  public Node getMyNode() {
    return myNode;
  }
}
