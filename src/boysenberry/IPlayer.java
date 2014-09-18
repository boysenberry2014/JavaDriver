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
	
	public List<Bullet> getBullets();
}
