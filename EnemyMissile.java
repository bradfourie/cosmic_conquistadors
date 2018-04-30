
/**
 * This class extends Missile to add extra functionality for a type
 * of missile that can be used as an enemy missile for the enemies,.
 *
 * @author Bradley Fourie
 * @author Daniel Banks
 * @author Heinrich Benz
 * @see Missile
 * @see Enemy
 */
public class EnemyMissile extends Missile {
  
  /**
    * Creates a EnemyMissile using the Missile class as a basis,
    * uses the Enemy it is shot from to determine its starting x 
    * and y coordinates. Enemy missiles have a default radius of
    * 3 and a y velocity of -15.
    *
    * @param enemy the enemy that shoots the EnemyMissile
    * @see Enemy
    */
    public EnemyMissile(Enemy enemy){
        super(enemy.getXCoord(), enemy.getYCoord(), 0, 0, 3);
        super.yVelocity = -15;
    }
    
    /**
    * Creates a EnemyMissile using the Missile class as a basis, this 
    * constructor is used for the BossEnemy, it allows the BossEnemy to 
    * fire three missiles at once in the direction of a shooter, each missile having
    * a specific angle that is offset from the middle missile. The constructor
    * needs to have case checking for the coordinates of the entities as the
    * sign of cosine and sine flips the velocity depending on the angle between the
    * shooter and the BossEnemy due to the nature of these mathematical operators.
    * 
    *
    * @param enemy the enemy that shoots the EnemyMissile
    * @param shooter the shooter is being shot at
    * @param angleOffset an offset angle for when a bullet shoots at a different angle to the shooter
    * @see BossEnemy
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
     * if the missile and shooter have collided and then returns true if a collision occurred.
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

    public void render(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
    
}
