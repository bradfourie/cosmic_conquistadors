public class Missile extends DefaultCritter{
 
 private int numBounced; //counts the number of bounces a missile has made
 
 public Missile(Shooter shooter){
  super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 5 * Math.cos(shooter.getAngleBarrel()), 5 * Math.sin(shooter.getAngleBarrel()), 1);
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
 
 public double getXCoord(){
   return super.xCoord;
 }
 
 public double getYCoord(){
   return super.yCoord;
 }
 
 public double getRadius(){
  return super.radius; 
 }
 
 public void move(){
  super.xCoord = super.xCoord + super.xVelocity;
  super.yCoord = super.yCoord + super.yVelocity;
  StdDraw.setPenColor(StdDraw.BLACK);
         StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
 }
  
}
