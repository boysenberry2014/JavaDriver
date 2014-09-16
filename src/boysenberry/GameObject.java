package boysenberry;

/**
 * @author      Todor Samardzhiev <tdsamardzhiev89@gmail.com>
 * @version     0.1
 * @since       2014-09-16
 */
public interface GameObject {
	/**
	 * Draw the game object on screen.
	 */
	void draw();
	
	/**
	 * Update the game object.
	 */
	void update();
}
