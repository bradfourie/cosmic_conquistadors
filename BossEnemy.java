public class BossEnemy extends Enemy {

   private final int MAX_Y_COORD = 320;
   private final int MIN_Y_COORD = -150;
   private final int MAX_X_COORD = 640;
   private double velocity;

    public BossEnemy(double xCoord, double yCoord){
        super(xCoord, yCoord, 0, 0, 40, 20, 100);
        velocity = 6;
    }

    public void randomWalk(){
       int newAngle = (int) ( (double) Math.random() * 360) ;
       System.out.println(newAngle);
      
       super.xVelocity = (velocity * Math.cos(newAngle * Math.PI/180));
       super.yVelocity = (velocity * Math.cos(newAngle * Math.PI/180));
    }
    
    public void move(){
       double newXCoord = super.xCoord + super.xVelocity;
       double newYCoord = super.yCoord + super.yVelocity;
      
       if( (newXCoord - super.radius) <= -MAX_X_COORD || (newXCoord + super.radius) >= MAX_X_COORD){
         super.xVelocity = -super.xVelocity;
       }
       if( (newYCoord - super.radius) <= MIN_Y_COORD || (newYCoord + super.radius) >= MAX_Y_COORD){
          super.yVelocity = -super.yVelocity;
       }
      
       super.xCoord = super.xCoord + super.xVelocity;
       super.yCoord = super.yCoord + super.yVelocity;
    }

    public void render(){
        if(getLives() >= 10){
          StdDraw.picture(super.xCoord,super.yCoord,"EnemyPurple.png",super.radius*2,super.radius*2);
        }
        if(getLives() < 10){
          StdDraw.picture(super.xCoord,super.yCoord,"EnemyPink.png",super.radius*2,super.radius*2);
        }
    }

}
