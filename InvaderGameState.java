package com.company;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Z;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_SPACE;


public class InvaderGameState{

    private final int START_Y_COORD_SHOOTER = -80;
    private final int START_X_COORD_SHOOTER = 0;
    private final int START_Y_COORD_ENEMY = 96;
    private final int START_X_COORD_ENEMY = -96;

    private int gameLoopCounter, score, round, stepLastShot;
    private double enemyXVelocity;
    private boolean gameOver, win;

    private Shooter shooter;
    private StarBackground star;

    private ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
    private ArrayList<Missile> missilesList = new ArrayList<Missile>();
    private ArrayList<StarBackground> starsList = new ArrayList<StarBackground>();

    public InvaderGameState(){
        round = 0;
        score = 0;
        enemyXVelocity = 0.8;
    }

    public void gameLoop(){

        initialiseRound();

        while (!gameOver && !win) {
            //  moving left
            boolean isShot = false;
            if (StdDraw.isKeyPressed(VK_Z)) {
                shooter.setXVelocity(-2);
            }
            //  moving right
            if (StdDraw.isKeyPressed(VK_C)) {
                shooter.setXVelocity(2);
            }
            //  stop movement
            if (StdDraw.isKeyPressed(VK_X)) {
                shooter.setXVelocity(0);
            }
            // if W is pressed create a missile
            if (StdDraw.isKeyPressed(VK_W) && (stepLastShot + 20 < gameLoopCounter)) {
                stepLastShot = gameLoopCounter;
                isShot = true;
            }
            // if A is pressed rotate barrel left
            if (StdDraw.isKeyPressed(VK_A)) {
                shooter.setRadialVelocityBarrel(0.1);
            }
            // if D is pressed rotate barrel right
            if (StdDraw.isKeyPressed(VK_D)) {
                shooter.setRadialVelocityBarrel(-0.1);
            }
            // if S is pressed stop rotation
            if (StdDraw.isKeyPressed(VK_S)) {
                shooter.setRadialVelocityBarrel(0);
            }
            if (StdDraw.isKeyPressed(VK_Q)) {
                System.exit(0);
            }
            if (isShot) {
                Missile missile = new Missile(shooter);
                missilesList.add(missile);
            }


            shooter.move();

            updateEnemyMovement();
            updateMissileMovement();
            updateBackground();

            checkWin();
            checkGameOver();

            renderUI();

            StdDraw.show(30);
            StdDraw.clear(StdDraw.BLACK);
            gameLoopCounter++;
        }

    }

    public void initialiseRound(){
        gameLoopCounter = 0;
        stepLastShot = 0;
        round++;
        enemyXVelocity = enemyXVelocity + 0.2;

        win = false;

        shooter = new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0);

        initialiseEnemies(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, enemyXVelocity);
    }



    public void initialiseEnemies(int startXCoordEnemy , int startYCoordEnemy, double enemyXVelocity){
        //loops to initialise the grid of enemies
        for (int i = 0; i < 24; i = i + 7) {
            for (int j = 0; j < 60; j = j + 7) {
                Enemy enemy = new Enemy(startXCoordEnemy + j, startYCoordEnemy - i, enemyXVelocity, 0);
                enemiesList.add(enemy);
            }
        }

    }

    public void updateEnemyMovement(){
        //loop through enemiesList and update each enemies movement
        for (int i = 0; i < enemiesList.size(); i++) {
            boolean isDestroy = false;
            Enemy currentEnemy = enemiesList.get(i);

            for (int j = 0; j < missilesList.size(); j++) {
                Missile currentMissile = missilesList.get(j);
                if (currentEnemy.onMissileCollision(currentMissile) && !currentMissile.isEnemyMissile()) {
                    isDestroy = true;
                    enemiesList.remove(i);
                    missilesList.remove(j);
                    score = score + 10;
                    break;
                }
            }

            if (!isDestroy) {
                currentEnemy.move();

                if(currentEnemy.isShoot()){
                    Missile missile = new Missile(currentEnemy);
                    missilesList.add(missile);
                }
            }
        }
    }

    public void updateMissileMovement(){
        //loop through missilesList and update each missiles movement
        for (int i = 0; i < missilesList.size(); i++) {
            Missile currentMissile = missilesList.get(i);

            //can anyone tell me what this +1 is for, if so please say on the group xD
            if (Math.abs(currentMissile.getXCoord() + currentMissile.getRadius()) + 1 > 100) {
                currentMissile.wallBounce();
            }

            if (currentMissile.getYCoord() == 100 || currentMissile.getNumBounced() >= 2) {
                // remove if its bounced more than once OR if its at the top of screen
                missilesList.remove(i);
            } else {
                currentMissile.move();
            }
        }
    }

    public void updateBackground(){
        int x = (int)(Math.random() * 600);
        double length = Math.random();
        double sign = Math.random();
        if(sign > 0.5){
            x = -x;
        }
        star = new StarBackground(x , 100 , length);
        if(x >= -100 && x <= 100){
            starsList.add(star);
        }

        for(int i = 0 ; i < starsList.size() ; i ++){
            StarBackground currentStar = starsList.get(i);
            if (currentStar.getTailYCoord() < -100){
                starsList.remove(i);
            } else {
                currentStar.move();
            }
        }
    }

    public void renderUI(){
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.text(-85, 95, "score: " + score);

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.text(85, 95, "round: " + round);
    }

    public void checkGameOver(){
        for (int i = 0; i < enemiesList.size(); i++) {
            Enemy currentEnemy = enemiesList.get(i);

            //loop through enemies and see if any enemy has touched the bottom of the grid
            if (Math.abs(currentEnemy.getYCoord() + currentEnemy.getRadius()) == -100) {
                gameOver = true;
            }

            //also check if one of the enemies have touched the shooter
            if (currentEnemy.isShooterCollision(shooter)) {
                gameOver = true;
            }
        }

        //also check if an enemy missile has touched the shooter
        for(int i=0; i<missilesList.size(); i++){
            Missile currentMissile = missilesList.get(i);

            if(currentMissile.isEnemyMissile() && currentMissile.isShooterCollision(shooter)){
                gameOver = true;
            }
        }

        if(gameOver){
            enemiesList.clear();
            missilesList.clear();
        }
    }

    public void checkWin(){
        if (enemiesList.size() == 0) {
            win = true;
        }
    }

    public int getScore(){
        return  score;
    }

    public int getRound(){
        return round;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isWin(){
        return win;
    }

}
