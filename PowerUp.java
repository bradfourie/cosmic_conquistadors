/**
 * A model object for a powerup with properties for coordinates, radius, velocity and the type of powerup
 */

public class PowerUp {
  /**
   * Constant values that correspond to the type of powerup
   */
  private final int NORMAL_MISSILE = -1, GATLING_MISSILE = 0, TRI_MISSILE = 1, SUPER_MISSILE = 2, FAST_MISSILE = 3, EXTRA_LIFE = 4;

  private double xCoord;
  private double yCoord;
  private double radius;
  private double yVelocity;
  private int power;

  /**
   * Class constructor that creates a powerup at the specified coordinates, powerups have default 
   * y velocities with a magnitude of 8, the constructor generates a random number and assigns a type 
   * to the powerup using the method choosePowerUp.
   *
   * @param xCoord the starting x-coordinate of the powerup
   * @param yCoord the starting y-coordinate of the powerup
   * @param radius the radius of the powerup
   */
  public PowerUp(int xCoord, int yCoord, double radius){
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.radius = radius;
    this.yVelocity = 8;
    double selection = Math.random();
    choosePowerUp(selection);
  }
  
  /**
   * A method that recieves a variable selection as its input and assigns a type to
   * the powerup according to the interval in which selection lies.
   * 
   * @param selection the randomly generated number which is used to assign a type
   *                  to the powerup
   */
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
    return yCoord;
  }
  public double getXCoord(){
    return xCoord;
  }
  public int getPower(){
    return power;
  }
  public double getRadius(){
    return radius;
  }

  /**
   * Moves the powerup downwards on the canvas according to its yVelocity, and sets it colour 
   * according to the type of the powerup.
   */
  public void move(){
    yCoord = yCoord - yVelocity;
    switch(this.power) {
      case GATLING_MISSILE:
        StdDraw.setPenColor(StdDraw.BLUE);
        break;
      case TRI_MISSILE:
        StdDraw.setPenColor(StdDraw.CYAN);
        break;
      case SUPER_MISSILE:
        StdDraw.setPenColor(StdDraw.YELLOW);
        break;
      case FAST_MISSILE:
        StdDraw.setPenColor(StdDraw.MAGENTA);
        break;
      case EXTRA_LIFE:
        StdDraw.setPenColor(StdDraw.GREEN);
        break;
    }
    render();
  }
  
  public void render(){
    StdDraw.filledCircle(xCoord, yCoord, radius);
  }
}
