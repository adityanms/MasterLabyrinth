package code;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A player to be used in a game of master labyrinth.  Has a color, x-y coordinates, a name, and a list containing any tokens
 * the player has collected.
 * @author team112
 *
 */
public class Player {
	private Color _color;
	private int _x, _y;
	private String _name;
	private ArrayList<Token> _playerTokens;	//Tokens that the player has obtained
	
	/**
	 * Constructor only used in testing.
	 * @param color color of the player
	 */
	public Player(Color color){
		_color = color;
	}
	
	/**
	 * Constructor which takes player name
	 * @param name name of the player
	 */
	public Player(String name){
		_name=name;		
		_playerTokens = new ArrayList<Token>();
	}
	
	/**
	 * Method to return a player with a specific name
	 * @param name name of the player wanted
	 * @return returns the player
	 */
	public Player getPlayer(String name){
		return this;
	}
	
	/**
	 * Method to return the name of the player
	 * @return name of the player
	 */
	public String getPlayerName(){
		return _name;
	}
	
	/**
	 * Returns the x coordinate of the player
	 * @return x coordinate
	 */
	public int getX(){
		return _x;
	}
	
	/**
	 * Returns the y coordinate of the player
	 * @return y coordinate
	 */
	public int getY(){
		return _y;
	}
	
	/** 
	 * Sets the x coordinate of the player
	 * @param x x coordinate
	 */
	public void setX(int x){
		_x = x;
	}
	
	/** 
	 * Sets the y coordinate of the player
	 * @param y y coordinate
	 */
	public void setY(int y){
		_y = y;
	}
	
	/**
	 * Gets the color of the player
	 * @return color of the player
	 */
	public Color getColor(){
		return _color;
	}
	
	/**
	 * Adds a token from the board to the player's token inventory.
	 * @param t the token to be added.
	 */
	public void addTokenSet(Token t){
		_playerTokens.add(t);
	}
	
	/**
	 * Accessor for the player's inventory of tokens collected.
	 * @return the player's list of tokens.
	 */
	public ArrayList<Token> getTokenSet(){
		return _playerTokens;
	}
	/**
	 * Sets the player's color.
	 * @param c the color to be set
	 */
	public void setColor(Color c){
		_color = c;
	}
	
	/**
	 * Computes the players score based on tokens they have collected.
	 * @return the player's score as an int value
	 */
	public int getScore(){
		
		int score = 0;
		
		for(int i=0; i<_playerTokens.size(); i++){
			score = score + _playerTokens.get(i).getTokenNumber();
		}
		return score;
	}
}
