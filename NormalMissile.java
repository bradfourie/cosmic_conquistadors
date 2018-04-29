/**
 *  This class extends Missile
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see Missile
 */
public class NormalMissile extends Missile{
  
  /**
     * Class constructor that creates a NormalMissile using the Missile class as a basis,
     * uses the shooter it is shot from to determine its properties.
     *
     * @param shooter the shooter that shoots the NormalMissile
     * @see Missile
     */
  public NormalMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos(shooter.getAngleBarrel()*Math.PI/180), 15 * Math.sin(shooter.getAngleBarrel()*Math.PI/180), 3);
  }
  
  /**
     * Class constructor that creates a NormalMissile using the Missile class as a basis,
     * uses the shooter it is shot from as well as an offset angle to determine its properties.
     *
     * @param shooter the shooter that shoots the NormalMissile
     * @param angleOffset an offset angle for when a bullet shoots at a different angle to the shooter
     * @see Missile
     */
  public NormalMissile(Shooter shooter, double angleOffset){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos((shooter.getAngleBarrel() + angleOffset)*Math.PI/180), 15 * Math.sin((shooter.getAngleBarrel() + angleOffset)*Math.PI/180), 3);
  }
  
  public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2, super.angle);
  }
}
