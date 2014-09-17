package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;
import boysenberry.IPlayer;

/**
 * Flies horizontally and throws bombs.
 */
public class Scout extends Enemy {
	
	/**
	 * The scout's direction. True for right, false for left. 
	 */
	//TODO: A direction class!
	private boolean direction;
	
	/**
	 * Create a new bomber.
	 * 
	 * @param game The game context.
	 */
	public Scout(IGame game, boolean direction) {
		super(game, 0, game.getRNG().nextInt(game.getHeight()),
				(direction ? 3 : -3), new File(direction ? "lib/img/enemy2.png" : "lib/img/enemy2_backwards.png"));
		setX(direction ? -getWidth() : game.getWidth());
		this.direction = direction;
	}

	/**
	 * Update the bomber.
	 */
	@Override
	public void update() {
		//TODO: Put these in enemy manager class!
		if (direction) {
			if (getX() > getContext().getWidth()) {
				setGarbage(true);
			}
		} else {
			if (getX() + getWidth() < 0) {
				setGarbage(true);
			}
		}
		////////////////////////////////////////
		
		setX(getX() + getSpeed());
		if (direction) {
			if (getX() > getWidth() * 2) {
				setSpeed(getSpeed() + 1);
			}
		} else {
			if (getX() < getContext().getWidth() - getWidth() * 2) {
				setSpeed(getSpeed() - 1);
			}
		}
	}

	/**
	 * Check for collision with the player.
	 * 
	 * @param player
	 *            The player we are eventually colliding with.
	 * 
	 * @return Is there a collision?
	 */
	@Override
	public boolean checkCollision(IPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

}
