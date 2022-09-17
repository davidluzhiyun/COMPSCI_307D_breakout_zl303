package breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;

public class PowerUpManager {
  //class variables
  private Group myGroup;
  private List<PowerUp> PowerUplist;

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

  public List<PowerUp> getPowerUplist() {
    return PowerUplist;
  }
}
