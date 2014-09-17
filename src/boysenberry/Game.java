package boysenberry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import boysenberry.enemy.Bomber;
import boysenberry.enemy.IEnemy;

/**
 * The main class for the game.
 * 
 * @author Todor Samardzhiev <tdsamardzhiev89@gmail.com>
 * @author Yanko Alexandrov <superactro@gmail.com>
 * @version 0.1
 * @since 2014-09-15
 */
@SuppressWarnings("serial")
public class Game extends JFrame implements IGame {

	/**
	 * Game window width.
	 */
	public static int windowWidth = 600;

	/**
	 * Game window height.
	 */
	public static int windowHeight = 600;

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
	private BufferedImage rearBuffer = new BufferedImage(windowWidth,
			windowHeight, BufferedImage.TYPE_INT_RGB);

	/**
	 * Helper for handling user input.
	 */
	private UserInputHandler handler;

	/**
	 * Game's random generator.
	 */
	private Random random;
	
	/**
	 * Container for all the game's objects.
	 */
	private List<IGameObject> gameObjects;
	
	/**
	 * Enemies also need to add themselves here.
	 */
	private List<IEnemy> enemies;

	/**
	 * Initialize everything needed to run.
	 */
	public Game() {
		setTitle("Space Shooter");
		setSize(rearBuffer.getWidth(), rearBuffer.getHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		insets = getInsets();
		setSize(insets.left + rearBuffer.getWidth() + insets.right, insets.top
				+ rearBuffer.getHeight() + insets.bottom);

		handler = new UserInputHandler(this);
		random = new Random();
		
		gameObjects = new ArrayList<IGameObject>();
		new Player(this, rearBuffer.getWidth() / 2, rearBuffer.getHeight() / 2,
				5);
		
		enemies = new ArrayList<IEnemy>();
	}

	/**
	 * Get the random number generator.
	 * 
	 * @return The game's Random object.
	 */
	public Random getRNG() {
		return random;
	}
	
	/**
	 * Add object to Game's container of gameObjects
	 * 
	 * @param o
	 *            The game object to be added to the list.
	 */
	@Override
	public void addGameObject(IGameObject o) {
		if (gameObjects.contains(o)) {
			throw new IllegalArgumentException("Cannot add object twice!");
		}
		
		gameObjects.add(o);
	}
	
	/**
	 * Add object to Game's container of gameObjects
	 * 
	 * @param o
	 *            The game object to be added to the list.
	 */
	@Override
	public void removeGameObject(IGameObject o) {
		if (!gameObjects.contains(o))
			throw new IllegalArgumentException("Cannot remove nonexistant object.");
		gameObjects.remove(o);
	}

	/**
	 * Get the rear buffer's graphics context. Game objects should use this
	 * context to draw!
	 * 
	 * @return The rear buffer.
	 */
	@Override
	public BufferedImage getRearBuffer() {
		return rearBuffer;
	}

	/**
	 * Get the game's global UserInputHandler.
	 * 
	 * @return The game's UserInputHandler object.
	 */
	@Override
	public UserInputHandler getUserInputHandler() {
		return handler;
	}

	/**
	 * This method actually runs the game.
	 */
	@Override
	public void run() {
		while (!done) {
			long time = System.currentTimeMillis();
			update();
			draw();
			syncFrames(time);

		}

		setVisible(false);
	}

	/**
	 * Synchronize frames.
	 * 
	 * @param time
	 *            The length of this frame.
	 */
	void syncFrames(long time) {
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
	
	// TODO: Use separate class to manage enemies!!!
	private void checkAddBomber() {
		if (random.nextInt(60) == 1) {
			new Bomber(this);
		}
	}
	
	/**
	 * Check user input, object movement and other events.
	 */
	@Override
	public void update() {
		checkAddBomber();
		
		for (IGameObject o : gameObjects) {
			o.update();
		}
	}

	/**
	 * Draw everything.
	 */
	@Override
	public void draw() {
		Graphics rear = rearBuffer.getGraphics();
		rear.setColor(Color.BLACK);
		rear.fillRect(0, 0, rearBuffer.getWidth(), rearBuffer.getHeight());

		for (IGameObject o : gameObjects) {
			o.draw();
		}

		getGraphics().drawImage(rearBuffer, insets.left, insets.top, this);
	}

	/**
	 * Main method for the game.
	 *
	 * @param args
	 *            Command line arguments (none used).
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

	/**
	 * Add enemy to the game.
	 * 
	 * @param e Enemy to add.
	 */
	@Override
	public void addEnemy(IEnemy e) {
		if (enemies.contains(e)) {
			throw new IllegalArgumentException("Cannot add enemy twice!");
		}
		
		enemies.add(e);
		
	}

	/**
	 * Remove enemy from the game.
	 * 
	 * @param e Enemy to remove.
	 */
	@Override
	public void removeEnemy(IEnemy e) {
		if (!enemies.contains(e)) {
			throw new IllegalArgumentException("Cannot remove nonexistant enemy!");
		}
		
		enemies.remove(e);
		
	}

}
