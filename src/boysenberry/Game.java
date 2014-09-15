package boysenberry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * @author      Todor Samardzhiev <tdsamardzhiev89@gmail.com>
 * @version     0.1
 * @since       2014-09-15
 */
@SuppressWarnings("serial")
public class Game extends JFrame {

	/**
	 * The width of the game window. 
	 */
	private static int windowWidth = 600;
	
	/**
	 * The height of the game window.
	 */
	private static int windowHeight = 600;
	
	/**
	 * Does the game need to quit?
	 */
	private boolean done;
	/**
	 * The game's frames per second rate.
	 */
	private int fps = 60;
	
	/**
	 * Specify game's border.
	 */
	private Insets insets;
	
	/**
	 * Draw to rear buffer ONLY, then swap buffers.
	 */
	private BufferedImage rearBuffer;
	
	/**
	 * Listen for user input.
	 */
	private UserInputHandler input;
	
	/*
	 * Dummy variables used for testing.
	 */
	private int x = windowWidth / 2;
	private int y = windowHeight / 2;
	
	/**
	 * Main method for the game.
	 *
	 * @param args Command line arguments (none used).
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
	
	/**
	 * Initialize everything needed to run.
	 */
	public Game() {
		setTitle("Java Driver 3000");
		setSize(Game.windowWidth, Game.windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right,
				insets.top + windowHeight + insets.bottom);
		
		rearBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		input = new UserInputHandler(this);
	}
	
	/**
	 * This method actually runs the game.
	 */
    public void run() {
    	while(!done) {
    		long time = System.currentTimeMillis();
    		update();
    		draw();
    		time = (1000 / fps) - (System.currentTimeMillis() - time);
    		if (time > 0) {
    			try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(1);
				}
    		}
    	}
    	
    	setVisible(false);
    }
   
	/**
	 * Check user input, object movement and other events.
	 */
    private void update()
    {
           if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
        	   x += 5;
           }
           
           if (input.isKeyDown(KeyEvent.VK_LEFT)) {
        	   x -= 5;
           }
           
           if (input.isKeyDown(KeyEvent.VK_UP)) {
        	   y -= 5;
           }
           
           if (input.isKeyDown(KeyEvent.VK_DOWN)) {
        	   y += 5;
           }
    }
   
    /**
     * Draw everything.
     */
    private void draw()
    {
    	Graphics front = getGraphics();
    	
    	Graphics rear = rearBuffer.getGraphics();
    	rear.setColor(Color.WHITE);
    	rear.fillRect(0, 0, windowWidth, windowHeight);
    	
    	rear.setColor(Color.BLACK);
    	rear.fillOval(x, y, 10, 10);
    	front.drawImage(rearBuffer, insets.left, insets.top, this);
    } 

}
