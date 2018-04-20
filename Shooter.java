public class Shooter extends DefaultCritter{

    private double INITIAL_ANGLE_BARREL = Math.PI/2.0;
    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double radiusBarrel;
    private double angleBarrel;
    private int lives;
    private int powerUp;

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity, int lives){
        super(xCoord, yCoord, xVelocity, yVelocity, 10);
        angleBarrel = 0;
        radiusBarrel = super.radius/2.0;
        xCoordBarrel = super.radius*Math.cos(radiusBarrel);
        yCoordBarrel = super.radius*Math.sin(radiusBarrel);
        this.lives = lives;
    }

    /* Setters */
    public void setRadialVelocityBarrel(double radialVelocityBarrel){
        this.radialVelocityBarrel = radialVelocityBarrel;
    }
    public void setPowerUp(int power){
      powerUp = power;
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
    public int getLives(){
        return lives;
    }

    public double getPower(){
    return powerUp;  
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
    public void removeLife(){
        lives--;
    }

    /* All other methods that add functionality */
    public void move(){
        /*  set the position of the shooter and draws it  */
        // if the shooter touches edge invert velocity
        if(Math.abs(super.xCoord + super.xVelocity) + super.radius > 640)       super.xVelocity = -super.xVelocity;
        // prevents shooting backwards
        if(angleBarrel <= -90 || angleBarrel >= 90)           radialVelocityBarrel = -radialVelocityBarrel;

        //  The shooter movement
        super.xCoord= super.xCoord + super.xVelocity;
        // The barrels rotational movement
        angleBarrel = angleBarrel + radialVelocityBarrel;
        xCoordBarrel = super.radius*Math.cos(angleBarrel*Math.PI/180+90) + xCoord;
        yCoordBarrel = super.radius*Math.sin(angleBarrel*Math.PI/180+90) + yCoord;
        render();
    }
   
    public void render()
    {
        if(getLives() == 3){
            StdDraw.setPenColor(StdDraw.BLUE);
        }
        if(getLives() == 2){
            StdDraw.setPenColor(StdDraw.ORANGE);
        }
        if(getLives() == 1){
            StdDraw.setPenColor(StdDraw.YELLOW);
        }
        // Redrawing the Barrel
        //StdDraw.filledCircle(xCoordBarrel,yCoordBarrel,radiusBarrel);
        //  Redrawing the Shooter
        StdDraw.picture(super.xCoord,super.yCoord,"PlayerBlue.png",radius*5,radius*5,angleBarrel);
    }

    public void resetState(int startXCoord, int startYCoord) {
        xCoord = startXCoord;
        yCoord = startYCoord;
        radialVelocityBarrel = 0;
        xCoordBarrel = super.radius*Math.cos(INITIAL_ANGLE_BARREL);
        yCoordBarrel = super.radius*Math.sin(INITIAL_ANGLE_BARREL);
    }
}