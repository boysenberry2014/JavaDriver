package boysenberry;

import java.awt.image.BufferedImage;

/**
 * @author      Todor Samardzhiev <tdsamardzhiev89@gmail.com>
 * @version     0.1
 * @since       2014-09-16
 */
public class DrawContext extends BufferedImage {
	/**
	 * The static object.
	 */
	private static DrawContext _instance;
	
	/**
	 * Game window width.
	 */
	public static int windowWidth = 600;
	
	/**
	 * Game window height.
	 */
	public static int windowHeight = 600;
	
	/**
	 * Private constructor to disable instantiation.
	 */
	private DrawContext() {
		super(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Cloning is disabled because it breaks the singleton pattern.
	 * 
	 * @throws CloneNotSupportedException  
	 */
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Get the synchronized global BufferedImage instance.
	 * 
	 * @return the draw context's buffer.
	 */
	public static synchronized DrawContext get()
	{
	    if (_instance == null) {
	        _instance = new DrawContext();
	    }
	    
	    return _instance;
	}
}
