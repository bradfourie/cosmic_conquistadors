public class Shooter extends DefaultCritter{

    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double radiusBarrel;
    private double angleBarrel;

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 5);
        angleBarrel = Math.PI/2;
        radiusBarrel = super.radius/2;
        xCoordBarrel = super.radius*Math.cos(angleBarrel);
        yCoordBarrel = super.radius*Math.sin(angleBarrel);
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
        if(wallBounce(super.xCoord + super.xVelocity, super.radius))       super.xVelocity = -super.xVelocity;
        // prevents shooting backwards
        if(horizontal(angleBarrel))           radialVelocityBarrel = -radialVelocityBarrel;

        //  The shooter movement
        super.xCoord= super.xCoord + super.xVelocity;
        // The barrels rotational movement
        angleBarrel = angleBarrel + radialVelocityBarrel;
        xCoordBarrel = super.radius*Math.cos(angleBarrel) + xCoord;
        yCoordBarrel = super.radius*Math.sin(angleBarrel) + yCoord;

        render();
    }

    public void render()
    {
        // Redrawing the Barrel
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(xCoordBarrel,yCoordBarrel,radiusBarrel);
        //  Redrawing the Shooter
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
    }
    // Checking if shooter is touching the wall
    private boolean wallBounce(double cv, double r){
        boolean out = false;
        if(Math.abs(cv) + r > 100.0) out  true;
        return out;
    }
    // Checking if horizontal
    private boolean horizontal(double angle){
        boolean out = false;
        if(angle <= -0.2 || angle >= (Math.PI + 0.2)) out = true;
        return out;
    }
}
