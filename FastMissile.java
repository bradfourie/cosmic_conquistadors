/**
 *  This class extends Missile
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see Missile
 */
public class FastMissile extends Missile{

  /**
     * Class constructor that creates a FastMissile using the Missile class as a basis,
     * uses the shooter it is shot from to determine its properties.
     *
     * @param shooter the shooter that shoots the NormalMissile
     * @see Missile
     */
 public FastMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 25 * Math.cos((shooter.getAngleBarrel())*Math.PI/180), 25 * Math.sin((shooter.getAngleBarrel())*Math.PI/180), 3);
 }
  /**
    * Renders the FastMissile on the canvas.
    */
 public void render(){
  StdDraw.picture(super.xCoord,super.yCoord,"BulletGreen.png",super.radius*5,super.radius*2, super.angle*Math.PI/180);
 }
  
}