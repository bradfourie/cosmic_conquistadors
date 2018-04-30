/**
 *  This class extends DefaultCritter and serves as a model object to create
 *  different types of missiles
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see DefaultCritter
 */
public class Missile extends DefaultCritter{
    private int numBounced;
    protected double angle;
    
    /**
     * Class constructor that creates a missile using the DefaultCritter class as a basis, also adds properties
     * to missile by specifying the angle at which the bullet was shot.
     *
     * @param xCoord the x-coordinate of the missile
     * @param yCoord the y-coordinate of the missile
     * @param xVelocity the x-velocity of the missile
     * @param yVelocity the y-velocity of the missile
     * @param radius the radius or "size" of the missile
     * @see DefaultCritter
     */
    public Missile(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius){
        super(xCoord, yCoord, xVelocity, yVelocity, radius);
        this.numBounced = 0;
        this.angle = Math.atan((yVelocity/xVelocity));
    }
    
    public int getNumBounced(){
        return numBounced;
    }
    
    /**
     * Indicates that the bullet has bounced and cannot bounce again,
     * inverts the and and x velocity of the bullet to simulate bouncing
     */
    public void wallBounce(){
        numBounced++;
        super.xVelocity = - super.xVelocity;
        angle=-angle;
    }

    public void move(){
        StdDraw.setPenColor(StdDraw.YELLOW);
        super.xCoord = super.xCoord + super.xVelocity;
        super.yCoord = super.yCoord + super.yVelocity;
    }
}