package boysenberry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Represents the player object.
 */
public class Player implements IGameObject {
	/**
	 * The player's x coordinate.
	 */
	private int x;
	
	/**
	 * The player's y coordinate.
	 */
	private int y;
	
	/**
	 * The player's speed.
	 */
	private int speed;
	
	/**
	 * The game context.
	 */
	private IGame context;
	
	/**
	 * 
	 * @param The game context
	 * @param x The starting x coordinate.
	 * @param y The starting y coordinate.
	 * @param speed The player's movement speed.
	 */
	public Player(IGame context, int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.context = context;
		context.addGameObject(this);
	}

	/**
	 * Draw the player object.
	 */
	@Override
	public void draw() {
		Graphics graphics = context.getRearBufferGraphics();
    	graphics.setColor(Color.BLACK);
    	graphics.fillOval(x, y, 10, 10);

	}

	/**
	 * Update the player object's status and coordinates.
	 */
	@Override
	public void update() {
		UserInputHandler input = context.getUserInputHandler();
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			x += speed;
		}
			if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			x -= speed;
		}
			if (input.isKeyDown(KeyEvent.VK_UP)) {
			y -= speed;
		}
			if (input.isKeyDown(KeyEvent.VK_DOWN)) {
			y += speed;
		}
	}

}
