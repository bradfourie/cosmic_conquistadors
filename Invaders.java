package com.company;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_SPACE;

public class Invaders {
  
  
  public static void main(String[] args){
    
    /*StdDraw.setXscale(-100, +100);
    StdDraw.setYscale(-100, +100);*/
    StdDraw.setCanvasSize(1280,720);
    StdDraw.setXscale(-640,640);
    StdDraw.setYscale(-360, 360);
    
    boolean gameStart = true;

      InvaderGameState invaderGameState = new InvaderGameState();
      invaderGameState.initializeStartRound();
    
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

      /*if the user won the round, show the end round screen and then reset the game loop in such a way as to progress
        *to the next round*/
      if(invaderGameState.isWin() && (invaderGameState.getRound() != 5)){
        renderEndRound(invaderGameState.getScore(), invaderGameState.getRound());
        invaderGameState.initializeNextRound();
      }

      /*if the user dies, show the end game screen and display his/her score and then go back to the menu and reset
       *invader gamestate in such a way as to set the game loop to its initial condition */
      if( (invaderGameState.isGameOver() && !gameStart) || (invaderGameState.isWin() && (invaderGameState.getRound() == 5)) ){
        gameStart = true;
        renderEndGame(invaderGameState.getScore());
        invaderGameState.initializeStartRound();
      }
    }
    System.exit(0);
  }
  
  
  public static void renderMenu(){
    StdDraw.setPenColor(StdDraw.BLACK);
    
    StdDraw.text(0,10,"Quit(q), Screencap (p)");
    StdDraw.text(0,40,"Move: Left (z), Stop(x), Right(c)");
    StdDraw.text(0,70,"Rotate: Left (a), Stop (s), Right(d)");
    StdDraw.text(0,100,"Shoot (w)");
    StdDraw.text(0,130,"Press (Spacebar) to Save The World!");
    
    StdDraw.setFont();
    
    StdDraw.text(0,300,"COSMIC CONQUISTADORS");
    
    StdDraw.show();
  }

    public static void renderEndGame(int score){
    StdDraw.clear();
    StdDraw.setPenColor(StdDraw.BLACK);
    
    StdDraw.text(0,50,"Your score is: " + score);
    
    //read from text file
    ArrayList<Integer> listHighScore = HighScore.checkHighScore(score);
    
    StdDraw.text(0,300,"__GAME__OVER__");
    
    StdDraw.text(0,250,"__HIGH_SCORES__");
    int plotY = 220;
    int noScoreCount = (5-listHighScore.size());
    
    for(int i=0; i<listHighScore.size(); i++){
      String strScore = String.valueOf(listHighScore.get(i));
      StdDraw.text(0,plotY,"" + strScore);
      plotY-=30;
    }
    
    for(int j=0; j<noScoreCount; j++){
      StdDraw.text(0,plotY,"--");
      plotY-=10;
    }
    
        StdDraw.show();
        StdDraw.pause(2000);
        StdDraw.clear();
  }
  
  public static void renderEndRound(int score, int round){
    StdDraw.clear();
    StdDraw.setPenColor(StdDraw.BLACK);
    
    StdDraw.text(0,340,"Congratulations! You Have Cleared The Wave");
    StdDraw.text(0,300,"Your Score Is: " + score);
    StdDraw.text(0,260,"Get Ready For Round " + (round+1));

      StdDraw.show();
      StdDraw.pause(2000);
      StdDraw.clear();
  }
}
