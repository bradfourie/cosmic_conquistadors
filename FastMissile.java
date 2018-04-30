
/**
 * This class extends Missile to add extra functionality for a type
 * of missile that can be used as a fast missile power up for the shooter,
 * FastMissiles are the same size as NormalMissiles, and do the same amount
 * of damage, however they move much faster.
 *
 * @author Bradley Fourie
 * @author Daniel Banks
 * @author Heinrich Benz
 * @see Missile
 * @see Shooter
 */
public class FastMissile extends Missile{

  /**
     * Creates a FastMissile using the Missile class as a basis,
     * uses the shooter it is shot from to determine its starting x and
     * y coordinates, its starting velocity and its angle specified by the
     * shooter at that instant, as well as its radius. Fast missiles have 
     * a default velocity of 25, and a radius of 3.
     *
     * @param shooter the shooter that shoots the NormalMissile
     * @see Missile
     */
 public FastMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 25 * Math.cos((shooter.getAngleBarrel())*Math.PI/180), 25 * Math.sin((shooter.getAngleBarrel())*Math.PI/180), 3);
 }

 public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletGreen.png",super.radius*5,super.radius*2, super.angle*Math.PI/180);
 }
  
}