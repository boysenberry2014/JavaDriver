package boysenberry.enemy;

import java.io.File;

import boysenberry.IGame;

public class Bomb extends FallingEnemy {
	
	public Bomb(IGame game, int x, int y) {
		super(game, 5, 0, 10, new File("lib/img/bomb.png"));
		setX(x);
		setY(y);
	}
}