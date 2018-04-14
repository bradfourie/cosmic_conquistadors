package com.company;

import edu.princeton.cs.introcs.StdDraw;

public class LightEnemy extends Enemy {
    public LightEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity,10 , 2);
    }

    public void render(){
        if(getLives() == 2){
            StdDraw.setPenColor(StdDraw.RED);
        }
        if(getLives() == 1){
            StdDraw.setPenColor(StdDraw.WHITE);
        }
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }

}
