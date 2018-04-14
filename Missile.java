public class Missile extends DefaultCritter{
    private int numBounced; //counts the number of bounces a missile has made
    protected double angle; //the angle that determines the direction the bullet is facing

    public Missile(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius){
        //here the magical constant 5 is the magnitude of the velocity
        //and the magical constant 1.5 is the radius of the missile
        super(xCoord, yCoord, xVelocity, xVelocity, radius);
        this.numBounced = 0;
    }

    public int getNumBounced(){
        return numBounced;
    }

    public void wallBounce(){
        numBounced++;
        super.xVelocity = - super.xVelocity;
        angle=-angle;
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
    }

}
