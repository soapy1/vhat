import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class vhat extends BasicGame{
	
	player henry;		// Reference the player class to create a "henry" player object
	TiledMap mapFirst;		// Reference the TiledMap class from Slick2D
	int x_map = 0;
	int y_map = 0;
	int speed = 8;
	static int screen_width = 1184;
	static int screen_height = 672;
	
	
	// Construct calls construct from BasicGame
	public vhat(){
		super("vhat");
	}
	
	// Initializes everything needed
	public void init(GameContainer gc) throws SlickException {
		henry = new player((screen_width/2),(screen_height/2),"res/henry.png");		// Creates a new "henry" object from the player class
		mapFirst = new TiledMap("res/first.tmx");										// Creates a new "map" object from the TiledMap class (from Slick2D)
	}

	// Makes sure that henry is in bounds.  If henry is not in bounds, he is moved back
	public void makeInBound(){
		if (henry.y < 0){
			y_map -= speed;
			henry.y += speed;
		}
		if (henry.y > (mapFirst.getHeight()*mapFirst.getTileHeight())){
			y_map += speed;
			henry.y -= speed;
		}
		
		if (henry.x < 0){
			x_map -= speed;
			henry.x += speed;
		}
		if (henry.x > (mapFirst.getWidth()*mapFirst.getTileWidth())){
			x_map += speed;
			henry.x -= speed;
		}
	}
	// Updates variables based on the user input
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();		// Creates an input object
		
		makeInBound();
	
		// Determines the new position on henry depending on the arrow key that is pressed
		if (input.isKeyDown(Input.KEY_UP)){				
			y_map += speed;
			henry.y -= speed;
		}else if (input.isKeyDown(Input.KEY_DOWN)){
			y_map -= speed;
			henry.y += speed;
		}else if (input.isKeyDown(Input.KEY_RIGHT)){
			x_map -= speed;
			henry.x += speed;
		}else if (input.isKeyDown(Input.KEY_LEFT)){
			x_map += speed;
			henry.x -= speed;
		}	
	}
	
	// Renders things to the screen based on the variables that were modified in the update method
	public void render(GameContainer gc, Graphics g) throws SlickException {
		mapFirst.render(x_map, y_map);
		henry.draw((screen_width/2),(screen_height/2), (float)1);	// Draws henry at his x and y position									
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new vhat());	// Creates new "app" object
		app.setDisplayMode(screen_width, screen_height, false);		// Sets the display mode: dimensions are 1200 by 700 and windowed
		app.start();												// Runs the app.
	}

}
