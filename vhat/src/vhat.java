import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class vhat extends BasicGame{
	
	player henry;			// Reference the player class to create a "henry" player object
	TiledMap mapFirst;		// The map for the first location
	TiledMap mapSecond;		// The map for the second location
	int location = 1;		// Keeps track of which location (map) henry should be on
	int x_map = 0;			// |
	int y_map = 0;			// | Keep track of movement - in this case, the map moves while henry stays stationary
	int speed = 3;			// | 
	static int screen_width = 960;
	static int screen_height = 640;
	
	// Construct calls construct from BasicGame
	public vhat(){
		super("vhat");
	}
	
	// Initializes everything needed
	public void init(GameContainer gc) throws SlickException {
		henry = new player((screen_width/2),(screen_height/2),"res/henry.png");		// Creates a new "henry" object from the player class
		mapFirst = new TiledMap("res/first.tmx");									// Creates a new "map" object from the TiledMap class (from Slick2D)
		mapSecond = new TiledMap("res/second.tmx");
	}

	/* 
	 * Makes sure that henry is in bounds.  If henry is not in bounds, he is moved back
	 * Note: All of the maps must be the same size (as mapFirst)... Well as it goes should have planed out how I was going
	 *		 program this... mapFirst is 30x20 tiles (tiles are 32x32 pixels)  
	 */ 
	public void makeInBound(){
		if (henry.get_y() < 0){													// Upper boundary
			y_map -= speed;
			henry.ch_y(speed);
		}
		if (henry.get_y() > (mapFirst.getHeight()*mapFirst.getTileHeight())){		// Lower boundary
			y_map += speed;
			henry.ch_y(-speed);
		}
		
		if (henry.get_x() < 0){													// Left-most boundary
			x_map -= speed;
			henry.ch_x(speed);
		}
		if (henry.get_x() > (mapFirst.getWidth()*mapFirst.getTileWidth())){		// Right-most boundary
			x_map += speed;
			henry.ch_x(-speed);
		}
	}
	
	/*
	 * Changes the map that is displayed 
	 * 	I know I am going to look at this tomorrow and want to throw this computer
	 * 		out a window. 
	 */
	public void changeLocation(){
		if (henry.get_y() == (mapFirst.getHeight()*mapFirst.getTileHeight()-20)){
			location = 2;
		}else if (henry.get_y() == 20){
			location = 1;
		}
	}
	
	// Updates variables based on the user input
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();		// Creates an input object
		
		makeInBound();
		changeLocation();
	
		// Determines the new position on henry depending on the arrow key that is pressed
		// Keep in mind that the map is the thing that is actually moving - henry is always stationary
		if (input.isKeyDown(Input.KEY_UP)){				
			y_map += speed;
			henry.ch_y(-speed);
		}else if (input.isKeyDown(Input.KEY_DOWN)){
			y_map -= speed;
			henry.ch_y(speed);
		}else if (input.isKeyDown(Input.KEY_RIGHT)){
			x_map -= speed;
			henry.ch_x(speed);
		}else if (input.isKeyDown(Input.KEY_LEFT)){
			x_map += speed;
			henry.ch_x(-speed);
		}	
	}
	
	// Renders things to the screen based on the variables that were modified in the update method
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Determines which map to render based on location - see line 58 
		if (location == 1){
			mapFirst.render(x_map, y_map);
		}else {
			mapSecond.render(x_map, y_map);
		}
		henry.draw((screen_width/2),(screen_height/2), (float)1);	// Draws henry at his x and y position									
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new vhat());	// Creates new "app" object
		app.setDisplayMode(screen_width, screen_height, false);		// Sets the display mode: dimensions are 1200 by 700 and windowed
		app.start();												// Runs the app.
	}

}
