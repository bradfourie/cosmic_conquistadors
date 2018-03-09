public class Shooter {
  /* Variables:
         double xCoordShooter       = x coordinate of the Shooter
         double yCoordShooter       = y coordinate of the Shooter
         double xVelocityShooter    = Velocity of the Shooter in the x direction
         double yVelocityShooter    = Velocity of the Shooter in the y direction
         double radiusShooter       = The radius of the big circle of the shooter
         double radiusbarrel        = The radius of the small circle of the shooter
         double angle               = The angle the shooter is facing
         double xBarrel             = x coordinate of the Barrel
         double ybarrel             = y coordinate of the Barrel
         double radialVelocity                = The radial velocity of the shooter
  */
  //variables for the Shooter
  private double xCoordShooter = 0, yCoordShooter = -0.9; 
  private double xVelocityShooter = 0, yVelocityShooter = 0;
  private double radiusShooter = 0.05;
  private double radiusBarrel = radiusShooter/2;
  private double angle = Math.PI/2;
  private double xBarrel = radiusBarrel*Math.cos(angle), ybarrel = radiusBarrel*Math.sin(angle);
  private double radialVelocity = 0;
  
  // x velocity setter
  public void setVelocity(double xv){
    xVelocityShooter = xv;
  }
  //Radial velocity setter
  public void setRadialVelocity(double rv){
    radVelocity = rv;
  }
  
  // Angle of shooter getter
  public double getAngle(){
    return angle;
  }
  // x coordinate getter
  public double getX(){
    return xCoordShooter;
  }
  // y coordinate getter
  public double getY(){
    return yCoordShooter;
  }
  
  public void move(){
    /*  set the position of the objects   */
    // if the shooter touches edge invert velocity
    if(Math.abs(xCoordShooter + xVelocityShooter) + radiusShooter > 1.0){
      xVelocityShooter = -xVelocityShooter;
    }
    // prevents shooting backwards
    if(x == -radiusShooter && radv == Math.PI/200) radv = 0;
    if(x == radiusShooter && radv == -Math.PI/200) radv = 0;
    //  The shooter movement
    xCoordShooter= xCoordShooter + xVelocityShooter;
    // The barrels rotational movement
    angle = angle +radialVelocity;
    x = radiusShooter*Math.cos(angle);
    y = radiusShooter*Math.sin(angle);
    //  Redrawing the Shooter
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.filledCircle(xCoordShooter, yCoordShooter, radiusShooter);
    // Redrawing the Barrel
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.filledCircle(x,y,radiusbarrel);
  }
}
