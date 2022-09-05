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

/**
 * Feel free to completely change this code or delete it entirely.
 * Game.java is modified from Main.java by Robert C. Duvall
 * Structure based on ExampleAnimation by Robert C. Duvall
 * @author David Lu
 */
public class Game extends Application {

  // useful names for constant values used
  // modified from Main.java by Robert C. Duvall
  public final String TITLE = "Example JavaFX Animation";
  public final int SIZE = 400;
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public final String RESOURCE_PATH = "/breakout/";
  public final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";
  public final String WALL_IMAGE = RESOURCE_PATH + "wall.png";
  public final String PLATFORM_IMAGE = RESOURCE_PATH + "platform.png";
  public final int BALL_SIZE = 14;
  public final int WALL_SIZE = 25;
  public final int PLATFORM_HEIGHT = 14;
  public double[] BALL_VELOCITY = {40, 40};
  public final double[] BALL_VELOCITY_INITIAL = {40, 40};
  public int PLATFORM_SPEED = 8;
  //Inspired ExampleAnimation.java by Robert C. Duvall
  public final int FRAMES_PER_SECOND = 120;
  public final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  // things needed to remember during the game
  private ImageView ball;
  private Group walls;
  private ImageView platform;
  private Scene myScene;
  private Timeline game;
  private Stage myStage;

  /**
   * Initialize what will be displayed and that it will be updated regularly.
   * From ExampleAnimation.java by Robert C. Duvall
   */
  @Override
  public void start (Stage stage) {
    // attach scene to the stage and display it
    myScene = setupGame();
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    myStage = stage;
    stage.show();
    // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
    game = new Timeline();
    game.setCycleCount(Timeline.INDEFINITE);
    game.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step()));
    game.play();
  }


  //Inspired by Robert C. Duvall's ExampleAnimation.java and Main.java (breakout)
  // Create the game's "scene": what shapes will be in the game and their starting properties
  public Scene setupGame() {
    ball = new ImageView(new Image(BALL_IMAGE));
    ball.setFitWidth(BALL_SIZE);
    ball.setFitHeight(BALL_SIZE);
    ball.setX(SIZE / 2 - ball.getBoundsInLocal().getWidth() / 2);
    ball.setY(SIZE / 2 - ball.getBoundsInLocal().getHeight() / 2);

    platform = new ImageView(new Image(PLATFORM_IMAGE));
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html#setPreserveRatio-boolean-
    platform.setPreserveRatio(true);
    platform.setFitHeight(14);
    platform.setX(SIZE / 2 - platform.getBoundsInLocal().getWidth() / 2);
    platform.setY(350);

    wallBuilder(16, 3);

    Group root = new Group(ball, walls, platform);
    Scene scene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
    //From ExampleAnimation.java by Robert C. Duvall
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  /**
   * Build the Group walls.
   */
  public void wallBuilder(int numX, int numY) {
    Image wall_image = new Image(WALL_IMAGE);
    walls = new Group();
    for (int i = 0; i < numX; i++) {
      for (int j = 0; j < numY; j++) {
        ImageView wall = new ImageView(wall_image);
        wall.setFitWidth(WALL_SIZE);
        wall.setFitHeight(WALL_SIZE);
        wall.setX(i * WALL_SIZE);
        wall.setY(j * WALL_SIZE);
        walls.getChildren().add(wall);
      }
    }
  }

  /**
   * Collision detector, returns an int.
   * 0 means no collision between ball and object
   * 1 means collision on the top/bottom bound
   * 2 means collision on the left/right bound,
   * Determines the side it hits by comparing the position angle of the ball relative to the center
   * of the platform/brick to that of the vertices of the platform/brick
   * */
  public int collisionDetector (ImageView object) {
    double ballX = ball.getX() + ball.getBoundsInLocal().getWidth()/2;
    double ballY = ball.getY() + ball.getBoundsInLocal().getHeight()/2;
    //https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Bounds.html
    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html
    //Works on both the walls and the platform because the Group walls and the Group root shares
    // the same coordinate system
    Bounds objectBound = object.getBoundsInParent();
    if (! objectBound.contains(ballX,ballY)) {
      return 0;
    }
    else {
      double objectX = object.getX() + objectBound.getWidth() / 2;
      double objectY = object.getY() + objectBound.getHeight() / 2;
      boolean region = Math.abs((ballY - objectY) / (ballX - objectX)) <= Math.abs(objectBound.getHeight() / objectBound.getWidth());
      if (region) {
        return 2;
      }
      else {
        return 1;
      }
    }
  }

  /**
   * Handles collisions with the edge of the playable area.
   * Ball gets reflected unless hitting the lower edge, which ends the game
   *
   */
  public boolean edgeHandler () {
    double ballX = ball.getX() + ball.getBoundsInLocal().getWidth()/2;
    double ballY = ball.getY() + ball.getBoundsInLocal().getHeight()/2;
    if (ballY > SIZE) {
      return false;
    }
    else {
      if (ballX < 0 || ballX > SIZE) {
        BALL_VELOCITY[0] = - BALL_VELOCITY[0];
      }
      if (ballY < 0) {
        BALL_VELOCITY[1] = - BALL_VELOCITY[1];
      }
      return true;
    }
  }

  /**
   * Handles collisions with platform.
   * Hitting top/bottom reflect ball normally.
   * Hitting sides reverse the velocity of the ball.
   * Behavior inspired by the original game.
   */
  public void platformHandler () {
    int collisionStatus = collisionDetector(platform);
    if (platform.getX() < 0) {
      platform.setX(SIZE);
    }
    if (platform.getX() > SIZE) {
      platform.setX(0);
    }
    switch (collisionStatus) {
      case 0:
        return;
      case 1:
        BALL_VELOCITY[1] = - BALL_VELOCITY[1];
        break;
      case 2:
        BALL_VELOCITY[0] = - BALL_VELOCITY[0];
        BALL_VELOCITY[1] = - BALL_VELOCITY[1];
        break;
    }
  }

  /**
   * Handles collisions with the walls
   * Hitting brick wall destroys it and reflects ball, increase ball speed.
   * Behavior inspired by the original game.
   */
  public void wallHandler () {
    for(Node wall : walls.getChildren()) {
      int collisionStatus = collisionDetector((ImageView) wall);
      if (collisionStatus == 0) {
        continue;
      }
      else {
        if (collisionStatus == 1) {
          BALL_VELOCITY[1] = - BALL_VELOCITY[1];
        }
        else  {
          BALL_VELOCITY[0] = - BALL_VELOCITY[0];
        }
        walls.getChildren().remove(wall);
        BALL_VELOCITY[0] = 1.1 * BALL_VELOCITY[0];
        BALL_VELOCITY[1] = 1.1 * BALL_VELOCITY[1];
        return;
      }
    }
  }

  //Base on code by Robert C. Duvall in ExampleAnimation.java
  // What to do each time a key is pressed
  private void handleKeyInput (KeyCode code) {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    switch (code) {
      case RIGHT -> platform.setX(platform.getX() + PLATFORM_SPEED);
      case LEFT -> platform.setX(platform.getX() - PLATFORM_SPEED);
    }
  }

  // What to do when restart
  private void handleRestart (KeyCode code) {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    if (code == KeyCode.SPACE) {
      myStage.close();
      BALL_VELOCITY[0] = BALL_VELOCITY_INITIAL[0];
      BALL_VELOCITY[1] = BALL_VELOCITY_INITIAL[1];
      start(new Stage());
    }
  }

  /**
   * Handles Game-over Scenarios
   */
  //Inspired by https://horstmann.com/corejava/corejava_11ed-bonuschapter13-javafx.pdf
  private void gameoverHandler () {
    Text over = new Text("GAME OVER!");
    Font f = new Font(36);
    over.setFont(f);
    Bounds textBounds = over.getBoundsInParent();
    over.setX(SIZE / 2 - textBounds.getWidth() / 2);
    over.setY(SIZE / 2 - textBounds.getHeight() / 2);
    Group root = new Group(over);
    Scene deathScene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
    deathScene.setOnKeyPressed(e -> handleRestart(e.getCode()));
    myScene = deathScene;
    myStage.setScene(deathScene);
  }

  /**
   * Handles frames and game rules
   */
  // Based on ExampleAnimation.java by Robert C. Duvall
  // Handle game "rules" for every "moment":
  // - movement: how do shapes move over time?
  // - collisions: did shapes intersect and, if so, what should happen?
  // - goals: did the game or level end?
  // Note, there are more sop
  private void step (){
    if (! edgeHandler()){
      game.stop();
      gameoverHandler();
    }
    else {
      platformHandler();
      wallHandler();
      ball.setX(ball.getX() + BALL_VELOCITY[0] * SECOND_DELAY);
      ball.setY(ball.getY() + BALL_VELOCITY[1] * SECOND_DELAY);
    }
  }

  /**
   * Start the program.
   * By Robert C. Duvall
   */
  public static void main (String[] args) {
    launch(args);
  }

}
