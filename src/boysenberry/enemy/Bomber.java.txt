package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;
import boysenberry.IPlayer;

/**
 * Flies horizontally and throws bombs.
 */
public class Bomber extends Enemy {
	
	/**
	 * Create a new bomber.
	 * 
	 * @param game The game context.
	 */
	public Bomber(IGame game) {
		super(game, 0, game.getRNG().nextInt(game.getHeight()), 
				2, new File("lib/img/enemy1.png"));
		setX(-getWidth());
	}

	/**
	 * Update the bomber.
	 */
	@Override
	public void update() {
		setX(getX() + getSpeed());
		
		if (getX() > getContext().getWidth()) {
			setGarbage(true);
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
