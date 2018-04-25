public class StarBackground {

    /* ADD YOUR CODE HERE */
    public double x;
    public double y;
    public double length;
    public double velocity = 15;

    public StarBackground(int x, int y, double length){
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public double getTailYCoord(){
        return (y- length);
    }

    public void move(){
        y = y - velocity;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledSquare(x , y , length);
    }

}