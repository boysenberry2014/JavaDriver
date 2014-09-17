package boysenberry.fallingObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import boysenberry.IGame;

public class FallingObject implements IFallingObject{
	private int speed;
	private BufferedImage image;
	
	protected int x;
    protected int y;
    
    private int width;
    private int height;
    
    private int size;
    
    protected IGame context;
	    
    public FallingObject(IGame game, int x, int y, int speed, int size) {    
    	this.context = game;
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
    }
    
    @Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}


	@Override
	public void registerCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.y += this.speed;		
		this.draw();
	}
	
	@Override
	public void draw() {
		Graphics graphics = context.getRearBuffer().getGraphics();
		graphics.drawImage(image, x, y, null);		
	}
}
