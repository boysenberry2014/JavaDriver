package boysenberry.enemy;

import java.io.File;
import java.util.Random;

import boysenberry.IGame;

/**
 * Represents a falling asteroid.
 */
public class Asteroid extends FallingEnemy {

	/**
	 * Path to asteroid images.
	 */
	private static String asteroidsPath = "lib/img/";
	
	/**
	 * Array of asteroid image filenames.
	 */
	private static String[] asteroidsFiles = new String[] { "asteroid1.png",
			"asteroid2.png", "asteroid3.png", "asteroid4.png" };

	/**
	 * Pick a random file for the asteroid's sprite.
	 * 
	 * @param random
	 *            A random number generator.
	 * @return Path + Filename
	 */
	private static String randomFile(Random random) {
		return asteroidsPath
				+ asteroidsFiles[random.nextInt(asteroidsFiles.length)];
	}

	/**
	 * Instantiate a new asteroid.
	 * 
	 * @param game
	 *            The game context.
	 */
	public Asteroid(IGame game) {
		super(game, game.getRNG().nextInt(5) + 1, 0, 9001, new File(
				randomFile(game.getRNG())));

	}
}
