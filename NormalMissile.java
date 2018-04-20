public class NormalMissile extends Missile{
  
  public NormalMissile(Shooter shooter){
    super(shooter.getXCoord(), shooter.getYCoord(), 15 * Math.cos(shooter.getAngleBarrel()*Math.PI/180+Math.PI/2), 15 * Math.sin(shooter.getAngleBarrel()*Math.PI/180+Math.PI/2), 3);
    super.xVelocity = 15*Math.cos(shooter.getAngleBarrel()*Math.PI/180+Math.PI/2);
    super.yVelocity = 15*Math.sin(shooter.getAngleBarrel()*Math.PI/180+Math.PI/2);
    super.angle = shooter.getAngleBarrel()+90;
  }
  
  public void moveAngle(double a){
    super.angle = super.angle + a;
    super.xVelocity = 15*Math.cos(super.angle * (Math.PI/180));
    super.yVelocity = 15*Math.sin(super.angle * (Math.PI/180));
  }
  
  public void render(){
    StdDraw.picture(super.xCoord,super.yCoord,"BulletYellow.png",super.radius*5,super.radius*2,angle);
  }
}
