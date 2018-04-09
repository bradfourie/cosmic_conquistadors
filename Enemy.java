package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class Enemy extends DefaultCritter {

    public Enemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 3); // final param radius
    }

    public boolean onMissileCollision(Missile missile){
        double radiusMissile = missile.getRadius();
        double distance = Math.sqrt( Math.pow(missile.getXCoord() - super.xCoord, 2) + Math.pow(missile.getYCoord() - super.yCoord, 2));

        if(distance <= (radiusMissile + super.radius)){
            return true;
        }else{
            return false;
        }

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


    public boolean isShoot(){
        boolean isShoot = false;
        int trigger = (int) (Math.random() * 1000) + 1;

        if(trigger == 1){
            isShoot = true;
        }
        return isShoot;
    }

    public void move(){
        double xVelocityTemp = super.xVelocity; // using this so i can set the xVelocity of the enemy to 0 just for one time step
        boolean isBounce = false;

        if((super.xCoord + super.xVelocity - super.radius) <= -100 || (super.xCoord  + super.xVelocity + super.radius) >= 100){
            isBounce = true;
            super.xVelocity = 0;
            super.yVelocity = -(super.radius*2);
        }

        super.xCoord = super.xCoord + super.xVelocity;
        super.yCoord = super.yCoord + super.yVelocity;

        if(isBounce){
            super.xVelocity = -xVelocityTemp; //setting xVelocity to opposite of what it was before
            super.yVelocity = 0;
            super.xCoord = super.xCoord + super.xVelocity;
            super.yCoord = super.yCoord + super.yVelocity;
        }

        render();
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
}