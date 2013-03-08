/*
 * A class to manage the user.
 */

import org.newdawn.slick.*;

public class player {
	
	float x, y;
	static Image img;
	static String items [] = new String [3];

	// construct
	public player(float x_pos, float y_pos, String image) throws SlickException{
		x = x_pos;							// x position of the player
		y = y_pos;							// x position of the player	
		img = new Image(image);				// Creates an image of the player that will be used in the main class 
	}
	
	/*
	 *  A modified draw method that uses the slick2d draw method because the main class was being a jerk.
	 *  Usage: Ex. 
	 *  	henry.draw(0, 0, 1);
	 *  
	 *  Post: The player is drawn at the updated position.
	 */
	public static void draw(float x, float y, float scale){
		img.draw(x,y,scale);				
	}
	
	// Returns the x position of the player
	public float get_x()throws SlickException{
		return x;
	}
	
	// Returns the y position of the player
	public float get_y() throws SlickException{
		return y;
	}
	
	// Returns the item in the array item given an index
	public static String get_items(int index){
		if (index < 3){				// Makes sure that the index given does not exceed the number of elements in the 
			return items[index];	// array items.
		}else{
			return "error";
		}
	}

		
	}

