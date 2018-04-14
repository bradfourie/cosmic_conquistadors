package com.company;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class InvaderGameState{

        private final int START_Y_COORD_SHOOTER = -300;
        private final int START_X_COORD_SHOOTER = 0;
        private final int START_Y_COORD_ENEMY = 300;
        private final int START_X_COORD_ENEMY = -600;

        private int gameLoopCounter, score, round, coolDown1, coolDown2, shootProbability;
        private double enemyXVelocity;
        private boolean gameOver, win, isShot1, isShot2, twoPlayer;

        private Shooter mainShooter, additionalShooter;
        private StarBackground star;

        private ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
        private ArrayList<Missile> missilesList = new ArrayList<Missile>();
        private ArrayList<StarBackground> starsList = new ArrayList<StarBackground>();


        public void gameLoop(){

            while (!gameOver && !win) {
                if(!twoPlayer && StdDraw.isKeyPressed(VK_P)){
                    additionalShooter= new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0, 3);
                    twoPlayer = true;
                }

                isShot1 = false;
                mainShooterKeyPresses();
                mainShooter.move();

                if(twoPlayer){
                    isShot2 = false;
                    additionalShooterKeyPresses();
                    additionalShooter.move();
                }

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

        public void initializeStartRound() {
            gameLoopCounter = 0;
            coolDown1 = 0;
            coolDown2 = 0;
            round = 1;
            score = 0;
            enemyXVelocity = 2;
            shootProbability = 1500;

            gameOver = false;
            win = false;
            twoPlayer = false;

            enemiesList.clear();
            missilesList.clear();

            mainShooter = new Shooter(START_X_COORD_SHOOTER, START_Y_COORD_SHOOTER, 0, 0, 3);

            initializeEnemies();
        }

        public void initializeNextRound(){
            gameLoopCounter = 0;
            round++;

            win = false;

            mainShooter.resetState(20, START_Y_COORD_SHOOTER);
            coolDown1 = 0;

            if(twoPlayer){
                additionalShooter.resetState(60, START_Y_COORD_SHOOTER);
                coolDown2 = 0;
            }

            enemiesList.clear();
            missilesList.clear();

            initializeEnemies();
    }



        public void initializeEnemies(){

            switch (round){
                case 1:
                    //only light enemies
                    setupLightEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 10, 5);
                    break;
                case 2:
                    //main light enemies and top row of heavy enemies
                    setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 5, 1);
                    setupLightEnemyGrid(START_X_COORD_ENEMY , START_Y_COORD_ENEMY - 100, 10, 4);
                    break;
                case 3:
                    //half light and half heavy enemies
                    setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 5, 3);
                    setupLightEnemyGrid(START_X_COORD_ENEMY , START_Y_COORD_ENEMY - 200, 10, 3);
                    break;
                case 4:
                    //only heavy enemies
                    setupHeavyEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY, 5, 5);
                    break;
                case 5:
                    //final boss fight
                    setupBossEnemyGrid(START_X_COORD_ENEMY, START_Y_COORD_ENEMY);
                    break;
            }

        }
        public void setupLightEnemyGrid(int start_x_coord, int start_y_coord, int xNumberEnemy, int yNumberEnemy){
            int enemyRadius = 10;
            int gap = 10;
            int enemySpace = enemyRadius + gap;
            for (int i = 0; i < (yNumberEnemy * enemySpace); i = i + (enemySpace) ){
                for (int j = 0; j < (xNumberEnemy * enemySpace); j = j + (enemySpace)) {
                    LightEnemy enemy = new LightEnemy(start_x_coord + j, start_y_coord - i, enemyXVelocity, 0);
                    enemiesList.add(enemy);
                }
            }
        }
        public void setupHeavyEnemyGrid(int start_x_coord, int start_y_coord, int xNumberEnemy, int yNumberEnemy){
            start_x_coord = start_x_coord + 20;
            start_y_coord = start_y_coord - 20;
            int enemyRadius = 20;
            int gap = 20;
            int enemySpace = enemyRadius + gap;
            for (int i = 0; i < (yNumberEnemy * enemySpace); i = i + (enemySpace) ){
                for (int j = 0; j < (xNumberEnemy * enemySpace); j = j + (enemySpace)) {
                    HeavyEnemy enemy = new HeavyEnemy(start_x_coord + j, start_y_coord - i, enemyXVelocity/2, 0);
                    enemiesList.add(enemy);
                }
            }
        }

        public void setupBossEnemyGrid(int start_x_coord, int start_y_coord){
            start_x_coord = start_x_coord + 50;
            start_y_coord = start_y_coord - 50;
            BossEnemy enemy = new BossEnemy(start_x_coord, start_y_coord, enemyXVelocity/2, 0);
            enemiesList.add(enemy);
        }

        public void updateEnemyMovement(){
            //loop through enemiesList and update each enemies movement
            boolean isEdge = false;
               for(int a = 0 ; a < enemiesList.size() ; a++){
                    Enemy currentEnemy = enemiesList.get(a);
                    if( (currentEnemy.getXCoord() - currentEnemy.getRadius()) <= -640 ||  (currentEnemy.getXCoord() + currentEnemy.getRadius())>= 640){
                      isEdge = true;
                      break;
                    }
               }

            for (int i = 0; i < enemiesList.size(); i++) {
                boolean isDestroy = false;
                Enemy currentEnemy = enemiesList.get(i);

                for (int j = 0; j < missilesList.size(); j++) {
                    Missile currentMissile = missilesList.get(j);

                    if (currentEnemy.onMissileCollision(currentMissile) && !currentMissile.isEnemyMissile()) {
                        isDestroy = true;
                        currentEnemy.removeLife();
                        if(currentEnemy.getLives() == 0){
                            enemiesList.remove(i);
                            i--;
                        }
                        missilesList.remove(j);
                        score = score + 10;
                        break;
                    }
                }

                if (!isDestroy) {
                    if(isEdge){
                        currentEnemy.moveDown();
                    }else{
                        currentEnemy.move();
                    }

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

                if (Math.abs(currentMissile.getXCoord() + currentMissile.getRadius()) + currentMissile.getRadius() > 640) {
                    currentMissile.wallBounce();
                }

                if (currentMissile.getYCoord() == 640 || currentMissile.getNumBounced() >= 2) {
                    // remove if its bounced more than once OR if its at the top of screen
                    missilesList.remove(i);
                } else {
                    currentMissile.move();
                }
            }
        }

        public void updateBackground(){
            int x = (int)(Math.random() *400);
            double length = Math.random();
            double sign = Math.random();
            if(sign > 0.5){
                x = -x;
            }
            star = new StarBackground(x , 360, length/1.1);
            if(x >= -640 && x <= 640){
                starsList.add(star);
            }

            for(int i = 0 ; i < starsList.size() ; i ++){
                StarBackground currentStar = starsList.get(i);
                if (currentStar.getTailYCoord() < -360){
                    starsList.remove(i);
                } else {
                    currentStar.move();
                }
            }
        }

        public void renderUI(){
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

            StdDraw.text(-600, 340, "score: " + score);
            StdDraw.text(-600, 310, "lives: " + mainShooter.getLives());
            if(twoPlayer){
                StdDraw.text(-600, 280, "lives: " + additionalShooter.getLives());
            }
            StdDraw.text(600, 340, "round: " + round);
        }

        public void checkGameOver(){
            for (int i = 0; i < enemiesList.size(); i++) {
                Enemy currentEnemy = enemiesList.get(i);
                //loop through enemies and see if any enemy has touched the bottom of the grid
                if (Math.abs(currentEnemy.getYCoord() + currentEnemy.getRadius()) == -100) {
                    gameOver = true;
                }
                //also check if one of the enemies have touched the shooter
                if (currentEnemy.isShooterCollision(mainShooter)) {
                    mainShooter.removeLife();
                    enemiesList.remove(i);
                }
                //also check if one of the enemies have touched the additional shooter
                if (twoPlayer) {
                    if(currentEnemy.isShooterCollision(additionalShooter)) {
                        additionalShooter.removeLife();
                        enemiesList.remove(i);
                    }
                }
            }

            //also check if an enemy missile has touched the shooter
            for(int i=0; i<missilesList.size(); i++){
                Missile currentMissile = missilesList.get(i);
                if(currentMissile.isEnemyMissile() && currentMissile.isShooterCollision(mainShooter)){
                    mainShooter.removeLife();
                    missilesList.remove(i);
                }
                if(twoPlayer){
                    if(currentMissile.isEnemyMissile() && currentMissile.isShooterCollision(additionalShooter)){
                        additionalShooter.removeLife();
                        missilesList.remove(i);
                    }
                }

            }

            if(mainShooter.getLives() == 0){
                gameOver = true;
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

    public void mainShooterKeyPresses() {
        if (StdDraw.isKeyPressed(VK_Z)) {
            mainShooter.setXVelocity(-8);
        }
        if (StdDraw.isKeyPressed(VK_C)) {
            mainShooter.setXVelocity(8);
        }
        if (StdDraw.isKeyPressed(VK_X)) {
            mainShooter.setXVelocity(0);
        }
        if (StdDraw.isKeyPressed(VK_W) && (coolDown1 + 20 < gameLoopCounter)) {
            coolDown1 = gameLoopCounter;
            isShot1 = true;
        }
        if (StdDraw.isKeyPressed(VK_A)) {
            mainShooter.setRadialVelocityBarrel(0.1);
        }
        if (StdDraw.isKeyPressed(VK_D)) {
            mainShooter.setRadialVelocityBarrel(-0.1);
        }
        if (StdDraw.isKeyPressed(VK_S)) {
            mainShooter.setRadialVelocityBarrel(0);
        }
        if (StdDraw.isKeyPressed(VK_Q)) {
            System.exit(0);
        }
        if (StdDraw.isKeyPressed(VK_P)) {
            //StdDraw.save();
        }
        if (isShot1) {
            Missile missile = new Missile(mainShooter);
            missilesList.add(missile);
        }
    }

    public void additionalShooterKeyPresses() {
        if (StdDraw.isKeyPressed(VK_NUMPAD1)) {
            additionalShooter.setXVelocity(-8);
        }
        if (StdDraw.isKeyPressed(VK_NUMPAD3)) {
            additionalShooter.setXVelocity(8);
        }
        if (StdDraw.isKeyPressed(VK_NUMPAD2)) {
            additionalShooter.setXVelocity(0);
        }
        if (StdDraw.isKeyPressed(VK_NUMPAD8) && (coolDown2 + 20 < gameLoopCounter)) {
            coolDown2 = gameLoopCounter;
            isShot2 = true;
        }
        if (StdDraw.isKeyPressed(VK_NUMPAD4)) {
            additionalShooter.setRadialVelocityBarrel(0.1);
        }
        if (StdDraw.isKeyPressed(VK_NUMPAD6)) {
            additionalShooter.setRadialVelocityBarrel(-0.1);
        }
        if (StdDraw.isKeyPressed(VK_NUMPAD5)) {
            additionalShooter.setRadialVelocityBarrel(0);
        }
        if (isShot2) {
            Missile missile = new Missile(additionalShooter);
            missilesList.add(missile);
        }
    }
}
