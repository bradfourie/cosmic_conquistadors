package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class Missile extends DefaultCritter{
    private int numBounced; //counts the number of bounces a missile has made
    private boolean enemyMissile;

    public Missile(Shooter shooter){
        //here the magical constant 5 is the magnitude of the velocity
        //and the magical constant 1 is the radius of the missile
        super(shooter.getXCoordBarrel(), shooter.getYCoordBarrel(), 15 * Math.cos(shooter.getAngleBarrel()), 15 * Math.sin(shooter.getAngleBarrel()), 3);
        this.numBounced = 0;
        this.enemyMissile = false;
    }

    public Missile(Enemy enemy){
        //here the magical constant 5 is the magnitude of the velocity
        //and the magical constant 1.5 is the radius of the missile
        super(enemy.getXCoord(), enemy.getYCoord(), 0, -15, 3);
        this.numBounced = 0;
        this.enemyMissile = true;
    }

    public boolean isShooterCollision(Shooter shooter){
        double radiusShooter = shooter.getRadius();
        double distanceToShooter = Math.sqrt( Math.pow(shooter.getXCoord() - super.xCoord, 2) + Math.pow(shooter.getYCoord() - super.yCoord, 2));
        double distanceToBarrel = Math.sqrt( Math.pow(shooter.getXCoordBarrel() - super.xCoord, 2) + Math.pow(shooter.getYCoordBarrel() - super.yCoord, 2));

        if( (distanceToShooter <= (radiusShooter + super.radius))  ||  (distanceToBarrel <= (radiusShooter + super.radius))){
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
        if(enemyMissile) {
            StdDraw.setPenColor(StdDraw.PINK);
        }else{
            StdDraw.setPenColor(StdDraw.BLUE);
        }
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }

    /*public class EnemyMissile extends Missile{

        public EnemyMissile(Enemy enemy) {
            super(enemy);
        }
    }*/

}
