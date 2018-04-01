import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Z;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_SPACE;
import java.util.ArrayList;

public class InvaderGameState{
  
  private int startYCoordShooter = -80;
  private int startXCoordShooter = 0;
  
  public InvaderGameState(){
    
    StdDraw.setXscale(-100, +100);
    StdDraw.setYscale(-100, +100);
    
    //while the user hasnt pressed q
    //  start by drawing the menu
    //  if user presses space start gameloop
    //  while gameOver == false
    //      gameOver == true if enemiesList has 0 elements
    //      gameOver == true if enemy reaches bottom
    //
    // draw finish screen and display user score
    // wait 5 seconds and restart gameloop
    
    boolean gameStart = true;
    
    while( !StdDraw.isKeyPressed(VK_Q) ) {
      if(gameStart){
        renderMenu();
        if(StdDraw.isKeyPressed(VK_SPACE)) {
          gameStart = false;
        }
        
      }
      if(!gameStart) {
        //clear the screen
        StdDraw.clear();
        
        boolean gameOver = false;
        boolean win = false;
        int score = 0;
        
        ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
        ArrayList<Missile> missilesList = new ArrayList<Missile>();
        StarBackground star;
        ArrayList<StarBackground> starsList = new ArrayList<StarBackground>();
        Shooter shooter = new Shooter(startXCoordShooter, startYCoordShooter, 0, 0);
        StdDraw.setXscale(-100, +100);
        StdDraw.setYscale(-100, +100);
        
        int gameLoopCounter = 0;
        int stepLastShot = 0;
        int startYCoordEnemy = 96;
        int startXCoordEnemy = -96;
        int enemyXVelocity = 1;
        
        //loops to initialise the grid of enemies
        for (int i = 0; i < 24; i = i + 7) {
          for (int j = 0; j < 60; j = j + 7) {
            Enemy enemy = new Enemy(startXCoordEnemy + j, startYCoordEnemy - i, enemyXVelocity, 0);
            enemiesList.add(enemy);
          }
        }
        
        while (!gameOver) {
          //  moving left
          boolean isShot = false;
          if (StdDraw.isKeyPressed(VK_Z)) {
            shooter.setXVelocity(-2);
          }
          //  moving right
          if (StdDraw.isKeyPressed(VK_C)) {
            shooter.setXVelocity(2);
          }
          //  stop movement
          if (StdDraw.isKeyPressed(VK_X)) {
            shooter.setXVelocity(0);
          }
          // if W is pressed create a missile
          if (StdDraw.isKeyPressed(VK_W) && (stepLastShot + 20 < gameLoopCounter)) {
            stepLastShot = gameLoopCounter;
            isShot = true;
          }
          // if A is pressed rotate barrel left
          if (StdDraw.isKeyPressed(VK_A)) {
            shooter.setRadialVelocityBarrel(0.1);
          }
          // if D is pressed rotate barrel right
          if (StdDraw.isKeyPressed(VK_D)) {
            shooter.setRadialVelocityBarrel(-0.1);
          }
          // if S is pressed stop rotation
          if (StdDraw.isKeyPressed(VK_S)) {
            shooter.setRadialVelocityBarrel(0);
          }
          if (isShot) {
            Missile missile = new Missile(shooter);
            missilesList.add(missile);
          }
          
          
          shooter.move();
          
          //loop through enemiesList and update each enemies movement
          for (int i = 0; i < enemiesList.size(); i++) {
            boolean isDestroy = false;
            Enemy currentEnemy = enemiesList.get(i);
            
            for (int j = 0; j < missilesList.size(); j++) {
              Missile currentMissile = missilesList.get(j);
              if (currentEnemy.onMissileCollision(currentMissile)) {
                isDestroy = true;
                enemiesList.remove(i);
                missilesList.remove(j);
                score = score + 10;
                break;
              }
            }
            
            if (!isDestroy) {
              currentEnemy.move();
            }
            
          }
          
          //loop through missilesList and update each missiles movement
          for (int i = 0; i < missilesList.size(); i++) {
            Missile currentMissile = missilesList.get(i);
            
            //can anyone tell me what this +1 is for, if so please say on the group xD
            if (Math.abs(currentMissile.getXCoord() + currentMissile.getRadius()) + 1 > 100) {
              currentMissile.wallBounce();
            }
            
            if (currentMissile.getYCoord() == 100 || currentMissile.getNumBounced() >= 2) {
              // remove if its bounced more than once OR if its at the top of screen
              missilesList.remove(i);
            } else {
              currentMissile.move();
            }
          }
          
          int x = (int)(Math.random() * 600);
          double length = Math.random();
          double sign = Math.random();
          if(sign > 0.5){
            x = -x;
          }
          star = new StarBackground(x , 100 , length);
          if(x >= -100 && x <= 100){
            starsList.add(star);
          }
          
          for(int i = 0 ; i < starsList.size() ; i ++){
            StarBackground currentStar = starsList.get(i);
            if(currentStar.getTailYCoord() < -100){
              starsList.remove(i);
            }else{
              currentStar.move();
            }
          }
          //drawing the score
          StdDraw.setPenColor(StdDraw.BLACK);
          StdDraw.text(-85, 95, "score: " + score);
          
          
          StdDraw.show(30);
          StdDraw.clear(StdDraw.BLACK);
          gameLoopCounter++;
          
          
          //checking if the game is over
          //first check if any enemies are left
          if (enemiesList.size() == 0) {
            gameOver = true;
            win = true;
          }
          //then loop through enemies and see if any enemy has touched the bottom of the grid
          for (int i = 0; i < enemiesList.size(); i++) {
            Enemy currentEnemy = enemiesList.get(i);
            
            if (Math.abs(currentEnemy.getYCoord() + currentEnemy.getRadius()) == -100) {
              gameOver = true;
            }
          }
        }
        
        //ok cool now draw the game over screen
        renderEndGame(win, score);
        try  {
          Thread.sleep(5000);
        } catch (InterruptedException e){
          //just here because java wants it to be here
        }
        
      }
    }
    
    System.exit(0);
  }
  
  public void renderMenu(){
    
    StdDraw.setPenColor(StdDraw.BLACK);
    // Drawing controls text
    StdDraw.text(0,10,"Quit(q), Screencap (p)");
    StdDraw.text(0,20,"Move: Left (z), Stop(x), Right(c)");
    StdDraw.text(0,30,"Rotate: Left (a), Stop (s), Right(d)");
    StdDraw.text(0,40,"Shoot (w)");
    StdDraw.text(0,60,"Press Space to Save The World");
    //Setting Title Size
    StdDraw.setFont();
    //Drawing Title
    StdDraw.text(0,75,"COSMIC CONQUISTADORS");
    
    StdDraw.show();
  }
  
  public void renderEndGame(boolean win, double score){
    StdDraw.setPenColor(StdDraw.BLACK);
    
    if(win){
      StdDraw.text(0,10,"Congratulations! You saved the World!");
      StdDraw.text(0,20,"Your score is: " + score);
    }else{
      StdDraw.text(0,10,"You have failed, humanity lies in ruins");
      StdDraw.text(0,20,"Your score is: " + score);
    }
    
    StdDraw.show();
  }
}
