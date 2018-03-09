public class DefaultCritter{
	
	protected double xCoord;
	protected double yCoord;
	protected double xVelocity;
	protected double yVelocity;
	protected double radius;
	
	public DefaultCritter(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.radius = radius;
	}
	
	//setters
	protected void setCoords(double xCoord, double yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	protected void setVelocity(double xVelocity, double yVelocity){
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	protected double setRadius(double radius){
		this.radius = radius;
	}
	
	//getters
	protected double getyCoord(){
		return yCoord;
	}
	protected double getxCoord(){
		return xCoord;
	}
	protected double getRadius(){
		return radius;
	}
}
