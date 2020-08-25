import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	 public Rectangle newGameButton;
     public Rectangle helpButton;
     public Rectangle quitButton;
     
     public void mousePressed(MouseEvent e) {
         int mX = e.getX();
         int mY = e.getY();
          
    
         //NewGame button
         if(mX >= (Game_Panel.WIDTH - 150) / 2 && mX <=  ((Game_Panel.WIDTH - 150) / 2) + 150) {
             if(mY >= (Game_Panel.HEIGHT / 2) - 50  && mY <= (Game_Panel.HEIGHT/ 2)) {                                  
                Game_Panel.isInMenu = false;
                 //System.out.println(Game_Panel.isInMenu);
                 System.out.println(((Game_Panel.WIDTH - 150) / 2));
                 System.out.println(((Game_Panel.WIDTH - 150) / 2) + 150);
                 System.out.println(mX);
                 System.out.println(mY);
                  
           }
          }
          
         //Quit button
         if(mX >= (Game_Panel.WIDTH - 150) / 2 && mX <= ((Game_Panel.WIDTH - 150)/ 2 ) + 150) {
             if(mY >= (Game_Panel.HEIGHT / 2) + 150  && mY <= (Game_Panel.HEIGHT/ 2) + 200) { 
          
                 System.exit(0);
                      
           }
         }
     }
}
