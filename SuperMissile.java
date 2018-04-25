public class SuperMissile extends Missile{
  public SuperMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos(shooter.getAngleBarrel()*Math.PI/180), 15 * Math.sin(shooter.getAngleBarrel()*Math.PI/180), 7);
  }
  public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2, super.angle);
  }
}
