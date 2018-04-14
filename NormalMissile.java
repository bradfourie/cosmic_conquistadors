package com.company;

import edu.princeton.cs.introcs.StdDraw;

public class NormalMissile extends Missile{

    public NormalMissile(Shooter shooter){
        super(shooter.getXCoord(), shooter.getYCoord(), 15 * Math.cos(shooter.getAngleBarrel()), 15 * Math.sin(shooter.getAngleBarrel()), 3);
    }

    public void render(){
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(super.xCoord,super.yCoord,super.radius);
    }
}
