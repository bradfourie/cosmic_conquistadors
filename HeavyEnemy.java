public class HeavyEnemy extends Enemy {
    public HeavyEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity,20 , 5);
        super.setShootProbability(1500);
    }

    public void render(){
        StdDraw.picture(super.xCoord,super.yCoord,"EnemyYellow.png",super.radius*2,super.radius*2);
    }
}

