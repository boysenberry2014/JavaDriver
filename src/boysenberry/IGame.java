package boysenberry;

import java.awt.image.BufferedImage;

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
	 * Run the game.
	 */
	void run();
}
