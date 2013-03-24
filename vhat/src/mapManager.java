/*
 * A class to manage maps.
 */

import org.newdawn.slick.*;

public class mapManager{

	private int loc;								// Details the map number - because I was not smart and called it location
	private int xSpawn, ySpawn;						// Details the x and y spawn points of henry on the map

	
	// construct
	public mapManager(int locationNum, int spawnPointX, int spawnPointY) throws SlickException{
		loc = locationNum;
		xSpawn = spawnPointX;
		ySpawn = spawnPointY;	
	}
	
	public int get_loc(){
		return loc;
	}
	
	public int get_xSpawn(){
		return xSpawn;
	}
	
	public int get_ySpawn(){
		return ySpawn;
	}	
}
