/**
 * A model object that extends the enemy class that has properties to create
 * an enemy that is larger, and that has more lives than a normal enemy.
 *
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @author Daniel Banks
 * @see Enemy
 */

public class HeavyEnemy extends Enemy {

    /**
     * Class constructor that creates a heavy enemy at the specified coordinates, heavy enemies have 
     * default properties of x-velocities with a magnitude of 5, y-velocities with the magnitude of 50 downwards,
     * 5 lives and a shootProbability with a range of 1500.
     * 
     * @param xCoord the x-coordinate of the heavy enemy
     * @param yCoord the y-coordinate of the heavy enemy
     * @see Enemy
     */
    public HeavyEnemy(double xCoord, double yCoord){
        super(xCoord, yCoord, 5, -50, 20 , 5, 1500);
    }

    public void render(){
        StdDraw.picture(super.xCoord,super.yCoord,"EnemyYellow.png",super.radius*2,super.radius*2);
    }

}

