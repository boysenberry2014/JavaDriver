package boysenberry.enemy;

import boysenberry.IGameObject;
import boysenberry.IPlayer;

/**
 * Interface for enemy objects.
 */
public interface IEnemy extends IGameObject {

	/**
	 * Check for collision with the player.
	 * 
	 * @param player
	 *            The player we are eventually colliding with.
	 * 
	 * @return Is there a collision?
	 */
	public boolean checkCollision(IPlayer player);

	/**
	 * Get the enemy's movement speed.
	 * 
	 * @return Enemy's movement speed.
	 */
	public int getSpeed();

	/**
	 * Set the enemy's movement speed.
	 * 
	 * @param speed
	 *            Enemy's new movement speed.
	 */
	public void setSpeed(int speed);

}
