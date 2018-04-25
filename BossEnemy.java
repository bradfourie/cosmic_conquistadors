public class BossEnemy extends Enemy {
  private double velocity;
    public BossEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 40, 20);
        super.setShootProbability(100);
        velocity = 2;
    }

    public void randomWalk(){
      int newAngle = (int) ( (double) Math.random() * 360 ) ;
      System.out.println(newAngle);
      
      super.xVelocity = (velocity * Math.cos(newAngle * Math.PI/180));
      super.yVelocity = (velocity * Math.cos(newAngle * Math.PI/180));
    }
    
    public void move(){
      double newXCoord = super.xCoord + super.xVelocity;
      double newYCoord = super.yCoord + super.yVelocity;
      
      if( (newXCoord - super.radius) <= -640 || (newXCoord + super.radius) >= 640){
        super.xVelocity = -super.xVelocity;
      }
      if( (newYCoord - super.radius) <= -150 || (newYCoord + super.radius) >= 320){
         super.yVelocity = -super.yVelocity;
      }
      
      super.xCoord = super.xCoord + super.xVelocity;
      super.yCoord = super.yCoord + super.yVelocity;
    }
    public void render(){
        //just for now
        if(getLives() >= 10){
          StdDraw.picture(super.xCoord,super.yCoord,"EnemyPurple.png",super.radius*2,super.radius*2);
        }
        if(getLives() < 10){
          StdDraw.picture(super.xCoord,super.yCoord,"EnemyPink.png",super.radius*2,super.radius*2);
        }
    }
}
