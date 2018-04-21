import javafx.scene.effect.Light;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;
public class InvaderGameState{
  
  private final int START_Y_COORD_SHOOTER = -300;
  private final int START_X_COORD_SHOOTER = 0;
  private final int START_Y_COORD_ENEMY = 300;
  private final int START_X_COORD_ENEMY = -600;
  private final int MAX_YCOORD = 360;
  private final int MAX_XCOORD = 640;
  
  private int gameLoopCounter, score, round, coolDown1, coolDown2, shootProbability, powerUpStep;
  private double enemyXVelocity;
  private boolean gameOver, win, isShot1, isShot2, twoPlayer, powActive;
  
  private Shooter mainShooter, additionalShooter;
  private StarBackground star;
  private PowerUp powerUp;
  
  private ArrayList<Enemy> enemiesList;
  private ArrayList<Missile> missilesList;
  private ArrayList<StarBackground> starsList = new ArrayList<StarBackground>();
  private ArrayList<PowerUp> powersList = new ArrayList<PowerUp>();



  public void gameLoop(){
    
    while (!gameOver && !win) {
      if(!twoPlayer && StdDraw.isKeyPressed(VK_P)){
        additionalShooter= new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0, 3);
        twoPlayer = true;
      }

      isShot1 = false;
      mainShooterKeyPresses();
      mainShooter.move();
      if(powActive){
      if(powerUpStep + 40 < gameLoopCounter){
        mainShooter.setPowerUp(0);
        powActive = false;
      }
      }
      if(twoPlayer){
        isShot2 = false;
        additionalShooterKeyPresses();
        additionalShooter.move();
      }
      
      updateEnemyMovement();
      updateMissileMovement();
      updateBackground();
      updatePowerUpMovement();
      checkWin();
      checkGameOver();
      
      renderUI();
      
      StdDraw.show(30);
      StdDraw.clear(StdDraw.BLACK);
      gameLoopCounter++;
    }
    
  }
  
  public void initializeStartRound() {
    powerUpStep = 0;
    gameLoopCounter = 0;
    coolDown1 = 0;
    coolDown2 = 0;
    round = 1;
    score = 0;
    enemyXVelocity = 2;
    shootProbability = 1500;
    powActive = false;
    gameOver = false;
    win = false;
    twoPlayer = false;
    
    enemiesList = new ArrayList<Enemy>();
    missilesList = new ArrayList<Missile>();
    
    enemiesList.clear();
    missilesList.clear();
    powersList.clear();
    
    mainShooter = new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0, 3);
    mainShooter.setPowerUp(0);
    initializeEnemies();
  }
  
  public void initializeNextRound(){
    gameLoopCounter = 0;
    round++;
    powerUpStep = 0;
    win = false;
    powActive = false;
    mainShooter.resetState(20, START_Y_COORD_SHOOTER);
    coolDown1 = 0;
    
    if(twoPlayer){
      additionalShooter.resetState(60, START_Y_COORD_SHOOTER);
      coolDown2 = 0;
    }
    
    enemiesList = new ArrayList<Enemy>();
    missilesList = new ArrayList<Missile>();
    
    enemiesList.clear();
    missilesList.clear();
    powersList.clear();
    mainShooter.setPowerUp(0);
    initializeEnemies();
  }
  
  
  
  public void initializeEnemies(){
    
    switch (round){
      case 1:
        //only light enemies
        setupLightEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 8, 3);
        break;
      case 2:
        //main light enemies and top row of heavy enemies
        setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 8, 1);
        setupLightEnemyGrid(START_X_COORD_ENEMY , START_Y_COORD_ENEMY - 80, 10, 3);
        break;
      case 3:
        //half light and half heavy enemies
        setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 10, 2);
        setupLightEnemyGrid(START_X_COORD_ENEMY + 40 , START_Y_COORD_ENEMY - 120, 10, 3);
        break;
      case 4:
        //only heavy enemies
        setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 10, 3);
        break;
      case 5:
        //final boss fight
        setupBossEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY);
        break;
    }
    
  }
  public void setupLightEnemyGrid(int start_x_coord, int start_y_coord, int xNumberEnemy, int yNumberEnemy){
    int enemyRadius = 18;
    int gap = 18;
    int enemySpace = enemyRadius + gap;
    for (int i = 0; i < (yNumberEnemy * enemySpace); i = i + (enemySpace) ){
      for (int j = 0; j < (xNumberEnemy * enemySpace); j = j + (enemySpace)) {
        LightEnemy enemy = new LightEnemy(start_x_coord + j, start_y_coord - i, enemyXVelocity, 0);
        enemiesList.add(enemy);
      }
    }
  }
  public void setupHeavyEnemyGrid(int start_x_coord, int start_y_coord, int xNumberEnemy, int yNumberEnemy){
    int enemyRadius = 20;
    int gap = 20;
    start_x_coord = start_x_coord + enemyRadius;
    start_y_coord = start_y_coord - enemyRadius;
    int enemySpace = enemyRadius + gap;
    for (int i = 0; i < (yNumberEnemy * enemySpace); i = i + (enemySpace) ){
      for (int j = 0; j < (xNumberEnemy * enemySpace); j = j + (enemySpace)) {
        HeavyEnemy enemy = new HeavyEnemy(start_x_coord + j, start_y_coord - i, enemyXVelocity, 0);
        enemiesList.add(enemy);
      }
    }
  }
  
  public void setupBossEnemyGrid(int start_x_coord, int start_y_coord){
    start_x_coord = start_x_coord + 50;
    start_y_coord = start_y_coord - 50;
    BossEnemy enemy = new BossEnemy(start_x_coord, start_y_coord, enemyXVelocity/2, 0);
    enemiesList.add(enemy);
  }
  
  public void updateEnemyMovement(){
    //loop through enemiesList and update each enemies movement
    boolean isEdge = false;
    
    for(int a = 0 ; a < enemiesList.size() ; a++){
      Enemy currentEnemy = enemiesList.get(a);
      if( (currentEnemy.getXCoord() - currentEnemy.getRadius()) <= -MAX_XCOORD ||  (currentEnemy.getXCoord() + currentEnemy.getRadius())>= MAX_XCOORD){
        isEdge = true;
        break;
      }
    }
    
    for (int i = 0; i < enemiesList.size(); i++) {
      boolean isDestroy = false;
      Enemy currentEnemy = enemiesList.get(i);
      
      for (int j = 0; j < missilesList.size(); j++) {
        Missile currentMissile = missilesList.get(j);
        
        if (currentEnemy.onMissileCollision(currentMissile) && !(currentMissile instanceof EnemyMissile)) {
          isDestroy = true;
          currentEnemy.removeLife();
          if(currentEnemy.getLives() == 0){
            enemiesList.remove(i);
            
          }
          missilesList.remove(j);
          score = score + 10;
          break;
        }
      }
      if(enemiesList.size() > i){
        if(enemiesList.get(i) instanceof LightEnemy){
          LightEnemy lightEnemy = (LightEnemy) enemiesList.get(i);
          lightEnemyMovement(lightEnemy, isDestroy, isEdge);
        }
        if(enemiesList.get(i) instanceof HeavyEnemy){
          HeavyEnemy heavyEnemy = (HeavyEnemy) enemiesList.get(i);
          heavyEnemyMovement(heavyEnemy, isDestroy, isEdge);
        }
        if(enemiesList.get(i) instanceof BossEnemy){
          BossEnemy bossEnemy = (BossEnemy) enemiesList.get(i);
          bossEnemyMovement(bossEnemy, isDestroy, isEdge);
        }
        
      }
    }
  }
  public void lightEnemyMovement(LightEnemy lightEnemy, boolean isDestroy, boolean isEdge){
    
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
      //EnemyMissileNoise();
    }
    
  }
  public void heavyEnemyMovement(HeavyEnemy heavyEnemy, boolean isDestroy, boolean isEdge){
    
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
      //EnemyMissileNoise();
    }
    
  }
  public void bossEnemyMovement(BossEnemy bossEnemy, boolean isDestroy, boolean isEdge){
    if (!isDestroy) {
      if(isEdge){
        bossEnemy.moveY();
        bossEnemy.render();
      }else{
        bossEnemy.moveX();
        bossEnemy.render();
      }
      
      if(bossEnemy.isShoot()){
        EnemyMissile missile = new EnemyMissile(bossEnemy);
        missilesList.add(missile);
        //EnemyMissileNoise();
      }
    }
  }
  
  public void updateMissileMovement(){
    //loop through missilesList and update each missiles movement
    for (int i = 0; i < missilesList.size(); i++) {
      if(missilesList.size() > i){
        if(missilesList.get(i) instanceof EnemyMissile && missilesList.size() > 0) {
          EnemyMissile enemyMissile = (EnemyMissile) missilesList.get(i);
          enemyMissileMovement(enemyMissile, i);
        }
      }
      if(missilesList.size() > i){
        if (missilesList.get(i) instanceof NormalMissile && missilesList.size() > 0) {
          NormalMissile normalMissile = (NormalMissile) missilesList.get(i);
          normalMissileMovement(normalMissile, i);
        }
      }
    }
  }
  
  
  
  public void enemyMissileMovement(EnemyMissile enemyMissile, int position){
    
    if (Math.abs(enemyMissile.getXCoord() + enemyMissile.getRadius()) + enemyMissile.getRadius() > MAX_XCOORD) {
      enemyMissile.wallBounce();
    }
    if (enemyMissile.getYCoord() <= -MAX_YCOORD || enemyMissile.getNumBounced() >= 2) {
      missilesList.remove(position);
    } else {
      
      enemyMissile.move();
      enemyMissile.render();
    }
    
  }
  public void normalMissileMovement(NormalMissile normalMissile, int position){
    
    if (Math.abs(normalMissile.getXCoord() + normalMissile.getRadius()) + normalMissile.getRadius() > MAX_XCOORD) {
      normalMissile.wallBounce();
    }
    if (normalMissile.getYCoord() >= MAX_YCOORD || normalMissile.getNumBounced() >= 2) {
      missilesList.remove(position);
    }  else {
      normalMissile.move();
      normalMissile.render();
    }
    
  }
  
  public void updateBackground(){
    int x = (int)(Math.random() *800);
    double length = Math.random();
    double sign = Math.random();
    if(sign > 0.5){
      x = -x;
    }
    star = new StarBackground(x , MAX_YCOORD, length/1.1);
    if(x >= -MAX_XCOORD && x <= MAX_XCOORD){
      starsList.add(star);
    }
    
    for(int i = 0 ; i < starsList.size() ; i ++){
      StarBackground currentStar = starsList.get(i);
      if (currentStar.getTailYCoord() < -MAX_YCOORD){
        starsList.remove(i);
      } else {
        currentStar.move();
      }
    }
  }
  
  public void updatePowerUpMovement(){
    int x = (int)(Math.random() *15000);
    double length = 5;
    double sign = Math.random();
    if(sign > 0.5){
      x = -x;
    }
    powerUp = new PowerUp(x , MAX_YCOORD, length);
    if(x >= -MAX_XCOORD && x <= MAX_XCOORD && Math.random() < 0.5){
      powersList.add(powerUp);
    }
    
    for(int i = 0 ; i < powersList.size() ; i ++){
      PowerUp currentPowerUp = powersList.get(i);
      int space = 10;
      double dis = Math.sqrt(Math.pow( (currentPowerUp.getXCoord() + currentPowerUp.getRadius()) - (mainShooter.getXCoord() + mainShooter.getRadius()) , 2) + Math.pow((currentPowerUp.getYCoord() + currentPowerUp.getRadius()) - (mainShooter.getYCoord() + mainShooter.getRadius()), 2));
      if (dis <= (currentPowerUp.getRadius() + mainShooter.getRadius())){
        mainShooter.setPowerUp(currentPowerUp.getPower());
        powersList.remove(i);
        powActive = false;
      } else if(currentPowerUp.getYCoord() < -MAX_YCOORD) {
        powersList.remove(i);
      } else{
        currentPowerUp.move();
      }
    }
  }
  
  public void renderUI(){
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.text(-580, 325, "Score: " + score);
    StdDraw.text(-580, -335, "Player 1: ");
    for(int i = 1 ; i <= mainShooter.getLives() ; i++){
      StdDraw.picture((-540 + i * 30),-335,"HEART.png", 30 , 35);
    }
    
    if(twoPlayer){
      for(int i = 1 ; i <= additionalShooter.getLives() ; i++){
      StdDraw.picture((-540 + i * 30),-285,"HEART.png", 30 , 35);
    }
      StdDraw.text(-580, -285, "Player 2: ");
    }
     
    StdDraw.text(580, 325, "Round: " + round);
  }
  
  public void checkGameOver(){
    for (int i = 0; i < enemiesList.size(); i++) {
      Enemy currentEnemy = enemiesList.get(i);
      //loop through enemies and see if any enemy has touched the bottom of the grid
      if (Math.abs(currentEnemy.getYCoord() + currentEnemy.getRadius()) == -100) {
        gameOver = true;
      }
      //also check if one of the enemies have touched the shooter
      if (currentEnemy.isShooterCollision(mainShooter)) {
        mainShooter.removeLife();
        enemiesList.remove(i);
        //DestroyNoise();
      }
      //also check if one of the enemies have touched the additional shooter
      if (twoPlayer) {
        if(currentEnemy.isShooterCollision(additionalShooter)) {
          additionalShooter.removeLife();
          enemiesList.remove(i);
          //DestroyNoise();
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
          //DestroyNoise();
        }
        if(twoPlayer){
          if(enemyMissile.isShooterCollision(additionalShooter)){
            additionalShooter.removeLife();
            missilesList.remove(i);
            //DestroyNoise();
          }
        }
      }
    }
    
    if(mainShooter.getLives() == 0){
      gameOver = true;
    }
  }
  
  public void checkWin(){
    if (enemiesList.size() == 0) {
      win = true;
    }
  }
  
  public int getScore(){
    return  score;
  }
  
  public int getRound(){
    return round;
  }
  
  public boolean isGameOver(){
    return gameOver;
  }
  
  public boolean isWin(){
    return win;
  }
  
  public void mainShooterKeyPresses() {

    if (StdDraw.isKeyPressed(VK_Z)) {
      mainShooter.setXVelocity(-8);
    }
    if (StdDraw.isKeyPressed(VK_C)) {
      mainShooter.setXVelocity(8);
    }
    if (StdDraw.isKeyPressed(VK_X)) {
      mainShooter.setXVelocity(0);
    }
    if (mainShooter.getPower() == 0 || mainShooter.getPower() == 2) {
      if (StdDraw.isKeyPressed(VK_W) && (coolDown1 + 20 < gameLoopCounter)) {
        coolDown1 = gameLoopCounter;
        isShot1 = true;
      }
    } else {
      if (StdDraw.isKeyPressed(VK_W)) {

        isShot1 = true;
      }
    }
    if (StdDraw.isKeyPressed(VK_A)) {
      mainShooter.setRadialVelocityBarrel(3);
    }
    if (StdDraw.isKeyPressed(VK_D)) {
      mainShooter.setRadialVelocityBarrel(-3);
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
    if (isShot1 && mainShooter.getPower() != 2) {
      NormalMissile normalMissile = new NormalMissile(mainShooter);
      missilesList.add(normalMissile);
      if(!powActive){
        
        powerUpStep = gameLoopCounter;
      }
      powActive = true;
      //ShooterMissileNoise();

    } else if (isShot1 && mainShooter.getPower() == 2) {

      NormalMissile normalMissile = new NormalMissile(mainShooter, 10);
      missilesList.add(normalMissile);

      NormalMissile normalMissile1 = new NormalMissile(mainShooter, -10);
      missilesList.add(normalMissile1);

      NormalMissile normalMissile2 = new NormalMissile(mainShooter);
      missilesList.add(normalMissile2);
      if(!powActive){
        
        powerUpStep = gameLoopCounter;
      }
      powActive = true;
      //ShooterMissileNoise();
    }
  }

  /*private void ShooterMissileNoise() {
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                StdAudio.play("shooter_missile.wav");
              }
            }).start();
  }
  private void EnemyMissileNoise() {
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                StdAudio.play("enemy_missile.wav");
              }
            }).start();
  }
  private void DestroyNoise() {
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                StdAudio.play("destroy.wav");
              }
            }).start();
  }*/

  public void additionalShooterKeyPresses (){
      if (StdDraw.isKeyPressed(VK_NUMPAD1)) {
        additionalShooter.setXVelocity(-8);
      }
      if (StdDraw.isKeyPressed(VK_NUMPAD3)) {
        additionalShooter.setXVelocity(8);
      }
      if (StdDraw.isKeyPressed(VK_NUMPAD2)) {
        additionalShooter.setXVelocity(0);
      }
      if (StdDraw.isKeyPressed(VK_NUMPAD8) && (coolDown2 + 20 < gameLoopCounter)) {
        coolDown2 = gameLoopCounter;
        isShot2 = true;
      }
      if (StdDraw.isKeyPressed(VK_NUMPAD4)) {
        additionalShooter.setRadialVelocityBarrel(3);
      }
      if (StdDraw.isKeyPressed(VK_NUMPAD6)) {
        additionalShooter.setRadialVelocityBarrel(-3);
      }
      if (StdDraw.isKeyPressed(VK_NUMPAD5)) {
        additionalShooter.setRadialVelocityBarrel(0);
      }
      if (isShot2) {
        NormalMissile normalMissile = new NormalMissile(additionalShooter);
        missilesList.add(normalMissile);
      }
  }

}
