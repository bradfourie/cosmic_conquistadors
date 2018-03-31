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

import java.util.ArrayList;

public class InvaderGameState{
  
  public static void main(String args[]){
    ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
    ArrayList<Missile> missilesList = new ArrayList<Missile>();
    Shooter shooter;
    shooter = new Shooter(0 , -80 , 0, 0);
    StdDraw.setXscale(-100, +100); //could be changed to 0 100
    StdDraw.setYscale(-100, +100);
    
    //we should probably add constants for the coordinates,
    //velocities and radii
    
    //Initialise enemies!!!
    
    //main game loop
    int stepper = 0;
    int stepLastShot = 0;
    int starty = 96;
    int startx = -96;
    Enemy createEnemy;
    for(int i = 0 ; i < 24 ; i = i + 7){
      for(int j = 0 ; j < 60 ; j = j + 7){
        createEnemy = new Enemy(startx + j , starty - i , 1 , 0);
        enemiesList.add(createEnemy);
      }
    }
    while( !StdDraw.isKeyPressed(VK_Q) ){
      
      /* Code for processing button presses */
      //  moving left 
      boolean isShot = false;
      if( StdDraw.isKeyPressed(VK_LEFT) ){
        shooter.setXVelocity(-3);
      }
      //  moving right 
      if( StdDraw.isKeyPressed(VK_RIGHT) ){
        shooter.setXVelocity(3);
      }
      //  stop movement 
      if( StdDraw.isKeyPressed(VK_UP) ){
        shooter.setXVelocity(0);
      }
      // if W is pressed create a missile 
      if( StdDraw.isKeyPressed(VK_W) && (stepLastShot + 20 < stepper)){
        stepLastShot = stepper;
        isShot = true;
      }
      // if A is pressed rotate barrel left
      if( StdDraw.isKeyPressed(VK_A)){ 
        shooter.setRadialVelocityBarrel(0.2);
      }
      // if D is pressed rotate barrel right
      if( StdDraw.isKeyPressed(VK_D)){ 
        shooter.setRadialVelocityBarrel(-0.2);
      }
      // if S is pressed stop rotation
      if( StdDraw.isKeyPressed(VK_S)){ 
        shooter.setRadialVelocityBarrel(0);
      }
      if(isShot){
        Missile missile = new Missile(shooter);
        missilesList.add(missile);
      }
      
      
      //Moving the shooter 
      //this method covers
      //-inverts velocity if touches the wall
      //-prevents shooting backwards
      //-handles rotation
      //draws changes
      shooter.move();
      
      
      //loop through enemiesList and update each enemies movement
      for(int i=0; i<enemiesList.size(); i++){
        boolean isDestroy = false;
        Enemy currentEnemy = enemiesList.get(i);
        for(int j = 0 ; j < missilesList.size() ; j ++){
          Missile currentMissile = missilesList.get(j);
          if(currentEnemy.onMissileCollision(currentMissile)){
            isDestroy = true;
          enemiesList.remove(i);
          missilesList.remove(j);
          break;
        }
        }
        if(!isDestroy){
          currentEnemy.move();
        }
        //TODO work on the enemies!
        
      }
      
      //loop through missilesList and update each missiles movement
      for(int i=0; i<missilesList.size(); i++){
        Missile currentMissile = missilesList.get(i);
        
        if(Math.abs(currentMissile.getXCoord() + 5) + 1 > 100){ 
          //check if its on the edge of the screen
          currentMissile.wallBounce();
        }
        if(currentMissile.getYCoord() == 100 || currentMissile.getNumBounced() >= 2){
          // remove if its bounced more than once OR if its at the top of screen
          missilesList.remove(i);
        }else{
          currentMissile.move();  
        }
      } 
      
      StdDraw.show(30);
      StdDraw.clear();
      stepper++;
    }
    
    System.exit(0);
  }
}
