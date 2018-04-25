public class EnemyMissile extends Missile {

    public EnemyMissile(Enemy enemy){
        super(enemy.getXCoord(), enemy.getYCoord(), 0, 0, 3);
        super.yVelocity = -15;
    }

    public EnemyMissile(Enemy enemy, double angleOffset , Shooter shooter){
        super(enemy.getXCoord(), enemy.getYCoord(), 0, 0, 3);
    
        if(enemy.getXCoord() >= shooter.getXCoord()){
          super.xVelocity = -15 * Math.cos(Math.atan((  shooter.getYCoord()  - enemy.getYCoord() )/(shooter.getXCoord() - enemy.getXCoord())) + (angleOffset)*Math.PI/180);
          super.yVelocity = -15 * Math.sin(Math.atan((shooter.getYCoord()  - enemy.getYCoord())/(shooter.getXCoord() - enemy.getXCoord())) + (angleOffset)*Math.PI/180);
        }else{
          super.xVelocity = 15 * Math.cos(Math.atan((  shooter.getYCoord()  - enemy.getYCoord() )/(shooter.getXCoord() - enemy.getXCoord())) + (angleOffset)*Math.PI/180);
          super.yVelocity = 15 * Math.sin(Math.atan((shooter.getYCoord()  - enemy.getYCoord())/(shooter.getXCoord() - enemy.getXCoord())) + (angleOffset)*Math.PI/180);
        }
          
    }

    public boolean isShooterCollision(Shooter shooter){
        double radiusShooter = shooter.getRadius();
        double distanceToShooter = Math.sqrt( Math.pow(shooter.getXCoord() - super.xCoord, 2) + Math.pow(shooter.getYCoord() - super.yCoord, 2));
        double distanceToBarrel = Math.sqrt( Math.pow(shooter.getXCoordBarrel() - super.xCoord, 2) + Math.pow(shooter.getYCoordBarrel() - super.yCoord, 2));

        if( (distanceToShooter <= (radiusShooter + super.radius))  ||  (distanceToBarrel <= (radiusShooter + super.radius))){
            return true;
        }else{
            return false;
        }
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
    
}
