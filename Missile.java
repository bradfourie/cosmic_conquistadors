package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class Missile extends DefaultCritter{
    private int numBounced; //counts the number of bounces a missile has made
    private boolean enemyMissile;

    public Missile(Shooter shooter){
        //here the magical constant 5 is the magnitude of the velocity
        //and the magical constant 1 is the radius of the missile
        super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 5 * Math.cos(shooter.getAngleBarrel()), 5 * Math.sin(shooter.getAngleBarrel()), 1);
        this.numBounced = 0;
        this.enemyMissile = false;
    }

    public Missile(Enemy enemy){
        //here the magical constant 5 is the magnitude of the velocity
        //and the magical constant 1 is the radius of the missile
        super(enemy.getXCoord(), enemy.getYCoord(), 0, -5, 1);
        this.numBounced = 0;
        this.enemyMissile = true;
    }

    public boolean isShooterCollision(Shooter shooter){
        double radiusShooter = shooter.getRadius();
        double distance = Math.sqrt( Math.pow(shooter.getXCoord() - super.xCoord, 2) + Math.pow(shooter.getYCoord() - super.yCoord, 2));

        if(distance <= (radiusShooter + super.radius)){
            return true;
        }else{
            return false;
        }
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

    public boolean isEnemyMissile(){
        return enemyMissile;
    }

    public void move(){
        super.xCoord = super.xCoord + super.xVelocity;
        super.yCoord = super.yCoord + super.yVelocity;

        render();
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }

}
