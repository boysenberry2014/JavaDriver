package boysenberry;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Represents the player object.
 */
public class Player implements IPlayer {
	
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
	 * The player's image.
	 */
	private BufferedImage image;
	
	/**
	 * The player's starting HP.
	 */
	private int hitPoints = 3;
	
	/**
	 * Time of the last hit.
	 */
	private Instant lastHitTime;
	
	List<Bullet> bullets;
	
	/**
	 * 
	 * @param context
	 *            The game context
	 * @param x
	 *            The starting x coordinate.
	 * @param y
	 *            The starting y coordinate.
	 * @param speed
	 *            The player's movement speed.
	 */
	public Player(IGame context, int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.context = context;
		this.lastHitTime = Instant.EPOCH;
		this.bullets = new ArrayList<Bullet>();
		
		File imgFile = new File("lib/img/player.png");
		try {
			this.image = ImageIO.read(imgFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't load image " + imgFile.getName());
			System.exit(1);
		}
		context.addGameObject(this);
	}

	/**
	 * Draw the player object.
	 */
	@Override
	public void draw() {
		Graphics graphics = context.getRearBuffer().getGraphics();
		graphics.drawImage(image, x, y, null);
		
		for (Bullet b : bullets) {
			b.draw();
		}

	}

	/**
	 * Update the player object's status and coordinates.
	 */
	@Override
	public void update() {
		UserInputHandler input = context.getUserInputHandler();
		if (input.isKeyDown(KeyEvent.VK_RIGHT) && x + getWidth() < context.getWidth()) {
			x += speed;
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT) && x > 0) {
			x -= speed;
		}
		if (input.isKeyDown(KeyEvent.VK_UP) && y > 0) {
			y -= speed;
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN) && y + getHeight() < context.getHeight()) {
			y += speed;
		}
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			fireBullet();
		}
		
		for (int i = 0; i < bullets.size(); ++i) {
			if (bullets.get(i).getGarbage()) {
				bullets.remove(bullets.get(i));
			} else {
				bullets.get(i).update();
			}
		}
	}

	private void fireBullet() {
//		if (Instant.now().getEpochSecond() > lastBulletFired.getEpochSecond()) {
			bullets.add(new Bullet(context, getX() + (getWidth())/ 2, getY()));
//			lastBulletFired = Instant.now();
//		}
		
	}
	
	
	
	/**
	 * Get the player's x coordinate.
	 * 
	 * @return The player's x coordinate.
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * Get the player's y coordinate.
	 * 
	 * @return The player's y coordinate.
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * Get the player's width coordinate.
	 * 
	 * @return The player's width coordinate.
	 */
	@Override
	public int getWidth() {
		return image.getWidth();
	}

	/**
	 * Get the player's height coordinate.
	 * 
	 * @return The player's height coordinate.
	 */
	@Override
	public int getHeight() {
		return image.getHeight();
	}

	/**
	 * When hit, player loses hp and becomes immortal for 3 sec.
	 */
	@Override
	public void hit() {
		if (Instant.now().getEpochSecond() - lastHitTime.getEpochSecond() > 3) {
			hitPoints -= 1;
			lastHitTime = Instant.now();
		}
	}

	/**
	 * Get the player's hit points.
	 */
	public int getHP() {
		return hitPoints;
	}

	@Override
	public List<Bullet> getBullets() {
		return bullets;
	}
}
