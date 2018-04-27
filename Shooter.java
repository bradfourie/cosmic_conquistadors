
/**
 * A class that extends DefaultCritter and serves as a model object
 * for a shooter with a barrel that can rotate and that can shoot
 * different types of missiles
 *
 *  @author Bradley Fourie
 *  @author Daniel Banks
 *  @author Heinrich Benz
 *  @see DefaultCritter
 */
public class Shooter extends DefaultCritter{

    /**
     * Constant values that correspond to the type of powerup
     */
    private final int NORMAL_MISSILE = -1, GATLING_MISSILE = 0, TRI_MISSILE = 1, SUPER_MISSILE = 2, FAST_MISSILE = 3, EXTRA_LIFE = 4;

    private double xCoordBarrel;
    private double yCoordBarrel;
    private double radialVelocityBarrel;
    private double angleBarrel;
    private int lives;
    private int powerUp;
    private int powerUpCounter;
    private boolean isPlayerOne;

    /**
     * Class constructor that creates a shooter using the DefaultCritter class as a basis, also adds extra
     * properties to the shooter to give the shooter a barrel that can move around with a radial velocity, and
     * also gives the shooter the ability to obtain various powerups, with powerUpCounter specifying how many
     * missiles of that powerup type the shooter has left before his powerup expires. The initial powerup of
     * the shooter is set to a normal missile and its barrel points straight upwards.
     *
     * @param xCoord the x coordinate of the shooter
     * @param yCoord the y coordinate of the shooter
     * @param xVelocity the x velocity of the shooter
     * @param yVelocity the y velocity of the shooter
     * @param lives the amount of lives assigned to the shooter
     * @param isPlayerOne a boolean variable representing whether or not the shooter is player one, used for
     *                    when rendering the shooter to specify the shooters colour
     */

    public Shooter(double xCoord, double yCoord, double xVelocity, double yVelocity, int lives, boolean isPlayerOne){
        super(xCoord, yCoord, xVelocity, yVelocity, 10);
        this.angleBarrel = 90;
        this.xCoordBarrel = super.xCoord;
        this.yCoordBarrel = super.getRadius() + super.yCoord;
        this.lives = lives;
        this.powerUp = NORMAL_MISSILE;
        this.powerUpCounter = 0;
        this.isPlayerOne = isPlayerOne;
    }

    /**
     * Receives an integer powerup which specifies which type of powerup the shooter currently has, and gives
     * the shooter a certain amount of those missiles (if applicable - powerUpCounter specifies how many missiles
     * of a certain type the shooter has before the powerup expires), otherwise it increases the powerups lives
     * if the shooter has gained an extra life.
     *
     * @param powerUp the current powerup that the shooter has obtained
     */
    public void setPowerUp(int powerUp){
        this.powerUp = powerUp;
        switch(powerUp){
            case GATLING_MISSILE:
                powerUpCounter = 10;
                break;
            case TRI_MISSILE:
                powerUpCounter = 3;
                break;
            case SUPER_MISSILE:
                powerUpCounter = 1;
                break;
            case FAST_MISSILE:
                powerUpCounter = 5;
                break;
            case EXTRA_LIFE:
                lives++;
                powerUpCounter = 0;
                break;
        }
    }

    public void setRadialVelocityBarrel(double radialVelocityBarrel){
        this.radialVelocityBarrel = radialVelocityBarrel;
    }

    public void decreasePowerUpCounter(){
        powerUpCounter--;
    }

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

    public int getPower(){
        return powerUp;
    }

    public double getPowerUpCounter(){
        return powerUpCounter;
    }

    public void removeLife(){
        lives--;
    }

    /**
     * Updates the shooters movement and ensures that the shooter stays within the bounds of the canvas,
     * and ensures that the shooters barrel can't move below the horizontal.
     */
    public void move(){
        if(Math.abs(super.xCoord + super.xVelocity) + super.radius > 640){
            super.xVelocity = -super.xVelocity;
        }
        if(angleBarrel >= 180 || angleBarrel <= 0) {
            radialVelocityBarrel = -radialVelocityBarrel;
        }

        super.xCoord= super.xCoord + super.xVelocity;
        angleBarrel = angleBarrel + radialVelocityBarrel;
        xCoordBarrel =  xCoord + super.radius * Math.cos(angleBarrel*Math.PI/180);
        yCoordBarrel = yCoord + super.radius *Math.sin(angleBarrel*Math.PI/180);
        render();
    }

    /**
     * Renders the shooter on the canvas, if the shooter is player one then render it as blue, otherwise
     * render the shooter as green
     */
    public void render()
    {
        double scaledAngleBarrel = angleBarrel - 90;
        if(isPlayerOne){
            StdDraw.picture(super.xCoord,super.yCoord,"PlayerBlue.png",super.radius*5,super.radius*5, scaledAngleBarrel);
        }else{
            StdDraw.picture(super.xCoord,super.yCoord,"PlayerGreen.png",super.radius*5,super.radius*5, scaledAngleBarrel);
        }
    }

    /**
     * Resets the shooter to its initial state and position on the canvas in the beginning
     * of each new round
     *
     * @param startXCoord the starting xCoord of the shooter
     * @param startYCoord the starting yCoord of the shooter
     */
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

