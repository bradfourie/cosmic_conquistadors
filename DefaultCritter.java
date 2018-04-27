/**
 * DefaultCritter is the base class for all of the entities that will be
 * created and used in the Cosmic-Conquistadors game, this class implements
 * the Critter interface which gives it the methods to change the coordinates,
 * velocities and radius of the entity.
 *
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @author Daniel Banks
 * @see Critter
 * @see Enemy
 * @see Shooter
 * @see Missile
 */
public class DefaultCritter implements Critter {

    protected double xCoord;
    protected double yCoord;
    protected double xVelocity;
    protected double yVelocity;
    protected double radius;

    /**
     * Constructor that creates the entity using the variables needed to specify the position,
     * movement and radius of the entity
     *
     * @param xCoord the x-coordinate the entity is to be initialised at
     * @param yCoord the y-coordinate the entity is to be initialised at
     * @param xVelocity the x-velocity of the entity
     * @param yVelocity the y-velocity of the entity
     * @param radius the radius or "size" of the entity
     */
    public DefaultCritter(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.radius = radius;
    }

    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity){
        this.yVelocity = yVelocity;
    }

    public double getYCoord(){
        return yCoord;
    }

    public double getXCoord(){
        return xCoord;
    }
    
    public double getRadius(){
        return radius;
    }

}
