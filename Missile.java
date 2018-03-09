public class Missile extends DefaultCritter{
	
	private initialAngle;

	public Missile(double xCoord, double yCoord, double xVelocity, double yVelocity, double initialAngle){
		double radius = 0.01
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
		
	}
		
}
