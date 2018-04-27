/**
 * A model object that extends the enemy class that has properties to create
 * an enemy that is larger, faster and that has more lives than the other types
 * of enemies
 *
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @author Daniel Banks
 * @see Enemy
 */
public class BossEnemy extends Enemy {
    /**
     * Constant values that specify the range that the boss enemy can move
     * around on in the canvas
     */
    private final int MAX_Y_COORD = 320;
    private final int MIN_Y_COORD = -150;
    private final int MAX_X_COORD = 640;

    private double velocity;

    /**
     * Class constructor that creates a boss enemy at the specified coordinates, boss enemies have
     * default properties of a velocity with a total magnitude of 6, a radius of 40, 20 lives and a 
     * shootProbability with a range of 100.
     *
     * @param xCoord the x-coordinate of the boss enemy
     * @param yCoord the y-coordinate of the boss enemy
     * @see Enemy
     */
    public BossEnemy(double xCoord, double yCoord){
        super(xCoord, yCoord, 0, 0, 40, 20, 100);
        velocity = 6;
    }

    /**
     * Generates the next random direction that the boss enemy will move in, and changes the 
     * x-velocity, and y-velocity accordingly
     */
    public void randomWalk(){
        int newAngle = (int) ( (double) Math.random() * 360) ;

        super.xVelocity = (velocity * Math.cos(newAngle * Math.PI/180));
        super.yVelocity = (velocity * Math.cos(newAngle * Math.PI/180));
    }

    /**
     * Updates the movement of the boss enemy on the canvas, if the next coordinate of the boss enemy
     * is outside the bounds of the specified boundaries of the boss enemies movement, invert the velocity
     * of the boss enemy in that direction.
     */
    public void move(){
        double newXCoord = super.xCoord + super.xVelocity;
        double newYCoord = super.yCoord + super.yVelocity;

        if( (newXCoord - super.radius) <= -MAX_X_COORD || (newXCoord + super.radius) >= MAX_X_COORD){
            super.xVelocity = -super.xVelocity;
        }
        if( (newYCoord - super.radius) <= MIN_Y_COORD || (newYCoord + super.radius) >= MAX_Y_COORD){
            super.yVelocity = -super.yVelocity;
        }

        super.xCoord = super.xCoord + super.xVelocity;
        super.yCoord = super.yCoord + super.yVelocity;
    }

    /**
     * A method that renders the boss enemy at a specific coordinate point on the canvas using the picture method
     * of StdDraw, the width and height of the sprite to be drawn is specified as the diameter of the sprite, and the 
     * if the boss enemy's lives are halfway depleted then change the colour of the sprite.
     */
    public void render(){
        if(getLives() >= 10){
            StdDraw.picture(super.xCoord,super.yCoord,"EnemyPurple.png",super.radius*2,super.radius*2);
        }
        if(getLives() < 10){
            StdDraw.picture(super.xCoord,super.yCoord,"EnemyPink.png",super.radius*2,super.radius*2);
        }
    }

}
