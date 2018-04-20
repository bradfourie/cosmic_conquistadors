import java.util.ArrayList;
public class PlasmaMissile extends Missile{
  ArrayList<Missile> plasmaMissilesList = new ArrayList<Missile>();
  public PlasmaMissile(Shooter shooter){
    super(shooter.getXCoord(), shooter.getYCoord(), 12 * Math.cos(shooter.getAngleBarrel()), 12 * Math.sin(shooter.getAngleBarrel()), 5);
    Missile m1 = new Missile(shooter.getXCoord(), shooter.getYCoord(), 15*Math.cos(shooter.getAngleBarrel()*Math.PI/180+Math.PI/2),15*Math.sin(shooter.getAngleBarrel()*Math.PI/180+Math.PI/2), 5);
    plasmaMissilesList.add(m1);
    
    Missile m2 = new Missile(shooter.getXCoord(), shooter.getYCoord(), 12 * Math.cos((shooter.getAngleBarrel()*Math.PI/180) + Math.PI/2 + Math.PI/6), 12 * Math.sin((shooter.getAngleBarrel()*Math.PI/180) + Math.PI/2 + Math.PI/6), 5);
    plasmaMissilesList.add(m2);
    
    Missile m3 = new Missile(shooter.getXCoord(), shooter.getYCoord(), 12 * Math.cos((shooter.getAngleBarrel()*Math.PI/180) + Math.PI/2 - Math.PI/6), 12 * Math.sin((shooter.getAngleBarrel()*Math.PI/180) + Math.PI/2 - Math.PI/6), 5);
    plasmaMissilesList.add(m3);
    
  }
  
  public void move(){
    System.out.println("SIZE: " + plasmaMissilesList.size());
    for(int i = 0 ; i < plasmaMissilesList.size() ; i ++){
      Missile m = plasmaMissilesList.get(i);
      if(m.getXCoord() < -360 || m.getXCoord() > 360 || m.getYCoord() > 360 || m.getYCoord() < -360){
        plasmaMissilesList.remove(i);
      }else{
        System.out.println("Move: " + i);
      m = plasmaMissilesList.get(i);
      m.move();
      
    }
  }
 
  }
}
