package boysenberry;

/**
 * Base class for all of the game's objects.
 */
public interface IGameObject {

	/**
	 * Get the object's x coordinate.
	 * 
	 * @return The object's x coordinate.
	 */
	public int getX();
	
	/**
	 * Get the object's y coordinate
	 * 
	 * @return The object's y coordinate.
	 */
	public int getY();
	
	/**
	 * Get the object's width.
	 * 
	 * @return The object's width.
	 */
	public int getWidth();
	
	/**
	 * Get the object's height.
	 * 
	 * @return The object's height.
	 */
	public int getHeight();
	
	/**
	 * Draw the object.
	 */
	public void draw();

	/**
	 * Update the object.
	 */
	public void update();

}
