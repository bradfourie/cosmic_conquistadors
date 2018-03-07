public class DefaultCritter{
	
	private double xCoord;
	private double yCoord;
	private double xVelocity;
	private double yVelocity;
	
	public DefaultCritter(){
		
	}
	
	//setters for the velocity and the coordinates
	public void setCoords(double xCoord, double yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	public void setVelocity(double xVelocity, double yVelocity){
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	//getter for the coordinates
	public double[] getCoords(){
		double[] currentCoords = {xCoord, yCoord};
		return currentCoords;
	}
}
