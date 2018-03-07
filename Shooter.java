public class Shooter extends DefaultCritter{
	
	private Boolean isShoot;
	private double xCoordTurret;
	private double yCoordTurret;
	private double radialVelocityTurret;
	private double angleTurret;
	
	public Shooter(){
		
	}
	
	public void setCoordsTurret(double xCoordTurret, double yCoordTurret){
		this.xCoordTurret = xCoordTurret;
		this.yCoordTurret = yCoordTurret;
	}
	public void setRadialVelocityTurret(double radialVelocity){
		this.radialVelocity = radialVelocity;	
	}
	public void setAngleTurret(){
		this.angleTurret = angleTurret;
	}
	
	public double[] getCoordsTurret(){
		double[] currentCoordsTurret = {xCoordTurret, yCoordTurret};
		return currentCoordsTurret;
	}
	public double getRadialVelocityTurret(){
		return radialVelocityTurret;
	}
	public double getAngleTurret(){
		return angleTurret;
	}
	
	
}
