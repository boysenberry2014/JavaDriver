package boysenberry.enemy;

import java.io.File;
import java.util.Random;

import boysenberry.IGame;

public class Asteroid extends FallingEnemy {

	private static String asteroidsPath = "lib/img/";
	private static String[] asteroidsFiles = new String[] {
		"asteroid1.png",
		"asteroid2.png",
		"asteroid3.png",
		"asteroid4.png"
	};
	
	private static String randomFile(Random random) {
		return asteroidsPath + asteroidsFiles[random.nextInt(asteroidsFiles.length)];
	}
	
	public Asteroid(IGame game) {
		super(game, game.getRNG().nextInt(5) + 1, 0, 9001, new File(randomFile(game.getRNG())));
		
	}
}
