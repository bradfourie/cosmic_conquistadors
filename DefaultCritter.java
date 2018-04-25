public class DefaultCritter implements Critter {

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
    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }
    public void setYVelocity(double yVelocity){
        this.yVelocity = yVelocity;
    }
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