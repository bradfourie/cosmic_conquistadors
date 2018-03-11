public class Missile extends DefaultCritter{
	
	private initialAngle;

	public Missile(double Velocity, double initialAngle, double Barrel){
		double radius = 0.01;
		double xVelocity;
		double yVelocity;
		double xCoord;
		double yCoord;
		
		xVelocity = Velocity*Math.cos(initialAngle);
		yVelocity = Velocity*Math.sin(initialAngle);
		xCoord = Barrel.getXCoordBarrel;
		yCoord = Barrel.getYcoordBarrel;
		yCoord = radius*Math.sin(initialAngle);
		
		this.initialAngle = initialAngle;
		super(xCoord, yCoord, xVelocity, yVelocity, radius);
	}
	
	/* Setters */
	public void setInitialAngle(double initialAngle){
		this.initialAngle = initialAngle;
	}
	
	/* Getters */
	public double getInitialAngle(){
		return initialAngle;
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
