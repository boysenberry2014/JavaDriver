package boysenberry;

import java.util.List;

/**
 * Base class for player objects.
 */
public interface IPlayer extends IGameObject {
	
	/**
	 * Hit the player.
	 */
	public void hit();
	
	/**
	 * Get the player's hit points.
	 */
	public int getHP();
	
	/**
	 * Get the player's active bullet objects.
	 *
	 * @return List of active ally bullets.
	 */
	public List<Bullet> getBullets();
}
