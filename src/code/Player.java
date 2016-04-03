package code;

import java.awt.Color;
import java.util.HashSet;

public class Player {
	private Color _color;
	private int _x, _y;
	private String _name;
	private HashSet<Token> _playerTokens;	//Tokens that the player has obtained
	
	/**
	 * Constructor
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
		_playerTokens = new HashSet<Token>();
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
	
	public void addTokenSet(Token t){
		_playerTokens.add(t);
	}
	
	public HashSet<Token> getTokenSet(){
		return _playerTokens;
	}
}
