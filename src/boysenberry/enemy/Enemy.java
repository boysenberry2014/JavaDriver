package boysenberry.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import boysenberry.IGame;
import boysenberry.IPlayer;

public class Enemy implements IEnemy {
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
	 * @param ImageIO.read(new File("lib/img/player.png"))
	 *            The picture avatar.
	 */
	public Enemy(IGame game, int x, int y, int speed, File file) {
		this.context = game;
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't load image " + file.getName());
		}
		context.addGameObject(this);
		context.addEnemy(this);
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
	 * Do nothing.
	 * <p>
	 * Override this in child classes.
	 */
	@Override
	public void update() {
	}

	/**
	 * Check for collision with a player.
	 * <p>
	 * Checks for 2 rectangles collision. If you need another kind of collision,
	 * override this method in child class.
	 * 
	 * @param player
	 *            The player.
	 * @return Is there a collision?
	 */
	@Override
	public boolean checkCollision(IPlayer player) {
		Rectangle rect = new Rectangle(x, y, image.getWidth(),
				image.getHeight());
		Rectangle playerRect = new Rectangle(player.getX(), player.getY(),
				player.getWidth(), player.getHeight());

		return rect.intersects(playerRect);
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

}
