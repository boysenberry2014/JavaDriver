package boysenberry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * @author Todor Samardzhiev <tdsamardzhiev89@gmail.com>
 * @author Yanko Alexandrov <superactro@gmail.com>
 * 
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
	UserInputHandler handler;

	/**
	 * Container for all the game's objects.
	 */
	private List<IGameObject> gameObjects;

	/**
	 * Initialize everything needed to run.
	 */
	public Game() {
		setTitle("Java Driver 3000");
		setSize(rearBuffer.getWidth(), rearBuffer.getHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		insets = getInsets();
		setSize(insets.left + rearBuffer.getWidth() + insets.right, insets.top
				+ rearBuffer.getHeight() + insets.bottom);

		handler = new UserInputHandler(this);

		gameObjects = new ArrayList<IGameObject>();
		new Player(this, rearBuffer.getWidth() / 2, rearBuffer.getHeight() / 2,
				5);
	}

	/**
	 * Add object to Game's container of gameObjects
	 * 
	 * @param o
	 *            The game object to be added to the list.
	 */
	@Override
	public void addGameObject(IGameObject o) {
		gameObjects.add(o);
	}

	/**
	 * Get the rear buffer's graphics context. Game objects should use this
	 * context to draw!
	 * 
	 * @return The rear buffer's graphics context.
	 */
	@Override
	public Graphics getRearBufferGraphics() {
		return rearBuffer.getGraphics();
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
	
	/**
	 * Check user input, object movement and other events.
	 */
	@Override
	public void update() {
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
		rear.setColor(Color.WHITE);
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

}
