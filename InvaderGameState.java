/**
 * This class is where the main game loop is run and controlled from.
 * 
 * @author Daniel Banks
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @see Enemy
 * @see LightEenemy
 * @see HeavyEnemy
 * @see BossEnemy
 * @see Missile
 * @see NormalMissile
 * @see SuperMissile
 * @see FastMissile
 * @see EnemyMissile
 * @see Shooter
 * @see PowerUp
 * @see StarBackground
 * @see Bunker
 * @see HighScore
 */

import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;

public class InvaderGameState{
  /**
   * Class constructor that creates an object of the InvaderGameState class.
   * 
   * Constants that specify the starting coordinates of the shooter, boss, bunkers and enemies
   * Constants that specify the velocities of the shooter and its barrel
   * Constants that represent the different powerups available in the game
   */
  private final int START_Y_COORD_SHOOTER = -300;
  private final int START_X_COORD_SHOOTER = 0;
  private final int START_Y_COORD_ENEMY = 300;
  private final int START_X_COORD_ENEMY = -600;
  private final int START_Y_COORD_BOSS = 250;
  private final int START_X_COORD_BOSS = 0;
  private final int START_Y_COORD_BUNKER = -200;
  private final int MAX_Y_COORD = 360;
  private final int MAX_X_COORD = 640;
  private final int SHOOTER_VELOCITY = 8;
  private final int SHOOTER_RADIAL_VELOCITY = 3;
  private final int NORMAL_MISSILE =-1, GATLING_MISSILE =0, TRI_MISSILE = 1, SUPER_MISSILE = 2, FAST_MISSILE = 3, EXTRA_LIFE = 4;
  /**
   * @param gameLoopCounter keeps track of the particular instance in the game
   * @param score represents the current score of the player
   * @param round represents which round/level the player is currently on
   * @param coolDown1 the gameLoopCounter number when the last bullet was shot for main player
   * @param coolDown2 the gameLoopCounter number when the last bullet was shot for additional player
   * @param gameOver if the game is over or not
   * @param win represents if the player won the game or not
   * @param isShot1 represents if the main player pressed the shoot button and a new missile must be created
   * @param isShot2 represents if the additional player pressed the shoot button and a new missile must be created
   * @param isTwoPlayer represents if the game is currently 2 player or not
   * @see Shooter
   * @see StarBackground
   * @see PowerUp
   * @param enemiesList array list containing all enemy objects on the screen
   * @param missilesList array list containing all missile objects on the screen
   * @param bunkersList array list containg all bunker objects on the screen
   * @param starsList array list containg all the Starbackground objects on the screen
   * @param powersList array list containing all PowerUp objects on the screen
   */
  private int gameLoopCounter, score, round, coolDown1, coolDown2;
  private boolean gameOver, win, isShot1, isShot2, isTwoPlayer;
  
  private Shooter mainShooter, additionalShooter;
  private StarBackground star;
  private PowerUp powerUp;
  
  private ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
  private ArrayList<Missile> missilesList = new ArrayList<Missile>();
  private ArrayList<Bunker> bunkersList = new ArrayList<Bunker>();
  private ArrayList<StarBackground> starsList = new ArrayList<StarBackground>();
  private ArrayList<PowerUp> powersList = new ArrayList<PowerUp>();
  
  /**
   * Runs the main game and calls all necessary methods for movement and updating
   * of game screen.
   * Clear screen every refresh and display new objects.
   */
  public void gameLoop(){
    
    while (!gameOver && !win) {
      if(!isTwoPlayer && StdDraw.isKeyPressed(VK_P)) {
        additionalShooter= new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0, 3, false);
        isTwoPlayer = true;
      }
      
      isShot1 = false;
      mainShooterKeyPresses();
      mainShooter.move();
 
      if(isTwoPlayer){
        isShot2 = false;
        additionalShooterKeyPresses();
        additionalShooter.move();
      }
    
      updateEnemyMovement();
      updateMissileMovement();
      updateBackground();
      updatePowerUpMovement();
      updateBunkers();
      checkWin();
      checkGameOver();
    
      renderUI();
    
      StdDraw.show(25);
      StdDraw.clear(StdDraw.BLACK);
      gameLoopCounter++;
    }
  }
  
/**
 * Initilaizes all necessary classes and sets all global variables to
 * necessary values for the starting round of the game.
 * 
 * Clear enemiesList, missilesList, powersList, bunkersList.
 * Initializes main player and level 1 enemies
 * 
 * @see Shooter
 */
  public void initializeStartRound() {
    gameLoopCounter = 0;
    coolDown1 = 0;
    coolDown2 = 0;
    round = 1;
    score = 0;
    gameOver = false;
    win = false;
    isTwoPlayer = false;
 
    enemiesList.clear();
    missilesList.clear();
    powersList.clear();
    bunkersList.clear();
  
    mainShooter = new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0, 3, true);
    initializeEnemies();
  }

  /**
   * Initilaizes all necessary classes and sets all global variables to
   * necessary values for the next round of the game.
   * 
   * Moves main and additional players back to the middle
   * Clear enemiesList, missilesList, powersList, bunkersList.
   * Initializes main player and level 1 enemies
   * 
   * @see Shooter
   */
  public void initializeNextRound(){
    gameLoopCounter = 0;
    round++;
    win = false;
    mainShooter.resetState(20, START_Y_COORD_SHOOTER);
    coolDown1 = 0;
  
    if(isTwoPlayer){
      additionalShooter.resetState(60, START_Y_COORD_SHOOTER);
      coolDown2 = 0;
    }
  
    enemiesList.clear();
    missilesList.clear();
    powersList.clear();
    bunkersList.clear();
    initializeEnemies();
  }
  
  /**
   * Initializes grids of enemies depending on the current round of the 
   * player.
   * 
   * Calls setupLisghtEnemyGrid, setupHeavyEnemyGrid,
   * setupBossEnemyGrid, setupBunkerGrid methods, to setup grids of
   * different enemy types.
   * Uses global variable round to decide enemies to initialize.
   */
  private void initializeEnemies(){
    switch (round){
      case 1:
        //only light enemies
        setupLightEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 8, 3);
        break;
      case 2:
        //main light enemies and top row of heavy enemies
        setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 8, 1);
        setupLightEnemyGrid(START_X_COORD_ENEMY , START_Y_COORD_ENEMY - 80, 10, 3);
        setupBunkerGrid(START_Y_COORD_BUNKER, 1);
        break;
      case 3:
        //half light and half heavy enemies
        setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 10, 2);
        setupLightEnemyGrid(START_X_COORD_ENEMY + 40 , START_Y_COORD_ENEMY - 120, 10, 3);
        setupBunkerGrid(START_Y_COORD_BUNKER, 2);
        break;
      case 4:
        //only heavy enemies
        setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 10, 3);
        setupBunkerGrid(START_Y_COORD_BUNKER, 4);
        break;
      case 5:
        //final boss fight
        setupBossEnemyGrid(START_X_COORD_BOSS, START_Y_COORD_BOSS);
        setupBunkerGrid(START_Y_COORD_BUNKER, 8);
        break;
    }
  }

  /**
   * Creates grid of the LightEnemy class, and populates array list with the
   * LightEnemy objects.
   * 
   * @param startXcoord the starting x-coordinate of the first light enemy
   * @param startYcoord the starting y-coordinate od the first light enemy
   * @param xNumberEnemy amount of columns of enemies that must be created
   * @param yNumberEnemy amount of rows of enemies that must be created
   */
  private void setupLightEnemyGrid(int startXcoord, int startYcoord, int xNumberEnemy, int yNumberEnemy){
    int enemyRadius = 18;
    int gap = 18;
    int enemySpace = enemyRadius + gap;
    for (int i = 0; i < (yNumberEnemy * enemySpace); i = i + (enemySpace) ){
      for (int j = 0; j < (xNumberEnemy * enemySpace); j = j + (enemySpace)) {
        LightEnemy enemy = new LightEnemy(startXcoord+ j, startYcoord - i);
        enemiesList.add(enemy);
      }
    }
  }
  
  /**
   * Creates grid of the HeavyEnemy class, and populates array list with the
   * HeavyEnemy objects.
   * 
   * @param start_X_coord the starting x-coordinate of the first heavy enemy
   * @param start_Y_coord the starting y-coordinate od the first heavy enemy
   * @param xNumberEnemy amount of columns of enemies that must be created
   * @param yNumberEnemy amount of rows of enemies that must be created
   */
  private void setupHeavyEnemyGrid(int start_x_coord, int start_y_coord, int xNumberEnemy, int yNumberEnemy){
    int enemyRadius = 20;
    int gap = 20;
    start_x_coord = start_x_coord + enemyRadius;
    start_y_coord = start_y_coord - enemyRadius;
    int enemySpace = enemyRadius + gap;
    for (int i = 0; i < (yNumberEnemy * enemySpace); i = i + (enemySpace) ){
      for (int j = 0; j < (xNumberEnemy * enemySpace); j = j + (enemySpace)) {
        HeavyEnemy enemy = new HeavyEnemy(start_x_coord + j, start_y_coord - i);
        enemiesList.add(enemy);
      }
    }
  }

  /**
   * Creates the boss enemy which the player(s) will fight in the final round.
   * 
   * @param start_x_coord the starting x-coordinate of the boss enemy
   * @param start_y_coord the starting y-coordinate od the boss enemy
   */
  private void setupBossEnemyGrid(int start_x_coord, int start_y_coord){
    start_x_coord = start_x_coord + 50;
    start_y_coord = start_y_coord - 50;
    BossEnemy enemy = new BossEnemy(start_x_coord, start_y_coord);
    enemiesList.add(enemy);
  }

  /**
   * Creates a line of a certain number of bunkers equal distances apart.
   * 
   * @param startYCoord the y-coordinate of the bunkers
   * @param xNumberBunker the amount of bunkers we want in the round
   */
  private void setupBunkerGrid(int startYCoord, int xNumberBunker) {
    double gap = MAX_X_COORD*2 / (xNumberBunker); 
    double xCoord = -MAX_X_COORD + gap/2;
    for (int i = 0; i < xNumberBunker; i++) {
      Bunker bunker = new Bunker(xCoord, startYCoord);
      bunkersList.add(bunker);
      xCoord = xCoord + gap;
    }
  }
 
  /**
   * All bunkers have 5 lives, it allows all enemy missiles through however
   * deletes powerups and player missiles trying to pass through, instead a
   * life is taken off of the bunker until it reaches 0 then it is destroyed.
   * For every life removed from a bunker the player gains 10 points to their 
   * score.
   * If an enemy touches a bunker the bunker gets destroyed without damaging
   * enemy.
   */
  private void updateBunkers(){
    for (int i = 0; i < bunkersList.size(); i++) {
      Bunker currentBunker = bunkersList.get(i);
      boolean isDestroyed = false;
    
      for (int j = 0; j < missilesList.size(); j++) {
        Missile currentMissile = missilesList.get(j);
      
        //enemy missiles go through bunkers
        //bunkers destroy powerups
        //shooter missiles remove a life from bunkers
        if (currentBunker.onMissileCollision(currentMissile) && ( (currentMissile instanceof NormalMissile) || (currentMissile instanceof FastMissile) ) ){
          currentBunker.removeLives(1);
          if (currentBunker.getLives() == 0) {
            bunkersList.remove(i);
            StdAudio.play("destroy.wav");
          }
          missilesList.remove(j);
          score = score + 2;
          break;
        }
        if (currentBunker.onMissileCollision(currentMissile) && ( (currentMissile instanceof SuperMissile) ) ) {
          currentBunker.removeLives(5);
          if (currentBunker.getLives() <= 0) {
            bunkersList.remove(i);
            StdAudio.play("destroy.wav");
          }
          missilesList.remove(j);
          score = score + 10;
          break;
        }
      }
    
      //if enemy touches a bunker remove the bunker and dont damage enemy
      for(int j = 0; j < enemiesList.size(); j++){
        Enemy currentEnemy = enemiesList.get(j);
      
        if(currentBunker.onEnemyCollision(currentEnemy) && !isDestroyed){
          bunkersList.remove(i);
          isDestroyed = true;
          StdAudio.play("destroy.wav");
        }
      }
    }
  
    for (int i = 0; i < bunkersList.size(); i++) {
      bunkersList.get(i).render();
    }
  }

  /**
   * Loop through the enemiesList and update the x & y coordinates of each
   * enemy according to a constant velocity.
   * Detects if an enemy is on the edge of the screen.
   * Detects for collisions with player missiles to either remove a life
   * from enemy object it is in contatc with or destroy it (remove from
   * array list).
   * Play a wav file if an enemy is removed from the array list.
   */
  private void updateEnemyMovement(){
    boolean isEdge = false;
  
    for(int a = 0 ; a < enemiesList.size() ; a++){
      Enemy currentEnemy = enemiesList.get(a);
      if( (currentEnemy.getXCoord() - currentEnemy.getRadius()) <= -MAX_X_COORD ||  (currentEnemy.getXCoord() + currentEnemy.getRadius())>= MAX_X_COORD){
        isEdge = true;
        break;
      }
    }
  
    for (int i = 0; i < enemiesList.size(); i++) {
      Enemy currentEnemy = enemiesList.get(i);
    
      for (int j = 0; j < missilesList.size(); j++) {
        Missile currentMissile = missilesList.get(j);
      
        //on collision for a fast missile and normal missile
        if (currentEnemy.onMissileCollision(currentMissile) && (currentMissile instanceof NormalMissile || currentMissile instanceof FastMissile) ) {
          currentEnemy.removeLives(1);
          if(currentEnemy.getLives() == 0){
            enemiesList.remove(i); 
            StdAudio.play("destroy.wav");
          }
          missilesList.remove(j);
          score = score + 10;
          break;
        }
        if (currentEnemy.onMissileCollision(currentMissile) && (currentMissile instanceof SuperMissile) ) {
          currentEnemy.removeLives(5);
        
          if(currentEnemy.getLives() <= 0){
            enemiesList.remove(i);
            StdAudio.play("destroy.wav");
          }
          missilesList.remove(j);
          score = score + 50;
          break;
        }
      }
      if(enemiesList.size() > i){
        if(enemiesList.get(i) instanceof LightEnemy){
          LightEnemy lightEnemy = (LightEnemy) enemiesList.get(i);
          lightEnemyMovement(lightEnemy, isEdge);
        }
        if(enemiesList.get(i) instanceof HeavyEnemy){
          HeavyEnemy heavyEnemy = (HeavyEnemy) enemiesList.get(i);
          heavyEnemyMovement(heavyEnemy, isEdge);
        }
        if(enemiesList.get(i) instanceof BossEnemy){
          BossEnemy bossEnemy = (BossEnemy) enemiesList.get(i);
          bossEnemyMovement(bossEnemy,  isEdge);
        }
      }
    }
  }

  /**
   * Moves and renders a single object of the LightEnemy class 
   * If the enemy touches the edges it moves down.
   * Randomly decides if this enemy is going to shoot a missile
   * and adds missile to missile array list.
   * Play a wav file if an enemy shoots a missile.
   * 
   * @param lightEnemy object of the LightEnemy class that needs to be moved
   * @param isEdge boolean stating if the current enemy is on the edge or not
   */
  private void lightEnemyMovement(LightEnemy lightEnemy, boolean isEdge){
    if(isEdge){
      lightEnemy.moveY();
      lightEnemy.render();
    }else{
      lightEnemy.moveX();
      lightEnemy.render();
    }
  
    if(lightEnemy.isShoot()){
      EnemyMissile missile = new EnemyMissile(lightEnemy);
      missilesList.add(missile);
      StdAudio.play("enemy_missile.wav");
    }
  }

  /**
   * Moves and renders a single object of the HeavyEnemy class 
   * If the enemy touches the edges it moves down.
   * Randomly decides if this enemy is going to shoot a missile
   * and adds missile to missile array list.
   * Play a wav file if an enemy shoots a missile.
   * 
   * @param heavyEnemy object of the HeavyEnemy class that needs to be moved
   * @param isEdge boolean stating if the current enemy is on the edge or not
   */
  private void heavyEnemyMovement(HeavyEnemy heavyEnemy,  boolean isEdge){
    if(isEdge){
      heavyEnemy.moveY();
      heavyEnemy.render();
    }else{
      heavyEnemy.moveX();
      heavyEnemy.render();
    }
  
    if(heavyEnemy.isShoot()){
      EnemyMissile missile = new EnemyMissile(heavyEnemy);
      missilesList.add(missile);
      StdAudio.play("enemy_missile.wav");
    }
  }
  
  /**
   * Moves and renders a single object of the BossEnemy class 
   * If the enemy touches the edges it moves down.
   * Randomly decides if this enemy is going to shoot a triple missile
   * and adds all 3 missiles to missile array list, if there are 2 players
   * the boss will sometimes shoot at both players.
   * Play a wav file if an enemy shoots a missile.
   * 
   * @param bossEnemy object of the BossEnemy class that needs to be moved
   * @param isEdge boolean stating if the current enemy is on the edge or not
   */
  private void bossEnemyMovement(BossEnemy bossEnemy,  boolean isEdge){
    int randomWalkTrigger = 200;
    if(gameLoopCounter % randomWalkTrigger == 0){
      bossEnemy.randomWalk();
    }
    bossEnemy.move();
    bossEnemy.render();
  
    if(bossEnemy.isShoot()){
      bossShootDecision(bossEnemy);
      StdAudio.play("enemy_missile.wav");
    }
  }
  private void bossShootDecision(BossEnemy bossEnemy){
    EnemyMissile missile1;
    EnemyMissile missile2;
    EnemyMissile missile3;
    
    if(isTwoPlayer){
      double shootDecision = Math.random();
      if(shootDecision < 0.5){
        missile1 = new EnemyMissile(bossEnemy, 10 , mainShooter);
        missile2 = new EnemyMissile(bossEnemy,-10 , mainShooter);
        missile3 = new EnemyMissile(bossEnemy , 0 , mainShooter);
      } else {
        missile1 = new EnemyMissile(bossEnemy, 10 , additionalShooter);
        missile2 = new EnemyMissile(bossEnemy,-10 , additionalShooter);
        missile3 = new EnemyMissile(bossEnemy , 0 , additionalShooter);
      }
    } else {
      missile1 = new EnemyMissile(bossEnemy, 10 , mainShooter);
      missile2 = new EnemyMissile(bossEnemy,-10 , mainShooter);
      missile3 = new EnemyMissile(bossEnemy , 0 , mainShooter);
    }
    missilesList.add(missile1);
    missilesList.add(missile2);
    missilesList.add(missile3);
  }

  /**
   * Loops through missilesList array list and moves each missile
   * individually according to what type of missile it is.
   */
  private void updateMissileMovement(){
    //loop through missilesList and update each missiles movement
    for (int i = 0; i < missilesList.size(); i++) {
      //this has to be here to prevent IndexOutOFBounds as arraylist can change whilst this loop runs
      if(missilesList.size() > i){
        if(missilesList.get(i) instanceof EnemyMissile &&  missilesList.size() > 0) {
          EnemyMissile enemyMissile = (EnemyMissile) missilesList.get(i);
          enemyMissileMovement(enemyMissile, i);
        }
      }
    
      if(missilesList.size() > i){
        if (missilesList.get(i) instanceof NormalMissile &&  missilesList.size() > 0) {
          NormalMissile normalMissile = (NormalMissile) missilesList.get(i);
          normalMissileMovement(normalMissile, i);
        }
      }
    
      if(missilesList.size() > i){
        if(missilesList.get(i) instanceof SuperMissile &&  missilesList.size() > 0){
          SuperMissile superMissile = (SuperMissile) missilesList.get(i);
          superMissileMovement(superMissile, i);
        }
      }
    
      if(missilesList.size() > i){
        if (missilesList.get(i) instanceof FastMissile &&  missilesList.size() > 0) {
          FastMissile fastMissile = (FastMissile) missilesList.get(i);
          fastMissileMovement(fastMissile, i);
        }
      }
    }
  }

  /**
   * Moves and renders one object of the EnemyMissile class.
   * Bounces the missile if it hasnt bounced before yet.
   * Deletes object from missile array list if it has bounced twice or is
   * at the top of the screen.
   * 
   * @param enemyMissile object of the EnemyMissile class that needs to be updated
   * @param position the location of current enemyMissile in missilesList array list
   */
  private void enemyMissileMovement(EnemyMissile enemyMissile, int position){
    if (Math.abs(enemyMissile.getXCoord() + enemyMissile.getRadius()) + enemyMissile.getRadius() > MAX_X_COORD) {
      enemyMissile.wallBounce();
    }
    if (enemyMissile.getYCoord() <= -MAX_Y_COORD || enemyMissile.getNumBounced() >= 2) {
      missilesList.remove(position);
    } else {
      enemyMissile.move();
      enemyMissile.render();
    }
  }
  
  /**
   * Moves and renders one object of the normalMissile class.
   * Bounces the missile if it hasnt bounced before yet.
   * Deletes object from missile array list if it has bounced twice or is
   * at the top of the screen.
   * 
   * @param normalMissile object of the NormalMissile class that needs to be updated
   * @param position the location of current normalMissile in missilesList array list
   */
  private void normalMissileMovement(NormalMissile normalMissile, int position){
    if (Math.abs(normalMissile.getXCoord() + normalMissile.getRadius()) + normalMissile.getRadius() > MAX_X_COORD) {
      normalMissile.wallBounce();
    }
    if (normalMissile.getYCoord() >= MAX_Y_COORD || normalMissile.getNumBounced() >= 2) {
      missilesList.remove(position);
    } else {
      normalMissile.move();
      normalMissile.render();
    }
  }

  /**
   * Moves and renders one object of the SuperMissile class.
   * Bounces the missile if it hasnt bounced before yet.
   * Deletes object from missile array list if it has bounced twice or is
   * at the top of the screen.
   * 
   * @param superMissile object of the SuperMissile class that needs to be updated
   * @param position the location of current superMissile in missilesList array list
   */
  private void superMissileMovement(SuperMissile superMissile, int position){
    if ( Math.abs( superMissile.getXCoord() + superMissile.getRadius() ) + superMissile.getRadius() > MAX_X_COORD ) {
      superMissile.wallBounce();
    }
    if (superMissile.getYCoord() >= MAX_Y_COORD || superMissile.getNumBounced() >= 2) {
      missilesList.remove(position);
    } else {
      superMissile.move();
      superMissile.render();
    }
  }

  /**
   * Moves and renders one object of the FastMissile class.
   * Bounces the missile if it hasnt bounced before yet.
   * Deletes object from missile array list if it has bounced twice or is
   * at the top of the screen.
   * 
   * @param fastMissile object of the FastMissile class that needs to be updated
   * @param position the location of current fastMissile in missilesList array list
   */
  private void fastMissileMovement(FastMissile fastMissile, int position){
    if ( Math.abs( fastMissile.getXCoord() + fastMissile.getRadius() ) + fastMissile.getRadius() > MAX_X_COORD ) {
      fastMissile.wallBounce();
    }
    if (fastMissile.getYCoord() >= MAX_Y_COORD || fastMissile.getNumBounced() >= 2) {
      missilesList.remove(position);
    } else {
      fastMissile.move();
      fastMissile.render();
    }
  }
  /**
   * Creates, moves and renders the background stars.
   * Adds stars to array list from the top of the screen.
   * Deletes and stars that are at the bottom of the screen
   * from the array list.
   * Randomly chooses a x-position for the star to start.
   * Randomly assignes a size to the star between radius 0 and 1.5;
   */
  private void updateBackground(){
    int starRange = 800;
    double starScale = 1.5;
    int x = (int)(Math.random() * starRange);
    double length = Math.random() * starScale;
    double sign = Math.random();
    if(sign > 0.5){
      x = -x;
    }
    star = new StarBackground(x , MAX_Y_COORD, length);
    if(x >= -MAX_X_COORD && x <= MAX_X_COORD){
      starsList.add(star);
    }
  
    for(int i = 0 ; i < starsList.size() ; i ++){
      StarBackground currentStar = starsList.get(i);
      if (currentStar.getTailYCoord() < -MAX_Y_COORD){
        starsList.remove(i);
      } else {
        currentStar.move();
      }
    }
  }

  /**
   * Randomly creates powerUps according to a probability that move 
   * from top to bottom of the screen.
   * Checks if the main/additional shooter has collided with a powerUp
   * and assignes the shooter relevant power, as well as play wav file.
   * Gives main shooter preference if both players touvhed the same powerUp.
   * Loops through powerUp list to see if a powerUp collides with bunker and 
   * deletes powerUp if collision is true.
   */
  private void updatePowerUpMovement(){
    int powerUpScale = 15000;
    int x = (int)(Math.random() *powerUpScale);
    double length = 5;
    double sign = Math.random();
    if(sign > 0.5){
      x = -x;
    } 
    powerUp = new PowerUp(x , MAX_Y_COORD, length);
    if(x >= -MAX_X_COORD && x <= MAX_X_COORD && Math.random() < 0.5){
      powersList.add(powerUp);
    }
    for(int i = 0 ; i < powersList.size() ; i ++){
      boolean isTaken = false;
      PowerUp currentPowerUp = powersList.get(i);
      double shooterRadius = mainShooter.getRadius();
      double powerUpRadius = currentPowerUp.getRadius();
      double tolerance = 10;
      double disToShooter = Math.sqrt(Math.pow( (currentPowerUp.getXCoord()) - (mainShooter.getXCoord()) , 2) + Math.pow((currentPowerUp.getYCoord()) - (mainShooter.getYCoord()), 2));
      if(disToShooter <= (powerUpRadius + shooterRadius + tolerance)){
        mainShooter.setPowerUp(currentPowerUp.getPower());
        powersList.remove(i);
        isTaken = true;
        StdAudio.play("powerup.wav");
      } 
    
      if(isTwoPlayer){
        double disToShooter2 = Math.sqrt(Math.pow( (currentPowerUp.getXCoord()) - (additionalShooter.getXCoord()) , 2) + Math.pow((currentPowerUp.getYCoord()) - (additionalShooter.getYCoord()), 2));
        if(disToShooter2 <= (powerUpRadius + shooterRadius + tolerance) && !isTaken){
          additionalShooter.setPowerUp(currentPowerUp.getPower());
          powersList.remove(i);
          isTaken = true;
          StdAudio.play("powerup.wav");
        }  
      }
      if(!isTaken){
        currentPowerUp.move();
      
        if(currentPowerUp.getYCoord() < -MAX_Y_COORD) {
          powersList.remove(i);
        } 
      } 
    
    //loop through all of the bunkers and see if the powerup collides with the bunker
      for(int j=0; j<bunkersList.size(); j++){
        Bunker currentBunker = bunkersList.get(j);
        double bunkerRadius = currentBunker.getRadius();
        double disToBunker = Math.sqrt(Math.pow( (currentPowerUp.getXCoord()) - (currentBunker.getXCoord()) , 2) + Math.pow((currentPowerUp.getYCoord()) - (currentBunker.getYCoord()), 2));
        if(disToBunker <= (powerUpRadius + bunkerRadius)) {
          powersList.remove(i);
        }
      }
    }
  }

  /**
   * Renders information elements in game such as score, live and round number.
   * 
   */
  private void renderUI(){
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.text(-580, 325, "Score: " + score);
    StdDraw.text(-580, -335, "Player 1: ");
    for(int i = 1 ; i <= mainShooter.getLives() ; i++){
      StdDraw.picture((-540 + i * 30),-335,"HEART.png", 30 , 35);
    }
  
    if(isTwoPlayer){
      for(int i = 1 ; i <= additionalShooter.getLives() ; i++){
        StdDraw.picture((-540 + i * 30),-285,"HEART.png", 30 , 35);
      }
      StdDraw.text(-580, -285, "Player 2: ");
    }
  
    StdDraw.text(580, 325, "Round: " + round);
  }
  
  /**
   * Checks if any enemy has touched main shooter = gameOver.
   * If any enemy touches additional shooter it destroys second
   * player.
   * Check if main player has 0 lives = gameOver, play wav file.
   * Destroys additional player if its lives = 0.
   */
  private void checkGameOver(){
    for (int i = 0; i < enemiesList.size(); i++) {
      Enemy currentEnemy = enemiesList.get(i);
      //loop through enemies and see if any enemy has touched the bottom of the grid
      if (Math.abs(currentEnemy.getYCoord() + currentEnemy.getRadius()) == -100) {
        gameOver = true;
      }
      //also check if one of the enemies have touched the shooter
      if (currentEnemy.isShooterCollision(mainShooter)) {
        StdAudio.play("destroy.wav");
        gameOver = true;
      }
      //also check if one of the enemies have touched the additional shooter
      if (isTwoPlayer) {
        if(currentEnemy.isShooterCollision(additionalShooter)) {
          enemiesList.remove(i);
          isTwoPlayer = false;
          additionalShooter = null;
          StdAudio.play("destroy.wav");
        }
      }
    }
  
    //also check if an enemy missile has touched the shooter
    for(int i=0; i<missilesList.size(); i++){
      Missile currentMissile = missilesList.get(i);
      if(currentMissile instanceof EnemyMissile){
        EnemyMissile enemyMissile = (EnemyMissile) currentMissile;
        if(enemyMissile.isShooterCollision(mainShooter)){
          mainShooter.removeLife();
          missilesList.remove(i);
          if(mainShooter.getLives() <= 0){
            StdAudio.play("destroy.wav");
            gameOver = true; 
          }
        }
        if(isTwoPlayer){
          if(enemyMissile.isShooterCollision(additionalShooter)){
            additionalShooter.removeLife();
            missilesList.remove(i);
            if(additionalShooter.getLives() <= 0){
              StdAudio.play("destroy.wav");
              additionalShooter = null;
              isTwoPlayer = false;
            }
          }
        }
      }
    }
  
    if(mainShooter.getLives() == 0){
      gameOver = true;
    }
  }

  /**
   * Checks if the size of the enemy array list is 0 which means the
   * player wins.
   */
  private void checkWin(){
    if (enemiesList.size() == 0) {
      win = true;
    }
  }

  /**
   * Checks for the all the possible key presses that can influence the
   * main player.
   * Inverting of velocity of shooter.
   * Stopping movement shooter.
   * Inverting shooter barrel velocity.
   * Stopping shooter barrel movement.
   * Sets barrel velocity to 0 if both left and right barrel movement buttons
   * are pressed simultaneously.
   * Shooting of a particular type of missile that requires a timer.
   * i.e. all missiles excluding gatling gun, missile is shot according to 
   * players current powerUp value , play wav file.
   * Removes timer if powerUp is gatling gun.
   */
  private void mainShooterKeyPresses() {
  
    if (StdDraw.isKeyPressed(VK_Z)) {
      mainShooter.setXVelocity(-SHOOTER_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_C)) {
      mainShooter.setXVelocity(SHOOTER_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_X)) {
      mainShooter.setXVelocity(0);
    }
    //0 is gatling gun, so if there has to be delay between missiles
    if (mainShooter.getPower() != 0) {
      if (StdDraw.isKeyPressed(VK_W) && (coolDown1 + 20 < gameLoopCounter)) {
        coolDown1 = gameLoopCounter;
        isShot1 = true;
        StdAudio.play("shooter_missile.wav");

        if(mainShooter.getPower() != NORMAL_MISSILE){ 
          if(mainShooter.getPowerUpCounter() == 0){
            mainShooter.setPowerUp(NORMAL_MISSILE);
          }
        }
      }
    } 
    //else
    if(mainShooter.getPower() == GATLING_MISSILE){
      if (StdDraw.isKeyPressed(VK_W)) {
        isShot1 = true;
        StdAudio.play("shooter_missile.wav");
        if(mainShooter.getPowerUpCounter() == 0){
          mainShooter.setPowerUp(NORMAL_MISSILE);
        }
      }
    }
    if (StdDraw.isKeyPressed(VK_A)) {
      mainShooter.setRadialVelocityBarrel(SHOOTER_RADIAL_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_D)) {
      mainShooter.setRadialVelocityBarrel(-SHOOTER_RADIAL_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_A) && StdDraw.isKeyPressed(VK_D)) {
      mainShooter.setRadialVelocityBarrel(0);
    }
    if (StdDraw.isKeyPressed(VK_S)) {
      mainShooter.setRadialVelocityBarrel(0);
    }
    if (StdDraw.isKeyPressed(VK_Q)) {
      System.exit(0);
    }
    if (StdDraw.isKeyPressed(VK_P)) {
        //StdDraw.save();
    }
  
    if(isShot1){
      instantiateMissile(mainShooter);
    }
  }

  /**
   * Checks for the all the possible key presses that can influence the
   * additional player.
   * Inverting of velocity of shooter.
   * Stopping movement shooter.
   * Inverting shooter barrel velocity.
   * Stopping shooter barrel movement.
   * Sets barrel velocity to 0 if both left and right barrel movement buttons
   * are pressed simultaneously.
   * Shooting of a particular type of missile that requires a timer.
   * i.e. all missiles excluding gatling gun, missile is shot according to 
   * players current powerUp value , play wav file.
   * Removes timer if powerUp is gatling gun.
   */
  private void additionalShooterKeyPresses (){
    if (StdDraw.isKeyPressed(VK_NUMPAD1)) {
      additionalShooter.setXVelocity(-SHOOTER_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_NUMPAD3)) {
      additionalShooter.setXVelocity(SHOOTER_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_NUMPAD2)) {
      additionalShooter.setXVelocity(0);
    }

    if (additionalShooter.getPower() != 0) {
      if (StdDraw.isKeyPressed(VK_NUMPAD8) && (coolDown2 + 20 < gameLoopCounter)) {
        coolDown2 = gameLoopCounter;
        isShot2 = true;
      
        if(additionalShooter.getPower() != NORMAL_MISSILE){ 
          if(additionalShooter.getPowerUpCounter() == 0){
            additionalShooter.setPowerUp(NORMAL_MISSILE);
          }
        }
      }
    } 
    if(additionalShooter.getPower() == GATLING_MISSILE){
      if (StdDraw.isKeyPressed(VK_NUMPAD8)) {
        isShot2 = true;
   
        if(additionalShooter.getPowerUpCounter() == 0){
          additionalShooter.setPowerUp(NORMAL_MISSILE);
        }
      }
    }
    
    if (StdDraw.isKeyPressed(VK_NUMPAD4)) {
      additionalShooter.setRadialVelocityBarrel(SHOOTER_RADIAL_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_NUMPAD6)) {
      additionalShooter.setRadialVelocityBarrel(-SHOOTER_RADIAL_VELOCITY);
    }
    if (StdDraw.isKeyPressed(VK_NUMPAD6) && StdDraw.isKeyPressed(VK_NUMPAD4)) {
      additionalShooter.setRadialVelocityBarrel(0);
    }
    if (StdDraw.isKeyPressed(VK_NUMPAD5)) {
      additionalShooter.setRadialVelocityBarrel(0);
    }
    if (isShot2) {
      instantiateMissile(additionalShooter);
    }
  }
  
  /**
   * Adds specific powerUp missiles to missile array list according
   * to which powerUp the shooter object currently has assigned to it.
   * Each powerUp has a specific amount of missiles the player is allowed to
   * shoot before the power is depleted. When a specific powerUp missile is
   * added to the array list the counter is decreased.
   */
  private void instantiateMissile(Shooter shooter){
    switch(shooter.getPower()){
      case -1: 
        NormalMissile normalMissile1 = new NormalMissile(shooter);
        missilesList.add(normalMissile1);
        break;
      case 0: 
        NormalMissile normalMissile2 = new NormalMissile(shooter);
        missilesList.add(normalMissile2);
        shooter.decreasePowerUpCounter();
        break;
      case 1: 
        NormalMissile normalMissile3 = new NormalMissile(shooter, 10);
        missilesList.add(normalMissile3);
        NormalMissile normalMissile4 = new NormalMissile(shooter, -10);
        missilesList.add(normalMissile4);
        NormalMissile normalMissile5 = new NormalMissile(shooter);
        missilesList.add(normalMissile5);
        shooter.decreasePowerUpCounter();
        break;
      case 2: 
        SuperMissile superMissile = new SuperMissile(shooter);
        missilesList.add(superMissile);
        shooter.decreasePowerUpCounter();
        break;
      case 3: 
        FastMissile fastMissile = new FastMissile(shooter);
        missilesList.add(fastMissile);
        shooter.decreasePowerUpCounter();
        break;
    }
  }

  /**
   * @return the current score of the player
   */
  public int getScore(){
    return  score;
  }

  /**
   * @return the current round
   */
  public int getRound(){
    return round;
  }

  /**
   * @return a boolean representing if the game is over or not
   */
  public boolean isGameOver(){
    return gameOver;
  }
  
  /**
   * @return a boolean representing if the player has won or lost
   */
  public boolean isWin(){
    return win;
  }

}
