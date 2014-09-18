package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;

/**
 * Flies horizontally and throws bombs.
 */
public class Bomber extends Enemy {
	
	/**
	 * When value gets to max, drop a bomb.
	 */
	private int bombCounter = 0;
	
	/**
	 * The max bombCounter value.
	 */
	private int bombCounterMax = 100;
	
	/**
	 * Create a new bomber.
	 * 
	 * @param game The game context.
	 */
	public Bomber(IGame game) {
		super(game, 0, game.getRNG().nextInt(game.getHeight() / 2), 
				2, 4, 50, new File("lib/img/enemy1.png"));
		setX(-getWidth());
	}

	/**
	 * Update the bomber.
	 */
	@Override
	public void update() {
		super.update();
		
		setX(getX() + getSpeed());
		
		if (getX() > getContext().getWidth()) {
			setGarbage(true);
		}
		
		if (bombCounter == bombCounterMax) {
			dropBomb();
			bombCounter = 0;
		} else {
			bombCounter++;
		}
	}
	
	/**
	 * Drop a bomb.
	 */
	private void dropBomb() {
		new Bomb(getContext(), getX() + getWidth() / 2, getY() + getHeight());
	}

}
