package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class Shooter extends DefaultCritter{

    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double radiusBarrel;
    private double angleBarrel;

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 5);
        angleBarrel = Math.PI/2;
        radiusBarrel = super.radius/2;
        xCoordBarrel = super.radius*Math.cos(angleBarrel);
        yCoordBarrel = super.radius*Math.sin(angleBarrel);
    }

    /* Setters */
    public void setRadialVelocityBarrel(double radialVelocityBarrel){
        this.radialVelocityBarrel = radialVelocityBarrel;
    }
    public void setRadiusBarrel(double radiusBarrel){
        this.radiusBarrel = radiusBarrel;
    }
    public void setAngleBarrel(double angleBarrel){
        this.angleBarrel = angleBarrel;
    }
    public void setXCoordBarrel(double xCoordBarrel){
        this.xCoordBarrel = xCoordBarrel;
    }
    public void setYCoordBarrel(){
        this.yCoordBarrel = yCoordBarrel;
    }

    /* Getters */
    public double getRadialVelocityBarrel(){
        return radialVelocityBarrel;
    }
    public double getRadiusBarrel(){
        return radiusBarrel;
    }
    public double getAngleBarrel(){
        return angleBarrel;
    }
    public double getXCoordBarrel(){
        return xCoordBarrel;
    }
    public double getYCoordBarrel(){
        return yCoordBarrel;
    }

    /* All other methods that add functionality */
    public void move(){
        /*  set the position of the shooter and draws it  */
        // if the shooter touches edge invert velocity
        if(Math.abs(super.xCoord + super.xVelocity) + super.radius > 100.0)       super.xVelocity = -super.xVelocity;
        // prevents shooting backwards
        if(angleBarrel <= -0.2 || angleBarrel >= (Math.PI + 0.2))           radialVelocityBarrel = -radialVelocityBarrel;

        //  The shooter movement
        super.xCoord= super.xCoord + super.xVelocity;
        // The barrels rotational movement
        angleBarrel = angleBarrel + radialVelocityBarrel;
        xCoordBarrel = super.radius*Math.cos(angleBarrel) + xCoord;
        yCoordBarrel = super.radius*Math.sin(angleBarrel) + yCoord;

        render();
    }

    public void render()
    {
        // Redrawing the Barrel
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(xCoordBarrel,yCoordBarrel,radiusBarrel);
        //  Redrawing the Shooter
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
    }
}