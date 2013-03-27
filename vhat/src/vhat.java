/*
 * Programmed By:  BA Media Club
 * Programmed For:  BA Media Club
 * Program Description:  A game... expanding on this later
 */

//TODO:  checkpoints

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;
import org.newdawn.slick.gui.*;
import java.awt.Font;

public class vhat extends BasicGame{
	
	player henry;						// Reference the player class to create a "henry" player object
	Image girl;							// Makes the image for henry's companion
	TextField girlTipOne;				// This is where the user will be able to hear what henry's companion has to say
	TextField girlTipTwo;				//		There are three because a text field does not support multi-line text
	TextField girlTipThree;
	TiledMap mapFirst;					// The map for the first location
	mapManager mapFirstInfo;			//		information about the map
	TiledMap mapSecond;					// The map for the second location
	mapManager mapSecondInfo;			//		information about the map
	TrueTypeFont f;
	
	int location = 1;					// Keeps track of which location (map) henry should be on
	int speed = 1;						// How many pixels henry moves 
	static int screen_width = 960;	
	static int screen_height = 705;
	
	// Construct calls construct from BasicGame 
	public vhat(){
		super("vhat");
	}
	
	// Initializes everything needed
	public void init(GameContainer gc) throws SlickException {
		f = new TrueTypeFont(new Font("", java.awt.Font.PLAIN, 14), true);			// Creates a font object
		
		mapFirst = new TiledMap("res/first.tmx");									// Creates a new "map" object from the TiledMap class (from Slick2D)
		mapSecond = new TiledMap("res/second.tmx");									//		Same as above except with a different map
	
		mapFirstInfo = new mapManager(1, 32*20,	mapFirst.getHeight()*mapFirst.		// Creates an instance of mapManager
				getTileHeight()-64);												// mapFirst is 30x20 tiles (tiles are 32x32 pixels)
		mapSecondInfo = new mapManager(2, 32*5,0);									//		Same as above
	
		henry = new player(mapFirstInfo.get_xSpawn(), mapFirstInfo.get_ySpawn(), 
				"res/henry.png");													// Creates a new "henry" object from the player class
		
		girl = new Image("res/ninja.png");											// Makes henry's companion
		girlTipOne = new TextField(gc, f, girl.getWidth(), 640, 500, 20);			// Allows for output from henry's companion
		girlTipOne.setAcceptingInput(false);										// So the user can't input anything
		girlTipTwo = new TextField(gc, f, girl.getWidth(), 660, 500, 20);			
		girlTipTwo.setAcceptingInput(false);
		girlTipThree = new TextField(gc, f, girl.getWidth(), 680, 500, 20);			
		girlTipThree.setAcceptingInput(false);
	}
		
	// Updates variables based on the user input	
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();	// Creates an input object
		
		if (location == 1){				// So the computer can keep track which map henry is on
			makeInBound(mapFirst);		//		and make henry inBound in that map.
		}else if (location == 2){
			makeInBound(mapSecond);
		}
		
		changeLocation();		// Changes the location of henry if he is at a changing point
		girlSpeak();			// For henry's companion to talk
		
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

	// Renders things to the screen based on the variables that were modified in the update method
	public void render(GameContainer gc, Graphics g) throws SlickException { 
		if (location == 1){				// Determines which map to render based on location
			mapFirst.render(0, 0);		//	(which map henry is on)
		}else if (location == 2){
			mapSecond.render(0, 0);
		}
		henry.draw(henry.get_x(),henry.get_y(), (float)1);		// Draws henry at his x and y position		
		girl.draw(0,screen_height-girl.getHeight());			// Draws henry's companion
		girlTipOne.render(gc, g);								// Draws what the companion has to say
		girlTipTwo.render(gc, g);
		girlTipThree.render(gc, g);
	}
	
	/*
	 * A method that determines when the girl will speak and what she will say
	 * 
	 * TODO: Do this according to story line
	 */
	public void girlSpeak() throws SlickException{
		
		if (location == 1){
			girlTipOne.setText("Hello Henry.  I am your companion.  I can help you through this.");
			girlTipTwo.setText("It seems you are in the first location.");
			girlTipThree.setText("");
		}else if (location ==2){
			girlTipOne.setText("Nice.  You are in the second location.");
			girlTipTwo.setText("");
			girlTipThree.setText("");
		}
	}
	
	/* 
	 * Makes sure that henry is in bounds.  If henry is not in bounds, he is moved back.
	 * This method includes keeping henry on the screen and making sure he stays on the path.
	
	 * TODO:  get game tile set an adjust method accordingly  
	 */ 
	public void makeInBound(TiledMap map) throws SlickException{
		
		// For keeping henry in the screen
		if (henry.get_y() < 0){												// Upper boundary
			henry.ch_y(speed);}
		if (henry.get_y() > (map.getHeight()*map.getTileHeight() - 64)){	// Lower boundary
			henry.ch_y(-speed);}
		if (henry.get_x() < 0){												// Left-most boundary
			henry.ch_x(speed);}
		if (henry.get_x() > (map.getWidth()*map.getTileWidth() - 32)){		// Right-most boundary
			henry.ch_x(-speed);}
		
		// Stupid thing for keeping henry in the path 
		/*
		int t;
		int x = ((int)henry.get_x()+henry.get_width())/32;		// Gets henry's x position in terms of tiles
		int y = ((int)henry.get_y()+henry.get_height())/32;			// Gets henry's y position in terms of tiles
		try{
			t = map.getTileId(x, y, 1);								// The id of the tile that henry is on
			if (t == 2){											// If henry is not on the brick tile, adjust
				if  (map.getTileId(x,((int)henry.get_y()+henry.get_height()-1)/32, 1) != 2){
					henry.ch_y(-1);
				}else if  (map.getTileId(x,((int)henry.get_y()+henry.get_height()+1)/3, 1) != 2){
					henry.ch_y(1);
				}else if  (map.getTileId(((int)henry.get_x()/2-1)/32,y, 1) != 2){
					henry.ch_x(-1);
				}else if  (map.getTileId(((int)henry.get_x()/2+1)/32,y, 1) != 2){
					henry.ch_x(1);
				}
			}
		}catch (ArrayIndexOutOfBoundsException e){				// So that it does not goof up
			//System.err.println("error found");
		}
		*/
		
		int t;
		int xR = ((int)henry.get_x()+henry.get_width()+8)/32;		// Gets henry's x position in terms of tiles
		int yB = ((int)henry.get_y()+henry.get_height()-32)/32;		// Gets henry's y position in terms of tiles
		int xL = ((int)henry.get_x()-24)/32;
		int yT = ((int)henry.get_y()+henry.get_height()+32)/32;
		try{
			t = map.getTileId((xR+xL)/2, (yT+yB)/2, 1);				// The id of the tile that henry is on
			if (t == 2){											// If henry is not on the brick tile, adjust
				int trTile = map.getTileId(xR, yT, 1);
				int tlTile = map.getTileId(xL, yT, 1);
				int brTile = map.getTileId(xR, yB, 1);
				int blTile = map.getTileId(xL, yB, 1);
				
				if (trTile != 2 && tlTile != 2){
					henry.ch_y(1);
				}
				if (brTile != 2 && blTile != 2){
					henry.ch_y(-1);
				}
				if (trTile != 2 && brTile != 2){
					henry.ch_x(1);
				}
				if (tlTile != 2 && blTile != 2){
					henry.ch_x(-1);
				}
			}
		}catch (ArrayIndexOutOfBoundsException e){				// So that it does not goof up
			//System.err.println("error found");
		}catch (IndexOutOfBoundsException e){
			//System.err.println("error found");
		}
		
	}
	
	
	/*
	 * Changes the map that is displayed 
	 * 		I know I am going to look at this tomorrow and want to throw this computer
	 * 		out a window. 
	 * 
	 * 		TODO: will need to have selection for different maps
	 */
	public void changeLocation() throws SlickException{
		
		if (henry.get_y() == (mapFirst.getHeight()*mapFirst.getTileHeight()-64) && 
				henry.get_x() > 32*28 && henry.get_x() < 32*30){						// When henry is on a transporting spot
		 	location = 2;																// Moves onto next location
		 	henry.new_x(mapSecondInfo.get_xSpawn());
		 	henry.new_y(mapSecondInfo.get_ySpawn());
		 }else if (henry.get_y() == 0 && henry.get_x() > 32*2 && henry.get_x() < 32*3){
		 	location = 1;
			henry.new_x(mapFirstInfo.get_xSpawn());
		 	henry.new_y(mapFirstInfo.get_ySpawn());
		 }
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new vhat());	// Creates new "app" object
		app.setDisplayMode(screen_width, screen_height, false);		// Sets the display mode
		app.start();												// Runs the app.
	}

}
