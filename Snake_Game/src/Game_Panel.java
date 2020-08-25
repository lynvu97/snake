import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import javax.swing.Timer;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class Game_Panel extends JPanel implements Runnable, KeyListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500, HEIGHT = 500;
	private final int size = 10;
	private int dots_total = 20;
	private int dots;
	private int DELAY = 50;
	
	private final int xCord[] = new int[dots_total];
	private final int yCord[] = new int[dots_total];
	
	
	public int x_apple = 250;
	public int y_apple = 250;
	//private Thread thread;

	
	//private boolean running;
	private boolean Game_running = true;
	private boolean right = true, left = false, up = false, down = false;
	
	
	private Timer timer;
    private Image body;
    private Image apple;
    private Image head;
    

    public static int count = 0;
    //public static GameState state = GameState.MENU;
    
    public static boolean isInMenu = true;
  
    private Menu menu;
    
   /* public static enum GameState{
        MENU,
        GAME
         
    }*/

	

	public Game_Panel() {
		
		
			addKeyListener(this);
		//System.out.println(isInMenu);
			addMouseListener(new MouseInput());
			setFocusable(true);
			setBackground(Color.black);
			
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			//System.out.println(isInMenu);
			if(!isInMenu) {
				System.out.println("I'm in doug");
				load_Images();
				start_game();
				//System.out.println(state);
			}
	}
	
	  
	public void start_game() {//this to decide where the snake will be when the game start
		//System.out.println(state);
			dots = 3;
		
			for (int z = 0; z < dots; z++) {
		         xCord[z] = 100 - z * 10;
		         yCord[z] = 400;
			}
			
			timer = new Timer(DELAY, this);
			timer.start();
	}
	
	
	public void load_Images() {
		  ImageIcon obj1 = new ImageIcon("src/rsc/dot.png");
	      body = obj1.getImage();

	      ImageIcon obj2 = new ImageIcon("src/rsc/apple.png");
	      apple = obj2.getImage();

	      ImageIcon obj3 = new ImageIcon("src/rsc/head.png");
	      head = obj3.getImage();
	}
	

	
	public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if(!isInMenu) {
	        	do_Drawing(g);
	        }else {
	        	new Menu().displayMenu(g);
	        }
	    }
	 
	
	public void generate_apple() {
		
		
		int pos = (int) (Math.random() * 20);
	    x_apple = ((pos*size));

	     pos = (int)  (Math.random() * 20);
	     y_apple = ((pos*size));
		//i tries couple of ways to generate new apple location
	     //but end up stucking after the 1st apple, only mutiply
	     //by random interger seems to generate correctly?
	    /* 
	     int min = 1;
	     int max = 500;
	     int range= max -min+1;
	     int pos = (int) (Math.random() * range)+min;
		 x_apple = ((pos*size));
		 pos = (int)  (Math.random() * range)+min;
		 y_apple = ((pos*size));*/
		/*Random randomGenerator = new Random();
		x_apple = randomGenerator.nextInt(WIDTH);
		y_apple = randomGenerator.nextInt(HEIGHT);*/
	}
	
	private void check_if_eaten() {
			if((xCord[0] == x_apple) && (yCord[0] == y_apple)) {
				dots++;
				generate_apple();
			}
	}
	
	private void win(Graphics g) {
		 String msg = "You Win";
		 Font fnt = new Font("arial", Font.BOLD, 20);
	     FontMetrics metr = getFontMetrics(fnt);
	     g.setColor(Color.white);
	     g.setFont(fnt);
	     g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
		
	}
	
	private void do_Drawing(Graphics g) {
		 g.drawImage(apple, x_apple, y_apple, this);//set x , y =250 so that first apple always in the centre every new game
	     if (Game_running) {
	            for (int z = 0; z < dots; z++) {
	                if (z == 0) {
	                    g.drawImage(head, xCord[z], yCord[z], this);
	                } else {
	                    g.drawImage(body, xCord[z], yCord[z], this);
	                }
	            }

	            Toolkit.getDefaultToolkit().sync();
	        }
	     
	     if((Game_running) && (dots==5)) {
	    	 win(g);
	    	 timer.stop();
	     }
	     
	     if(!Game_running )
	     	{
	        	Game_Over(g);
	        }
	}
	 
	 private void Game_Over(Graphics g) {
		 String msg = "You Lose";
		 Font small = new Font("Helvetica", Font.BOLD, 14);
	     FontMetrics metr = getFontMetrics(small);
	     g.setColor(Color.white);
	     g.setFont(small);
	     g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
	 }
	 
	 private void checkCollision() {
		 if((xCord[0] >= WIDTH)||(xCord[0]<0)) {
			 Game_running = false;
			 timer.stop();
		 }
		 if((yCord[0] >= HEIGHT)||(yCord[0] < 0)) {
			 Game_running = false;
			 timer.stop();
		 }
		 if(dots>=3){
			 for(int i = 3;i<dots;i++) {
				 if((xCord[0] == xCord[i])&&(yCord[0]==yCord[i])) {
					 Game_running = false;
					 timer.stop();
				 }
			 }
		 }
		 
		 
	 }
	    
	
	 
	public void move() {
        for (int z = dots; z > 0; z--) {
            xCord[z] = xCord[(z - 1)];
            yCord[z] = yCord[(z - 1)];
        }

        if (right) {
        	xCord[0] += size;
        }
        
        if (left) {
        	xCord[0] -= size;
        }

        if (up) {
        	yCord[0] -= size;
        }

        if (down) {
        	yCord[0] += size;
        }
 
	}
	
	
	public void Snake_Control(){
		
	}
	
	
	
	
	
	public void run() {
		// todo auto generated method

	}

	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(Game_running) {
			checkCollision();
			check_if_eaten();
			move();
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_UP && !down) {
			up = true;
			left = false;
			right = false;
		}
		
		if(key == KeyEvent.VK_DOWN && !up) {
			down = true;
			left = false;
			right = false;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	

}
