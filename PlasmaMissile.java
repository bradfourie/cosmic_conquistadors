public class PlasmaMissile extends Missile{

    public PlasmaMissile(Shooter shooter){
        super(shooter.getXCoord(), shooter.getYCoord(), 12 * Math.cos(shooter.getAngleBarrel()), 12 * Math.sin(shooter.getAngleBarrel()), 5);
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
}
