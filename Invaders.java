import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_SPACE;

public class Invaders {
  
  
  public static void main(String[] args){
    StdDraw.setCanvasSize(1280,720);
    StdDraw.setXscale(-640,640);
    StdDraw.setYscale(-360, 360);
    
    boolean gameStart = true;
    boolean isColour = true;
    int colourCounter = 0;
    
    InvaderGameState invaderGameState = new InvaderGameState();
    invaderGameState.initializeStartRound();

    while( !StdDraw.isKeyPressed(VK_Q) ) {
      
      if (gameStart) {
        renderMenu(isColour);
        colourCounter++;
        if(isColour && colourCounter==12){
          colourCounter=0;
          isColour=false;
        }else if(!isColour&&colourCounter==12){
          colourCounter=0;
          isColour=true;
        }
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
        renderEndRound( invaderGameState.getRound());
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
  
  
  public static void renderMenu(boolean isColour){
    try {
      //create the font to use. Specify the size!
      Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("operational amplifier.ttf")).deriveFont(45f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      //register the font
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("operational amplifier.ttf")));
      StdDraw.setFont(customFont);
    } catch (IOException e) {
      e.printStackTrace();
    } catch(FontFormatException e) {
      e.printStackTrace();
    }
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.filledRectangle(0,0,640,360);
    StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.text(0,-90,"Quit(q)");
    StdDraw.text(0,-40,"Move: Left (z), Stop(x), Right(c)");
    StdDraw.text(0,10,"Rotate: Left (a), Stop (s), Right(d)");
    StdDraw.text(0,60,"Shoot (w)");
    StdDraw.setPenColor(StdDraw.GREEN);
    StdDraw.text(0,300,"COSMIC CONQUISTADORS");
    if(isColour){
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.rectangle(0,-12,250,105);
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.text(0,190,"Press (Spacebar) to Save The World!");
    }else{
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.rectangle(0,-12,250,105);
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.text(0,190,"Press (Spacebar) to Save The World!");
    }
    StdDraw.show(30);
  }
  
  public static void renderEndGame(int score){
    for(int i = 0 ; i < 800; i ++){
      StdDraw.picture(0,-30 + (i * 0.25),"GAMEOVER.png", 80 + i*0.3 , 80 + i*0.3);
      StdDraw.show();
      StdDraw.setPenColor(StdDraw.WHITE);
      if(i > 300){
          StdDraw.text(0 , -70 , "YOUR SCORE: " + score);
        }
      }
      ArrayList<Integer> listHighScore = HighScore.checkHighScore(score);

      StdDraw.text(0,-120,"HIGH SCORES:");
      int plotY = -170;

      for(int j=0; j<listHighScore.size(); j++){
          String strScore = String.valueOf(listHighScore.get(j));
          StdDraw.text(0,plotY,"" + strScore);
          StdDraw.show();
          plotY-=40;
      }
      StdDraw.show();
      StdDraw.pause(2000);
      StdDraw.clear();
    }

  
  public static void renderEndRound( int round){
   StdDraw.clear(StdDraw.BLACK);
    StdDraw.setPenColor(StdDraw.WHITE); 
    StdDraw.text(0,0,"Get Ready For Round " + (round+1));
    
    StdDraw.show();
    StdDraw.pause(2000);
    StdDraw.clear();
  }
}
