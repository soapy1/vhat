/*
 * A class to manage the user.
 */

import org.newdawn.slick.*;

public class player {
	
	private float x, y;
	private Image img;
	private String items [] = new String [3];

	// construct
	public player(float xPos, float yPos, String image) throws SlickException{
		x = xPos;
		y = yPos;
		img = new Image(image);				// Creates an image of the player that will be used in the main class 
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
	
	// Returns the item in the array item given an index
	public String get_items(int index) throws SlickException{
		if (index < 3){				// Makes sure that the index given does not exceed the number of elements in the 
			return items[index];	// array items.
		}else{
			return "error";
		}
	}

		
	}

