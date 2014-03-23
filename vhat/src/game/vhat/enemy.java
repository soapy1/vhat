package game.vhat;
/*
 * A class to manage the enemies.
 * Basically the player + AI
 */

import org.newdawn.slick.*;

public class enemy extends player{
	
	private enum dirmap{
		up,
		down,
		left,
		right
	}
	private dirmap dir;
	
	public enemy(float xPos, float yPos, String image, location loc) throws SlickException{
		super(xPos, yPos, image, loc);
	}
	
	public void choosedir() throws SlickException{
		int r = (int)(Math.random()*4);
		if (r==0){
			dir = dirmap.down;
		}else if (r==1){
			dir = dirmap.left;
		}else if (r==2){
			dir = dirmap.right;
		}else{
			dir = dirmap.up;
		}
	}
	
	public void moveon() throws SlickException{
		int speed = 1;
		if (dir == dirmap.down){
			ch_y(speed);
		}else if (dir == dirmap.up){
			ch_y(-speed);
		}else if (dir == dirmap.left){
			ch_x(-speed);
		}else if (dir == dirmap.right){
			ch_x(speed);
		}
	}
	
	public void makeInBound(mapManager map) throws SlickException{
		int speed = 4;
		// For keeping henry in the screen
		if (get_y() < 0){												// Upper boundary
			ch_y(speed);
			choosedir();
			}
		if (get_y() > (map.getHeight()*map.getTileHeight() - 64)){	// Lower boundary
			ch_y(-speed);
			choosedir();
			}
		if (get_x() < 0){												// Left-most boundary
			ch_x(speed);
			choosedir();
			}
		if (get_x() > (map.getWidth()*map.getTileWidth() - 32)){		// Right-most boundary
			ch_x(-speed);
			choosedir();
			}
		
		// Stupid thing for keeping henry in the path 
		int top = (int)get_y()+24;						//|
		int bottom = (int)get_y()+get_height()+24;		//| Defines henry's 
		int left = (int)get_x()+4;						//| hit box
		int right = (int)get_x()+get_width()-4;		//|
		int t = 0;
		try{
			t = map.getTileId(((int)((left+right)/2)/32),(int)(((top+bottom)/2)/32), 0);	// Tiled id at center of hit box
			if (t >= 3 && t <= 10){		// If the center of the hit box has a tile id that corresponds to a wall tile, adjust
				if (map.getTileId(((int)((left+right)/2)/32), (int)(top/32), 0) <= 2 ||				// Top tile is not wall
						map.getTileId(((int)((left+right)/2)/32), (int)(top/32), 0) >= 11){
					ch_y(-2);
					choosedir();
				}else if (map.getTileId(((int)((left+right)/2)/32), (int)(bottom/32), 0) <=2 ||		// Bottom tile is not a wall
						map.getTileId(((int)((left+right)/2)/32), (int)(bottom/32), 0) >= 11){
					ch_y(2);
					choosedir();
				}else if (map.getTileId((int)(left/32), (int)((top+bottom)/2)/32, 0) <= 2 ||		// Leftmost tile " " " "
						map.getTileId((int)(left/32), (int)((top+bottom)/2)/32, 0) >= 11){
					ch_x(-2);
					choosedir();
				}else if (map.getTileId((int)(right/32), (int)((top+bottom)/2)/32, 0) <= 2 ||		// Rightmost tile " " " "
						map.getTileId((int)(right/32), (int)((top+bottom)/2)/32, 0) >= 11){
					ch_x(2);
					choosedir();
				}
			}
		}catch (ArrayIndexOutOfBoundsException e){				// So that it does not goof up
			e.printStackTrace();
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}

}
