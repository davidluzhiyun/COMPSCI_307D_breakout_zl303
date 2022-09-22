package breakout;

//Importation by Robert C. Duvall in Main.java (Project breakout)
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
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
  public static final String TITLE = "Example JavaFX Animation";
  public static final int SIZE = 400;
  // many resources may be in the same shared folder
  // note, leading slash means automatically start in "src/main/resources" folder
  // note, Java always uses forward slash, "/", (even for Windows)
  public static final String RESOURCE_PATH = "/breakout/";
  public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";
  public static final String BRICK_IMAGE = RESOURCE_PATH + "wall.png";
  public static final String PADDLE_IMAGE = RESOURCE_PATH + "paddle.png";

  public static final int BRICK_SIZE = 25;
  public static final int PADDLE_HEIGHT = 14;
  public static final int PADDLE_CENTER_Y = 350;

  public static final int PADDLE_SPEED = 8;
  public static final String[] LEVELS = {RESOURCE_PATH + "level_test.txt", RESOURCE_PATH + "level1.txt", RESOURCE_PATH + "level2.txt"};
  //Inspired ExampleAnimation.java by Robert C. Duvall
  public static final int FRAMES_PER_SECOND = 120;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  // things needed to remember during the game
  private  FieldEdge myFieldEdge;
  private Ball myBall;
  private Wall myWall;
  private Paddle myPaddle;
  private Scene myScene;
  private Timeline game;
  private Stage myStage;
  private LifeCount myLifeCount;
  private PowerUpManager myManager;
  //goes from 0 to 2
  private int myLevel;
  /**
   * Initialize what will be displayed and that it will be updated regularly.
   * From ExampleAnimation.java by Robert C. Duvall
   */
  @Override
  public void start (Stage stage) {
    start(stage,0);
  }

  /**
   * Start according to given level, makes sure myLevel is within range
   */
  public void start (Stage stage, int level) {
    // attach scene to the stage and display it
    level = Math.max(level,0);
    level = Math.min(level, LEVELS.length-1);
    myScene = setupGame(level);
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
  public Scene setupGame(int level) {
    //Set up myBall
    myFieldEdge = new FieldEdge();
    myBall = new Ball(myFieldEdge.getX()/2.0,myFieldEdge.getY()/2.0);


    //Set up myPaddle
    myPaddle = new Paddle(myFieldEdge.getX()/2.0);

    //Set up myWall
    myWall = new Wall(LEVELS[level]);

    //Set up myManager
    myManager = new PowerUpManager(myWall);

    //Set up myLifeCount
    myLifeCount = new LifeCount(myFieldEdge.getX()/2.0,myFieldEdge.getY()/2.0);

    Group root = new Group(myBall.getMyNode(), myWall.getGroupWall(), myPaddle.getMyNode(),myLifeCount.getMyNode(),myManager.getMyGroup());
    Scene scene = new Scene(root, myFieldEdge.getX(), myFieldEdge.getY(), Color.DARKBLUE);
    //From ExampleAnimation.java by Robert C. Duvall
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  /**
   * Handles key control and cheat
   */
  private void handleKeyInput (KeyCode code) {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    myPaddle.handleKeyInput(code);
    handleCheat(code);
  }
  private void handleCheat (KeyCode code) {
    if (code.isDigitKey()){
      int level = Integer.parseInt(code.getChar()) - 1;
      level = Math.min(level, LEVELS.length-1);
      myLevel = level;
      restart();
    }
    else {
      switch (code) {
        case L -> myLifeCount.increaseLife();
        case R -> restart();
      }
    }
  }

  // What to do when restart
  private void handleRestart (KeyCode code) {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    if (code == KeyCode.SPACE) {
      restart();
    }
  }

  /**
   * Restart according to current myLevel
   */
  private void restart() {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    myStage.close();
    game.stop();
    start(new Stage(),myLevel);
  }
  /**
   * Handles Clearing
   */
  private void clearHandler() {
    // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
    //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
    if (myWall.getListWall().isEmpty()){
      myLevel += 1;
      if (myLevel < 3){
        restart();
      }
      else {
        myStage.close();
      }
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
    over.setX(myFieldEdge.getX() / 2 - textBounds.getWidth() / 2);
    over.setY(myFieldEdge.getY() / 2 - textBounds.getHeight() / 2);
    Group root = new Group(over);
    Scene deathScene = new Scene(root, myFieldEdge.getX(), myFieldEdge.getY(), Color.DARKBLUE);
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
    if (! myFieldEdge.collisionHandler(myBall)){
      if (myLifeCount.failHandler()){
        myBall.failHandler();
      }
      else {
        gameoverHandler();
      }
    }
    else {
      clearHandler();
      myManager.step(this);
      myPaddle.collisionHandler(myBall);
      myFieldEdge.collisionHandler(myPaddle);
      myWall.collisionHandler(myBall,myManager);
      myBall.step(SECOND_DELAY);
    }
  }
  /**
   * Just some getters
   */
  public Paddle getMyPaddle() {
    return myPaddle;
  }

  public FieldEdge getMyFieldEdge() {
    return myFieldEdge;
  }

  public LifeCount getMyLifeCount() {
    return myLifeCount;
  }

  public Ball getMyBall() {
    return myBall;
  }

  /**
   * Start the program.
   * By Robert C. Duvall
   */
  public static void main (String[] args) {
    launch(args);
  }

}
