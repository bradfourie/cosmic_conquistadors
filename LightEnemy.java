public class LightEnemy extends Enemy {

    public LightEnemy(double xCoord, double yCoord){
        super(xCoord, yCoord, 5, -50, 15 , 2, 1800);
    }

    public void render(){
        StdDraw.picture(super.xCoord,super.yCoord,"EnemyRed.png",super.radius*2,super.radius*2);
    }
    
}


