package com.company;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_SPACE;

public class Invaders {


    public static void main(String[] args){

        StdDraw.setXscale(-100, +100);
        StdDraw.setYscale(-100, +100);

        boolean gameStart = true;

        InvaderGameState invaderGameState = new InvaderGameState();

        while( !StdDraw.isKeyPressed(VK_Q) ) {
            if (gameStart) {
                renderMenu();
                if (StdDraw.isKeyPressed(VK_SPACE)) {
                    gameStart = false;
                }
            }

            if(!gameStart) {
                invaderGameState.gameLoop();
            }

            if(invaderGameState.isWin()){
                renderEndRound(invaderGameState.getScore(), invaderGameState.getRound());
            }

            if(invaderGameState.isGameOver()){
                renderEndGame(invaderGameState.getScore());
                gameStart = true;
            }

            try  {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                //just here because java wants it to be here
            }
        }
    }


    public static void renderMenu(){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.text(0,10,"Quit(q), Screencap (p)");
        StdDraw.text(0,20,"Move: Left (z), Stop(x), Right(c)");
        StdDraw.text(0,30,"Rotate: Left (a), Stop (s), Right(d)");
        StdDraw.text(0,40,"Shoot (w)");
        StdDraw.text(0,60,"Hold in (Spacebar) to Save The World");

        StdDraw.setFont();

        StdDraw.text(0,75,"COSMIC CONQUISTADORS");

        StdDraw.show();
    }

    public static void renderEndGame(int score){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.text(0,10,"Your score is: " + score);

        //read from text file
        ArrayList<Integer> listHighScore = HighScore.checkHighScore(score);

        StdDraw.text(0,90,"__GAME__OVER__");

        StdDraw.text(0,70,"__HIGH_SCORES__");
        int plotY = 60;
        int noScoreCount = (5-listHighScore.size());

        for(int i=0; i<listHighScore.size(); i++){
            String strScore = String.valueOf(listHighScore.get(i));
            StdDraw.text(0,plotY,"" + strScore);
            plotY-=10;
        }

        for(int j=0; j<noScoreCount; j++){
            StdDraw.text(0,plotY,"--");
            plotY-=10;
        }

        StdDraw.show();
    }

    public static void renderEndRound(int score, int round){
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.text(0,10,"Congratulations! You Have Cleared The Wave");
        StdDraw.text(0,20,"Your Score Is: " + score);
        StdDraw.text(0,40,"Get Ready For Round " + (round+1));

        StdDraw.show();
    }
}
