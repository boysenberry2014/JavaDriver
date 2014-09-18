package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;

public abstract class FallingEnemy extends Enemy {

	public FallingEnemy(IGame game, int speed, int score, int hitPoints, File file) {
		super(game, game.getRNG().nextInt(game.getWidth()), 0, 
				speed, score, hitPoints, file);
		setY(-getHeight());
		
	}
	
	@Override
	public void update() {
		super.update();
		setY(getY() + getSpeed());	
	}
	
}
