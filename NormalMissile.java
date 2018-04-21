
public class NormalMissile extends Missile{

  public NormalMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos(shooter.getAngleBarrel()*Math.PI/180), 15 * Math.sin(shooter.getAngleBarrel()*Math.PI/180), 3);
  }

  public NormalMissile(Shooter shooter, double angleOffset){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos((shooter.getAngleBarrel() + angleOffset)*Math.PI/180), 15 * Math.sin((shooter.getAngleBarrel() + angleOffset)*Math.PI/180), 3);
  }
  
  public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2, super.angle);
  }
}
