public class InvaderGameState{
	ArrayList<Enemy> enemies = new ArrayList<Enemy>(0);
	ArrayList<Missile> missiles = new ArrayList<Missile>(0);
	Shooter shooter;
	//handle the state of the game, i.e. menu or in game
	//handle key presses
	//update draw
	Public void Draw(){
		StdDraw.clear();
		for(int i=0;i<enemies.length;i++){
			enemies.get(i).move;
		}
		for(int i=0;i<missiles.length;i++){
			enemies.get(i).move;
		}
		shooter.move;
	}
}
