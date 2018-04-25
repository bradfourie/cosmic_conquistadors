public class LightEnemy extends Enemy {
    public LightEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity,15 , 2);
        super.setShootProbability(1800);
    }

    public void render(){

        StdDraw.picture(super.xCoord,super.yCoord,"EnemyRed.png",super.radius*2,super.radius*2);

        
    }

}


