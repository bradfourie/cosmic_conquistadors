public class Enemy extends DefaultCritter {
	
	public Enemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
		double radius = 0.02;
		super(xCoord, yCoord, xVelocity, yVelocity);
		
	}
	
	private void onMissileCollision(){
		
	}
 
 	public void move(){
   		double xVelocityTemp = super.xVelocity; // using this so i can set the xVelocity of the enemy to 0 just for one time step
   		boolean isBounce = false;
   		if(super.xCoord == 0 || super.xCoord == 100){
     			isBounce = true;
     			super.xVelocity = 0;
     			super.yVelocity = -0.04;
   		}
  		super.xCoord = super.xCoord + super.xVelocity;
  		super.yCoord = super.yCoord + super.yVelocity;
  		StdDraw.setPenColor(StdDraw.RED);
         	StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
         	if(isBounce){
            		super.xVelocity = -xVelocityTemp; //setting xVelocity to opposite of what it was before
            		super.yVelocity = 0;
         	}
        
 	}
}
