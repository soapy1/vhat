/*
 * Programmed By:  BA Media Club
 * Programmed For:  BA Media Club
 * Program Description:  A game... expanding on this laater
 */

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class vhat extends BasicGame{
	
	player henry;					// Reference the player class to create a "henry" player object
	TiledMap mapFirst;				// The map for the first location
	TiledMap mapSecond;				// The map for the second location
	int location = 1;				// Keeps track of which location (map) henry should be on
	int speed = 3;					// How many pixels henry moves 
	static int screen_width = 1200;	
	static int screen_height = 640;

	// Construct calls construct from BasicGame 
	public vhat(){
		super("vhat");
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Initializes everything needed
	
	public void init(GameContainer gc) throws SlickException {
		mapFirst = new TiledMap("res/first.tmx");			// Creates a new "map" object from the TiledMap class (from Slick2D)
		mapSecond = new TiledMap("res/second.tmx");			//		Same as above except with a different map
		henry = new player(600,50, "res/henry.png");		// Creates a new "henry" object from the player class
		}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
//     
//		So it seems like everything is going fine.
//
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods needed for the game to work without too many redundancies... ... there will still be redundancies 
		
	/* 
	 * Makes sure that henry is in bounds.  If henry is not in bounds, he is moved back
	 * Note: All of the maps must be the same size (as mapFirst)... Well as it goes should have planed out how I was going
	 *		 program this... mapFirst is 30x20 tiles (tiles are 32x32 pixels)  
	 */ 
	public void makeInBound() throws SlickException{
		if (henry.get_y() < 0){														// Upper boundary
			henry.ch_y(speed);
		}
		if (henry.get_y() > (mapFirst.getHeight()*mapFirst.getTileHeight() - 32)){		// Lower boundary
			henry.ch_y(-speed);
		}
		
		if (henry.get_x() < 0){														// Left-most boundary
			henry.ch_x(speed);
		}
		if (henry.get_x() > (mapFirst.getWidth()*mapFirst.getTileWidth() - 32)){			// Right-most boundary
			henry.ch_x(-speed);
		}
	}
	
	/*
	 * Changes the map that is displayed 
	 * 		I know I am going to look at this tomorrow and want to throw this computer
	 * 		out a window. 
	 */
	public void changeLocation() throws SlickException{
		
		/*
		if (henry.get_y() == (mapFirst.getHeight()*mapFirst.getTileHeight()-20)){
		 	location = 2;
		 }else if (henry.get_y() == 20){
		 	location = 1;
		 }
		 */
		
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
//	
//		Oh man, this is getting messy.
//	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Updates variables based on the user input
	
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();		// Creates an input object
		
		makeInBound();
		changeLocation();
	
		// Determines the new position on henry depending on the arrow key that is pressed
		// Keep in mind that the map is the thing that is actually moving - henry is always stationary
		if (input.isKeyDown(Input.KEY_UP)){				
			henry.ch_y(-speed);
		}else if (input.isKeyDown(Input.KEY_DOWN)){
			henry.ch_y(speed);
		}else if (input.isKeyDown(Input.KEY_RIGHT)){
			henry.ch_x(speed);
		}else if (input.isKeyDown(Input.KEY_LEFT)){
			henry.ch_x(-speed);
		}	
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
//
//		...
//	
//--------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Renders things to the screen based on the variables that were modified in the update method
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Determines which map to render based on location - see line 58 
		if (location == 1){
			mapFirst.render(0, 0);
		}else if (location == 2){
			mapSecond.render(0, 0);
		}
		henry.draw(henry.get_x(),henry.get_y(), (float)1);	// Draws henry at his x and y position									
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------

		
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new vhat());	// Creates new "app" object
		app.setDisplayMode(screen_width, screen_height, false);		// Sets the display mode: dimensions are 1200 by 700 and windowed
		app.start();												// Runs the app.
	}

}
