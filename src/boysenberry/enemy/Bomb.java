package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;

/**
 * A falling bomb.
 */
public class Bomb extends FallingEnemy {

	/**
	 * Drop it!
	 * 
	 * @param game
	 *            The game context to which the bomb belongs.
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public Bomb(IGame game, int x, int y) {
		super(game, 5, 0, 10, new File("lib/img/bomb.png"));
		setX(x);
		setY(y);
	}
}