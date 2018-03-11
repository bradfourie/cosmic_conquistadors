public class Missile extends DefaultCritter{
	
	public Missile(Shooter shooter){
		double velocity = 0.02;
		double radius = 0.01;
		
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
	
	public void wallBounce(){
		super.xVelocity = - super.xVelocity;
	}
	public void move(){
		super.xCoord = super.xCoord + super.xVelocity;
		super.yCoord = super.yCoord + super.yVelocity;
		StdDraw.setPenColor(StdDraw.YELLOW);
        	StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
	}
		
}
