public class FastMissile extends Missile{
  public FastMissile(Shooter shooter){
    super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 25 * Math.cos((shooter.getAngleBarrel())*Math.PI/180), 25 * Math.sin((shooter.getAngleBarrel())*Math.PI/180), 3);
  }
  
  public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletGreen.png",super.radius*5,super.radius*2, super.angle);
  }
  
}