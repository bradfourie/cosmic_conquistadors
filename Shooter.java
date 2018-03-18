public class Shooter extends DefaultCritter{
  
    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double radiusBarrel;
    private double angleBarrel; 

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 5); // final param radius
        angleBarrel = Math.PI/2;
        radiusBarrel = 5/2; //where 5 is the radius of the shooter
        xCoordBarrel = radiusBarrel*Math.cos(angleBarrel);
        yCoordBarrel = radiusBarrel*Math.sin(angleBarrel);
    }
  
    /* Setters */
    public void setRadialVelocityBarrel(double radialVelocityBarrel){
        this.radialVelocityBarrel = radialVelocityBarrel;
    }
    public void setRadiusBarrel(double radiusBarrel){
        this.radiusBarrel = radiusBarrel;
    }
    public void setAngleBarrel(double angleBarrel){
        this.angleBarrel = angleBarrel; 
    }
    public void setXCoordBarrel(double xCoordBarrel){
        this.xCoordBarrel = xCoordBarrel;
    }
    public void setYCoordBarrel(){
        this.yCoordBarrel = yCoordBarrel;
    }
  
    /* Getters */
    public double getRadialVelocityBarrel(){
        return radialVelocityBarrel;
    }
    public double getRadiusBarrel(){
        return radiusBarrel;
    }
    public double getAngleBarrel(){
        return angleBarrel; 
    }
    public double getXCoordBarrel(){
        return xCoordBarrel;
    }
    public double getYCoordBarrel(){
        return yCoordBarrel;
    }
  
    /* All other methods that add functionality */
    public void move(){
        /*  set the position of the shooter and draws it  */
        // if the shooter touches edge invert velocity
        if(Math.abs(super.xCoord + super.xVelocity) + radius > 100.0)     super.xVelocity = -super.xVelocity;
        // prevents shooting backwards
        if(x == -radiusBarrel && radialVelocityBarrel == Math.PI/200)   radialVelocityBarrel = 0;
        if(x == radiusBarrel && radialVelocityBarrel == -Math.PI/200)   radialVelocityBarrel = 0;
        //  The shooter movement
        super.xCoord= super.xCoord + super.xVelocity;
        // The barrels rotational movement
        angleBarrel = angleBarrel + radialVelocityBarrel;
        xCoordBarrel = radiusBarrel*Math.cos(angle);
        yCoordBarrel = radiusbarrel*Math.sin(angle);
        // Redrawing the Barrel
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(xCoordbarrel,yCoordBarrel,radiusBarrel);
        //  Redrawing the Shooter
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
    }
}
