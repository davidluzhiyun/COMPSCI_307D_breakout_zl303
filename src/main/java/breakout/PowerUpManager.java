package breakout;

import java.util.ArrayList;
import javafx.scene.Group;

public class PowerUpManager {
  //class variables
  private Group myGroup;
  private ArrayList<PowerUp> PowerUplist;

  /**
   * Construct from given wall
   */
  public PowerUpManager(Wall wall){
    myGroup = new Group();
    PowerUplist = new ArrayList<PowerUp>();
    for (Brick currentBrick:wall.getListWall()){
      currentBrick.buildPowerUp(this);
    }
  }
  /**
   * Handles stepping
   */
  public void step(Game game){
    for (PowerUp powerUp: PowerUplist){
      powerUp.step(game);
    }
  }

  /**
   * Getters
   */

  public Group getMyGroup() {
    return myGroup;
  }

  public ArrayList<PowerUp> getPowerUplist() {
    return PowerUplist;
  }
}
