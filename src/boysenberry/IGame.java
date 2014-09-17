package boysenberry;

import java.awt.image.BufferedImage;
import java.util.Random;

import boysenberry.enemy.IEnemy;

/**
 * Base object representing the game itself.
 */
public interface IGame extends IGameObject {
	
	/**
	 * Add object to Game's container of gameObjects
	 * 
	 * @param o
	 *            The game object to be added to the list.
	 */
	public void addGameObject(IGameObject o);

	/**
	 * Get the rear buffer's graphics context. Game objects should use this
	 * context to draw!
	 * 
	 * @return The rear buffer.
	 */
	public BufferedImage getRearBuffer();

	/**
	 * Get the game's global UserInputHandler.
	 * 
	 * @return The game's UserInputHandler object.
	 */
	public UserInputHandler getUserInputHandler();

	/**
	 * Get the random number generator.
	 * 
	 * @return The game's Random object.
	 */
	public Random getRNG();
	
	/**
	 * Run the game.
	 */
	void run();

	/**
	 * Add game object to the game's container.
	 * 
	 * @param o
	 */
	void removeGameObject(IGameObject o);
	
	/**
	 * Add enemy to the game.
	 * 
	 * @param e Enemy to add.
	 */
	void addEnemy(IEnemy e);
	
	/**
	 * Remove enemy from the game.
	 * 
	 * @param e Enemy to remove.
	 */
	void removeEnemy(IEnemy e);
}
