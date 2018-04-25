public class Bunker extends Enemy {
    public Bunker(double xCoord, double yCoord) {
        super(xCoord, yCoord, 0, 0, 40, 10);
    }

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
