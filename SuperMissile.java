/**
 *  This class extends Missile
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see Missile
 */
public class SuperMissile extends Missile{
  
  /**
     * Class constructor that creates a SuperMissile using the Missile class as a basis,
     * uses the shooter it is shot from to determine its properties.
     *
     * @param shooter the shooter that shoots the SuperMissile
     * @see Missile
     */
   public SuperMissile(Shooter shooter){
     super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15*Math.cos(shooter.getAngleBarrel()*Math.PI/180), 15*Math.sin(shooter.getAngleBarrel()*Math.PI/180), 7);
   }
   
   /**
    * Renders the SuperMissile on the canvas.
    */
   public void render(){
     StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2, super.angle*Math.PI/180);
   }
}