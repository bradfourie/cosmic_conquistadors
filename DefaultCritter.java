public class DefaultCritter{

    protected double xCoord;
    protected double yCoord;
    protected double xVelocity;
    protected double yVelocity;
    protected double radius;

    public DefaultCritter(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.radius = radius;
    }

    //setters
    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }
    //getters
    public double getYCoord(){
        return yCoord;
    }
    public double getXCoord(){
        return xCoord;
    }
    public double getRadius(){
        return radius;
    }
}
