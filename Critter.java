
/**
 * An interface which has the properties to specify an entity which can move
 * and interact with the canvas
 * 
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @author Daniel Banks
 * @see Critter
 */

public interface Critter {

    public void setXVelocity(double xVelocity);
    public void setYVelocity(double yVelocity);
    public double getYCoord();
    public double getXCoord();
    public double getRadius();

}
