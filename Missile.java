public class Missile extends DefaultCritter{
    private int numBounced; //counts the number of bounces a missile has made

    public Missile(Shooter shooter){
        //here the magical constant 5 is the magnitude of the velocity
        //and the magical constant 1 is the radius of the missile
        super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 5 * Math.cos(shooter.getAngleBarrel()), 5 * Math.sin(shooter.getAngleBarrel()), 1);
        this.numBounced = 0;
    }

    public int getNumBounced(){
        return numBounced;
    }

    public void wallBounce(){
        numBounced++;
        super.xVelocity = - super.xVelocity;
    }

    public double getXCoord(){
        return super.xCoord;
    }

    public double getYCoord(){
        return super.yCoord;
    }

    public double getRadius(){
        return super.radius;
    }

    public void move(){
        super.xCoord = super.xCoord + super.xVelocity;
        super.yCoord = super.yCoord + super.yVelocity;

        render();
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }

}

