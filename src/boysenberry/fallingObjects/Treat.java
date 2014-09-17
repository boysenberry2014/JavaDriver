package boysenberry.fallingObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import boysenberry.IGame;

public class Treat extends FallingObject {
	
	private BufferedImage image;
	private boolean isGarbage;

	public Treat(IGame game, int x, int y, int speed, int size, File file) {
		super(game, x, y, speed, size);
		
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't load image " + file.getName());
		}
		
		context.addGameObject(this);
	}
	
	private void destroy() {
        this.isGarbage = true;
    }
    
    public Boolean shouldSpawn(int frequency)
    {
        Random rand = new Random();
        if (rand.nextInt(100 - frequency) < 5)
        {
            return true;
        }
        return false;
    }

    @Override
    public void registerCollision() {
        this.destroy();
    }	
    
    @Override
   	public void draw() {    	
   		super.draw(this.image);
   	}
}
