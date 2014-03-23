package game.vhat;
/*
 * A class to manage the enemies.
 * Basically the player + AI
 */

import org.newdawn.slick.*;

public class enemy extends player{
	
	public enemy(float xPos, float yPos, String image, location loc) throws SlickException{
		super(xPos, yPos, image, loc);
	}

}
