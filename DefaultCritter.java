public class DefaultCritter{
	
	private double xCoord;
	private double yCoord;
	private double xVelocity;
	private double yVelocity;
	private double radius;
	
	public DefaultCritter(){
		
	}
	
	//setters
	public void setCoords(double xCoord, double yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	public void setVelocity(double xVelocity, double yVelocity){
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	public double setRadius(double radius){
		this.radius = radius;
	}
	
	//getters
	public double getyCoord(){
		return yCoord;
	}
	public double getxCoord(){
		return xCoord;
	}
	public double getRadius(){
		return radius;
	}
}
