public class LightEnemy extends Enemy {
    public LightEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity,10 , 2);
    }

    public void render(){
        if(getLives() == 2){
            StdDraw.picture(super.xCoord,super.yCoord,"EnemyRed.png",super.radius*2,super.radius*2);
        }
        if(getLives() == 1){
            StdDraw.picture(super.xCoord,super.yCoord,"EnemyYellow.png",super.radius*2,super.radius*2);
        }
        
    }

}
