package com.company;

import edu.princeton.cs.introcs.StdDraw;

public class Shooter extends DefaultCritter{

    private double INITIAL_ANGLE_BARREL = Math.PI/2.0;
    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double radiusBarrel;
    private double angleBarrel;
    private int lives;

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity, int lives){
        super(xCoord, yCoord, xVelocity, yVelocity, 10);
        angleBarrel = Math.PI/2;
        radiusBarrel = super.radius/2.0;
        xCoordBarrel = super.radius*Math.cos(radiusBarrel);
        yCoordBarrel = super.radius*Math.sin(radiusBarrel);
        this.lives = lives;
    }

    /* Setters */
    public void setRadialVelocityBarrel(double radialVelocityBarrel){
        this.radialVelocityBarrel = radialVelocityBarrel;
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
    public int getLives(){
        return lives;
    }

    public void removeLife(){
        lives--;
    }

    /* All other methods that add functionality */
    public void move(){
        /*  set the position of the shooter and draws it  */
        // if the shooter touches edge invert velocity
        if(Math.abs(super.xCoord + super.xVelocity) + super.radius > 640)       super.xVelocity = -super.xVelocity;
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
        if(getLives() == 3){
            StdDraw.setPenColor(StdDraw.BLUE);
        }
        if(getLives() == 2){
            StdDraw.setPenColor(StdDraw.ORANGE);
        }
        if(getLives() == 1){
            StdDraw.setPenColor(StdDraw.YELLOW);
        }
        // Redrawing the Barrel
        StdDraw.filledCircle(xCoordBarrel,yCoordBarrel,radiusBarrel);
        //  Redrawing the Shooter
        StdDraw.filledCircle(super.xCoord,super.yCoord,radius);
    }

    public void resetState(int startXCoord, int startYCoord) {
        xCoord = startXCoord;
        yCoord = startYCoord;
        radialVelocityBarrel = 0;
        xCoordBarrel = super.radius*Math.cos(INITIAL_ANGLE_BARREL);
        yCoordBarrel = super.radius*Math.sin(INITIAL_ANGLE_BARREL);
    }
}