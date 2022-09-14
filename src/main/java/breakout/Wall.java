package breakout;

import java.util.ArrayList;
import javafx.scene.Group;

public class Wall {
  //useful constants
  public static final int DEFAULT_NUM_X = 16;
  public static final int DEFAULT_NUM_Y = 3;
  public static final double SPEEDING_FACTOR = 1.08;
  //class variable
  private Group GroupWall;
  //Inspired by https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
  private ArrayList<Brick> ListWall;

  /**
   * Construct a wall given columns and rows.
   */
  public Wall (int numX, int numY) {
    GroupWall = new Group();
    ListWall = new ArrayList<>();
    for (int i = 0; i < numX; i++) {
      for (int j = 0; j < numY; j++) {
        Brick currentBrick = new Brick(i * Brick.BRICK_X_SIZE,j*Brick.BRICK_Y_SIZE);
        ListWall.add(currentBrick);
        GroupWall.getChildren().add(currentBrick.getMyNode());
      }
    }
  }
  public  Wall (){
    this(DEFAULT_NUM_X,DEFAULT_NUM_Y);
  }

  /**
   * Get the group
   */
  public Group getGroupWall() {
    return GroupWall;
  }

  /**
   * Handles collisions with the ball
   * Hitting brick destroys it and reflects ball, increase ball
   * speed. Behavior inspired by the original game.
   *
   */
  public void collisionHandler(Ball ball) {
    for(Brick thisBrick : ListWall) {
      int collisionStatus = ball.collisionDetector(thisBrick);
      double[] ballVelocity = ball.getMyBallVelocity();
      if (collisionStatus == 0) {
        continue;
      }
      else {
        if (collisionStatus == 1) {
          ballVelocity[1] = - ballVelocity[1];
        }
        else  {
          ballVelocity[0] = - ballVelocity[0];
        }
        ListWall.remove(thisBrick);
        GroupWall.getChildren().remove(thisBrick.getMyNode());
        ballVelocity[0] = SPEEDING_FACTOR * ballVelocity[0];
        ballVelocity[1] = SPEEDING_FACTOR * ballVelocity[1];
        return;
      }
    }
  }
}
