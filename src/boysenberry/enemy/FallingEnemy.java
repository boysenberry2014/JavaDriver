package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;

public abstract class FallingEnemy extends Enemy {

	/**
	 * Represents a falling down enemy object.
	 * 
	 * @param game
	 *            The game context.
	 * @param speed
	 *            The enemy's speed.
	 * @param score
	 *            The score you get for killing it.
	 * @param hitPoints
	 *            The hit points of the enemy.
	 * @param file
	 *            The sprite file.
	 */
	public FallingEnemy(IGame game, int speed, int score, int hitPoints,
			File file) {
		super(game, game.getRNG().nextInt(game.getWidth()), 0, speed, score,
				hitPoints, file);
		setY(-getHeight());

	}

	/**
	 * Update the enemy object.
	 */
	@Override
	public void update() {
		super.update();
		setY(getY() + getSpeed());
	}

}
