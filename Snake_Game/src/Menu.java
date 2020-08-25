import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Menu {

	 public Rectangle newGameButton;
     public Rectangle helpButton;
     public Rectangle quitButton;
 
     public Button button1;
     
	//public static Rectangle playButton = new Rectangle(Game_Panel.WIDTH/2 - 50 , 150,100,50);
	//public static Rectangle helpButton = new Rectangle(Game_Panel.WIDTH/2 - 50 , 250,100,50);
	//public static Rectangle quitButton = new Rectangle(Game_Panel.WIDTH/2 - 50 , 350,100,50);
	
	public  Menu(){
		 int buttonWidth = 150;
         int buttonHeight = 50;
      
         newGameButton = new Rectangle((Game_Panel.WIDTH - buttonWidth) / 2, (Game_Panel.HEIGHT / 2) - 50, buttonWidth, buttonHeight); 
         helpButton = new Rectangle((Game_Panel.WIDTH - buttonWidth) / 2, (Game_Panel.HEIGHT / 2) + 50, buttonWidth, buttonHeight);
         quitButton = new Rectangle((Game_Panel.WIDTH - buttonWidth) / 2, (Game_Panel.HEIGHT / 2) + 150, buttonWidth, buttonHeight);
          
       
		
	}
	
	public void displayMenu(Graphics g) {
      Graphics2D  buttonGraphics =(Graphics2D) g;
         
      String title = "SNAKE GAME";
      Font font = new Font("Arial", Font.BOLD, 50);
      FontMetrics size = g.getFontMetrics(font);
       
      g.setFont(font);
      g.setColor(Color.GREEN);
      g.drawString(title,(Game_Panel.WIDTH-size.stringWidth(title)) / 2, (Game_Panel.HEIGHT / 2) - 130);
   
       
      Font buttonFont = new Font("Arial", Font.BOLD, 28);
      g.setFont(buttonFont);
       
      g.drawString("New Game",newGameButton.x + 2, newGameButton.y + 35);
      buttonGraphics.draw(newGameButton);
       
      g.drawString("Help",helpButton.x + 45,helpButton.y + 35);
      buttonGraphics.draw(helpButton);
       
      g.drawString("Quit", quitButton.x + 45, quitButton.y + 35);
      buttonGraphics.draw(quitButton);    
  }   
}
