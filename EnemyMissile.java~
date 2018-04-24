package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class EnemyMissile extends Missile {

    public EnemyMissile(Enemy enemy){
        super(enemy.getXCoord(), enemy.getYCoord(), 0, -15, 3);
        super.yVelocity = -15;
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
    

    public void render(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
}
