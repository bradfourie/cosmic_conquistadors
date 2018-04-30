/**
 * A class that extends Missile to add extra functionality to specify a default 
 * missile type that can be used by the shooter.
 *
 * @author Bradley Fourie
 * @author Daniel Banks
 * @author Heinrich Benz
 * @see Missile
 * @see Shooter
 */
public class NormalMissile extends Missile{
  
  /**
     * A constructor used for when the Shooter has no power ups and a normal missile has no
     * offset angle and only needs to be created using the coordinates of the shooter.
     *
     * @param shooter the shooter that shoots the NormalMissile
     * @see Shooter
     */
  public NormalMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos(shooter.getAngleBarrel()*Math.PI/180), 15 * Math.sin(shooter.getAngleBarrel()*Math.PI/180), 3);
  }
  
  /**
     * A second constructor that allows a NormalMissile to be instantiated at a specific angle
     * from the barrel, this constructor is used for the tri-barrel powerup.
     *
     * @param shooter the shooter that shoots the NormalMissile
     * @param angleOffset an offset angle for when a missile shoots at a different angle to the shooter
     * @see Shooter
     */
  public NormalMissile(Shooter shooter, double angleOffset){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos((shooter.getAngleBarrel() + angleOffset)*Math.PI/180), 15 * Math.sin((shooter.getAngleBarrel() + angleOffset)*Math.PI/180), 3);
  }
  
  public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2, super.angle*180/Math.PI);
  }
}
