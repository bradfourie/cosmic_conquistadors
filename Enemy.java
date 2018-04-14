package com.company;

import edu.princeton.cs.introcs.StdDraw;
public class Enemy extends DefaultCritter {
    private int lives;
    private int shootProbability;

    public Enemy(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius,int lives){
        super(xCoord, yCoord, xVelocity, yVelocity, radius); // final param radius
        this.lives = lives;
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

    public void removeLife(){
        lives--;
    }
    public int getLives(){
        return lives;
    }
    public void setShootProbability(int shootProbability){
        this.shootProbability = shootProbability;
    }


    public boolean isShoot(){
        shootProbability = 1500;
        boolean isShoot = false;
        int trigger = (int) (Math.random() * shootProbability) + 1;

        if(trigger == 1){
            isShoot = true;
        }

        return isShoot;
    }

   public void moveDown(){
    super.yCoord = super.yCoord - super.radius*2; 
    invertVelocity();
    move();
    render();
  }
  
  private void invertVelocity(){
    super.xVelocity = -super.xVelocity;  
  }
  
  public double getXCoord(){
    return super.xCoord;  
  }
    
    public void move(){
        super.xCoord = super.xCoord + super.xVelocity;
        render();
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
