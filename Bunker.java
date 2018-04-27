
/**
 * Extends enemy and is a model object for a bunker that is to the enemies advantage
 * bunkers destroy powerups, have lives and enemy missiles can pass through them.
 * 
 * @author Bradley Fourie
 * @author Heinrich Benz
 * @author Daniel Banks
 * @see Enemy
 */
public class Bunker extends Enemy {

    /**
     * Class constructor that creates a bunker at the specified coordinates, bunkers have
     * default properties a radius of 40, no velocities, 10 lives and no probability of shooting
     *
     * @param xCoord the x-coordinate of the light enemy
     * @param yCoord the y-coordinate of the light enemy
     * @see Enemy
     */
    public Bunker(double xCoord, double yCoord) {
        super(xCoord, yCoord, 0, 0, 40, 10, 0);
    }

    /**
     * Checks if the bunker has collided with an enemy on the canvas by checking the euclidean 
     * distance between the two entities. 
     * 
     * @param enemy the enemy with which the collision is being checked for
     * @return a boolean corresponding to whether or not the two entities have
     * collided.
     */
    public boolean onEnemyCollision(Enemy enemy){
        double radiusEnemy = enemy.getRadius();
        double distance = Math.sqrt( Math.pow(enemy.getXCoord() - super.xCoord, 2) + Math.pow(enemy.getYCoord() - super.yCoord, 2));

        if(distance <= (radiusEnemy + super.radius)){
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


