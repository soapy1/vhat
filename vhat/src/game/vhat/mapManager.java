package game.vhat;
/*
 * A class to manage maps. Including items/enemies that henry can interact with
 */

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
/*
 * TODO: make this inherit from Tiled Map and then make the appropriate changes to vhat
 */

public class mapManager extends TiledMap{

	private location loc;								// Details the map number - because I was not smart and called it location
	private int xSpawn, ySpawn;						// Details the x and y spawn points of henry on the map

	public mapManager(String map) throws SlickException{
		super(map); 
	}
	
	// construct
	public mapManager(String map, location locationNum, int spawnPointX, int spawnPointY) throws SlickException{
		super(map);
		loc = locationNum;
		xSpawn = spawnPointX;
		ySpawn = spawnPointY;	
	}
	
	public location get_loc(){
		return loc;
	}
	
	public int get_xSpawn(){
		return xSpawn;
	}
	
	public int get_ySpawn(){
		return ySpawn;
	}	
	
	public int getForwardX(){
		return getObjectX(0, 0);
	}
	
	public int getForwardY(){
		return getObjectY(0,0);
	}
	
	public int getBackwardX(){
		return getObjectX(0, 1);
	}
	
	public int getBackwardY(){
		return getObjectY(0,1);
	}
	
	// Resets everything and changes map to the next location
	public void change_loc(player p, location nextLocation) throws SlickException{
		p.set_loc(nextLocation);
	}
}
