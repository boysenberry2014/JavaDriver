package boysenberry;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import javax.swing.JFrame;

import boysenberry.enemy.Asteroid;
import boysenberry.enemy.Bomber;
import boysenberry.enemy.IEnemy;
import boysenberry.enemy.Scout;

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
	 * The game player.
	 */
	private IPlayer player;
	
	/**
	 * Enemies also need to add themselves here.
	 */
	private List<IEnemy> enemies;

	/**
	 * The game score.
	 */
	private int score = 0;
	
	/**
	 * At what score steps do we increase difficulty?
	 */
	private static int scoreThreshold = 100;
	
	/**
	 * The starting difficulty.
	 */
	private static int spawnTimerMax = 140;
	
	/**
	 * The last difficulty.
	 */
	private static int spawnTimerMin = 40;
	
	/**
	 * Lower this to increase difficulty.
	 */
	private int spawnTimer = spawnTimerMax;
	
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
		player = new Player(this, rearBuffer.getWidth() / 2, rearBuffer.getHeight() / 2,
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
	 * Remove object from Game's container of gameObjects
	 * 
	 * @param o
	 *            The game object to be removed from the list.
	 */
	private void removeGameObject(IGameObject o) {
		if (!gameObjects.contains(o))
			throw new IllegalArgumentException("Cannot remove nonexistant object.");
		gameObjects.remove(o);
	}
	
	/**
	 * Remove object from Game's container of gameObjects
	 * 
	 * @param o
	 *            The game object to be removed from the list.
	 */
	private void removeEnemy(IEnemy e) {
		if (!enemies.contains(e))
			throw new IllegalArgumentException("Cannot remove nonexistant object.");
		score += e.getScore();
		enemies.remove(e);
		removeGameObject(e);
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

	/*
	 * TODO: States should be separate classes, but I've got no time
	 * to do it!
	 */
	private void menu() {
		play();
	}
	
	private void play() {
		while (true) {
			long time = System.currentTimeMillis();
			refreshEnemies();
			update();
			draw();
			syncFrames(time);
			
			if (player.getHP() < 1) {
				break;
			}
		}
		
		over();
	}
	
	private void over() {
		// TODO: Refactor this.
		Graphics rear = rearBuffer.getGraphics();
		rear.setColor(Color.BLACK);
		rear.fillRect(0, 0, rearBuffer.getWidth(), rearBuffer.getHeight());

		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 36);
		rear.setFont(font);
		
		rear.setColor(Color.RED);
		String over = "GAME OVER";
		int textX = (getWidth() - (int)rear.getFontMetrics().getStringBounds(over, rear).getWidth()) / 2;
		int textY = (getHeight() - rear.getFontMetrics().getHeight()) / 2;
		rear.drawString(over, textX, textY);
		
		rear.setColor(Color.WHITE);
		over = "Your score: " + score;
		textX = (getWidth() - (int)rear.getFontMetrics().getStringBounds(over, rear).getWidth()) / 2;
		textY += rear.getFontMetrics().getHeight();
		rear.drawString(over, textX, textY);
		
		getGraphics().drawImage(rearBuffer, insets.left, insets.top, this);		
	}
	
	/**
	 * This method actually runs the game.
	 */
	@Override
	public void run() {
		menu();
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
	private void refreshEnemies() {
		
		// Add new enemies
		switch (random.nextInt(spawnTimer)) {
			case 0:
				new Asteroid(this);
				break;
			case 1:
				new Bomber(this);
				break;
			case 2:
				new Scout(this, random.nextBoolean());
				break;
		}
		
		// Remove dead or disappeared enemies.
		for (int i = 0; i < enemies.size(); ++i) {
			if (enemies.get(i).getGarbage())
				removeEnemy(enemies.get(i));
		}
	}
	
	/**
	 * Check user input, object movement and other events.
	 */
	@Override
	public void update() {
		
		for (int i = 0; i < enemies.size(); ++i) {
			if (enemies.get(i).applyCollision(player)) {
				player.hit();
			}
			
			for (int j = 0; j < player.getBullets().size(); ++j) {
				if (enemies.get(i).applyCollision(player.getBullets().get(j))) {
					player.getBullets().get(j).hit();
				}
			}
		}
		
		
		
		for (int i = 0; i < gameObjects.size(); ++i) {
			gameObjects.get(i).update();
		}
		
		
		if (score > (scoreThreshold * (spawnTimerMax - spawnTimer)) 
				&& spawnTimer > spawnTimerMin) {
			spawnTimer -= 1;
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

		rear.setColor(Color.WHITE);
		rear.drawString("Score: " + score, 5, 20);
		rear.drawString("Health: " + player.getHP(), 5, 40);
		
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
		gameObjects.add(e);
		
	}

}
