public class Shooter extends DefaultCritter{

    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double angleBarrel;
    private int lives;
    private int powerUp;
    private int powerUpCounter;
    private boolean isPlayerOne;

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity, int lives, boolean isPlayerOne){
        super(xCoord, yCoord, xVelocity, yVelocity, 10);
        this.angleBarrel = 90;
        this.xCoordBarrel = super.xCoord;
        this.yCoordBarrel = super.getRadius() + super.yCoord;
        this.lives = lives;
        this.powerUp = -1;
        this.powerUpCounter = 0;
        this.isPlayerOne = isPlayerOne;
    }

    /* Setters */
    public void setRadialVelocityBarrel(double radialVelocityBarrel){
      this.radialVelocityBarrel = radialVelocityBarrel;
    }
    
    public void setPowerUp(int powerUp){
      this.powerUp = powerUp;
      switch(powerUp){
        case 0:
          powerUpCounter = 10;
          break;
        case 1:
          powerUpCounter = 3;
          break;
        case 2:
          powerUpCounter = 1;
          break;
        case 3:
          powerUpCounter = 5;
          break;
        case 4:
          lives++;
          powerUpCounter = 0;
          break;
      }
    }
    
    public void decreasePowerUpCounter(){
      this.powerUpCounter--;
    }

    /* Getters */
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
    public double getPower(){
      return powerUp;  
    }
    public double getPowerUpCounter(){
      return powerUpCounter;  
    }
    public void removeLife(){
        lives--;
    }

    public void move(){
        /*  set the position of the shooter and draws it  */
        // if the shooter touches edge invert velocity
        if(Math.abs(super.xCoord + super.xVelocity) + super.radius > 640){
            super.xVelocity = -super.xVelocity;
        }
        // prevents shooting backwards
        if(angleBarrel >= 180 || angleBarrel <= 0) {
            radialVelocityBarrel = -radialVelocityBarrel;
        }

        //  The shooter movement
        super.xCoord= super.xCoord + super.xVelocity;
        // The barrels rotational movement
        angleBarrel = angleBarrel + radialVelocityBarrel;
        xCoordBarrel =  xCoord + super.radius * Math.cos(angleBarrel*Math.PI/180);
        yCoordBarrel = yCoord + super.radius *Math.sin(angleBarrel*Math.PI/180);
        render();
    }
   
    public void render()
    {
        double scaledAngleBarrel = angleBarrel - 90;
        if(isPlayerOne){
          StdDraw.picture(super.xCoord,super.yCoord,"PlayerBlue.png",super.radius*5,super.radius*5, scaledAngleBarrel);
        }else{
          StdDraw.picture(super.xCoord,super.yCoord,"PlayerGreen.png",super.radius*5,super.radius*5, scaledAngleBarrel);
        }
    }

    public void resetState(int startXCoord, int startYCoord) {
        powerUp = -1;
        powerUpCounter = 0;
        xCoord = startXCoord;
        yCoord = startYCoord;
        radialVelocityBarrel = 0;
        xCoordBarrel = super.xCoord;
        yCoordBarrel = super.getRadius() + super.yCoord;
    }
}

