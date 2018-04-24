package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class PowerUp {

  //instead of power 1 and 2 rather do this
  /*private final int GATLING_MISSILE = 0;
  private final int TRI_MISSILE = 1;*/
  private double x;
  private double y;
  private double length;
  private double velocity;
  private int power;
  
  public PowerUp(int x, int y, double length){
    this.x = x;
    this.y = y;
    this.length = length;
    this.velocity = 8;
    if(Math.random() < 0.3){
      this.power = 1;
    }else{
      this.power = 2;
    }
  }
  
  public double getYCoord(){
    return y;
  }
  public double getXCoord(){
    return x; 
  }
  public int getPower(){
    return power; 
  }
  
  public double getRadius(){
    return length;
  }
  
  public void move(){
    y = y - velocity;
    if(power == 1){
      StdDraw.setPenColor(StdDraw.GREEN);
    }else{
      StdDraw.setPenColor(StdDraw.PINK);
    }
    StdDraw.filledCircle(x , y , length);
    
  }
  
}
