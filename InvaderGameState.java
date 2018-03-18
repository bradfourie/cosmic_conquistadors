import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Z;
import static java.awt.event.KeyEvent.VK_S;

import java.util.ArrayList;

public class InvaderGameState{
	ArrayList<Enemy> enemiesList = new ArrayList<Enemy>(0);
	ArrayList<Missile> missilesList = new ArrayList<Missile>(0);
	Shooter shooter;
	boolean inMenu = false;
	
	StdDraw.setXscale(0, +100); //could be changed to 0 100
        StdDraw.setYscale(0, +100);
	
	//we should probably add constants for the coordinates,
	//velocities and radii
	
	//Initialise enemies!!!
	
	//main game loop
	public Static void gameLoop(){
		while( !StdDraw.isKeyPressed(VK_Q) ){
			// Menu loop
	   		while( !StdDraw.isKeyPressed(VK_space) ){
				if(inMenu){
					menu();
					inMenu= true;
				}
			}
			// Game loop
			while( StdDraw.isKeyPressed(VK_space) ){
				inGame();
				inMenu= false;
			}
		}
		
	}
	public Static void menu(){
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		// Drawing controls text
		StdDraw.text(50,10,"Quit(q), Screencap (p)");
		StdDraw.text(50,20,"Move: Left (z), Stop(x), Right(c)");
		StdDraw.text(50,30,"Rotate: Left (a), Stop (s), Right(d)");
		StdDraw.text(50,40,"Shoot 9w)");
		StdDraw.text(50,60,"Press Space to Save The World");
		//Setting Title Size
		StdDraw.setFontSize(20);
		//Drawing Title
		StdDraw.text(50,75,"COSMIC CONQUISTADORS");
	}
	public Static void inGame(){
		 /* Code for processing button presses */
            	//  moving left 
            	if( StdDraw.isKeyPressed(VK_Z) ){
                	shooter.setXVelocity(-15);
            	}
            	//  moving right 
            	if( StdDraw.isKeyPressed(VK_C) ){
                	shooter.setXVelocity(15);
            	}
            	//  stop movement 
            	if( StdDraw.isKeyPressed(VK_X) ){
                	shooter.setXVelocity(0);
            	}
            	// if W is pressed create a missile 
            	if( StdDraw.isKeyPressed(VK_W)){ 
			Missile missile = new Missile(shooter);
			missilesList.add(missile);
            	}
		// if A is pressed rotate barrel left
		if( StdDraw.isKeyPressed(VK_A)){ 
			shooter.setRadialVelocityBarrel(-20);
            	}
	     	// if D is pressed rotate barrel right
            	if( StdDraw.isKeyPressed(VK_D)){ 
		shooter.setRadialVelocityBarrel(20);
            	}
	    	// if S is pressed stop rotation
            	if( StdDraw.isKeyPressed(VK_S)){ 
			shooter.setRadialVelocityBarrel(0);
            	}
		
		
	    	//Moving the shooter 
			//this method covers
			//-inverts velocity if touches the wall
			//-prevents shooting backwards
			//-handles rotation
			//draws changes
	   	shooter.move();
		

           	//loop through enemiesList and update each enemies movement
           	for(int i=0; i<missilesList.size(); i++){
			Enemy currentEnemy = enemiesList.get(i);
			currentEnemy.move(); //TODO work on the enemies!
	   	}
		
	   	//loop through missilesList and update each missiles movement
          	for(int i=0; i<missilesList.size(); i++){
  			Missile currentMissile = missilesList.get(i);
    
   			if((currentMissile.getXCoord() + 0.01 == 100) || (currentMissile.getXCoord() - 0.01 == 0)){ 
     				//check if its on the edge of the screen
				currentMissile.wallBounce();
    			}
   			if(currentMissile.getYCoord() == 100 || currentMissile.getNumBounced() >= 2){
				// remove if its bounced more than once OR if its at the top of screen
     				missilesList.remove(i);
   			}else{
   				currentMissile.move();  
				//TODO update missile.move()
   			}
    	   	}
	}
}
