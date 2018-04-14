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
    public void setXCoord(double xCoord){
        this.xCoord = xCoord;
    }
    public void setYCoord(double yCoord){
        this.yCoord = yCoord;
    }
    public void setXVelocity(double xVelocity){
        this.xVelocity = xVelocity;
    }
    public void setYVelocity(double yVelocity){
        this.yVelocity = yVelocity;
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