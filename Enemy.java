/**
 *  This class extends DefaultCritter and serves as a model object to create
 *  different types of enemies
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see DefaultCritter
 */

public class Enemy extends DefaultCritter {
    private int lives;
    private int shootProbability;

    /**
     * Class constructor that creates an enemy using the DefaultCritter class as a basis, also adds properties
     * to Enemy by specifying the amount of lives and the probability that the enemy will shoot a missile.
     *
     * @param xCoord the x-coordinate of the enemy
     * @param yCoord the y-coordinate of the enemy
     * @param xVelocity the x-velocity of the enemy
     * @param yVelocity the y-velocity of the enemy
     * @param radius the radius or "size" of the enemy
     * @param lives the amount of lives the enemy has before it gets destroyed or removed from the game
     * @param shootProbability the probability that the enemy will shoot a missile
     * @see DefaultCritter
     */
    public Enemy(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius,int lives, int shootProbability){
        super(xCoord, yCoord, xVelocity, yVelocity, radius);
        this.lives = lives;
        this.shootProbability = shootProbability;
    }

    /**
     * Calculates the Euclidean distance between a missile and the enemy, and checks
     * if the missile and enemy have collided.
     * 
     * @param missile the missile on the canvas that is being checked for if it has collided with the enemy
     * @return a boolean that is true if the enemy has collided with the missile else false
     */
    public boolean onMissileCollision(Missile missile){
        double radiusMissile = missile.getRadius();
        double distance = Math.sqrt( Math.pow(missile.getXCoord() - super.xCoord, 2) + Math.pow(missile.getYCoord() - super.yCoord, 2));

        if(distance <= (radiusMissile + super.radius)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Calculates the Euclidean distance between the enemy and a shooter and checks if the
     * shooter and missile have collided.
     * 
     * @param shooter the shooter on the canvas that is being used to check for a collision
     * @return a boolean that is true if the enemy has collided with a shooter else false
     */
    public boolean isShooterCollision(Shooter shooter){
        double radiusShooter = shooter.getRadius();
        double distance = Math.sqrt( Math.pow(shooter.getXCoord() - super.xCoord, 2) + Math.pow(shooter.getYCoord() - super.yCoord, 2));

        if(distance <= (radiusShooter + super.radius)){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Removes a certain amount of lives from the enemy
     * 
     * @param lives the amount of lives to be removed from the enemy
     */
    public void removeLives(int lives){
        this.lives = this.lives - lives;
    }

    /**
     * Calculates whether or not the enemy is firing a missile, a trigger value is generated
     * in the range from 1 to shootProbability, and then if this trigger value is equal to a 
     * specific value, in this case 1 then the enemy fires a missile
     * 
     * @return a boolean value corresponding to whether or not the enemy is firing a missile
     */
    public boolean isShoot(){
        boolean isShoot = false;
        int trigger = (int) (Math.random() * shootProbability) + 1;

        if(trigger == 1){
            isShoot = true;
        }

        return isShoot;
    }
    
    /**
     * Moves the enemy in the y-direction, if the enemy touches the edge of the canvas then the 
     * velocity of the enemy has to be inverted and the enemy's position in the x-direction has 
     * to be updated as well.
     */
    public void moveY(){
        super.yCoord = super.yCoord + super.yVelocity;
        invertVelocity();
        moveX();
    }
    
    public void moveX(){
        super.xCoord = super.xCoord + super.xVelocity;
    }

    private void invertVelocity(){
        super.xVelocity = -super.xVelocity;
    }

    public double getXCoord(){
        return super.xCoord;
    }
    
    public int getLives(){
        return lives;
    }

}
