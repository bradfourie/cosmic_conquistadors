/**
 *  This class extends Missile
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see Missile
 */
public class EnemyMissile extends Missile {
  
  /**
    * Class constructor that creates a EnemyMissile using the Missile class as a basis,
    * uses the Enemy it is shot from to determine its properties.
    *
    * @param enemy the enemy that shoots the EnemyMissile
    * @see Missile
    */
    public EnemyMissile(Enemy enemy){
        super(enemy.getXCoord(), enemy.getYCoord(), 0, 0, 3);
        super.yVelocity = -15;
    }
    
    /**
    * Class constructor that creates a EnemyMissile using the Missile class as a basis,
    * uses the Enemy it is shot from, the shooter it is shooting at as well as an offset angle to determine its properties.
    *
    * @param enemy the enemy that shoots the EnemyMissile
    * @param shooter the shooter is being shot at
    * @param angleOffset an offset angle for when a bullet shoots at a different angle to the shooter
    * @see Missile
    */
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
    /**
     * Calculates the Euclidean distance between the missile and a shooter, and checks
     * if the missile and shooter have collided.
     * 
     * @param shooter the shooter on the canvas that is being checked for if it has collided with the missile
     * @return a boolean that is true if a shooter has collided with the missile else false
     */
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
    
    /**
     * Renders the missile on the canvas.
     */
    public void render(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
    
}
