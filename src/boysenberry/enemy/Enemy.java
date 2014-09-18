package boysenberry.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import boysenberry.IGame;
import boysenberry.IGameObject;

public abstract class Enemy implements IEnemy {
	/**
	 * Enemy's x coordinate.
	 */
	private int x;

	/**
	 * Enemy's y coordinate.
	 */
	private int y;

	/**
	 * Enemy's speed.
	 */
	private int speed;

	/**
	 * Enemy's picture.
	 */
	private BufferedImage image;

	/**
	 * The game context.
	 */
	private IGame context;
	
	/**
	 * Schedule item for removal.
	 */
	private boolean garbage;

	/**
	 * The score you get for killing this.
	 */
	private int score;

	/**
	 * The hitpoints of this enemy.
	 */
	private int hitPoints;

	/**
	 * Abstract enemy's constructor.
	 * 
	 * @param game
	 *            The game context.
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param speed
	 *            The speed.
	 * 
	 * @param score
	 *            What pts you get for killing this.
	 * 
	 * @param hitPoints
	 *            How hard is this to kill.
	 * 
	 * @param file
	 *            The picture avatar.
	 */
	public Enemy(IGame game, int x, int y, int speed, int score, int hitPoints, File file) {
		this.context = game;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.score = score;
		this.hitPoints = hitPoints;
		
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't load image " + file.getName());
		}
		context.addEnemy(this);
	}

	/**
	 * Get this enemy's game context.
	 * 
	 * @return The enemy's context.
	 */
	public IGame getContext() {
		return context;
	}

	/**
	 * Set x position.
	 * 
	 * @param x
	 *            New x position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set y position.
	 * 
	 * @param y
	 *            New y position.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Draw the enemy image.
	 */
	@Override
	public void draw() {
		Graphics graphics = context.getRearBuffer().getGraphics();
		graphics.drawImage(image, x, y, null);
	}

	/**
	 * Check if the enemy is dead.
	 * <p>
	 * Call this in child classes.
	 */
	@Override
	public void update() {
		if (hitPoints < 1) {
			setGarbage(true);
		}
	}

	/**
	 * Check for collision with a player.
	 * <p>
	 * Checks for 2 rectangles collision. If you need another kind of collision,
	 * override this method in child class.
	 * 
	 * @param o
	 *            The player.
	 * @return Is there a collision?
	 */
	@Override
	public boolean applyCollision(IGameObject o) {
		Rectangle rect = new Rectangle(x, y, image.getWidth(),
				image.getHeight());
		Rectangle otherRect = new Rectangle(o.getX(), o.getY(),
				o.getWidth(), o.getHeight());

		if (rect.intersects(otherRect)) {
			score += 1;
			hitPoints -= 1;
			return true;
		}
		
		return false;
	}

	/**
	 * Return enemy's x coordinate.
	 * 
	 * @return The x coordinate.
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * Return enemy's y coordinate.
	 * 
	 * @return The y coordinate.
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * Return enemy's width.
	 * 
	 * @return The enemy's width.
	 */
	@Override
	public int getWidth() {
		return image.getWidth();
	}

	/**
	 * Return enemy's height.
	 * 
	 * @return The enemy's height.
	 */
	@Override
	public int getHeight() {
		return image.getHeight();
	}

	/**
	 * Get enemy's speed.
	 * 
	 * @return The enemy's speed.
	 */
	@Override
	public int getSpeed() {
		return speed;
	}

	/**
	 * Set enemy's speed.
	 * 
	 * @param speed
	 *            The enemy's speed.
	 */
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;

	}
	
	/**
	 * Check if the item has done its job and is ready for removal.
	 * 
	 * @return True if the item shoud be removed, False otherwise.
	 */
	public boolean getGarbage() {
		return garbage;
	}

	/**
	 * Set whether the item should be removed.
	 * 
	 * @param Is item for removal?
	 */
	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}
	
	/**
	 * Get the score for killing an enemy.
	 * 
	 * @return Score for killing the enemy.
	 */
	public int getScore() {
		return score;
	}

}
