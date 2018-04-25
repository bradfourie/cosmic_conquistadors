public class HeavyEnemy extends Enemy {

    public HeavyEnemy(double xCoord, double yCoord){
      super(xCoord, yCoord, 5, -50, 20 , 5, 1500);
    }

    public void render(){
      StdDraw.picture(super.xCoord,super.yCoord,"EnemyYellow.png",super.radius*2,super.radius*2);
    }

}

