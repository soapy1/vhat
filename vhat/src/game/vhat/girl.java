package game.vhat;

import org.newdawn.slick.SlickException;

public class girl extends player {

	private String script[] = {"Welcome henry","my name is ****","I can help you through this"};
	
	// constructor because we have to
	public girl(float xPos, float yPos, String image, int loc) throws SlickException {
		super(xPos, yPos, image, loc);
	}

	// constructor that will actually be used
	public girl(float xPos, float yPos, String image) throws SlickException {
		super(xPos, yPos, image, -1);		// location is set to -1, means presence everywhere
	}

	// clears the text so that the girl is not saying anything
	public void clear_words(){
		for (String i:script){
			i = "";
		}
	}
	
	public void set_words_index(String w, int i){
		script[i] = w;
	}
	
	public void set_words_bulk(String w1, String w2, String w3){
		script[0] = w1;
		script[1] = w2;
		script[2] = w3;
	}
	
	public String get_words(int i){
		return script[i];
	}
}

