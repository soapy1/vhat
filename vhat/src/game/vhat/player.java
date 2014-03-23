package game.vhat;
/*
 * A class to manage the user.
 * This is specifically the player user and not anything else
 */

import org.newdawn.slick.*;
import game.vhat.vhat;

public class player {
	
	private float x, y;
	private Image img;
	private SpriteSheet sprite;
	private location location;
	private String items [] = new String [3];
	private int height, width;
	private int numChangesOne=1, numChangesTwo=1;
	
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
	
	/* 
	 * Makes sure that henry is in bounds.  If henry is not in bounds, he is moved back.
	 * This method includes keeping henry on the screen and making sure he stays on the path.
	 */ 
	public void makeInBound(mapManager map) throws SlickException{
		int speed = 4;
		// For keeping henry in the screen
		if (get_y() < 0){												// Upper boundary
			ch_y(speed);}
		if (get_y() > (map.getHeight()*map.getTileHeight() - 32)){	// Lower boundary
			ch_y(-speed);}
		if (get_x() < 0){												// Left-most boundary
			ch_x(speed);}
		if (get_x() > (map.getWidth()*map.getTileWidth() - 32)){		// Right-most boundary
			ch_x(-speed);}
		
		// Stupid thing for keeping henry in the path 
		int top = (int)get_y()+24;						//|
		int bottom = (int)get_y()+get_height();		//| Defines henry's 
		int left = (int)get_x()+4;						//| hit box
		int right = (int)get_x()+get_width()-4;		//|
		
		if (left>=map.getForwardX() && right<=map.getForwardX()+64 && top>=map.getForwardY() && bottom<=map.getForwardY()+32){
			updateLocationOne();
			numChangesOne += 1;
		}
		
		if (left>=map.getBackwardX() && right<=map.getBackwardX()+64 && top>=map.getBackwardY() && bottom<=map.getBackwardY()+32){
			updateLocationTwo();
			numChangesTwo += 1;
		}
		
		if (get_loc() == location.hallway){
			return;
		}
		
		int t = map.getTileId(((int)((left+right)/2)/32),(int)(((top+bottom)/2)/32), 0);	// Tiled id at center of hit box
		try{
			if (t >= 3 && t <= 10){		// If the center of the hit box has a tile id that corresponds to a wall tile, adjust
				if (map.getTileId(((int)((left+right)/2)/32), (int)(top/32), 0) <= 2 ||				// Top tile is not wall
						map.getTileId(((int)((left+right)/2)/32), (int)(top/32), 0) >= 11){
					ch_y(-2);
				}else if (map.getTileId(((int)((left+right)/2)/32), (int)(bottom/32), 0) <=2 ||		// Bottom tile is not a wall
						map.getTileId(((int)((left+right)/2)/32), (int)(bottom/32), 0) >= 11){
					ch_y(2);
				}else if (map.getTileId((int)(left/32), (int)((top+bottom)/2)/32, 0) <= 2 ||		// Leftmost tile " " " "
						map.getTileId((int)(left/32), (int)((top+bottom)/2)/32, 0) >= 11){
					ch_x(-2);
				}else if (map.getTileId((int)(right/32), (int)((top+bottom)/2)/32, 0) <= 2 ||		// Rightmost tile " " " "
						map.getTileId((int)(right/32), (int)((top+bottom)/2)/32, 0) >= 11){
					ch_x(2);
				}
			}
		}catch (ArrayIndexOutOfBoundsException e){				// So that it does not goof up
			e.printStackTrace();
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	// First algorithm to choose which random place to put henry
		public void updateLocationOne() throws SlickException{
			double n = (numChangesOne+numChangesTwo+(Math.random()*50+50))/100.0;
			double re = Math.abs((Math.cos(n)/Math.log(n)));
			if (re > 4){
				re = Math.random()*4;
			}
			int r = (int)re;
			System.out.println("re: " + re + " n: " + n);
			System.out.println("numChangesOne: " + numChangesOne + " r: " + r);
			set_loc(location.values()[r]);
			putOnSpawn(get_loc());
		}
		
		// Second random algorithm to choose which random place to put henry
		public void updateLocationTwo() throws SlickException{
			System.out.println("One: " + numChangesOne + " Two: " + numChangesTwo);
			if (numChangesTwo%2 == 1 && (numChangesTwo+numChangesOne)%2 == 0){
				set_loc(location.end);
				putOnSpawn(get_loc());
			}else{
				set_loc(location.values()[(int)(Math.random()*4)]);
				putOnSpawn(get_loc());
			}
		}
		
		public void putOnSpawn(location l) throws SlickException{
			if (get_loc() == location.entrance){				
				new_y(vhat.entrance.get_ySpawn());	
				new_x(vhat.entrance.get_xSpawn());		
			}else if (get_loc() == location.zim){
				new_y(vhat.zim.get_ySpawn());	
				new_x(vhat.zim.get_xSpawn());
			}else if (get_loc() == location.hallway){
				new_y(vhat.hallway.get_ySpawn());	
				new_x(vhat.hallway.get_xSpawn());
			}else if (get_loc() == location.wombo){
				new_y(vhat.wombo.get_ySpawn());	
				new_x(vhat.wombo.get_xSpawn());
			}else if (get_loc() == location.end){
				new_y(vhat.end.get_ySpawn());	
				new_x(vhat.end.get_xSpawn());
			}
		}
	
}

