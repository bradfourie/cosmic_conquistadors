package com.company;

import edu.princeton.cs.introcs.StdDraw;

public class BossEnemy extends Enemy {
    public BossEnemy(double xCoord, double yCoord, double xVelocity, double yVelocity){
        super(xCoord, yCoord, xVelocity, yVelocity, 40, 20);
    }

    public void render(){
        //just for now
            StdDraw.setPenColor(StdDraw.RED);
        if(getLives() == 2){
            StdDraw.setPenColor(StdDraw.RED);
        }
        if(getLives() == 1){
            StdDraw.setPenColor(StdDraw.WHITE);
        }
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
}
