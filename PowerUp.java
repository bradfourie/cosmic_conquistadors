
public class PowerUp {

  private final int GATLING_MISSILE =0, TRI_MISSILE = 1, SUPER_MISSILE = 2, FAST_MISSILE = 3, EXTRA_LIFE = 4;
  
  private double x;
  private double y;
  private double length;
  private double velocity;
  private int power;
  
  public PowerUp(int x, int y, double length){
    this.x = x;
    this.y = y;
    this.length = length;
    this.velocity = 8;
    double selection = Math.random();
    choosePowerUp(selection);
  }
  private void choosePowerUp(double selection){
    if(selection < 0.05){
      this.power = GATLING_MISSILE; //5%
    }
    if(selection >= 0.05 && selection < 0.35){
      this.power = TRI_MISSILE; // 30%
    }
    if(selection >= 0.35 && selection < 0.65){
      this.power = SUPER_MISSILE; //30%
    }
    if(selection >= 0.65 && selection < 0.98){
      this.power = FAST_MISSILE; // 33%
    }
    if(selection >= 0.98 && selection < 1){
      this.power = EXTRA_LIFE; // 2%
    }
  }
  
  public double getYCoord(){
    return y;
  }
  public double getXCoord(){
    return x; 
  }
  public int getPower(){
    return power; 
  }
  
  public double getRadius(){
    return length;
  }
  
  public void move(){
    y = y - velocity;
    switch(this.power){
      case GATLING_MISSILE: 
            StdDraw.setPenColor(StdDraw.GREEN);
            break;
      case TRI_MISSILE: 
            StdDraw.setPenColor(StdDraw.PINK);
            break;
      case SUPER_MISSILE: 
            StdDraw.setPenColor(StdDraw.YELLOW);
            break;
      case FAST_MISSILE: 
            StdDraw.setPenColor(StdDraw.ORANGE);
            break;
      case EXTRA_LIFE: 
            StdDraw.setPenColor(StdDraw.BLUE);
            break;
    }
    StdDraw.filledCircle(x , y , length);
  }
}
