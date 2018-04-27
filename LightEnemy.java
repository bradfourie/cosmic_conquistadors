
/**
 * A model object that extends the enemy class that has properties to create
 * an enemy that is smaller and and has less lives than a heavy enemy.
 *
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @author Daniel Banks
 * @see Enemy
 */

public class LightEnemy extends Enemy {

    /**
     * Class constructor that creates a light enemy at the specified coordinates, light enemies have
     * default properties of x-velocities with a magnitude of 5, y-velocities with the magnitude of 50 downwards,
     * 2 lives and a shootProbability with a range of 1800.
     *
     * @param xCoord the x-coordinate of the light enemy
     * @param yCoord the y-coordinate of the light enemy
     * @see Enemy
     */
    public LightEnemy(double xCoord, double yCoord){
        super(xCoord, yCoord, 5, -50, 15 , 2, 1800);
    }

    /**
     * A method that renders the light enemy at a specific coordinate point on the canvas using the picture method
     * of StdDraw, the width and height of the sprite to be drawn is specified as the diameter of the sprite.
     */
    public void render(){
        StdDraw.picture(super.xCoord,super.yCoord,"EnemyRed.png",super.radius*2,super.radius*2);
    }

}

