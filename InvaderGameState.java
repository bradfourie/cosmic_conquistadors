import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Z;

public class InvaderGameState{
	ArrayList<Enemy> enemiesList = new ArrayList<Enemy>(0);
	ArrayList<Missile> missilesList = new ArrayList<Missile>(0);
	Shooter shooter;
	
	StdDraw.setXscale(0, +100); //could be changed to 0 100
        StdDraw.setYscale(0, +100);
	
	//we should probably add constants for the coordinates,
	//velocities and radii
	
	//Initialise enemies!!!
	
	//main game loop
	while( !StdDraw.isKeyPressed(VK_Q) ){
	    /* Code for processing button presses */
            //  moving left 
            if( StdDraw.isKeyPressed(VK_Z) ){
                shooter.setXVelocity() = -15;
            }
            //  moving right 
            if( StdDraw.isKeyPressed(VK_C) ){
                shooter.setXVelocity = +15;
            }
            //  stop movement 
            if( StdDraw.isKeyPressed(VK_X) ){
                shooter.setXVelocity = 0;
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
		
	    //Moving the shooter 
		//this method covers
		//-inverts velocity if touches the wall
		//-prevents shooting backwards
		//-handles rotation
		//draws changes
	   shooter.move();
		
           //loop through missilesList and update each missiles movement
           for(int i=0; i<missilesList.size(); i++){
		Missile currentMissile = missilesList.get(i);
		currentMissile.move(); //TODO update missile.move()
	   }
           //loop through enemiesList and update each enemies movement
           for(int i=0; i<missilesList.size(); i++){
		Enemy currentEnemy = enemiesList.get(i);
		currentEnemy.move(); //TODO work on the enemies!
	   }
	}
	
}
