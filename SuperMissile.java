
/**
 * This class extends Missile to add extra functionality for a type
 * of missile that can be used as a super missile power up for the shooter,
 * SuperMissiles are larger than NormalMissiles, and do much more damage.
 *
 * @author Bradley Fourie
 * @author Daniel Banks
 * @author Heinrich Benz
 * @see Missile
 * @see Shooter
 */
public class SuperMissile extends Missile{
  
  /**
     * Creates a SuperMissile using the Missile class as a basis,
     * uses the shooter it is shot from to determine its starting x and
     * y coordinates, its starting velocity and its angle specified by the
     * shooter at that instant, as well as its radius. Super missiles have
     * a default velocity of 15, and a radius of 7.
     *
     * @param shooter the shooter that shoots the SuperMissile
     * @see Missile
     */
   public SuperMissile(Shooter shooter){
     super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15*Math.cos(shooter.getAngleBarrel()*Math.PI/180), 15*Math.sin(shooter.getAngleBarrel()*Math.PI/180), 7);
   }
   
   public void render(){
     StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2, super.angle*Math.PI/180);
   }
}