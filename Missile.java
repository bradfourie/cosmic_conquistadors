public class Missile extends DefaultCritter{
	
	private int numBounced; //counts the number of bounces a missile has made
	
	public Missile(Shooter shooter){
		double velocity = 30;
		double radius = 10;
		
		this.numBounced = 0;
		
		//The initial x and y coordinates of the missile
		//is the same as that of the x and y coordinates
		//of the shooter at the instant the missile is created
		
		double xCoordBarrel = shooter.getXCoordBarrel();
		double yCoordBarrel = shooter.getYCoordBarrel();
		double initialAngle = shooter.getAngleBarrel();
		
		double xVelocity = velocity * Math.cos(initialAngle);
		double yVelocity = velocity * Math.sin(initialAngle);
		
		super(xCoordbarrel, yCoordBarrel, xVelocityBarrel, yVelocityBarrel, radius);
	}
	
	public int getNumBounced(){
  		return hasBounced; 
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
