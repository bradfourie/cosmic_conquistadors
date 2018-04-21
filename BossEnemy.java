public class BossEnemy extends Enemy {
    public BossEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 40, 20);
    }

    public void render(){
        //just for now
        if(getLives() >= 2){
          StdDraw.picture(super.xCoord,super.yCoord,"EnemyPurple.png",super.radius*2,super.radius*2);
        }
        if(getLives() == 1){
          StdDraw.picture(super.xCoord,super.yCoord,"EnemyPink.png",super.radius*2,super.radius*2);
        }
    }
}
