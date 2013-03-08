import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class vhat extends BasicGame{
	
	player henry;	// Reference the player class to create a "henry" player object
	TiledMap map;	// Reference the TiledMap class from Slick2D
	
	// Construct calls construct from BasicGame
	public vhat(){
		super("vhat");
	}
	
	// Initializes everything needed
	public void init(GameContainer gc) throws SlickException {
		henry = new player(0,0,"res/henry.png");	// Creates a new "henry" object from the player class
		map = new TiledMap("res/map.tmx");			// Creates a new "map" object from the TiledMap class (from Slick2D)
	}

	// Updates variables based on the user input
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();					// Creates an input object 
		
		// Determines the new position on henry depending on the arrow key that is pressed
		if (input.isKeyDown(Input.KEY_UP)){				
			henry.y -= 12;
		}else if (input.isKeyDown(Input.KEY_DOWN)){
			henry.y += 12;
		}else if (input.isKeyDown(Input.KEY_RIGHT)){
			henry.x += 12;
		}else if (input.isKeyDown(Input.KEY_LEFT)){
			henry.x -= 12;
		}
		
	}
	
	// Renders things to the screen based on the variables that were modified in the update method
	public void render(GameContainer gc, Graphics g) throws SlickException {
		player.draw(henry.get_x(), henry.get_y(), (float)1);	// Draws henry at his x and y position
		map.render(1,1);									
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new vhat());	// Creates new "app" object
		app.setDisplayMode(1200, 700, false);						// Sets the display mode: dimensions are 1200 by 700 and windowed
		app.start();												// Runs the app.
	}

}
