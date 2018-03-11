public class Enemy extends DefaultCritter {
	
	public Enemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
		double radius = 0.02;
		super(xCoord, yCoord, xVelocity, yVelocity);
		
	}
	
	private void onMissileCollision(){
		
	}
	
	public void move(){
		super.xCoord = super.xCoord + super.xVelocity;
		super.yCoord = super.yCoord + super.yVelocity;
		StdDraw.setPenColor(StdDraw.RED);
        	StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
	}
}
