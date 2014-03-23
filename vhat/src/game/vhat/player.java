package game.vhat;
/*
 * A class to manage the user.
 * This is specifically the player user and not anything else
 */

import org.newdawn.slick.*;

public class player {
	
	private float x, y;
	private Image img;
	private SpriteSheet sprite;
	private location location;
	private String items [] = new String [3];
	private int height, width;
	
	private int u=0, d=0, l=0, r=0;

	// construct
	public player(float xPos, float yPos, String image, location loc) throws SlickException{
		x = xPos;
		y = yPos;
		img = new Image(image);				// Creates an image of the player that will be used in the main class 
		location = loc;						// The location/map that henry is currently on	
		height = img.getHeight();
		width = img.getWidth();
	}
	
	public player(float xPos, float yPos, String image, int sx, int sy, location loc) throws SlickException{
		x = xPos;
		y = yPos;
		sprite = new SpriteSheet(image, sx, sy);				 
		location = loc;	
		height = sy;
		width = sx;
	}
	
	/*
	 *  A modified draw method that uses the slick2d draw method because the main class was being a jerk.
	 *  Usage: Ex. 
	 *  	henry.draw(0, 0, 1);
	 *  
	 *  Post: The player is drawn at the updated position.
	 */
	public void draw(float x, float y, float scale) throws SlickException{
		img.draw(x,y,scale);				
	}
	
	
	public void draw(int sx, int sy, float x, float y, float scale) throws SlickException{
		sprite.getSprite(sx, sy).draw(x,y,scale);				
	}
	
	public int get_height() throws SlickException{
		return height;
	}
	
	public int get_width() throws SlickException{
		return width;
	}
	
	// Returns the x coordinate of henry
	public float get_x() throws SlickException{
		return x;
	}
	
	// Returns the x coordinate of henry
	public float get_y() throws SlickException{
		return y;
	}
	
	// Changes the y coordinate of henry based on speed
	public void ch_y(int speed) throws SlickException{
		y += speed;
	}

	// Changes the y coordinate of henry based on speed
	public void ch_x(int speed) throws SlickException{
		x += speed;
	}
	
	public void new_x(int newX) throws SlickException{
		x = newX;
	}
	
	public void new_y(int newY) throws SlickException{
		y = newY;
	}
	
	public location get_loc() throws SlickException{
		return location;
	}
	
	public void set_loc(location l) throws SlickException{
		location = l;
	}
	
	// Returns the item in the array item given an index
	public String get_items(int index) throws SlickException{
		if (index < 3){				// Makes sure that the index given does not exceed the number of elements in the 
			return items[index];	// array items.
		}else{
			return "error";
		}
	}
	
	/*
	 * A general method that allows henry to interact with the environment
	 */
	public void interact(girl gr) throws SlickException{
		//TODO: add method
		gr.set_words_bulk("You tried to do something", "", "");
	}
	
}

