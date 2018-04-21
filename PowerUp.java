/**
 * Auto Generated Java Class.
 */
public class PowerUp {
  
  /* ADD YOUR CODE HERE */
  public double x;
  public double y;
  public double length;
  public double velocity = 8;
  public int power;
  
  public PowerUp(int x, int y, double length){
    this.x = x;
    this.y = y;
    this.length = length;
    if(Math.random() < 0.3){
      power = 1;
    }else{
      power = 2;
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
    if(power == 1){
      StdDraw.setPenColor(StdDraw.GREEN);
    }else{
      StdDraw.setPenColor(StdDraw.PINK);
    }
    StdDraw.filledCircle(x , y , length);
    
  }
  
}
