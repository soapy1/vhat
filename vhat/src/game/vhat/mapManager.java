package game.vhat;
/*
 * A class to manage maps. Including items/enemies that henry can interact with
 */

import org.newdawn.slick.*;

public class mapManager{

	private int loc;								// Details the map number - because I was not smart and called it location
	private int xSpawn, ySpawn;						// Details the x and y spawn points of henry on the map
	private int xExit, yExit;
	
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
	
	// Resets everything and changes map to the next location
	public void change_loc(player p, location nextLocation) throws SlickException{
		p.set_loc(nextLocation);
	}
}
