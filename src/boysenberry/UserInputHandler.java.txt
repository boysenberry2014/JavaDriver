package boysenberry;

import java.awt.Component;
import java.awt.event.*;

/**
 * Helper to check whether a button is pressed.
 */
public class UserInputHandler implements KeyListener {

	/**
	 * Number of keys to handle.
	 */
	private static int keysCount = 256;

	/**
	 * Array for the keys' states.
	 */
	private boolean keys[];

	/**
	 * Initialize the UserInputHandler.
	 * 
	 * @param c
	 *            Item to get input from.
	 */

	public UserInputHandler(Component c) {
		keys = new boolean[keysCount];
		c.addKeyListener(this);
	}

	/**
	 * Check whether a key is pressed.
	 * 
	 * @param key
	 *            Index of the key to check for.
	 * 
	 * @return Is the key pressed or not
	 */

	public boolean isKeyDown(int key) {
		validateKey(key);
		return keys[key];
	}

	/**
	 * Indicate a key press.
	 * 
	 * @param e
	 *            Event representing a key press.
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		validateKey(key);
		keys[key] = true;
	}

	/**
	 * Indicate a key release.
	 * 
	 * @param e
	 *            Event representing a key release.
	 */

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		validateKey(key);
		keys[key] = false;
	}

	/**
	 * Empty method.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	};

	/**
	 * Throw an exception if the key index is invalid.
	 * 
	 * @param key
	 *            Index of a key.
	 */
	private void validateKey(int key) {
		if (key < 0 || key >= keysCount) {
			throw new IllegalArgumentException(String.format(
					"Key index out of range (0-%d)", keysCount - 1));
		}
	}
}
