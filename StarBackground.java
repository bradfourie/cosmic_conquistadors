/**
 * A class that moves and renders the stars in the background of the main Game.
 * 
 * @author Daniel Banks
 * @see InvaderGameState
 */
public class StarBackground extends DefaultCritter{
  
   /**
    * Creates a new object of the StarBackground class with a specific 
    * starting x,y position as well as a radius for the star.
    * 
    * @param x is the starting x-coordinate of the star
    * @param y is the starting y-coordinate of the star
    * @param radius the radius of the star
    */
   public StarBackground(int x, int y, double radius){
       super(x, y, 0, -15, radius);
   }

   /**
    * Calculates the y position of the top most position of the star.
    * 
    * @return the top most y-coordinate of the star
    */
   public double getTailYCoord(){
       return (super.yCoord - radius);
   }


   public void move(){
       super.yCoord = super.yCoord + yVelocity;
       render();
   }
   
   public void render(){
       StdDraw.setPenColor(StdDraw.WHITE);
       StdDraw.filledSquare(xCoord , yCoord, radius);
   }

}