/*
 * A class to manage maps.
 */

import org.newdawn.slick.*;

// WARNING: Useless code below

public class mapManager {

	private int loc;								// Details the map number - because I was not smart and called it location
	private int xSpawn, ySpawn;						// Details the x and y spawn points of henry on the map
	private int[][] changeLoc = new int [4][2];		// Details where you can change location
	
	public mapManager(int locationNum, int spawnPointX, int spawnPointY, int[][] changeLocation){
		loc = locationNum;
		xSpawn = spawnPointX;
		ySpawn = spawnPointY;
		changeLoc = changeLocation;
		
	}
}
