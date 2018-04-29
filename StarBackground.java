/**
 * A class that moves and renders the stars in the background of the main Game.
 * 
 * @author Daniel Banks
 * @see InvaderGameState
 */
public class StarBackground {
  
  /**
   * Constant value corresponding to the velocity of the stars moving in the background
   */
   private final double velocity = 15;
  
   public double x;
   public double y;
   public double radius;

   /**
    * Class constructor that creates a new object of the StarBackground class with a specific 
    * starting x,y position as well as a radius for the star.
    * 
    * @param x is the starting x-coordinate of the star
    * @param y is the starting y-coordinate of the star
    * @param radius the radius of the star
    */
   public StarBackground(int x, int y, double radius){
       this.x = x;
       this.y = y;
       this.radius = radius;
   }

   /**
    * Calculates the y position of the top most position of the star.
    * 
    * @return the top most y-coordinate of the star
    */
   public double getTailYCoord(){
       return (y- radius);
   }

   /**
    * Calculates the new y-coordinate of the star and renders it.
    */
   public void move(){
       y = y - velocity;
       StdDraw.setPenColor(StdDraw.WHITE);
       StdDraw.filledSquare(x , y , radius);
   }

}