public class Missile extends DefaultCritter{
	
	private int numBounced; //counts the number of bounces a missile has made
	
	public Missile(Shooter shooter){
		super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 30 * Math.cos(initialAngle), 30 * Math.sin(initialAngle), 10);
		this.numBounced = 0;
		
		//The initial x and y coordinates of the missile
		//is the same as that of the x and y coordinates
		//of the shooter's barrel at the instant the missile
		//is created
		//ill add proper comments here tomorrow
		
	}
	
	public int getNumBounced(){
  		return numBounced; 
 	}
	
	public void wallBounce(){
		numBounced++;
  		super.xVelocity = - super.xVelocity;
	}
	
	public void move(){
		super.xCoord = super.xCoord + super.xVelocity;
		super.yCoord = super.yCoord + super.yVelocity;
		StdDraw.setPenColor(StdDraw.YELLOW);
        	StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
	}
		
}
