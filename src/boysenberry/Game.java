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
	private DrawContext drawContext = DrawContext.get();
	
	/**
	 * Listen for user input.
	 */
	private UserInputHandler input;
	
	/*
	 * Dummy variables used for testing.
	 */
	private int x = drawContext.getWidth() / 2;
	private int y = drawContext.getHeight() / 2;
	
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
		setSize(drawContext.getWidth(), drawContext.getHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		insets = getInsets();
		setSize(insets.left + drawContext.getWidth() + insets.right,
				insets.top + drawContext.getHeight() + insets.bottom);
		
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
    	
    	Graphics rear = drawContext.getGraphics();
    	rear.setColor(Color.WHITE);
    	rear.fillRect(0, 0, drawContext.getWidth(), drawContext.getHeight());
    	
    	rear.setColor(Color.BLACK);
    	rear.fillOval(x, y, 10, 10);
    	front.drawImage(drawContext, insets.left, insets.top, this);
    } 

}
