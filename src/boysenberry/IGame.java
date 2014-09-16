package boysenberry;

import java.awt.Graphics;

public interface IGame extends IGameObject {
	/**
	 * Add object to Game's container of gameObjects
	 * 
	 * @param o The game object to be added to the list.
	 */
	public void addGameObject(IGameObject o);
	
	/**
	 * Get the rear buffer's graphics context.
	 * Game objects should use this context to draw!
	 * 
	 * @return The rear buffer's graphics context.
	 */
	public Graphics getRearBufferGraphics();
	
	/**
	 * Get the game's global UserInputHandler.
	 * 
	 * @return The game's UserInputHandler object.
	 */
	public UserInputHandler getUserInputHandler();
	
	// This method actually runs the game.
	
    public void run();
   
	// Check user input, object movement and other events.
    
    @Override
    public void update();
   
    // Draw everything.
    
    @Override
    public void draw();
}
