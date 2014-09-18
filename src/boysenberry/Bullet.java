package boysenberry;

import java.awt.Color;
import java.awt.Graphics;

//TODO: Bullet system needs heavy refactoring!!!
/**
 * Represents the bullet object.
 */
public final class Bullet implements IGameObject {
	
	private static int width = 5;
	
	private static int height = 10;
	
	/**
	 * The bullet's x coordinate.
	 */
	private int x;

	/**
	 * The bullet's y coordinate.
	 */
	private int y;

	/**
	 * The bullet's speed.
	 */
	private int speed = 10;

	/**
	 * The game context.
	 */
	private IGame context;
	
	/**
	 * The bullet's starting HP.
	 */
	private int hitPoints = 1;
	
	private boolean garbage = false;
	
	/**
	 * 
	 * @param context
	 *            The game context
	 * @param x
	 *            The starting x coordinate.
	 * @param y
	 *            The starting y coordinate.
	 */
	public Bullet(IGame context, int x, int y) {
		this.x = x - width/2;
		this.y = y;
		this.context = context;
	}

	/**
	 * Draw the bullet object.
	 */
	@Override
	public void draw() {
		Graphics graphics = context.getRearBuffer().getGraphics();
		graphics.setColor(Color.RED);
		graphics.fillRect(x, y, width, height);;
	}

	/**
	 * Update the bullet object's status and coordinates.
	 */
	@Override
	public void update() {
		y -= speed;
	}

	/**
	 * Get the bullet's x coordinate.
	 * 
	 * @return The bullet's x coordinate.
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * Get the bullet's y coordinate.
	 * 
	 * @return The bullet's y coordinate.
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * Get the bullet's width coordinate.
	 * 
	 * @return The bullet's width coordinate.
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Get the bullet's height coordinate.
	 * 
	 * @return The bullet's height coordinate.
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Get the bullet's hit points.
	 */
	public int getHP() {
		return hitPoints;
	}
	
	/**
	 * Check if the bullet has done its job and is ready for removal.
	 * 
	 * @return True if the bullet should be removed, False otherwise.
	 */
	public boolean getGarbage() {
		return garbage;
	}

	public void hit() {
		hitPoints -= 1;
		if (hitPoints < 1) {
			garbage = true;
		}
		
	}
}
