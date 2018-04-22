public class Bunker extends Enemy {
    public Bunker(double xCoord, double yCoord) {
        super(xCoord, yCoord, 0, 0, 40, 10);
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }

}
