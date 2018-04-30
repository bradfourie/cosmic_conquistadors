/**
 * A class that extends DefaultCritter and serves as a model object
 * for a powerup with different effects on the shooter depending on its type.
 * 
 * @see DefaultCritter
 * @see Shooter
 */

public class PowerUp extends DefaultCritter{
  /**
   * Constant values that correspond to the type of power up.
   */
  private final int NORMAL_MISSILE = -1, GATLING_MISSILE = 0, TRI_MISSILE = 1, SUPER_MISSILE = 2, FAST_MISSILE = 3, EXTRA_LIFE = 4;

  private int power;

  /**
   * Creates a powerup at the specified coordinates, power ups have default
   * y velocities with a magnitude of 8, the constructor generates a random number and assigns a type
   * to the powerup using the method choosePowerUp.
   *
   * @param xCoord the starting x-coordinate of the powerup
   * @param yCoord the starting y-coordinate of the powerup
   * @param radius the radius of the powerup
   */
  public PowerUp(int xCoord, int yCoord, double radius){
    super(xCoord,yCoord, 0, -8, radius);
    double selection = Math.random();
    choosePowerUp(selection);
  }

  /**
   * Receives a variable selection as its input and assigns a type to
   * the power up according to the interval in which selection lies.
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

  public int getPower(){
    return power;
  }

  /**
   * Moves the powerup downwards on the canvas according to its yVelocity, and sets it colour
   * according to the type of the powerup.
   */
  public void move(){
    yCoord = yCoord + yVelocity;
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
