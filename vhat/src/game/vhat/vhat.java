package game.vhat;
/*
 * Programmed By:  BA Media Club
 * Programmed For:  BA Media Club
 * Program Description:  A game... expanding on this later
 */

//TODO:  checkpoints

import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import java.awt.Font;
/*
 * TODO: make it with states so that you can have a menu screen and stuff
 * 		 also, other stuff
 */
public class vhat extends BasicGame{
	
	player henry;						// Reference the player class to create a "henry" player object
	girl girl;							// Makes henry's companion
	enemy nimb;
	enemy bimb;
	enemy bob;
	TextField girlTipOne;				// This is where the user will be able to hear what henry's companion has to say
	TextField girlTipTwo;				//		There are three because a text field does not support multi-line text
	TextField girlTipThree;
	static mapManager entrance;				// First map
	static mapManager zim;						// Second map
	static mapManager hallway;					
	static mapManager wombo;
	static mapManager end;
	TrueTypeFont f;
	
	int numChangesOne = 1;
	int numChangesTwo = 1;
	
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
		
		gc.setShowFPS(false);
		
		// mapFirst is 30x20 tiles (tiles are 32x32 pixels)
		entrance = new mapManager("res/entrance.tmx", location.entrance, 32*20,32*20-64); 		// Creates map that can be used
		zim = new mapManager("res/zim.tmx", location.zim, 32*5,0);				//		Same as above
		hallway = new mapManager("res/hallway.tmx", location.hallway, 32*2, 32*2);
		wombo = new mapManager("res/wombo.tmx", location.wombo, 32*5, 32*7);
		end = new mapManager("res/end.tmx", location.end, 32*15, 32*10);
		henry = new player(entrance.get_xSpawn(), entrance.get_ySpawn(), 
				"res/henryTwoPointO.png", location.entrance);								// Creates a new "henry" object from the player class
		
		nimb = new enemy(12*32,10*32,"res/henry.png", location.entrance);
		nimb.choosedir();
		bimb = new enemy(25*32,7*32,"res/henry.png", location.entrance);
		bimb.choosedir();
		bob = new enemy(0*32,18*32,"res/henry.png", location.entrance);
		bob.choosedir();
		
		girl = new girl(0,0,"res/ninja.png");										// Makes henry's companion
		girlTipOne = new TextField(gc, f, girl.get_width(), 640, 500, 20);			// Allows for output from henry's companion
		girlTipOne.setAcceptingInput(false);										// So the user can't input anything
		girlTipTwo = new TextField(gc, f, girl.get_width(), 660, 500, 20);			
		girlTipTwo.setAcceptingInput(false);
		girlTipThree = new TextField(gc, f, girl.get_width(), 680, 500, 20);			
		girlTipThree.setAcceptingInput(false);
		
		updateGirlSpeak();
	}
		
	// Updates variables based on the user input	
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();	// Creates an input object
		
		if (henry.hitenemy(nimb) || henry.hitenemy(bimb) || henry.hitenemy(bob)){
			//System.out.println("DEAD");
			henry.set_loc(location.end);
			girl.clear_words();
			girl.set_words_index("Henry, you died :(",  0);
			girl.set_words_index("That means you loose",  1);
		}
		
		nimb.moveon();
		bimb.moveon();
		bob.moveon();
		
		if (henry.get_loc() == location.entrance){				// So the computer can keep track which map henry is on
			henry.makeInBound(entrance);		//		and make henry inBound in that map.
			nimb.makeInBound(entrance);
			bimb.makeInBound(entrance);
			bob.makeInBound(entrance);
		}else if (henry.get_loc() == location.zim){
			henry.makeInBound(zim);
			nimb.makeInBound(zim);
			bimb.makeInBound(zim);
			bob.makeInBound(zim);
		}else if (henry.get_loc() == location.hallway){
			henry.makeInBound(hallway);
			nimb.makeInBound(hallway);
			bimb.makeInBound(hallway);
			bob.makeInBound(hallway);
		}else if (henry.get_loc() == location.wombo){
			henry.makeInBound(wombo);
			nimb.makeInBound(wombo);
			bimb.makeInBound(wombo);
			bob.makeInBound(wombo);
		}else if (henry.get_loc() == location.end){
			henry.makeInBound(end);
		}
		
		
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
		
		if (input.isKeyPressed(Input.KEY_A)){
			henry.interact(girl);	
		}
		
		updateGirlSpeak();
	}

	// Renders things to the screen based on the variables that were modified in the update method
	public void render(GameContainer gc, Graphics g) throws SlickException { 
		if (henry.get_loc() == location.entrance){				// Determines which map to render based on location
			entrance.render(0, 0);		//	(which map henry is on)4nimb.draw(nimb.get_x(), nimb.get_y(), (float)1);
			nimb.draw(nimb.get_x(), nimb.get_y(), (float)1);
			bimb.draw(bimb.get_x(), bimb.get_y(), (float)1);
			bob.draw(bob.get_x(), bob.get_y(), (float)1);
			g.setColor(Color.red);
			g.drawRect(nimb.get_x()-32,nimb.get_y()-32, 96, 128);
			g.drawRect(bimb.get_x()-32,bimb.get_y()-32, 96, 128);
			g.drawRect(bob.get_x()-32,bob.get_y()-32, 96, 128);
		}else if (henry.get_loc() == location.zim){
			zim.render(0, 0);
			nimb.draw(nimb.get_x(), nimb.get_y(), (float)1);
			bimb.draw(bimb.get_x(), bimb.get_y(), (float)1);
			bob.draw(bob.get_x(), bob.get_y(), (float)1);
			g.setColor(Color.red);
			g.drawRect(nimb.get_x()-32,nimb.get_y()-32, 96, 128);
			g.drawRect(bimb.get_x()-32,bimb.get_y()-32, 96, 128);
			g.drawRect(bob.get_x()-32,bob.get_y()-32, 96, 128);
		}else if (henry.get_loc() == location.hallway){
			hallway.render(0, 0);
		}else if (henry.get_loc() == location.wombo){
			wombo.render(0, 0);
			nimb.draw(nimb.get_x(), nimb.get_y(), (float)1);
			bimb.draw(bimb.get_x(), bimb.get_y(), (float)1);
			bob.draw(bob.get_x(), bob.get_y(), (float)1);
			g.setColor(Color.red);
			g.drawRect(nimb.get_x()-32,nimb.get_y()-32, 96, 128);
			g.drawRect(bimb.get_x()-32,bimb.get_y()-32, 96, 128);
			g.drawRect(bob.get_x()-32,bob.get_y()-32, 96, 128);
		}else if (henry.get_loc() == location.end){
			end.render(0, 0);
		}
		
		henry.draw(henry.get_x(),henry.get_y(), (float)1);		// Draws henry at his x and y position		
		girl.draw(0,screen_height-girl.get_height(),1);			// Draws henry's companion
		g.setColor(Color.white);
		girlTipOne.render(gc, g);								// Draws what the companion has to say
		girlTipTwo.render(gc, g);
		girlTipThree.render(gc, g);
	}
	
	/*
	 * A method that determines when the girl will speak and what she will say
	 */
	public void updateGirlSpeak() throws SlickException{
		girlTipOne.setText(girl.get_words(0));
		girlTipTwo.setText(girl.get_words(1));
		girlTipThree.setText(girl.get_words(2));
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app = new AppGameContainer(new vhat());	// Creates new "app" object
		app.setDisplayMode(screen_width, screen_height, false);		// Sets the display mode
		app.start();												// Runs the app.
	}

}
